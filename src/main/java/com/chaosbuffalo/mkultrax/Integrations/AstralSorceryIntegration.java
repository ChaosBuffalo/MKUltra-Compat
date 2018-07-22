package com.chaosbuffalo.mkultrax.Integrations;

import com.chaosbuffalo.mkultra.core.ArmorClass;
import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
import com.chaosbuffalo.mkultra.utils.ItemUtils;
import hellfirepvp.astralsorcery.common.item.tool.ItemChargedCrystalAxe;
import hellfirepvp.astralsorcery.common.item.tool.ItemChargedCrystalSword;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalAxe;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/21/2018.
 */
public class AstralSorceryIntegration implements IIntegration {


    @Override
    public boolean needsRun() {
        return Loader.isModLoaded("astralsorcery");
    }

    @Override
    public void setup() {
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrystalAxe.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrystalSword.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemChargedCrystalAxe.class, 1);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemChargedCrystalSword.class, 1);
        ItemUtils.addCriticalStats(ItemCrystalAxe.class, 1, .15f, 2.0f);
        ArmorClass.ROBES.register(RegistryItems.imbuedLeatherMaterial);
    }
}
