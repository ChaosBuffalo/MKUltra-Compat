package com.chaosbuffalo.mkultrax.init;

import com.chaosbuffalo.mkultra.spawn.AttributeSetter;
import com.chaosbuffalo.mkultra.spawn.CustomSetter;
import com.chaosbuffalo.mkultrax.Log;
import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.integrations.IIntegration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(MKUltraX.MODID)
public class MKXSpawnRegistry {

    private static final Set<CustomSetter> CUSTOM_SETTERS = new HashSet<>();
    private static final Set<AttributeSetter> ATTRIBUTE_SETTERS = new HashSet<>();

    public static void regInternalSetter(CustomSetter setter) {
        CUSTOM_SETTERS.add(setter);
    }

    public static void regInternalAttributeSetter(AttributeSetter setter){
        ATTRIBUTE_SETTERS.add(setter);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerCustomSetters(RegistryEvent.Register<CustomSetter> event) {
        CUSTOM_SETTERS.forEach(event.getRegistry()::register);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerAttributeSetters(RegistryEvent.Register<AttributeSetter> event) {
        ATTRIBUTE_SETTERS.forEach(event.getRegistry()::register);
    }

    public static void initCustomSetters() {
        Log.info("Initializing item phase");
        for (IIntegration integration : MKUltraX.integrations){
            if (integration.isLoaded()){
                integration.init_attribute_setters_phase();
                integration.init_custom_setters_phase();
            }
        }
    }
}
