package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.core.ArmorClass;
import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
import com.chaosbuffalo.mkultra.utils.ItemUtils;
import hellfirepvp.astralsorcery.common.item.tool.ItemChargedCrystalAxe;
import hellfirepvp.astralsorcery.common.item.tool.ItemChargedCrystalSword;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalAxe;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
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
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrystalAxe.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemCrystalSword.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemChargedCrystalAxe.class, 1);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemChargedCrystalSword.class, 1);
        ItemUtils.addCriticalStats(ItemCrystalAxe.class, 1, .15f, 2.0f);
        ArmorClass.ROBES.register(RegistryItems.imbuedLeatherMaterial);
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

    @Override
    public void register_tile_entities() {

    }

    @Override
    public void init_blocks_phase() {

    }
}
