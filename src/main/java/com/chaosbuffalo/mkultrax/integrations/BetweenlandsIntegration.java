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

    }

}
