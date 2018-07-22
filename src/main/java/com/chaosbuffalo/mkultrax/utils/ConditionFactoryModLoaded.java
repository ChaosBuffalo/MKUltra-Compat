package com.chaosbuffalo.mkultrax.utils;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.BooleanSupplier;

/**
 * Created by Jacob on 7/22/2018.
 */
public class ConditionFactoryModLoaded implements IConditionFactory {
    public ConditionFactoryModLoaded() {
    }

    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String modName = json.get("mod_name").getAsString();
        boolean result = Loader.isModLoaded(modName);
        return () -> {
            return result;
        };
    }
}
