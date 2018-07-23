package com.chaosbuffalo.mkultrax.init;

import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.integrations.IIntegration;

/**
 * Created by Jacob on 7/22/2018.
 */
public class MKXTileRegistry {

    public static void registerTileEntities() {
        for (IIntegration integration : MKUltraX.integrations){
            if (integration.isLoaded()){
                integration.register_tile_entities();
            }
        }
    }

}
