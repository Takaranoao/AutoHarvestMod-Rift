package me.recursiveg.autoharvest.rift.mixin;

import net.minecraft.client.GameSettings;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.recursiveg.autoharvest.GameSettingsLoadListener;

@Mixin(GameSettings.class)
public class MixinGameSettings {
    @Inject(method = "loadOptions", at = @At("HEAD"))
    private void onLoadOptions(CallbackInfo ci) {
        //GameSettings.class.newInstance().loadOptions();
        for (GameSettingsLoadListener listener : RiftLoader.instance.getListeners(GameSettingsLoadListener.class)) {
            listener.onLoadOptions((GameSettings) (Object) this);
        }
    }
}
