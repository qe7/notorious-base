package io.github.qe7.ui.panel.component;

import io.github.qe7.Base;
import io.github.qe7.font.CFontRenderer;
import io.github.qe7.settings.api.types.Setting;
import io.github.qe7.settings.impl.SettingBoolean;
import io.github.qe7.utils.ui.GuiUtil;

import java.awt.*;
import java.io.IOException;

public class ComponentBoolean extends Component {

    private final SettingBoolean setting;

    protected final CFontRenderer fontRenderer;

    private final float panelHeight = 14.F;
    private final float panelWidth = 100.F;

    private float posX;
    private float posY;

    private final Color accentColor = new Color(10, 100, 200, 200);
    private final Color backgroundColor = new Color(44, 48, 54, 200);

    public ComponentBoolean(SettingBoolean setting) {
        super(setting);

        this.setting = setting;

        fontRenderer = Base.INSTANCE.getFontManager().getFontRenderer("TAHOMA_16");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float posX, float posY, float partialTicks) {
        this.posX = posX;
        this.posY = posY;

        GuiUtil.drawRect(this.posX, this.posY, this.panelWidth, this.panelHeight, backgroundColor.getRGB());
        GuiUtil.drawRect(this.posX + this.panelWidth - 10, this.posY + 4, 6, this.panelHeight - 8, this.setting.getValue() ? accentColor.getRGB() : backgroundColor.getRGB());
        GuiUtil.drawOutlineRect(this.posX + this.panelWidth - 10, this.posY + 4, 6, this.panelHeight - 8, 0.5F, new Color(39, 43, 49).getRGB());
        fontRenderer.drawStringWithShadow(this.getSetting().getName(), this.posX + 4.F, this.posY + 1.F + (this.panelHeight / 2.F) - (fontRenderer.getStringHeight(this.getSetting().getName()) / 2.F), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        final boolean mouseOver = mouseX >= this.posX && mouseX <= this.posX + this.panelWidth && mouseY >= this.posY && mouseY <= this.posY + this.panelHeight;

        if (mouseOver) {
            if (mouseButton == 0) {
                this.setting.setValue(!this.setting.getValue());
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {

    }

    @Override
    public Setting<?> getSetting() {
        return setting;
    }
}
