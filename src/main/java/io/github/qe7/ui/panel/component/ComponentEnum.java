package io.github.qe7.ui.panel.component;

import io.github.qe7.Base;
import io.github.qe7.font.CFontRenderer;
import io.github.qe7.settings.api.types.Setting;
import io.github.qe7.settings.impl.SettingBoolean;
import io.github.qe7.settings.impl.SettingEnum;
import io.github.qe7.settings.impl.interfaces.ISettingEnum;
import io.github.qe7.utils.ui.GuiUtil;

import java.awt.*;
import java.io.IOException;

public class ComponentEnum extends Component {

    private final SettingEnum<ISettingEnum> setting;

    protected final CFontRenderer fontRenderer;

    private final float panelHeight = 14.F;
    private final float panelWidth = 100.F;

    private float posX;
    private float posY;

    private final Color accentColor = new Color(10, 100, 200, 200);
    private final Color backgroundColor = new Color(44, 48, 54, 200);

    public ComponentEnum(SettingEnum<?> setting) {
        super(setting);

        this.setting = (SettingEnum<ISettingEnum>) setting;

        fontRenderer = Base.INSTANCE.getFontManager().getFontRenderer("TAHOMA_16");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float posX, float posY, float partialTicks) {
        this.posX = posX;
        this.posY = posY;

        GuiUtil.drawRect(this.posX, this.posY, this.panelWidth, this.panelHeight, backgroundColor.getRGB());
        fontRenderer.drawStringWithShadow(this.getSetting().getName(), this.posX + 4.F, this.posY + 1.F + (this.panelHeight / 2.F) - (fontRenderer.getStringHeight(this.getSetting().getName()) / 2.F), -1);
        fontRenderer.drawStringWithShadow(this.setting.getValue().getName(), this.posX + this.panelWidth - fontRenderer.getStringWidth(this.setting.getValue().getName()) - 4.F, this.posY + 1.F + (this.panelHeight / 2.F) - (fontRenderer.getStringHeight(this.getSetting().getName()) / 2.F), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        final boolean mouseOver = mouseX >= this.posX && mouseX <= this.posX + this.panelWidth && mouseY >= this.posY && mouseY <= this.posY + this.panelHeight;

        if (mouseOver) {
            switch (mouseButton) {
                case 0 -> {
                    int index = this.setting.getValues().indexOf(this.setting.getValue());
                    if (index + 1 >= this.setting.getValues().size()) {
                        this.setting.setValue(this.setting.getValues().getFirst());
                    } else {
                        this.setting.setValue(this.setting.getValues().get(index + 1));
                    }
                }
                case 1 -> {
                    int index = this.setting.getValues().indexOf(this.setting.getValue());
                    if (index - 1 < 0) {
                        this.setting.setValue(this.setting.getValues().getLast());
                    } else {
                        this.setting.setValue(this.setting.getValues().get(index - 1));
                    }
                }
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
