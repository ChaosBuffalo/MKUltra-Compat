package com.chaosbuffalo.mkultrax;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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
import scala.collection.immutable.Stream;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Jacob on 7/21/2018.
 */
@Mod.EventBusSubscriber(Side.SERVER)
public class MKXWorldListener implements IWorldEventListener {
    public static MKXWorldListener INSTANCE = new MKXWorldListener();

    private static final HashSet<Consumer<Entity>> ENTITY_LOADED_CALLBACKS = new HashSet<>();


    public MKXWorldListener(){
        super();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load loadEvent) {
        World world = loadEvent.getWorld();
        world.addEventListener(INSTANCE);
    }

    public static void registerEntityLoadedCallback(Consumer<Entity> callback){
        ENTITY_LOADED_CALLBACKS.add(callback);
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
        for (Consumer<Entity> callback : ENTITY_LOADED_CALLBACKS){
            callback.accept(entityIn);
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
