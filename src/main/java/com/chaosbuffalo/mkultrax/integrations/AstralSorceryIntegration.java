package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.utils.ItemUtils;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalAxe;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/21/2018.
 */
public class AstralSorceryIntegration implements IIntegration {


    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("astralsorcery");
    }

    @Override
    public void mod_init() {
        ItemUtils.addCriticalStats(ItemCrystalAxe.class, 1, .15f, 2.0f);
    }
}
