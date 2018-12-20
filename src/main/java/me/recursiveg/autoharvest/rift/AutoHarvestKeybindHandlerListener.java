package me.recursiveg.autoharvest.rift;

import me.recursiveg.autoharvest.AutoHarvest;
import me.recursiveg.autoharvest.GameSettingsLoadListener;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.dimdev.rift.listener.client.KeybindHandler;
import org.lwjgl.glfw.GLFW;

public class AutoHarvestKeybindHandlerListener implements GameSettingsLoadListener, KeybindHandler  {
    private KeyBinding key;
    private boolean down;

    public AutoHarvestKeybindHandlerListener() {
        key = new KeyBinding("key.toggleAutoharvest", GLFW.GLFW_KEY_H, "key.categories.misc");
    }
    @Override
    public void processKeybinds() {
        Minecraft mc = Minecraft.getInstance();
        boolean wasDown = down;
        down = key.isKeyDown();
        if(mc.mouseHelper.isMouseGrabbed() && down && !wasDown)
        if(AutoHarvest.KeyListener != null)
            AutoHarvest.KeyListener.onProcessKey();
    }

    @Override
    public void onLoadOptions(GameSettings gameSettings) {
        gameSettings.keyBindings = ArrayUtils.add(gameSettings.keyBindings, key);
    }
}
