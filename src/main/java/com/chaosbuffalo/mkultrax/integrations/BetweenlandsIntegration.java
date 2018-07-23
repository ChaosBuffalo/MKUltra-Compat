package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.core.ArmorClass;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import thebetweenlands.common.item.BLMaterialRegistry;

/**
 * Created by Jacob on 7/21/2018.
 */
public class BetweenlandsIntegration implements IIntegration {

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("thebetweenlands");
    }

    @Override
    public void mod_init() {
        ArmorClass.HEAVY
                .register(BLMaterialRegistry.ARMOR_VALONITE);

        ArmorClass.ROBES
                .register(BLMaterialRegistry.ARMOR_RUBBER)
                .register(BLMaterialRegistry.ARMOR_BL_CLOTH)
                .register(BLMaterialRegistry.ARMOR_LURKER_SKIN);

        ArmorClass.LIGHT
                .register(BLMaterialRegistry.ARMOR_BONE);

        ArmorClass.MEDIUM
                .register(BLMaterialRegistry.ARMOR_SYRMORITE);
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
