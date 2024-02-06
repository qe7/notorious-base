package io.github.qe7.features.impl.render;

import io.github.qe7.features.api.enums.FeatureCategory;
import io.github.qe7.features.api.interfaces.FeatureAnnotation;
import io.github.qe7.features.api.types.Feature;
import io.github.qe7.ui.GuiClick;
import org.lwjgl.input.Keyboard;

@FeatureAnnotation(name = "ClickGUI", description = "The ClickGUI for Notorious.", category = FeatureCategory.RENDER, keyCode = Keyboard.KEY_RSHIFT)
public class FeatureClickGui extends Feature {

    private GuiClick guiClick;

    @Override
    public void onEnable() {
        if (guiClick == null) {
            guiClick = new GuiClick();
        }
        getMinecraft().displayGuiScreen(guiClick);
        setEnabled(false);
    }

    @Override
    public void onDisable() {

    }
}
