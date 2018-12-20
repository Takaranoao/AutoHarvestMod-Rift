package me.recursiveg.autoharvest.rift;

import me.recursiveg.autoharvest.AutoHarvest;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class AutoHarvestRiftCore implements InitializationListener {

    @Override
    public void onInitialization() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.autoharvest.json");

        if(AutoHarvest.instance == null){
            AutoHarvest.instance = new AutoHarvest();
        }
        AutoHarvest.instance.preInit();

    }
}
