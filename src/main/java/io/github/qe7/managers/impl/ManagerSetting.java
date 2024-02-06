package io.github.qe7.managers.impl;

import io.github.qe7.features.api.types.Feature;
import io.github.qe7.settings.api.types.Setting;

import java.util.*;

public class ManagerSetting {

    private final Map<Feature, List<Setting<?>>> setting = new HashMap<>();

    public Map<Feature, List<Setting<?>>> getSetting() {
        return setting;
    }

    public List<Setting<?>> getSettingsByFeature(Feature feature) {
        return setting.getOrDefault(feature, Collections.emptyList());
    }

    public void addSetting(Feature feature, Setting<?> property) {
        setting.putIfAbsent(feature, new ArrayList<>());
        setting.get(feature).add(property);
    }
}
