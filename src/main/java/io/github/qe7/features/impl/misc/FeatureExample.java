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
