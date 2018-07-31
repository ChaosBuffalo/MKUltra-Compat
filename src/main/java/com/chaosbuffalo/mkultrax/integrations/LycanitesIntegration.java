package com.chaosbuffalo.mkultrax.integrations;

import com.chaosbuffalo.mkultra.core.PlayerAttributes;
import com.chaosbuffalo.mkultrax.MKXWorldListener;
import com.chaosbuffalo.targeting_api.Targeting;
import com.lycanitesmobs.core.entity.EntityCreatureBase;
import com.lycanitesmobs.elementalmobs.entity.EntityNymph;
import com.lycanitesmobs.elementalmobs.entity.EntityWisp;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Loader;

import java.util.UUID;
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
    public void mod_init() {
        Targeting.registerFriendlyEntity("com.lycanitesmobs.elementalmobs.entity.EntityNymph");
        Targeting.registerFriendlyEntity("com.lycanitesmobs.elementalmobs.entity.EntityWisp");
        BiFunction<Entity, Entity, Boolean> lycanitesWrapper = (caster, target) -> {
            return Targeting.isValidTarget(Targeting.TargetType.ENEMY, caster, target, true);
        };
        com.lycanitesmobs.api.Targeting.registerCallback(lycanitesWrapper);
        MKXWorldListener.registerEntityLoadedCallback(LycanitesIntegration::on_entity_added);
    }

    public static void on_entity_added(Entity entityIn) {
        if (entityIn instanceof EntityCreatureBase){
            EntityCreatureBase creature = (EntityCreatureBase) entityIn;
            double scale = creature.getRenderScale();
            if (scale > 1.0){
                AttributeModifier modifier = creature.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("749d6722-b566-472d-b33c-d3c1b8cd0b8d"));
                if (modifier == null){
                    creature.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
                            .applyModifier(new AttributeModifier(
                                    UUID.fromString("749d6722-b566-472d-b33c-d3c1b8cd0b8d"),
                                    "Size Health Bonus",
                                    scale + 2.0, PlayerAttributes.OP_SCALE_MULTIPLICATIVE));
                }
            }
            double distance2 = creature.getDistanceSq(BlockPos.ORIGIN);

            double distanceOut = distance2 / (DISTANCE_SCALING * DISTANCE_SCALING);
            if (distanceOut > 1.0){
                int scaleFactor = Math.min((int) distanceOut, MAX_SCALE_ZONES);
                if (creature.getLevel() < (scaleFactor+2)*LEVEL_SCALING){
                    creature.addLevel((creature.getRNG().nextInt(scaleFactor*LEVEL_SCALING)));
                }

            }
        }

    }
}
