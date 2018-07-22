package com.chaosbuffalo.mkultrax;

import com.chaosbuffalo.mkultrax.Integrations.*;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import scala.Array;

import java.util.ArrayList;

@Mod(modid = MKUltraX.MODID, name = MKUltraX.NAME, version = MKUltraX.VERSION,
        dependencies="after:thebetweenlands;after:iceandfire;after:lycanitesmobs;after:astralsorcery")
public class MKUltraX
{
    public static final String MODID = "mkultrax";
    public static final String NAME = "MK Ultra Compat";
    public static final String VERSION = "0.02";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ArrayList<IIntegration> toDo = new ArrayList<IIntegration>();
        toDo.add(new BetweelandsIntegration());
        toDo.add(new IceAndFireIntegration());
        toDo.add(new LycanitesIntegration());
        toDo.add(new AstralSorceryIntegration());

        for (IIntegration integration : toDo){
            if (integration.needsRun()){
                integration.setup();
            }
        }
    }
}
