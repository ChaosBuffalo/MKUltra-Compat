package com.chaosbuffalo.mkultrax.integrations;

import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/22/2018.
 */
public class BWMIntegration implements IIntegration {
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
}
