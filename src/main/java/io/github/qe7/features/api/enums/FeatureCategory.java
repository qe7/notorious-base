package io.github.qe7.features.api.enums;

public enum FeatureCategory {

    COMBAT("Combat"), MOVEMENT("Movement"), RENDER("Visuals"), MISC("Miscellaneous"), AUTOMATION("Automation");

    // Field to store the category name
    private final String name;

    /**
     * Constructor for FeatureCategory enum.
     *
     * @param name The name of the feature category.
     */
    FeatureCategory(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the feature category.
     *
     * @return The name of the feature category.
     */
    public String getName() {
        return name;
    }
}
