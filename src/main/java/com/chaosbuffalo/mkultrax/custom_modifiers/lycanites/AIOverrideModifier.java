package com.chaosbuffalo.mkultrax.custom_modifiers.lycanites;

import com.chaosbuffalo.mkultra.spawn.CustomModifier;
import net.minecraft.entity.EntityLivingBase;

import java.util.function.BiConsumer;

public class AIOverrideModifier extends CustomModifier {
    public boolean doOverride;

    public AIOverrideModifier(BiConsumer<EntityLivingBase, CustomModifier> applierIn, boolean doOverride) {
        super(applierIn);
        this.doOverride = doOverride;
    }
}
