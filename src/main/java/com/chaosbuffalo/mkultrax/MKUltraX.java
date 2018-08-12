package com.chaosbuffalo.mkultrax;

import com.chaosbuffalo.mkultrax.init.MKXBlockRegistry;
import com.chaosbuffalo.mkultrax.init.MKXItemRegistry;
import com.chaosbuffalo.mkultrax.init.MKXTileRegistry;
import com.chaosbuffalo.mkultrax.integrations.*;
import com.chaosbuffalo.mkultrax.utils.IntegrationUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(modid = MKUltraX.MODID, name = MKUltraX.NAME, version = MKUltraX.VERSION,
        dependencies="required-after:mkultra@[0.76];after:thebetweenlands;after:iceandfire;after:lycanitesmobs;after:astralsorcery;" +
                "after:basemetals;after:poweradvantage;after:betterwithmods;required-after:targeting_api")
public class MKUltraX
{
    public static final String MODID = "mkultrax";
    public static final String NAME = "MK Ultra Compat";
    public static final String VERSION = "0.05";

    public static BetweenlandsIntegration betweenLands;
    public static IceAndFireIntegration iceAndFire;
    public static LycanitesIntegration lycanites;
    public static AstralSorceryIntegration astralSorcery;
    public static BaseMetalsIntegration baseMetals;
    public static SpartanWeaponryIntegration spartanWeaponry;
    public static LootableBodiesIntegration lootableBodiesIntegration;
    public static BWMIntegration bwmIntegration;

    public static final ArrayList<IIntegration> integrations = new ArrayList<>();

    static {
        if (IntegrationUtils.isBetweenlandsPresent()){
            integrations.add(betweenLands = new BetweenlandsIntegration());
        }
        if (IntegrationUtils.isAstralSorceryPresent()){
            integrations.add(astralSorcery = new AstralSorceryIntegration());
        }
        if (IntegrationUtils.isBaseMetalsPresent()){
            integrations.add(baseMetals = new BaseMetalsIntegration());
        }
        if (IntegrationUtils.isBWMPresent()){
            integrations.add(bwmIntegration = new BWMIntegration());
        }
        if (IntegrationUtils.isIceAndFirePresent()){
            integrations.add(iceAndFire = new IceAndFireIntegration());
        }
        if (IntegrationUtils.isLycantesPresent()){
            integrations.add(lycanites = new LycanitesIntegration());
        }
        if (IntegrationUtils.isSpartanWeaponryPresent()){
            integrations.add(spartanWeaponry = new SpartanWeaponryIntegration());
        }
        if (IntegrationUtils.isLycantesPresent()){
            integrations.add(lootableBodiesIntegration = new LootableBodiesIntegration());
        }
    }

    public static Logger LOG;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOG = event.getModLog();
        MKXItemRegistry.initItems();
        MKXBlockRegistry.initBlocks();
        MKXTileRegistry.registerTileEntities();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        for (IIntegration integration : integrations){
            if (integration.isLoaded()){
                integration.mod_init();
            }
        }
    }
}
