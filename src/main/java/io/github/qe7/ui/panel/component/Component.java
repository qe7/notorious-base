package io.github.qe7.ui.panel.component;

import io.github.qe7.settings.api.types.Setting;

import java.io.IOException;

public abstract class Component {

    private final Setting<?> setting;

    public Component(Setting<?> setting) {
        this.setting = setting;
    }

    public Setting<?> getSetting() {
        return setting;
    }

    public abstract void drawScreen(int mouseX, int mouseY, float posX, float posY, float partialTicks);
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException;
    public abstract void mouseReleased(int mouseX, int mouseY, int state);
    public abstract void keyTyped(char typedChar, int keyCode) throws IOException;

    public float getHeight() {
        return 14.F;
    }
}
