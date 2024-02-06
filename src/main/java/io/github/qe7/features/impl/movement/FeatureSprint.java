package io.github.qe7.features.impl.movement;

import io.github.qe7.events.api.interfaces.EventSubscribe;
import io.github.qe7.events.impl.player.EventTick;
import io.github.qe7.features.api.enums.FeatureCategory;
import io.github.qe7.features.api.interfaces.FeatureAnnotation;
import io.github.qe7.features.api.types.Feature;
import org.lwjgl.input.Keyboard;

@FeatureAnnotation(name = "Sprint", description = "Automatically sprints for you", keyCode = Keyboard.KEY_F, category = FeatureCategory.MOVEMENT)
public class FeatureSprint extends Feature {

    @Override
    public void onDisable() {
        final boolean shouldSprint = Keyboard.isKeyDown(getMinecraft().gameSettings.keyBindSprint.getKeyCode());
        if (!shouldSprint) getMinecraft().thePlayer.setSprinting(false);
        getMinecraft().gameSettings.keyBindSprint.setPressed(shouldSprint);
    }

    @EventSubscribe
    public void onTick(final EventTick ignored) {
        getMinecraft().gameSettings.keyBindSprint.setPressed(Keyboard.isKeyDown(getMinecraft().gameSettings.keyBindForward.getKeyCode()));
    }
}
