package io.github.qe7.ui.panel;

import io.github.qe7.Base;
import io.github.qe7.features.api.enums.FeatureCategory;
import io.github.qe7.features.api.types.Feature;
import io.github.qe7.font.CFontRenderer;
import io.github.qe7.utils.ui.GuiUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Panel {

    protected final FeatureCategory category;

    protected final CFontRenderer fontRenderer;

    private final ArrayList<Button> buttons = new ArrayList<>();

    private float posX, posY, dragPosX, dragPosY;

    private final float width, height;
    private float totalHeight;

    private boolean open, dragging;

    private final Color accentColor = new Color(19, 111, 209);
    private final Color backgroundColor = new Color(39, 43, 49);

    public Panel(FeatureCategory category, float posX, float posY, float width, float height) {
        super();

        this.category = category;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.open = true;
        this.dragging = false;

        fontRenderer = Base.INSTANCE.getFontManager().getFontRenderer("TAHOMA_16");

        for (Feature feature : Base.INSTANCE.getFeatureManager().getMap().values()) {
            if (feature.getFeatureAnnotation().category() != this.category) continue;
            this.buttons.add(new Button(feature, this.width, 14.F));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (dragging) {
            this.posX = mouseX - dragPosX;
            this.posY = mouseY - dragPosY;
        }

        GuiUtil.drawRect(this.posX, this.posY, this.width, this.height, backgroundColor.getRGB());
        GuiUtil.drawOutlineRect(this.posX, this.posY, this.width, this.totalHeight, 0.5F, accentColor.getRGB());
        fontRenderer.drawStringWithShadow(this.getCategory().getName(), this.posX + 4.F, this.posY + 1.F + (this.height / 2.F) - (fontRenderer.getStringHeight(this.getCategory().getName()) / 2.F), -1);
        final String open = this.open ? "-" : "+";
        fontRenderer.drawStringWithShadow(open, this.posX + this.width - fontRenderer.getStringWidth(open) - 4.F, this.posY + 1.F + (this.height / 2.F) - (fontRenderer.getStringHeight(this.getCategory().getName()) / 2.F), -1);

        this.totalHeight = this.height;
        if (!this.open) return;
        for (Button button : this.buttons) {
            button.drawScreen(mouseX, mouseY, this.posX, this.posY + this.totalHeight, partialTicks);
            this.totalHeight += button.getTotalHeight();
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        final boolean mouseOver = mouseX >= this.posX && mouseX <= this.posX + this.width && mouseY >= this.posY && mouseY <= this.posY + this.height;

        if (mouseOver) {
            switch (mouseButton) {
                case 0: {
                    this.dragPosX = mouseX - this.posX;
                    this.dragPosY = mouseY - this.posY;
                    this.dragging = true;
                    break;
                }
                case 1: {
                    this.open = !this.open;
                    break;
                }
            }
        }

        if (!open) return;
        for (Button button : this.buttons) {
            button.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (dragging) {
            this.dragging = false;
        }

        if (!open) return;
        for (Button button : this.buttons) {
            button.mouseReleased(mouseX, mouseY, state);
        }
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {

        if (!open) return;
        for (Button button : this.buttons) {
            button.keyTyped(typedChar, keyCode);
        }
    }

    public FeatureCategory getCategory() {
        return this.category;
    }
}
