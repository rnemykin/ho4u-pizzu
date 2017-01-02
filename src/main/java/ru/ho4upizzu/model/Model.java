package ru.ho4upizzu.model;

import org.springframework.data.domain.Persistable;

public abstract class Model implements Persistable<Integer> {
    @Override
    public boolean isNew() {
        return getId() != null;
    }
}
