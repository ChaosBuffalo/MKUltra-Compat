package com.chaosbuffalo.mkultrax.Integrations;

import com.chaosbuffalo.mkultra.core.ArmorClass;
import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
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
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrystalAxe.class);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrystalSword.class);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemChargedCrystalAxe.class);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemChargedCrystalSword.class);
        ArmorClass.ROBES.register(RegistryItems.imbuedLeatherMaterial);
    }
}
