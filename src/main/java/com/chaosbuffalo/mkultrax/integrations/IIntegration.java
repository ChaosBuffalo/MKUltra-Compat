package com.chaosbuffalo.mkultrax.integrations;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

/**
 * Created by Jacob on 7/21/2018.
 */
public interface IIntegration {

    boolean isLoaded();

    default void mod_init() {
    }

    default void crafting_register(RegistryEvent.Register<IRecipe> event) {
    }

    default void init_items_phase() {
    }

    default void register_tile_entities() {
    }

    default void init_blocks_phase() {
    }

    default void init_ai_generators_phase() {
    }

    default void init_custom_setters_phase(){
    }

    default void init_attribute_setters_phase(){

    }
}
