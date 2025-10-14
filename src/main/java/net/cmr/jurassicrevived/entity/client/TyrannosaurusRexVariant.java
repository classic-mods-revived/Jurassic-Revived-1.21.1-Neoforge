package net.cmr.jurassicrevived.entity.client;

import java.util.Arrays;
import java.util.Comparator;

public enum TyrannosaurusRexVariant {
    MALE(0),
    FEMALE(1);

    private static final TyrannosaurusRexVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(TyrannosaurusRexVariant::getId)).toArray(TyrannosaurusRexVariant[]::new);

    private final int id;

    TyrannosaurusRexVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TyrannosaurusRexVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
