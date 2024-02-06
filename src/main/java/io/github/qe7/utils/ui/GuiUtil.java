package io.github.qe7.utils.ui;

import io.github.qe7.font.CFontRenderer;
import net.minecraft.client.gui.Gui;

public class GuiUtil {

    public static void drawRect(float left, float top, float width, float height, int color) {
        Gui.drawRect(left, top, left + width, top + height, color);
    }

    public static void drawOutlineRect(float left, float top, float width, float height, float borderWidth, int color) {
        Gui.drawRect(left - borderWidth, top - borderWidth, left + width + borderWidth, top, color);
        Gui.drawRect(left - borderWidth, top + height, left + width + borderWidth, top + height + borderWidth, color);
        Gui.drawRect(left - borderWidth, top, left, top + height, color);
        Gui.drawRect(left + width, top, left + width + borderWidth, top + height, color);
    }

    public static void drawBorderedRect(float left, float top, float width, float height, float borderWidth, int insideColor, int borderColor) {
        Gui.drawRect(left, top, left + width, top + height, insideColor);
        Gui.drawRect(left - borderWidth, top - borderWidth, left + width + borderWidth, top, borderColor);
        Gui.drawRect(left - borderWidth, top + height, left + width + borderWidth, top + height + borderWidth, borderColor);
        Gui.drawRect(left - borderWidth, top, left, top + height, borderColor);
        Gui.drawRect(left + width, top, left + width + borderWidth, top + height, borderColor);
    }
}
