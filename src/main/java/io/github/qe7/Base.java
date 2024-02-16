package io.github.qe7;

import io.github.qe7.events.api.EventBus;
import io.github.qe7.managers.impl.ManagerFont;
import io.github.qe7.managers.impl.ManagerFeature;
import io.github.qe7.managers.impl.ManagerSetting;

/**
 * The {@code Base} class represents a singleton instance that encapsulates
 * basic information about a base, including its name, build version,
 * authors, and credits.
 *
 * <p>The class is designed to be thread-safe and follows the Singleton pattern,
 * ensuring that only one instance of {@code Base} can exist in the system.
 *
 * <p>Note: This class is marked as {@code final} to prevent inheritance.
 */
public final class Base {

    /**
     * The singleton instance of the {@code Base} class.
     */
    public static final Base INSTANCE = new Base();

    // Private fields to store information about the base
    private final String name;
    private final String build;
    private final String[] authors;
    private final String[] credits;

    private final EventBus eventBus;
    private final ManagerFeature featureManager;
    private final ManagerSetting settingManager;
    private final ManagerFont managerFont;

    // Initialization block to set default values for the fields
    private Base() {
        name = "Notorious";
        build = "1";
        authors = new String[]{"Shae"};
        credits = new String[]{"Eternal - Eventbus and Answering questions."};

        eventBus = new EventBus();

        featureManager = new ManagerFeature();
        eventBus.register(featureManager);

        settingManager = new ManagerSetting();

        managerFont = new ManagerFont();
    }

    /**
     * Initializes the base, including the feature manager and event manager.
     */
    public void initialize() {
        featureManager.initialize();
        managerFont.initialize();
    }

    /**
     * Retrieves the name of the base.
     *
     * @return The name of the base.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the version/build number of the base.
     *
     * @return The version/build number of the base.
     */
    public String getBuild() {
        return build;
    }

    /**
     * Retrieves an array of authors contributing to the base.
     *
     * @return An array of authors.
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * Retrieves an array of individuals or entities credited for the base.
     *
     * @return An array of credits.
     */
    public String[] getCredits() {
        return credits;
    }

    /**
     * Retrieves the event manager.
     *
     * @return The event manager.
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Retrieves the feature manager.
     *
     * @return The feature manager.
     */
    public ManagerFeature getFeatureManager() {
        return featureManager;
    }

    /**
     * Retrieves the setting manager.
     *
     * @return The setting manager.
     */
    public ManagerSetting getSettingManager() {
        return settingManager;
    }

    /**
     * Retrieves the font manager.
     *
     * @return The font manager.
     */
    public ManagerFont getFontManager() {
        return managerFont;
    }
}
