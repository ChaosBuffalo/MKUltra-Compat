package com.chaosbuffalo.mkultrax.custom_modifiers.lycanites;

import com.chaosbuffalo.mkultra.spawn.CustomModifier;
import net.minecraft.entity.EntityLivingBase;

import java.util.function.BiConsumer;

public class SubspeciesModifier extends CustomModifier {
    public int subspeciesIndex;

    public SubspeciesModifier(BiConsumer<EntityLivingBase, CustomModifier> applierIn, int subspeciesIndex) {
        super(applierIn);
        this.subspeciesIndex = subspeciesIndex;
    }
}
