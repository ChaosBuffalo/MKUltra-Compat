package com.chaosbuffalo.mkultrax.init;

import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.integrations.IIntegration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Jacob on 7/22/2018.
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class MKXRecipeRegistry {

    public static void addRecipe(RegistryEvent.Register<IRecipe> event, ItemStack stack, Object... recipe) {
        ResourceLocation name = stack.getItem().getRegistryName();
        if (name != null) {
            event.getRegistry().register(new ShapedOreRecipe(name, stack, recipe).setRegistryName(name));
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void initCrafting(RegistryEvent.Register<IRecipe> event) {
        for (IIntegration integration : MKUltraX.integrations){
            if (integration.isLoaded()){
                integration.crafting_register(event);
            }
        }

    }
}


