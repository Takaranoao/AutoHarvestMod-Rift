package me.recursiveg.autoharvest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;

public class KeyPressListener{

    //public static final KeyBinding toggleKey = new KeyBinding("key.toggleAutoharvest", GLFW.GLFW_KEY_H, "key.categories.misc");

//    public KeyPressListener() {
//        Minecraft.getInstance().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getInstance().gameSettings.keyBindings, toggleKey);
//    }
    public void onProcessKey() {
            AutoHarvest a = AutoHarvest.instance;
            String modeName = a.toNextMode().toString().toLowerCase();
            AutoHarvest.msg("notify.switch_to." + modeName);
    }
}
