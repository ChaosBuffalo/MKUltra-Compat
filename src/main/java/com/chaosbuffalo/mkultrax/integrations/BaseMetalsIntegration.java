package com.chaosbuffalo.mkultrax.integrations;

import betterwithmods.common.BWRegistry;
import com.chaosbuffalo.mkultra.MKUltra;
import com.chaosbuffalo.mkultra.core.ArmorClass;
import com.chaosbuffalo.mkultra.core.PlayerAttributes;
import com.chaosbuffalo.mkultra.init.ModItems;
import com.chaosbuffalo.mkultra.item.ItemAttributeArmor;
import com.chaosbuffalo.mkultra.item.ItemAttributeEntry;
import com.chaosbuffalo.mkultra.item.ManaRegenIdol;
import com.chaosbuffalo.mkultrax.Log;
import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.init.MKXItemRegistry;
import com.chaosbuffalo.mkultrax.init.MKXRecipeRegistry;
import com.chaosbuffalo.mkultrax.utils.IntegrationUtils;
import com.mcmoddev.basemetals.data.MaterialNames;
import com.mcmoddev.basemetals.init.Materials;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.registry.CrusherRecipeRegistry;
import com.mcmoddev.lib.registry.recipe.ICrusherRecipe;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistryModifiable;


/**
 * Created by Jacob on 7/22/2018.
 */
public class BaseMetalsIntegration implements IIntegration {

    public static Item obsidian_chain_leggings;
    public static Item obsidian_chain_chestplate;
    public static Item obsidian_chain_helmet;
    public static Item obsidian_chain_boots;

    public static Item copper_threaded_cloth;
    public static Item copper_threaded_leggings;
    public static Item copper_threaded_chestplate;
    public static Item copper_threaded_helmet;
    public static Item copper_threaded_boots;

    public static Item diamond_dusted_invar_leggings;
    public static Item diamond_dusted_invar_chestplate;
    public static Item diamond_dusted_invar_helmet;
    public static Item diamond_dusted_invar_boots;


    public static Item steel_infused_bone_leather;
    public static Item steel_infused_bone_leggings;
    public static Item steel_infused_bone_chestplate;
    public static Item steel_infused_bone_helmet;
    public static Item steel_infused_bone_boots;

    public static Item manaRegenIdolCopper;
    public static Item manaRegenIdolSilver;
    public static Item manaRegenIdolBrass;
    public static Item manaRegenIdolBronze;

    public static ItemArmor.ArmorMaterial OBSIDIAN_CHAIN = EnumHelper.addArmorMaterial(
            "mkultrax_obsidian_chain",
            "mkultrax:obsidian_chain", 50,
            new int[]{2, 5, 5, 3}, 20, null, 0);

    public static ItemArmor.ArmorMaterial DIAMOND_DUSTED_INVAR_MAT = EnumHelper.addArmorMaterial(
            "mkultrax_diamond_dusted_invar",
            "mkultrax:diamond_dusted_invar", 50,
            new int[]{3, 6, 6, 3}, 24, null, 0);

    public static ItemArmor.ArmorMaterial STEEL_INFUSED_BONE_MAT = EnumHelper.addArmorMaterial(
            "mkultrax_steel_infused_bone",
            "mkultrax:steel_infused_bone", 40,
            new int[]{2, 3, 3, 2}, 12, null, 0);

