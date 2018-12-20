package me.recursiveg.autoharvest;

import net.minecraft.client.GameSettings;

public interface GameSettingsLoadListener {
    void onLoadOptions(GameSettings gameSettings);
}
