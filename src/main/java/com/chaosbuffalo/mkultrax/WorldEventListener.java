package com.chaosbuffalo.mkultrax;

import com.chaosbuffalo.mkultra.core.PlayerAttributes;
import com.chaosbuffalo.mkultra.log.Log;
import com.lycanitesmobs.core.entity.EntityCreatureBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Created by Jacob on 7/21/2018.
 */
@Mod.EventBusSubscriber(Side.SERVER)
public class WorldEventListener implements IWorldEventListener {
    public static WorldEventListener INSTANCE = new WorldEventListener();

    public boolean isLycanitesLoaded;

    public WorldEventListener(){
        super();
        isLycanitesLoaded = false;
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load loadEvent) {
        World world = loadEvent.getWorld();
        world.addEventListener(INSTANCE);
    }

    @Override
    public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {

    }

    @Override
    public void notifyLightSet(BlockPos pos) {

    }

    @Override
    public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {

    }

    @Override
    public void playSoundToAllNearExcept(@Nullable EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {

    }

    @Override
    public void playRecord(SoundEvent soundIn, BlockPos pos) {

    }

    @Override
    public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {

    }

    @Override
    public void spawnParticle(int id, boolean ignoreRange, boolean p_190570_3_, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {

    }

    @Override
    public void onEntityAdded(Entity entityIn) {
        if (isLycanitesLoaded){
            if (entityIn instanceof EntityCreatureBase){
                EntityCreatureBase creature = (EntityCreatureBase) entityIn;
                double scale = creature.getRenderScale();
                if (scale > 1.0){
                    creature.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
                            .applyModifier(new AttributeModifier(
                                    UUID.fromString("749d6722-b566-472d-b33c-d3c1b8cd0b8d"),
                                    "Size Health Bonus",
                                    scale + 1.0, PlayerAttributes.OP_SCALE_MULTIPLICATIVE));
                }
            }
        }
    }

    @Override
    public void onEntityRemoved(Entity entityIn) {

    }

    @Override
    public void broadcastSound(int soundID, BlockPos pos, int data) {

    }

    @Override
    public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {

    }

    @Override
    public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {

    }
}
