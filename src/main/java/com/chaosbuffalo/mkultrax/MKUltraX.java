package com.chaosbuffalo.mkultrax;

import com.chaosbuffalo.mkultrax.init.MKXBlockRegistry;
import com.chaosbuffalo.mkultrax.init.MKXItemRegistry;
import com.chaosbuffalo.mkultrax.init.MKXTileRegistry;
import com.chaosbuffalo.mkultrax.integrations.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(modid = MKUltraX.MODID, name = MKUltraX.NAME, version = MKUltraX.VERSION,
        dependencies="required-after:mkultra;after:thebetweenlands;after:iceandfire;after:lycanitesmobs;after:astralsorcery;" +
                "after:basemetals;after:poweradvantage;after:betterwithmods")
public class MKUltraX
{
    public static final String MODID = "mkultrax";
    public static final String NAME = "MK Ultra Compat";
    public static final String VERSION = "0.02";

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
        integrations.add(betweenLands = new BetweenlandsIntegration());
        integrations.add(iceAndFire = new IceAndFireIntegration());
        integrations.add(lycanites = new LycanitesIntegration());
        integrations.add(astralSorcery = new AstralSorceryIntegration());
        integrations.add(baseMetals = new BaseMetalsIntegration());
        integrations.add(spartanWeaponry = new SpartanWeaponryIntegration());
        integrations.add(lootableBodiesIntegration = new LootableBodiesIntegration());
        integrations.add(bwmIntegration = new BWMIntegration());
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
