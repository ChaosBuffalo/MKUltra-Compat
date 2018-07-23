package com.chaosbuffalo.mkultrax.init;


import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.integrations.IIntegration;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacob on 7/22/2018.
 */
@Mod.EventBusSubscriber
public class MKXBlockRegistry {
    private static final Set<Block> ALL_BLOCKS = new HashSet<>();


    public static void regInternal(Block block) {
        ALL_BLOCKS.add(block);
    }

    public static void initBlocks() {
        for (IIntegration integration : MKUltraX.integrations){
            if (integration.isLoaded()){
                integration.init_blocks_phase();
            }
        }
    }


    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ALL_BLOCKS.forEach(event.getRegistry()::register);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        ALL_BLOCKS.stream().filter(block -> block.getRegistryName() != null)
                .map((block -> new ItemBlock(block).setRegistryName(block.getRegistryName())))
                .forEach((item -> event.getRegistry().register(item)));
    }

    @SuppressWarnings("unused")
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemBlockModels(ModelRegistryEvent event) {
        ALL_BLOCKS.stream()
                .filter(b -> b.getRegistryName() != null)
                .forEach(block ->
                        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
                                new ModelResourceLocation(block.getRegistryName(), "inventory")));
    }
}
