package io.github.qe7.settings.impl.interfaces;

public interface ISettingEnum {
    String getName();
    Enum<?> getEnum(String name);
}
