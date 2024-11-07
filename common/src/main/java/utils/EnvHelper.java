package ru.beeline.common.utils;

import org.apache.commons.lang3.StringUtils;

import static java.lang.System.getenv;

public final class EnvHelper {

    private EnvHelper() {

    }

    public static boolean envNotEmpty(final String env) {
        return StringUtils.isNotEmpty(getenv(env));
    }

}
