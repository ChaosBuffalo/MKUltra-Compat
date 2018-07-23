package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
import com.chaosbuffalo.mkultra.utils.ItemUtils;
import com.oblivioussp.spartanweaponry.item.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
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
        ItemRestrictionHandler.addShieldRestrictedItem(ItemWarhammer.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemGreatsword.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemPike.class, 0);

        ItemUtils.addCriticalStats(ItemKatana.class, 1, .1f, 3.0f);
        ItemUtils.addCriticalStats(ItemRapier.class, 1, .1f, 2.5f);
        ItemUtils.addCriticalStats(ItemLongsword.class, 1, .05f, 2.5f);
        ItemUtils.addCriticalStats(ItemSaber.class, 1, .05f, 2.5f);
        ItemUtils.addCriticalStats(ItemHammer.class, 1, .15f, 2.0f);
        ItemUtils.addCriticalStats(ItemWarhammer.class, 1, .15f, 2.0f);
        ItemUtils.addCriticalStats(ItemCaestus.class, 1, .2f, 1.5f);
        ItemUtils.addCriticalStats(ItemSpear.class, 1, .05f, 2.5f);
        ItemUtils.addCriticalStats(ItemHalberd.class, 1, .1f, 2.0f);
        ItemUtils.addCriticalStats(ItemPike.class, 1, .05f, 2.0f);
        ItemUtils.addCriticalStats(ItemLance.class, 1, .05f, 2.5f);
    }

    @Override
    public void crafting_register(RegistryEvent.Register<IRecipe> event) {

    }

    @Override
    public void on_entity_added(Entity entityIn) {

    }

    @Override
    public void init_items_phase() {

    }
}
