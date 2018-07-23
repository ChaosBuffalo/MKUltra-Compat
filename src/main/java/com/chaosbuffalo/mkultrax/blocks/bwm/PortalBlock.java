package com.chaosbuffalo.mkultrax.blocks.bwm;

import betterwithmods.api.block.ISoulSensitive;
import com.chaosbuffalo.mkultra.MKUltra;
import com.chaosbuffalo.mkultra.log.Log;
import com.chaosbuffalo.mkultrax.MKUltraX;
import com.chaosbuffalo.mkultrax.tiles.bwm.PortalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Jacob on 7/22/2018.
 */
public class PortalBlock extends Block implements ISoulSensitive, ITileEntityProvider {

    public static final int COST_SCALE = 1;
    public static final int SOUL_MULTIPLIER = 50;
    public static final int MAX_SOULS = 10000;

    public PortalBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(MKUltra.MKULTRA_TAB);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(MKUltraX.MODID, unlocalizedName);
    }

    public PortalBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.ROCK, hardness, resistance);
    }

    public PortalBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }

    private boolean isBlockThatCounts(Block block) {
        return (block.equals(Blocks.COBBLESTONE) || block.equals(Blocks.OBSIDIAN) ||
                block.equals(Blocks.GLOWSTONE) || block.equals(Blocks.GLASS));
    }

    private int valueForBlock(Block block) {
        if (block.equals(Blocks.COBBLESTONE)) {
            return 8;
        } else if (block.equals(Blocks.OBSIDIAN)) {
            return 64;
        } else if (block.equals(Blocks.GLASS)) {
            return 1;
        } else if (block.equals(Blocks.GLOWSTONE)) {
            return 128;
        } else {
            return 0;
        }
    }

    private int calculateCost(World world, BlockPos pos){
        int eastVal = calculateValueAtBlockPos(world, pos.add(2, 0, 0));
        int westVal = calculateValueAtBlockPos(world, pos.add(-2, 0, 0));
        int northVal = calculateValueAtBlockPos(world, pos.add(1, 0, 2));
        int southVal = calculateValueAtBlockPos(world, pos.add(-1, 0, 2));
        int upVal = calculateValueAtBlockPos(world, pos.add(1, 0, -2));
        int downVal = calculateValueAtBlockPos(world, pos.add(-1, 0, -2));
        int yDist = upVal - downVal;
        int xDist = eastVal - westVal;
        int zDist = northVal - southVal;
        int cost = (Math.abs(xDist) + Math.abs(yDist) + Math.abs(zDist)) * COST_SCALE;
        return cost;
    }

    private Vec3i calculateDistance(World world, BlockPos pos) {
        int eastVal = calculateValueAtBlockPos(world, pos.add(2, 0, 0));
        int westVal = calculateValueAtBlockPos(world, pos.add(-2, 0, 0));
        int northVal = calculateValueAtBlockPos(world, pos.add(1, 0, 2));
        int southVal = calculateValueAtBlockPos(world, pos.add(-1, 0, 2));
        int upVal = calculateValueAtBlockPos(world, pos.add(1, 0, -2));
        int downVal = calculateValueAtBlockPos(world, pos.add(-1, 0, -2));
        int yDist = upVal - downVal;
        int xDist = eastVal - westVal;
        int zDist = northVal - southVal;
        return new Vec3i(xDist, yDist, zDist);
    }

    private int calculateValueAtBlockPos(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        int count = 0;
        while (isBlockThatCounts(state.getBlock())) {
            count += valueForBlock(state.getBlock());
            pos = pos.add(0, 1, 0);
            state = world.getBlockState(pos);
        }
        return count;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {

        PortalTileEntity portalEntity = (PortalTileEntity) world.getTileEntity(pos);
        int cost = calculateCost(world, pos);
        if (cost > 0 && portalEntity != null) {
            if (portalEntity.is_powered) {
                if (portalEntity.current_souls < cost){
                    if (rand.nextInt(MAX_SOULS / portalEntity.current_souls) == 0) {
                        int x = pos.getX();
                        int y = pos.getY();
                        int z = pos.getZ();
                        world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                                SoundEvents.ENTITY_GHAST_AMBIENT, SoundCategory.BLOCKS, 1.0F, rand.nextFloat() * 0.1F + 0.45F,
                                false);
                        float flX = x + rand.nextFloat();
                        float flY = y + rand.nextFloat() * 0.5F + 0.625F;
                        float flZ = z + rand.nextFloat();
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, flX, flY, flZ, 0.0D, 0.0D, 0.0D);
                    }
                } else {
                    spawnParticles(world, pos);
                }
            }
        }
    }

    private void spawnParticles(World worldIn, BlockPos pos) {
        Random random = worldIn.rand;
        double d0 = 0.0625D;
        for (int i = 0; i < 6; ++i) {
            double d1 = (double) ((float) pos.getX() + random.nextFloat());
            double d2 = (double) ((float) pos.getY() + random.nextFloat());
            double d3 = (double) ((float) pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube()) {
                d2 = (double) pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube()) {
                d2 = (double) pos.getY() - d0;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube()) {
                d3 = (double) pos.getZ() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube()) {
                d3 = (double) pos.getZ() - d0;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube()) {
                d1 = (double) pos.getX() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube()) {
                d1 = (double) pos.getX() - d0;
            }

            if (d1 < (double) pos.getX() || d1 > (double) (pos.getX() + 1) || d2 < 0.0D || d2 > (double) (pos.getY() + 1) || d3 < (double) pos.getZ() || d3 > (double) (pos.getZ() + 1)) {
                worldIn.spawnParticle(EnumParticleTypes.PORTAL, d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand,
                                    EnumFacing side,
                                    float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            PortalTileEntity portalEntity = (PortalTileEntity) world.getTileEntity(pos);
            if (portalEntity != null && portalEntity.is_powered){
                int cost = calculateCost(world, pos);
                if (portalEntity.current_souls >= cost){
                    portalEntity.subtractSouls(cost);
                    Vec3i dist = calculateDistance(world, pos);
                    player.setPositionAndUpdate(pos.getX() + dist.getX(), pos.getY() + dist.getY(),
                            pos.getZ() + dist.getZ());
                }
            }
        }
        return true;
    }

    @Override
    public boolean isSoulSensitive(IBlockAccess iBlockAccess, BlockPos blockPos) {
        PortalTileEntity portalTileEntity = (PortalTileEntity) iBlockAccess.getTileEntity(blockPos);
        return portalTileEntity != null && MAX_SOULS > portalTileEntity.current_souls;
    }

    @Override
    public int getMaximumSoulIntake(IBlockAccess iBlockAccess, BlockPos blockPos) {
        PortalTileEntity portalTileEntity = (PortalTileEntity) iBlockAccess.getTileEntity(blockPos);
        if (portalTileEntity != null){
            return (MAX_SOULS - (portalTileEntity.current_souls * SOUL_MULTIPLIER)) / SOUL_MULTIPLIER;
        }
        return 0;
    }

    @Override
    public int processSouls(World world, BlockPos blockPos, int souls) {
        return Math.min(getMaximumSoulIntake(world, blockPos), souls);
    }

    @Override
    public boolean consumeSouls(World world, BlockPos blockPos, int souls) {
        PortalTileEntity portalEntity = (PortalTileEntity) world.getTileEntity(blockPos);
        if (portalEntity != null){
            portalEntity.addSouls(souls * SOUL_MULTIPLIER);
            return souls > 0;
        }
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PortalTileEntity();
    }
}
