package io.github.qe7.settings.impl;

import io.github.qe7.settings.api.types.Setting;

public class SettingDouble extends Setting<Double> {

    private final double min;
    private final double max;
    private final double step;

    public SettingDouble(String name, Double defaultValue, double min, double max, double step) {
        super(name, defaultValue);

        this.min = min;
        this.max = max;
        this.step = step;

        if (defaultValue < min || defaultValue > max) {
            throw new IllegalArgumentException("Default value must be between min and max");
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
        return step;
    }
}
