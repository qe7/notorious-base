package io.github.qe7.settings.impl;

import io.github.qe7.settings.api.types.Setting;
import io.github.qe7.settings.impl.interfaces.ISettingEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingEnum<T extends ISettingEnum> extends Setting<T> {

    private final List<ISettingEnum> values;

    public SettingEnum(String name, T defaultValue) {
        super(name, defaultValue);
        this.values = new ArrayList<>(Arrays.asList(defaultValue.getClass().getEnumConstants()));
    }

    public void setCastedValue(ISettingEnum property) {
        setValue((T) property);
    }

    public T getValueByIndex(int index) {
        return (T) values.get(index);
    }

    public List<ISettingEnum> getValues() {
        return values;
    }

    public int getIndex() {
        return values.indexOf(getValue());
    }
}
