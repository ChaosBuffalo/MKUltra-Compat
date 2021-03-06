package com.chaosbuffalo.mkultrax.integrations;
import com.chaosbuffalo.mkultra.MKUltra;
import com.chaosbuffalo.mkultra.init.Remapper;
import com.chaosbuffalo.mkultrax.Log;
import com.chaosbuffalo.mkultrax.init.MKXItemRegistry;
import com.chaosbuffalo.mkultrax.items.lootablebodies.PhoenixDust;
import com.chaosbuffalo.mkultrax.network.packets.client.ResurrectPlayerPacket;
import com.chaosbuffalo.targeting_api.Targeting;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

/**
 * Created by Jacob on 7/22/2018.
 */
public class LootableBodiesIntegration implements IIntegration {

    private static Class lootableBodyClass;
    public static Item phoenix_dust;

    private final static String BODY_ENTITY_NAME = "cyano.lootable.entities.EntityLootableBody";

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("lootablebodies");
    }

    @Override
    public void mod_init() {

        MKUltra.packetHandler.registerPacket(ResurrectPlayerPacket.class, new ResurrectPlayerPacket.Handler(), Side.SERVER);
        Targeting.registerFriendlyEntity(BODY_ENTITY_NAME);

        try {
            lootableBodyClass = Class.forName(BODY_ENTITY_NAME);
        }
        catch (ClassNotFoundException c) {

        }
    }

    public static List<Entity> getLootableBodiesForPlayer(EntityPlayer player) {

        World world = player.world;

        if (lootableBodyClass != null) {

            String corpseName = player.getName();
            List<Entity> corpses = world.getEntities(lootableBodyClass, e -> e.getCustomNameTag() != null && e.getCustomNameTag().equals(corpseName));
            corpses.forEach(e -> Log.info("found lootable body %s %d", e.getUniqueID().toString(), e.ticksExisted));
            return corpses;
        }

        return Lists.newArrayList();
    }

    public static List<Entity> getLootableBodiesAroundPlayer(EntityPlayer player, float range) {

        World world = player.world;

        if (lootableBodyClass != null && world != null) {
            List<Entity> corpses = world.getEntitiesWithinAABB(lootableBodyClass, player.getEntityBoundingBox().grow(range));
            corpses.forEach(e -> Log.info("found lootable body %s %d", e.getUniqueID().toString(), e.ticksExisted));
            return corpses;
        }

        return Lists.newArrayList();
    }

    public static Entity getLootableBodyAroundPlayer(EntityPlayer player, float range) {

        World world = player.world;

        if (lootableBodyClass != null) {
            return world.findNearestEntityWithinAABB(lootableBodyClass, player.getEntityBoundingBox().grow(range), player);
        }

        return null;
    }

    @Override
    public void init_items_phase() {
        MKXItemRegistry.regInternal(phoenix_dust = new PhoenixDust("phoenix_dust")
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        Remapper.replace(new ResourceLocation("mkultra:phoenix_dust"), phoenix_dust.getRegistryName());
    }

}
