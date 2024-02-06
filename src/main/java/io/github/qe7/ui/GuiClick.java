package io.github.qe7.ui;

import io.github.qe7.features.api.enums.FeatureCategory;
import io.github.qe7.ui.panel.Panel;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;

public class GuiClick extends GuiScreen {

    private final ArrayList<Panel> panels = new ArrayList<>();

    public GuiClick() {
        super();

        float startX = 4.F;
        float startY = 14.F;

        for (FeatureCategory category : FeatureCategory.values()) {
            float panelHeight = 14.F;
            float panelWidth = 100.F;
            this.panels.add(new Panel(category, startX, startY, panelWidth, panelHeight));
            startX += panelWidth + 4.F;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (Panel panel : this.panels) {
            panel.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (Panel panel : this.panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        for (Panel panel : this.panels) {
            panel.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        for (Panel panel : this.panels) {
            panel.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
