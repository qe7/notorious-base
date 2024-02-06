package io.github.qe7.managers.impl;

import io.github.qe7.Base;
import io.github.qe7.events.api.interfaces.EventSubscribe;
import io.github.qe7.events.impl.game.EventKeyInput;
import io.github.qe7.features.api.enums.FeatureCategory;
import io.github.qe7.features.api.types.Feature;
import io.github.qe7.features.impl.misc.FeatureExample;
import io.github.qe7.features.impl.movement.FeatureSprint;
import io.github.qe7.features.impl.render.FeatureClickGui;
import io.github.qe7.managers.api.Manager;
import io.github.qe7.settings.api.types.Setting;

import java.lang.reflect.Field;
import java.util.List;

public class ManagerFeature extends Manager<Feature> {

    /**
     * The array of feature classes.
     */
    private static final Class<? extends Feature>[] FEATURE_CLASSES = new Class[]{
            FeatureExample.class, FeatureSprint.class, FeatureClickGui.class
    };

    /**
     * Initializes the manager.
     */
    public void initialize() {
        for (Class<? extends Feature> clazz : FEATURE_CLASSES) {
            try {
                register(clazz);

                // If the feature is enabled by default, enable it.
                if (get(clazz).getFeatureAnnotation().enabled()) {
                    get(clazz).setEnabled(true);
                    Base.INSTANCE.getEventBus().register(get(clazz));
                }

                for (Field declaredField : clazz.getDeclaredFields()) {
                    if (declaredField.getType().getSuperclass() != null && declaredField.getType().getSuperclass().equals(Setting.class)) {
                        if(!declaredField.trySetAccessible()) {
                            System.out.println("Couldn't get field " + declaredField.getName() + " from feature " + clazz.getSimpleName() + ".");
                        }
                        Base.INSTANCE.getSettingManager().addSetting(get(clazz), (Setting<?>) declaredField.get(get(clazz)));
                    }
                }

                System.out.println("Registered class " + clazz.getSimpleName() + ".");
                if (!Base.INSTANCE.getSettingManager().getSettingsByFeature(get(clazz)).isEmpty())
                    System.out.println("Registered " + Base.INSTANCE.getSettingManager().getSettingsByFeature(get(clazz)).size() + " settings for class " + clazz.getSimpleName() + ".");
            } catch (Exception e) {
                System.out.println("Failed to register class " + clazz.getSimpleName() + ".");
            }
        }
    }

    /**
     * Toggles the feature with the specified key code.
     *
     * @param keyInputEvent The key input event (duh).
     */
    @EventSubscribe
    public void onKeyInputListener(EventKeyInput keyInputEvent) {
        for (Feature feature : getMap().values()) {
            if (feature.getKeyCode() == keyInputEvent.keyCode()) {
                feature.setEnabled(!feature.isEnabled());
            }
        }
    }

    public List<Feature> featuresFromCategory(FeatureCategory category) {
        return getMap().values().stream().filter(feature -> feature.getFeatureAnnotation().category() == category).toList();
    }
}
