package me.recursiveg.autoharvest.rift;

import me.recursiveg.autoharvest.AutoHarvest;
import net.minecraft.client.Minecraft;
import org.dimdev.rift.listener.client.ClientTickable;

public class AutoHarvestClientTickableListener implements ClientTickable {
    @Override
    public void clientTick(Minecraft client) {
        if(AutoHarvest.listener != null){
            AutoHarvest.listener.onTick(client.player);
        }
    }
}
