package com.chaosbuffalo.mkultrax.Integrations;

import com.chaosbuffalo.mkultrax.WorldEventListener;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/21/2018.
 */
public class LycanitesIntegration implements IIntegration {
    @Override
    public boolean needsRun() {
        return Loader.isModLoaded("lycanitesmobs");
    }

    @Override
    public void setup() {
        WorldEventListener.INSTANCE.isLycanitesLoaded = true;
    }
}
