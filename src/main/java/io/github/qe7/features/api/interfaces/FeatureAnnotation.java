package io.github.qe7.features.api.interfaces;

import io.github.qe7.features.api.enums.FeatureCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation interface for annotating feature classes with metadata.
 * Used to specify name, description, key code, category, and initial enabled state for features.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureAnnotation {

    /**
     * Gets the name of the feature.
     *
     * @return The name of the feature.
     */
    String name() default "Name not set";

    /**
     * Gets the description of the feature.
     *
     * @return The description of the feature.
     */
    String description() default "Description not set";

    /**
     * Gets the key code associated with the feature.
     *
     * @return The key code associated with the feature.
     */
    int keyCode() default 0;

    /**
     * Gets the category of the feature.
     *
     * @return The category of the feature.
     */
    FeatureCategory category() default FeatureCategory.MISC;

    /**
     * Gets the initial enabled state of the feature.
     *
     * @return The initial enabled state of the feature.
     */
    boolean enabled() default false;
}
