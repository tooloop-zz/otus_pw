package ru.otus.asamofalov.pw.helper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Tools {

    private Tools() {
    }

    public static String getTraceDetails(Object object) {
        return log.isTraceEnabled() ? (": " + object) : "";
    }

}
