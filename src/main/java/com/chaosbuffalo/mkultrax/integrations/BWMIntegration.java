package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.blocks.bwm.PortalBlock;
import com.chaosbuffalo.mkultrax.init.MKXBlockRegistry;
import com.chaosbuffalo.mkultrax.tiles.bwm.PortalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
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
