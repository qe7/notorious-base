package io.github.qe7.ui.panel;

import io.github.qe7.Base;
import io.github.qe7.features.api.types.Feature;
import io.github.qe7.font.CFontRenderer;
import io.github.qe7.settings.api.types.Setting;
import io.github.qe7.settings.impl.SettingBoolean;
import io.github.qe7.settings.impl.SettingDouble;
import io.github.qe7.settings.impl.SettingEnum;
import io.github.qe7.ui.panel.component.Component;
import io.github.qe7.ui.panel.component.ComponentBoolean;
import io.github.qe7.ui.panel.component.ComponentDouble;
import io.github.qe7.ui.panel.component.ComponentEnum;
import io.github.qe7.utils.ui.GuiUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Button {

    protected final Feature feature;

    protected final CFontRenderer fontRenderer;

    private final ArrayList<Component> components = new ArrayList<>();

    protected final float width, height;

    private float posX, posY, totalHeight;

    protected boolean open;

    private final Color accentColor = new Color(10, 100, 200, 200);
    private final Color backgroundColor = new Color(44, 48, 54, 200);

    public Button(Feature feature, float width, float height) {
        super();

        this.feature = feature;
        this.width = width;
        this.height = height;
        this.open = false;

        fontRenderer = Base.INSTANCE.getFontManager().getFontRenderer("TAHOMA_16");

        for (Setting<?> setting : Base.INSTANCE.getSettingManager().getSettingsByFeature(this.feature)) {
            if (setting instanceof SettingBoolean settingBoolean) {
                this.components.add(new ComponentBoolean(settingBoolean));
            }
            if (setting instanceof SettingEnum<?> settingEnum) {
                this.components.add(new ComponentEnum(settingEnum));
            }
            if (setting instanceof SettingDouble settingDouble) {
                this.components.add(new ComponentDouble(settingDouble));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float posX, float posY, float partialTicks) {
        this.posX = posX;
        this.posY = posY;
        this.totalHeight = this.height;

        GuiUtil.drawRect(this.posX, this.posY, this.width, this.height, backgroundColor.getRGB());
        GuiUtil.drawRect(this.posX + this.width - 10, this.posY + 4, 6, this.height - 8, this.feature.isEnabled() ? accentColor.getRGB() : backgroundColor.getRGB());
        GuiUtil.drawOutlineRect(this.posX + this.width - 10, this.posY + 4, 6, this.height - 8, 0.5F, new Color(39, 43, 49).getRGB());
        fontRenderer.drawStringWithShadow(this.getFeature().getFeatureAnnotation().name(), this.posX + 4.F, this.posY + 1.F + (this.height / 2.F) - (fontRenderer.getStringHeight(this.getFeature().getFeatureAnnotation().name()) / 2.F), -1);

        if (this.components.isEmpty()) return;
        fontRenderer.drawStringWithShadow("...", this.posX + this.width - fontRenderer.getStringWidth("...") - 14.F, this.posY + 1.F + (this.height / 2.F) - (fontRenderer.getStringHeight(this.getFeature().getFeatureAnnotation().name()) / 2.F), this.open ? -1 : 0xFFAAAAAA);

        if (!this.open) return;
        for (Component component : this.components) {
            component.drawScreen(mouseX, mouseY, this.posX, this.posY + this.totalHeight, partialTicks);
            this.totalHeight += component.getHeight();
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        final boolean mouseOver = mouseX >= this.posX && mouseX <= this.posX + this.width && mouseY >= this.posY && mouseY <= this.posY + this.height;

        if (mouseOver) {
            switch (mouseButton) {
                case 0: {
                    this.feature.toggle();
                    break;
                }
                case 1: {
                    if (this.components.isEmpty()) return;
                    this.open = !this.open;
                    break;
                }
            }
        }

        if (!this.open) return;
        for (Component component : this.components) {
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (!this.open) return;
        for (Component component : this.components) {
            component.mouseReleased(mouseX, mouseY, state);
        }
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (!this.open) return;
        for (Component component : this.components) {
            component.keyTyped(typedChar, keyCode);
        }
    }

    public Feature getFeature() {
        return feature;
    }

    public float getTotalHeight() {
        return this.totalHeight;
    }
}
