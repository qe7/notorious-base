package io.github.qe7.settings.impl;

import io.github.qe7.settings.api.types.Setting;

public class SettingBoolean extends Setting<Boolean> {

    public SettingBoolean(String name, Boolean defaultValue) {
        super(name, defaultValue);
    }
}
