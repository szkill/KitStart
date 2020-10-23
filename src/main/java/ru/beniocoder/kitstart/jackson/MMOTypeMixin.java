package ru.beniocoder.kitstart.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;

public interface MMOTypeMixin {

    @JsonCreator
    static MMOTypeMixin get(String s) {
        return null;
    }

}