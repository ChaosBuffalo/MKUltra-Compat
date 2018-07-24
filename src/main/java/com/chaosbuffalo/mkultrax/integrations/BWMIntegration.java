package com.chaosbuffalo.mkultrax.integrations;

import betterwithmods.common.BWMItems;
import betterwithmods.common.items.tools.ItemSoulforgedBattleAxe;
import betterwithmods.common.items.tools.ItemSoulforgedMattock;
import betterwithmods.common.items.tools.ItemSteelSaw;
import com.chaosbuffalo.mkultra.core.ArmorClass;
import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
import com.chaosbuffalo.mkultra.utils.ItemUtils;
import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.blocks.bwm.PortalBlock;
import com.chaosbuffalo.mkultrax.init.MKXBlockRegistry;
import com.chaosbuffalo.mkultrax.tiles.bwm.PortalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.chaosbuffalo.mkultra.init.Remapper;
import com.chaosbuffalo.mkultrax.Log;
import com.chaosbuffalo.mkultrax.init.MKXItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
 * Created by Jacob on 7/22/2018.
 */
public class BWMIntegration implements IIntegration {
    public static Block portalBlock;

    public static Item HEMP_SEED_BREAD = new ItemFood(7, 8.0f, false).setUnlocalizedName("hemp_seed_bread");

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("betterwithmods");
    }

    @Override
    public void mod_init() {
        Log.info("BWMIntegration, mod_init");

        ArmorClass.ROBES.register(((ItemArmor) BWMItems.WOOL_CHEST).getArmorMaterial());

        ArmorClass.LIGHT.register(((ItemArmor) BWMItems.LEATHER_TANNED_CHEST).getArmorMaterial());

        ArmorClass.HEAVY.register(((ItemArmor) BWMItems.STEEL_CHEST).getArmorMaterial());


        ItemUtils.addCriticalStats(ItemSoulforgedMattock.class, 1, .05f, 2.0f);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemSoulforgedBattleAxe.class, 0);
    }

    @Override
    public void crafting_register(RegistryEvent.Register<IRecipe> event) {
        Log.info("BWMIntegration, crafting_register");

        ResourceLocation goldThreadedCloth = new ResourceLocation("mkultra:gold_threaded_cloth");
        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        modRegistry.remove(goldThreadedCloth);
        modRegistry.remove(new ResourceLocation("mkultra:iron_threaded_cloth"));
    }

    @Override
    public void init_items_phase() {
        Log.info("BWMIntegration, init_items_phase");
        MKXItemRegistry.regInternal(HEMP_SEED_BREAD);

        Remapper.replace(new ResourceLocation("mkultra:hempseedbread"), HEMP_SEED_BREAD.getRegistryName());
        Remapper.replace(new ResourceLocation("mkultra:hempseeds"), new ResourceLocation("betterwithmods", "hemp"));
    }

    @Override
    public void register_tile_entities() {
        GameRegistry.registerTileEntity(PortalTileEntity.class, new ResourceLocation(MKUltraX.MODID, "portal_tile"));
    }

    @Override
    public void init_blocks_phase() {
        MKXBlockRegistry.regInternal(portalBlock = new PortalBlock("portal_block", Material.ANVIL, 5.0f, 1000.0f));
        portalBlock.setHarvestLevel("pickaxe", 3);
    }
}
