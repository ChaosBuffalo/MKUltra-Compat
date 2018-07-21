package com.chaosbuffalo.mkultrax;

import com.chaosbuffalo.mkultrax.Integrations.BetweelandsIntegration;
import com.chaosbuffalo.mkultrax.Integrations.IIntegration;
import com.chaosbuffalo.mkultrax.Integrations.IceAndFireIntegration;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import scala.Array;

import java.util.ArrayList;

@Mod(modid = MKUltraX.MODID, name = MKUltraX.NAME, version = MKUltraX.VERSION,
        dependencies="after:thebetweenlands;after:iceandfire")
public class MKUltraX
{
    public static final String MODID = "mkultrax";
    public static final String NAME = "MK Ultra Compat";
    public static final String VERSION = "0.01";

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

        for (IIntegration integration : toDo){
            if (integration.needsRun()){
                integration.setup();
            }
        }
    }
}
