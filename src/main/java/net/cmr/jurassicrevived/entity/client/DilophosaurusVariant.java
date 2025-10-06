package net.cmr.jurassicrevived.entity.client;

import java.util.Arrays;
import java.util.Comparator;

public enum DilophosaurusVariant {
    MALE(0),
    FEMALE(1);

    private static final DilophosaurusVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(DilophosaurusVariant::getId)).toArray(DilophosaurusVariant[]::new);

    private final int id;

    DilophosaurusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DilophosaurusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
