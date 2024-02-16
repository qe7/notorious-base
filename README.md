# Notorious-Base

Welcome to Notorious-Base, your go-to Minecraft client base!

## Features

- **Standard Base**: This base provides the fundamental structure for your Minecraft client.
- **Feature/Module System**: System that includes customizable settings.
- **Event bus**: A simple Event bus that has event priority.
- **Clickable UI**: activated by default with `R_SHIFT`.

## Credits

- Eternal: Parts of the Event bus are likely from his, and also answering questions that were probably implimented in this base.

## Requirements

- Java 21
- Intellij (Recommended IDE)

## About

Notorious-Base is designed to be somewhat expandable and easy to use for new and veteran developers alike. Whether you're a beginner or an experienced developer, this base offers a bare template for creating a decent client.

## Images

![image_1](https://raw.githubusercontent.com/qe7/notorious-base/main/images/image_1.png)
![image_2](https://raw.githubusercontent.com/qe7/notorious-base/main/images/image_2.png)

## Examples

### SettingBoolean Class Example:

Example of what a setting class might look like.

```java
package io.github.qe7.settings.impl;

import io.github.qe7.settings.api.types.Setting;

public class SettingBoolean extends Setting<Boolean> {
    
    public SettingBoolean(String name, Boolean defaultValue) {
        super(name, defaultValue);
    }
    
    // Additional methods can be added here for setting-specific functionality
    
    // Example usage of a setting:
    public void setExampleSettingValue(Boolean value) {
        // Set the value of the setting
        setValue(value);
        
        // Additional logic related to setting the value can be added here
        // For example, triggering events, updating UI, etc.
    }
    
    public Boolean getExampleSettingValue() {
        // Get the current value of the setting
        return getValue();
    }
}
```

### FeatureExample Class Example:

Example of what a feature class might look like.

```java
package io.github.qe7.features.impl.misc;

import io.github.qe7.events.api.interfaces.EventSubscribe;
import io.github.qe7.events.impl.player.EventTick;
import io.github.qe7.features.api.interfaces.FeatureAnnotation;
import io.github.qe7.features.api.types.Feature;
import io.github.qe7.settings.impl.SettingBoolean;
import io.github.qe7.settings.impl.SettingDouble;
import io.github.qe7.settings.impl.SettingEnum;
import io.github.qe7.settings.impl.interfaces.ISettingEnum;
import net.minecraft.util.ChatComponentText;

@FeatureAnnotation(name = "Example", description = "Example feature, used for example purposes")
public class FeatureExample extends Feature {

    /* example of the different type of settings */
    private final SettingBoolean settingBoolean = new SettingBoolean("Boolean", false);
    private final SettingEnum<EnumExample> settingEnum = new SettingEnum<>("Enum", EnumExample.EXAMPLE_1);
    private final SettingDouble settingDouble = new SettingDouble("Double", 0.0, 0.0, 10.0, 0.01);
    // enjoy shit setting sysetem! just cast 2 int :)) or make own setting
    private final SettingDouble settingDouble2 = new SettingDouble("Int", 0.0, 0.0, 10.0, 1);

    /* here are your override methods for enable and disable */
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventSubscribe
    public void onTick(final EventTick ignored) {
        // net.minecraft.client.entity.EntityPlayerSP.onUpdate
        getMinecraft().thePlayer.addChatMessage(new ChatComponentText("example tick event, see @ net.minecraft.client.entity.EntityPlayerSP.onUpdate"));
    }

    /* example of the EnumSetting enum */
    private enum EnumExample implements ISettingEnum {
        EXAMPLE_1("Example 1"),
        EXAMPLE_2("Example 2"),
        EXAMPLE_3("Example 3");

        private final String name;

        EnumExample(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Enum<?> getEnum(String name) {
            return Enum.valueOf(EnumExample.class, name);
        }
    }
}
```

## License

Notorious-Base is released under the MIT License. For more details, refer to the [LICENSE](LICENSE) file.

We welcome contributions to expand Notorious-Base.
