package com.chaosbuffalo.mkultrax.utils;

import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/31/2018.
 */
public class IntegrationUtils {

    public static boolean isBWMPresent(){
        return Loader.isModLoaded("betterwithmods");
    }

    public static boolean isBaseMetalsPresent() {
        return Loader.isModLoaded("basemetals");
    }

    public static boolean isIceAndFirePresent() {
        return Loader.isModLoaded("iceandfire");
    }

    public static boolean isAstralSorceryPresent() {
        return Loader.isModLoaded("astralsorcery");
    }

    public static boolean isBetweenlandsPresent() {
        return Loader.isModLoaded("thebetweenlands");
    }

    public static boolean isLycantesPresent(){
        return Loader.isModLoaded("lycanitesmobs");
    }

    public static boolean isSpartanWeaponryPresent(){
        return Loader.isModLoaded("spartanweaponry");
    }

    public static boolean isLootableBodiesPresent(){
        return Loader.isModLoaded("lootablebodies");
    }
}
