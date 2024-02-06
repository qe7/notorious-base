package io.github.qe7.managers.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Type for managing classes.
 *
 * @param <T> The type of class to manage.
 */
public class Manager<T> {

    /**
     * The map of classes.
     */
    private final Map<Class<? extends T>, T> classMap = new HashMap<>();

    /**
     * Registers a class to the manager.
     *
     * @param clazz The class to register.
     */
    public void register(Class<? extends T> clazz) {
        try {
            classMap.putIfAbsent(clazz, clazz.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Failed to register class " + clazz.getSimpleName() + ".");
        }
    }

    /**
     * @param clazz The class to get.
     * @return The class.
     */
    public T get(Class<? extends T> clazz) {
        return classMap.get(clazz);
    }

    /**
     * @return The map of classes.
     */
    public Map<Class<? extends T>, T> getMap() {
        return classMap;
    }

    /**
     * @return The size of the map.
     */
    public int getSize() {
        return classMap.size();
    }
}