    public static ItemArmor.ArmorMaterial COPPER_THREADED_MAT = EnumHelper.addArmorMaterial(
            "mkultrax_copper_threaded",
            "mkultrax:copper_threaded", 35,
            new int[]{0, 1, 1, 0}, 5, null, 0);

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("basemetals");
    }

    @Override
    public void mod_init() {
        if (IntegrationUtils.isBWMPresent()){
            for (ICrusherRecipe recipe : CrusherRecipeRegistry.getAll()){
                for (ItemStack input : recipe.getInputs()){
                    BWRegistry.MILLSTONE.addMillRecipe(input, recipe.getOutput());
                }
            }
        }
    }

    @Override
    public void crafting_register(RegistryEvent.Register<IRecipe> event) {
        ResourceLocation sun_icon = new ResourceLocation("mkultra:sun_icon");
        ResourceLocation moon_icon = new ResourceLocation("mkultra:moon_icon");
        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        modRegistry.remove(sun_icon);
        modRegistry.remove(moon_icon);

        CrusherRecipeRegistry.addNewCrusherRecipe(Items.DIAMOND, new ItemStack(ModItems.diamond_dust, 4));
        CrusherRecipeRegistry.addNewCrusherRecipe(Blocks.OBSIDIAN,
                new ItemStack(com.mcmoddev.basemetals.init.Materials.getMaterialByName(MaterialNames.OBSIDIAN).getItem(Names.POWDER), 4));

    }

    @Override
    public void init_items_phase() {

        Log.info("Running basemetals item registration");

        MKXItemRegistry.regInternal(steel_infused_bone_leather = new Item().setCreativeTab(MKUltra.MKULTRA_TAB),
                "steel_infused_bone_leather", MKUltraX.MODID);
        STEEL_INFUSED_BONE_MAT.setRepairItem(new ItemStack(steel_infused_bone_leather));
        MKXItemRegistry.regInternal(steel_infused_bone_chestplate = new ItemAttributeArmor(
                "steel_infused_bone_chestplate",
                STEEL_INFUSED_BONE_MAT, 1,
                EntityEquipmentSlot.CHEST,
                new ItemAttributeEntry(1.0, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAGIC_ATTACK_DAMAGE))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(steel_infused_bone_helmet = new ItemAttributeArmor(
                "steel_infused_bone_helmet", STEEL_INFUSED_BONE_MAT, 1,
                EntityEquipmentSlot.HEAD,
                new ItemAttributeEntry(0.5, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute) PlayerAttributes.MANA_REGEN))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(steel_infused_bone_leggings = new ItemAttributeArmor(
                "steel_infused_bone_leggings", STEEL_INFUSED_BONE_MAT, 2,
                EntityEquipmentSlot.LEGS,
                new ItemAttributeEntry(2, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAX_MANA))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(steel_infused_bone_boots = new ItemAttributeArmor(
                "steel_infused_bone_boots", STEEL_INFUSED_BONE_MAT, 2,
                EntityEquipmentSlot.FEET,
                new ItemAttributeEntry(2, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAX_MANA))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(diamond_dusted_invar_chestplate = new ItemAttributeArmor(
                "diamond_dusted_invar_chestplate",
                DIAMOND_DUSTED_INVAR_MAT, 1,
                EntityEquipmentSlot.CHEST,
                new ItemAttributeEntry(5.0, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute) SharedMonsterAttributes.MAX_HEALTH))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(diamond_dusted_invar_helmet = new ItemAttributeArmor(
                "diamond_dusted_invar_helmet",
                DIAMOND_DUSTED_INVAR_MAT, 1,
                EntityEquipmentSlot.HEAD,
                new ItemAttributeEntry(0.75, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MANA_REGEN))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(diamond_dusted_invar_leggings = new ItemAttributeArmor(
                "diamond_dusted_invar_leggings",
                DIAMOND_DUSTED_INVAR_MAT, 2,
                EntityEquipmentSlot.LEGS,
                new ItemAttributeEntry(5.0, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAX_MANA))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(diamond_dusted_invar_boots = new ItemAttributeArmor(
                "diamond_dusted_invar_boots",
                DIAMOND_DUSTED_INVAR_MAT, 2,
                EntityEquipmentSlot.FEET,
                new ItemAttributeEntry(.1, PlayerAttributes.OP_SCALE_ADDITIVE,
                        (RangedAttribute)PlayerAttributes.COOLDOWN))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(copper_threaded_cloth = new Item().setCreativeTab(MKUltra.MKULTRA_TAB),
                "copper_threaded_cloth", MKUltraX.MODID);
        COPPER_THREADED_MAT.setRepairItem(new ItemStack(copper_threaded_cloth));

        MKXItemRegistry.regInternal(copper_threaded_chestplate = new ItemAttributeArmor(
                "copper_threaded_chestplate", COPPER_THREADED_MAT, 1,
                EntityEquipmentSlot.CHEST,
                new ItemAttributeEntry(2, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAX_MANA))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(copper_threaded_helmet = new ItemAttributeArmor(
                "copper_threaded_helmet", COPPER_THREADED_MAT, 1,
                EntityEquipmentSlot.HEAD,
                new ItemAttributeEntry(0.5, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MANA_REGEN))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(copper_threaded_leggings = new ItemAttributeArmor(
                "copper_threaded_leggings", COPPER_THREADED_MAT, 2,
                EntityEquipmentSlot.LEGS,
                new ItemAttributeEntry(2, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAX_MANA))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(copper_threaded_boots = new ItemAttributeArmor(
                "copper_threaded_boots", COPPER_THREADED_MAT, 2,
                EntityEquipmentSlot.FEET,
                new ItemAttributeEntry(2, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MAX_MANA))
                .setCreativeTab(MKUltra.MKULTRA_TAB));


        OBSIDIAN_CHAIN.setRepairItem(new ItemStack(
                Materials.getMaterialByName(MaterialNames.OBSIDIAN).getItem(Names.INGOT)));
        MKXItemRegistry.regInternal(obsidian_chain_boots = new ItemAttributeArmor(
                "obsidian_chain_boots", OBSIDIAN_CHAIN, 2, EntityEquipmentSlot.FEET,
                new ItemAttributeEntry(.1, PlayerAttributes.OP_SCALE_ADDITIVE,
                        (RangedAttribute)SharedMonsterAttributes.MOVEMENT_SPEED))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(obsidian_chain_chestplate = new ItemAttributeArmor(
                "obsidian_chain_chestplate", OBSIDIAN_CHAIN, 1, EntityEquipmentSlot.CHEST,
                new ItemAttributeEntry(.1, PlayerAttributes.OP_SCALE_ADDITIVE,
                        (RangedAttribute)PlayerAttributes.COOLDOWN))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(obsidian_chain_leggings = new ItemAttributeArmor(
                "obsidian_chain_leggings", OBSIDIAN_CHAIN, 2, EntityEquipmentSlot.LEGS,
                new ItemAttributeEntry(5.0, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)SharedMonsterAttributes.MAX_HEALTH))
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(obsidian_chain_helmet = new ItemAttributeArmor(
                "obsidian_chain_helmet", OBSIDIAN_CHAIN, 1, EntityEquipmentSlot.HEAD,
                new ItemAttributeEntry(1.0, PlayerAttributes.OP_INCREMENT,
                        (RangedAttribute)PlayerAttributes.MANA_REGEN))
                .setCreativeTab(MKUltra.MKULTRA_TAB));

        MKXItemRegistry.regInternal(manaRegenIdolCopper = new ManaRegenIdol(
                "mana_regen_idol_copper", .5f, 0, 0, 0, 250)
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(manaRegenIdolSilver = new ManaRegenIdol(
                "mana_regen_idol_silver", .75f, 5, 2, 4, 400)
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(manaRegenIdolBrass = new ManaRegenIdol(
                "mana_regen_idol_brass", 0.25f, 5, 1, 0, 200)
                .setCreativeTab(MKUltra.MKULTRA_TAB));
        MKXItemRegistry.regInternal(manaRegenIdolBronze = new ManaRegenIdol(
                "mana_regen_idol_bronze", .5f, 5, 0, 0, 300)
                .setCreativeTab(MKUltra.MKULTRA_TAB));
    }
}
