package com.shortthirdman.sharedlibs.util;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionUtils {

    /**
     * @param array the typed array
     * @return
     */
    public static <T> List<T> toList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    /**
     * @param array the typed array
     * @return
     */
    public static <T> Set<T> toSet(T[] array) {
        return Arrays.stream(array).collect(Collectors.toSet());
    }

    /**
     * @param array the typed array
     * @return
     */
    public static <T> Set<T> toSet(T[] array, boolean preserveOrder) {
        if (preserveOrder) {
            return Arrays.stream(array)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return Arrays.stream(array).collect(Collectors.toSet());
    }

    public static <T> Set<T> toSet(Collection<T> collection, boolean preserveOrder) {
        if (preserveOrder) {
            return new LinkedHashSet<>(collection);
        }
        return new HashSet<>(collection);
    }

    public static <T> List<T> filterArray(T[] array, Predicate<? super T> predicate) {
        return Arrays.stream(array).filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T> List<T> filterCollection(Collection<T> collection, Predicate<? super T> predicate) {
        return collection.stream().filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
