package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.core.ArmorClass;
import com.chaosbuffalo.mkultra.event.ItemRestrictionHandler;
import com.chaosbuffalo.mkultra.utils.ItemUtils;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.enums.EnumDragonArmor;
import com.github.alexthe666.iceandfire.item.ItemAlchemySword;
import com.github.alexthe666.iceandfire.item.ItemModAxe;
import com.github.alexthe666.iceandfire.item.ItemTrollWeapon;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jacob on 7/21/2018.
 */
public class IceAndFireIntegration implements IIntegration {

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("iceandfire");
    }

    @Override
    public void mod_init() {

        ItemUtils.addCriticalStats(ItemAlchemySword.class, 1, .1f, 2.25f);
        ItemUtils.addCriticalStats(ItemTrollWeapon.class, 1, .05f, 3.0f);
        ItemUtils.addCriticalStats(ItemModAxe.class, 1, .15f, 2.0f);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemAlchemySword.class, 0);
        ItemRestrictionHandler.addShieldRestrictedItem(ItemTrollWeapon.class, 0);

    }

}
