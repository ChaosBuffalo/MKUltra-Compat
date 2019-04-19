package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.core.IMobData;
import com.chaosbuffalo.mkultra.core.MKUMobData;
import com.chaosbuffalo.mkultra.core.PlayerAttributes;
import com.chaosbuffalo.mkultra.spawn.*;
import com.chaosbuffalo.mkultra.utils.MathUtils;
import com.chaosbuffalo.mkultrax.Log;
import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.MKXWorldListener;
import com.chaosbuffalo.mkultrax.ai.lycanites.EntityAILycanitesLeash;
import com.chaosbuffalo.mkultrax.custom_modifiers.lycanites.AIOverrideModifier;
import com.chaosbuffalo.mkultrax.custom_modifiers.lycanites.SubspeciesModifier;
import com.chaosbuffalo.mkultrax.init.MKXSpawnRegistry;
import com.chaosbuffalo.targeting_api.Targeting;
import com.google.gson.JsonObject;
import com.lycanitesmobs.core.entity.EntityCreatureBase;
import com.lycanitesmobs.core.entity.ai.EntityAITargetAttack;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by Jacob on 7/21/2018.
 */
public class LycanitesIntegration implements IIntegration {
    private static double DISTANCE_SCALING = 1500.0;
    private static int LEVEL_SCALING = 4;
    private static int MAX_SCALE_ZONES = 8;

    @Override
    public boolean isLoaded() {
        return Loader.isModLoaded("lycanitesmobs");
    }

    @Override
    public void init_attribute_setters_phase(){
        BiConsumer<EntityLivingBase, AttributeRange> level_setter = (entity, range) -> {
            double creatureLevel = MathUtils.lerp_double(range.start, range.stop,
                    range.level, range.maxLevel);
            if (entity instanceof EntityCreatureBase){
                EntityCreatureBase creature = (EntityCreatureBase)entity;
                creature.firstSpawn = false;
                creature.applyLevel((int) creatureLevel);
            }
        };
        AttributeSetter lycanites_level = new AttributeSetter(MKUltraX.MODID,
                "lycanites_level", level_setter);
        MKXSpawnRegistry.regInternalAttributeSetter(lycanites_level);
        BiConsumer<EntityLivingBase, AttributeRange> attack_range_setter = (entity, range) -> {
          if (entity instanceof EntityCreatureBase){
              EntityCreatureBase creature = (EntityCreatureBase)entity;
              for (EntityAITasks.EntityAITaskEntry task : creature.targetTasks.taskEntries){
                  if (EntityAITargetAttack.class.isAssignableFrom(task.action.getClass())){
                      EntityAITargetAttack targetTask = (EntityAITargetAttack) task.action;
                      targetTask.setRange(MathUtils.lerp_double(range.start, range.stop, range.level,
                              range.maxLevel));
                  }
              }
          }
        };
        AttributeSetter lycanites_target_range = new AttributeSetter(MKUltraX.MODID,
                "lycanites_aggro_range", attack_range_setter);
        MKXSpawnRegistry.regInternalAttributeSetter(lycanites_target_range);
        BiConsumer<EntityLivingBase, AttributeRange> size_setter = (entity, range) -> {
            if (entity instanceof EntityCreatureBase){
                EntityCreatureBase creature = (EntityCreatureBase)entity;
                creature.firstSpawn = false;
                double scale = MathUtils.lerp_double(range.start, range.stop,
                        range.level, range.maxLevel);
                creature.setSizeScale(scale);
            }
        };
        AttributeSetter lycanites_mob_size = new AttributeSetter(MKUltraX.MODID,
                "lycanites_size_scale", size_setter);
        MKXSpawnRegistry.regInternalAttributeSetter(lycanites_mob_size);

    }

    @Override
    public void init_ai_generators_phase(){
        BiFunction<EntityLiving, BehaviorChoice, EntityAIBase> addLeashRange = (entity, choice) -> {
            IMobData mobData = MKUMobData.get(entity);
            if (!(entity instanceof EntityCreatureBase)){
                Log.info("Failed to add lycanites leash range ai, " +
                        "because it is not an EntityCreatureBase");
                return null;
            }
            return new EntityAILycanitesLeash((EntityCreatureBase)entity, mobData, 1.0);
        };
        AIGenerator leashRange = new AIGenerator(MKUltraX.MODID, "lycanites_leash_range", addLeashRange);
        MKXSpawnRegistry.regInternalAIGenerator(leashRange);
    }

