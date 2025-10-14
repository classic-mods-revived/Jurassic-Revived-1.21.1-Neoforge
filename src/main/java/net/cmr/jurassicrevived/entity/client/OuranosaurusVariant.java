package net.cmr.jurassicrevived.entity.client;

import java.util.Arrays;
import java.util.Comparator;

public enum OuranosaurusVariant {
    MALE(0),
    FEMALE(1);

    private static final OuranosaurusVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(OuranosaurusVariant::getId)).toArray(OuranosaurusVariant[]::new);

    private final int id;

    OuranosaurusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static OuranosaurusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
