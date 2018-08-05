package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
import com.chaosbuffalo.mkultra.utils.EntityUtils;
import com.chaosbuffalo.mkultra.utils.ItemUtils;
import com.oblivioussp.spartanweaponry.entity.projectile.*;
import com.oblivioussp.spartanweaponry.item.*;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/22/2018.
 */
public class SpartanWeaponryIntegration implements IIntegration {
    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("spartanweaponry");
    }

    @Override
    public void mod_init() {
        ItemRestrictionHandler.addShieldRestrictedItem(ItemLongbow.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemKatana.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrossbow.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemHalberd.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemGreatsword.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemPike.class, 0);

        ItemUtils.addCriticalStats(ItemKatana.class, 1, .1f, 3.0f);
        ItemUtils.addCriticalStats(ItemRapier.class, 1, .1f, 2.5f);
        ItemUtils.addCriticalStats(ItemLongsword.class, 1, .05f, 2.5f);
        ItemUtils.addCriticalStats(ItemSaber.class, 1, .05f, 2.5f);
        ItemUtils.addCriticalStats(ItemHammer.class, 1, .15f, 3.0f);
        ItemUtils.addCriticalStats(ItemWarhammer.class, 1, .15f, 3.0f);
        ItemUtils.addCriticalStats(ItemCaestus.class, 1, .2f, 2.0f);
        ItemUtils.addCriticalStats(ItemSpear.class, 1, .05f, 2.5f);
        ItemUtils.addCriticalStats(ItemHalberd.class, 1, .1f, 2.0f);
        ItemUtils.addCriticalStats(ItemPike.class, 1, .05f, 2.0f);
        ItemUtils.addCriticalStats(ItemLance.class, 1, .05f, 2.5f);

        EntityUtils.addCriticalStats(EntityThrownWeapon.class, 1, .05f, 3.0f);
        EntityUtils.addCriticalStats(EntityThrownJavelin.class, 2, .15f, 3.0f);
        EntityUtils.addCriticalStats(EntityThrowingAxe.class, 2, .1f, 3.5f);
        EntityUtils.addCriticalStats(EntityThrowingKnife.class, 2, .2f, 2.5f);
        EntityUtils.addCriticalStats(EntityBolt.class, 1, .05f, 3.0f);
        EntityUtils.addCriticalStats(EntityBoltTipped.class, 2, .05f, 3.0f);
        EntityUtils.addCriticalStats(EntityBoltSpectral.class, 2, .1f, 3.0f);
    }
}