    @Override
    public void init_custom_setters_phase(){
        BiFunction<JsonObject, CustomSetter, CustomModifier> subspecies_deserializer = (obj, setter) ->{
            if (obj.has("subspecies_index")){
                int speciesIndex = obj.get("subspecies_index").getAsInt();
                SubspeciesModifier mod = new SubspeciesModifier(setter.getApplier(), speciesIndex);
                return mod;
            }
            Log.info("Error deserializing subspecies setter. %s", obj.toString());
            return null;
        };
        BiConsumer<EntityLivingBase, CustomModifier> subspecies_modifier = (entity, modifier) ->{
            if (entity instanceof EntityCreatureBase && modifier instanceof SubspeciesModifier){
                SubspeciesModifier subModifier = (SubspeciesModifier) modifier;
                EntityCreatureBase creature = (EntityCreatureBase) entity;
                creature.firstSpawn = false;
                creature.setSubspecies(subModifier.subspeciesIndex);
            } else {
                Log.info("Skipping apply subspecies modifier either entitiy is" +
                        " not EntityCreatureBase or modifier is not right type.");
            }
        };
        CustomSetter setSubspecies = new CustomSetter(
                new ResourceLocation(MKUltraX.MODID, "set_subspecies"),
                subspecies_deserializer, subspecies_modifier);
        MKXSpawnRegistry.regInternalSetter(setSubspecies);

//        BiFunction<JsonObject, CustomSetter, CustomModifier> ai_override_deserialize = (obj, setter) ->{
//            if (obj.has("do_override")){
//                boolean doOverride = obj.get("do_override").getAsBoolean();
//                return new AIOverrideModifier(setter.getApplier(), doOverride);
//            }
//            Log.info("Error deserializing ai override setter. %s", obj.toString());
//            return null;
//        };
//        BiConsumer<EntityLivingBase, CustomModifier> override_modifier = (entity, modifier) ->{
//            if (entity instanceof EntityCreatureBase && modifier instanceof AIOverrideModifier){
//                AIOverrideModifier subModifier = (AIOverrideModifier) modifier;
//                EntityCreatureBase creature = (EntityCreatureBase) entity;
//                creature.setShouldTargetingOverride(subModifier.doOverride);
//            } else {
//                Log.info("Skipping apply ai override modifier either entitiy is" +
//                        " not EntityCreatureBase or modifier is not right type.");
//            }
//        };
//        CustomSetter setAIOverride = new CustomSetter(
//                new ResourceLocation(MKUltraX.MODID, "ai_override"),
//                ai_override_deserialize, override_modifier);
//        MKXSpawnRegistry.regInternalSetter(setAIOverride);
    }

    @Override
    public void mod_init() {
        BiFunction<Entity, Entity, Boolean> lycanitesWrapper =
                (caster, target) -> Targeting.isValidTarget(Targeting.TargetType.ENEMY,
                        caster, target, true);
        com.lycanitesmobs.api.Targeting.registerCallback(lycanitesWrapper);
        com.lycanitesmobs.api.Targeting.replaceTargetingCallback(lycanitesWrapper);
        MKXWorldListener.registerEntityLoadedCallback(LycanitesIntegration::on_entity_added);
    }

    public static void on_entity_added(Entity entityIn) {
        if (entityIn instanceof EntityCreatureBase) {
            EntityCreatureBase creature = (EntityCreatureBase) entityIn;
            double scale = creature.getRenderScale();
            if (scale > 1.0) {
                AttributeModifier modifier = creature.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("749d6722-b566-472d-b33c-d3c1b8cd0b8d"));
                if (modifier == null) {
                    creature.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
                            .applyModifier(new AttributeModifier(
                                    UUID.fromString("749d6722-b566-472d-b33c-d3c1b8cd0b8d"),
                                    "Size Health Bonus",
                                    scale + 2.0, PlayerAttributes.OP_SCALE_MULTIPLICATIVE));
                }
            }
        }
    }
}
