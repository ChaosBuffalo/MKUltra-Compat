package com.chaosbuffalo.mkultrax.Integrations;

import com.chaosbuffalo.mkultra.core.ArmorClass;
import net.minecraftforge.fml.common.Loader;
import thebetweenlands.common.item.BLMaterialRegistry;

/**
 * Created by Jacob on 7/21/2018.
 */
public class BetweelandsIntegration implements IIntegration {

    @Override
    public boolean needsRun() {
        return Loader.isModLoaded("thebetweenlands");
    }

    @Override
    public void setup() {
        ArmorClass.HEAVY
                .register(BLMaterialRegistry.ARMOR_VALONITE);

        ArmorClass.ROBES
                .register(BLMaterialRegistry.ARMOR_RUBBER)
                .register(BLMaterialRegistry.ARMOR_BL_CLOTH)
                .register(BLMaterialRegistry.ARMOR_LURKER_SKIN);

        ArmorClass.LIGHT
                .register(BLMaterialRegistry.ARMOR_BONE);

        ArmorClass.MEDIUM
                .register(BLMaterialRegistry.ARMOR_SYRMORITE);
    }
}
