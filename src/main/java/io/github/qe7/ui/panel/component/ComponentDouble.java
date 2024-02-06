package io.github.qe7.ui.panel.component;

import io.github.qe7.Base;
import io.github.qe7.font.CFontRenderer;
import io.github.qe7.settings.api.types.Setting;
import io.github.qe7.settings.impl.SettingDouble;
import io.github.qe7.utils.ui.GuiUtil;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;

public class ComponentDouble extends Component {

    private final SettingDouble setting;

    protected final CFontRenderer fontRenderer;

    private boolean dragging;

    private final float panelHeight = 14.F;
    private final float panelWidth = 100.F;

    private float posX;
    private float posY;

    private final Color accentColor = new Color(10, 100, 200, 200);
    private final Color backgroundColor = new Color(44, 48, 54, 200);

    public ComponentDouble(SettingDouble setting) {
        super(setting);

        this.setting = setting;

        fontRenderer = Base.INSTANCE.getFontManager().getFontRenderer("TAHOMA_16");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float posX, float posY, float partialTicks) {
        this.posX = posX;
        this.posY = posY;

        double value = setting.getValue();
        double min = setting.getMin();
        double max = setting.getMax();
        double percent = (value - min) / (max - min);

        if (dragging) {
            double diff = Math.min(this.panelWidth, Math.max(0, mouseX - posX));
            if (diff == 0) setting.setValue(min);
            double percentNew = diff / (this.panelWidth - 4.0f);
            if (percentNew < 0.0f) percentNew = 0.0f;
            if (percentNew > 1.0f) percentNew = 1.0f;
            double newValue = min + (max - min) * percentNew;
            setting.setValue(roundToStep(newValue, setting.getStep()));
        }

        GuiUtil.drawRect(this.posX, this.posY, this.panelWidth, 17.F, backgroundColor.getRGB());
        GuiUtil.drawRect(this.posX + 2.F, this.posY + this.panelHeight, this.panelWidth - 4.F, 1.F, backgroundColor.getRGB());
        GuiUtil.drawRect(this.posX + 2.F, this.posY + this.panelHeight, (float) ((this.panelWidth - 4.F) * percent), 1.F, accentColor.getRGB());
        GuiUtil.drawOutlineRect(this.posX + 2.F, this.posY + this.panelHeight, this.panelWidth - 4.F, 1.F, 0.5F, new Color(39, 43, 49).getRGB());
        fontRenderer.drawStringWithShadow(this.getSetting().getName(), this.posX + 4.F, this.posY + 1.F + (this.panelHeight / 2.F) - (fontRenderer.getStringHeight(this.getSetting().getName()) / 2.F), -1);
        fontRenderer.drawStringWithShadow(String.format("%.2f", this.setting.getValue()), this.posX + this.panelWidth - fontRenderer.getStringWidth(String.format("%.2f", this.setting.getValue())) - 4.F, this.posY + 1.F + (this.panelHeight / 2.F) - (fontRenderer.getStringHeight(this.getSetting().getName()) / 2.F), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        final boolean mouseOver = mouseX >= this.posX && mouseX <= this.posX + this.panelWidth && mouseY >= this.posY && mouseY <= this.posY + 17.F;

        if (mouseOver) {
            if (mouseButton == 0) {
                this.dragging = true;
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.dragging) this.dragging = false;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {

    }

    private double roundToStep(double value, double step) {
        if (step == 0) return value;
        if (value < 0) return -roundToStep(-value, step);
        return Math.round(value / step) * step;
    }

    @Override
    public Setting<?> getSetting() {
        return setting;
    }

    @Override
    public float getHeight() {
        return 17.F;
    }
}
