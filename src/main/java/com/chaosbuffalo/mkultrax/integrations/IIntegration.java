package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultrax.init.MKXItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

/**
 * Created by Jacob on 7/21/2018.
 */
public interface IIntegration {

    boolean isLoaded();

    void mod_init();

    void crafting_register(RegistryEvent.Register<IRecipe> event);

    void on_entity_added(Entity entityIn);

    void init_items_phase();

    void register_tile_entities();

    void init_blocks_phase();
}
