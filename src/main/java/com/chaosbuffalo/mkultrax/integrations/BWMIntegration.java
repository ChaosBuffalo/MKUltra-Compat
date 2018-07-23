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
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Jacob on 7/22/2018.
 */
public class BWMIntegration implements IIntegration {
    public static Block portalBlock;

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("betterwithmods");
    }

    @Override
    public void mod_init() {

        ArmorClass.ROBES.register(((ItemArmor) BWMItems.WOOL_CHEST).getArmorMaterial());

        ArmorClass.LIGHT.register(((ItemArmor) BWMItems.LEATHER_TANNED_CHEST).getArmorMaterial());

        ArmorClass.HEAVY.register(((ItemArmor) BWMItems.STEEL_CHEST).getArmorMaterial());


        ItemUtils.addCriticalStats(ItemSoulforgedMattock.class, 1, .05f, 2.0f);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemSoulforgedBattleAxe.class, 0);

    }

    @Override
    public void crafting_register(RegistryEvent.Register<IRecipe> event) {

    }

    @Override
    public void on_entity_added(Entity entityIn) {

    }

    @Override
    public void init_items_phase() {

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
