package io.github.qe7.managers.impl;

import io.github.qe7.font.CFontRenderer;

import java.awt.*;
import java.util.HashMap;

public final class ManagerFont {

    private final HashMap<String, CFontRenderer> registered = new HashMap<>();

    public void initialize() {
        this.register("TAHOMA_14", new CFontRenderer(new Font("Tahoma", Font.PLAIN, 14), true, true));
        this.register("TAHOMA_16", new CFontRenderer(new Font("Tahoma", Font.PLAIN, 16), true, true));

        this.register("SF_18", new CFontRenderer("SF-UI-Pro", 18, 0, true, true));
    }

    public <T extends CFontRenderer> void register(final String tag, final T font) {
        this.registered.put(tag, font);
    }

    public CFontRenderer getFontRenderer(String tag) {
        return this.registered.get(tag);
    }
}
