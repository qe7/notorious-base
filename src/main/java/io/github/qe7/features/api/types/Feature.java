package io.github.qe7.features.api.types;

import io.github.qe7.Base;
import io.github.qe7.features.api.interfaces.FeatureAnnotation;
import net.minecraft.client.Minecraft;

/**
 * The base class for all features in the application.
 * Features are modular components with customizable behavior.
 */
public abstract class Feature {

    // Fields to store feature properties
    protected final FeatureAnnotation featureAnnotation;
    protected int keyCode;
    protected boolean enabled;

    /**
     * Gets the Minecraft client instance.
     *
     * @return The Minecraft client instance.
     */
    protected Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    /**
     * Constructor for the Feature class.
     * Initializes feature properties based on the FeatureAnnotation.
     *
     * @throws IllegalStateException If FeatureAnnotation is not found for the class.
     */
    protected Feature() {
        if (this.getClass().isAnnotationPresent(FeatureAnnotation.class)) {
            // Initialize feature properties from FeatureAnnotation
            this.featureAnnotation = this.getClass().getAnnotation(FeatureAnnotation.class);
            this.keyCode = featureAnnotation.keyCode(); // def=0 (none)
            this.enabled = featureAnnotation.enabled(); // def=false
        } else {
            // Throw an exception if FeatureAnnotation is not found
            throw new IllegalStateException("FeatureAnnotation not found for class: " + this.getClass().getName());
        }
    }

    // Getter and setter methods for feature properties

    /**
     * Gets the annotation of the feature.
     *
     * @return The annotation.
     */
    public FeatureAnnotation getFeatureAnnotation() {
        return featureAnnotation;
    }

    /**
     * Gets the key code assigned to the feature.
     *
     * @return The key code.
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Sets the key code for the feature.
     *
     * @param keyCode The key code to set.
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    // Abstract methods for feature behavior

    /**
     * Called when the feature is enabled.
     */
    public void onEnable() { }

    /**
     * Called when the feature is disabled.
     */
    public void onDisable() { }

    /**
     * Toggles the feature's enabled state.
     */
    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
            Base.INSTANCE.getEventBus().register(this);
        } else {
            onDisable();
            Base.INSTANCE.getEventBus().unregister(this);
        }
    }

    /**
     * Checks if the feature is currently enabled.
     *
     * @return True if the feature is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled state of the feature.
     *
     * @param enabled The new enabled state.
     */
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) toggle();
    }
}
