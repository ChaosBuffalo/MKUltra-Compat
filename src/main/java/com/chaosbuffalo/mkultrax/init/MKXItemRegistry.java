package com.chaosbuffalo.mkultrax.init;

import com.chaosbuffalo.mkultrax.Log;
import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.integrations.IIntegration;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;


@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(MKUltraX.MODID)
public class MKXItemRegistry {

    private static final Set<Item> ALL_ITEMS = new HashSet<>();

    public static void regInternal(Item item, String pathName, String modId) {
        item.setUnlocalizedName(pathName);
        item.setRegistryName(modId, pathName);
        ALL_ITEMS.add(item);
    }

    public static void regInternal(Item item){
        item.setRegistryName(MKUltraX.MODID, item.getUnlocalizedName().substring(5));
        ALL_ITEMS.add(item);
    }

    public static void regInternal(Item item, ResourceLocation location) {
        item.setUnlocalizedName(location.getResourcePath());
        item.setRegistryName(location);
        ALL_ITEMS.add(item);
    }

    public static void initItems() {
        Log.info("Initializing item phase");
        for (IIntegration integration : MKUltraX.integrations){
            if (integration.isLoaded()){
                integration.init_items_phase();
            }
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ALL_ITEMS.forEach(event.getRegistry()::register);
    }

    @SuppressWarnings("unused")
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ALL_ITEMS.stream()
                .filter(item -> item.getRegistryName() != null)
                .forEach(item ->
                        ModelLoader.setCustomModelResourceLocation(item, 0,
                                new ModelResourceLocation(item.getRegistryName(), "inventory")));
    }
}

