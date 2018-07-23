package com.chaosbuffalo.mkultrax.tiles.bwm;

import com.chaosbuffalo.mkultra.log.Log;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

/**
 * Created by Jacob on 7/22/2018.
 */
public class PortalTileEntity extends TileEntity {
    public int current_souls;
    public boolean is_powered;

    public PortalTileEntity() {
        current_souls = 0;
        is_powered = false;

    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = this.serializeNBT();
        return new SPacketUpdateTileEntity(this.pos, 0, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    public void setPowered(boolean newValue){
        is_powered = newValue;
        sync();
    }

    public void addSouls(int value){
        Log.info("Adding %d souls", value);
        current_souls += value;
        if (current_souls > 0){
            is_powered = true;
        }
        sync();
    }

    public void subtractSouls(int value){
        current_souls -= value;
        if (current_souls <= 0){
            is_powered = false;
            current_souls = 0;
        }
        sync();
    }

    protected final void sync() {
        this.markDirty();
        //this.getWorld().markBlockForUpdate(getPos());
        Packet<?> packet = this.getUpdatePacket();
        if (packet == null) return;
        List<EntityPlayerMP> players = this.getWorld().getPlayers(EntityPlayerMP.class, (EntityPlayerMP p) -> p.getPosition().distanceSq(getPos()) < 256);
        for (EntityPlayerMP player : players) {
            player.connection.sendPacket(packet);
        }
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagRoot) {
        tagRoot.setInteger("current_souls", current_souls);
        tagRoot.setBoolean("is_powered", is_powered);
        return super.writeToNBT(tagRoot);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagRoot) {
        current_souls = tagRoot.getInteger("current_souls");
        is_powered = tagRoot.getBoolean("is_powered");
        super.readFromNBT(tagRoot);
    }

}
