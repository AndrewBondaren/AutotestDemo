package ru.beeline.common.utils;

import ru.beeline.common.data.types.MediaType;

public final class MediaTypeHelper {

    private MediaTypeHelper() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class!");
    }

    public static MediaType setMediaType(final String typeName) {
        switch (typeName) {
            case "Изображение":
                return MediaType.IMAGE;
            case "Видео":
                return MediaType.VIDEO;
            default:
                throw new IllegalArgumentException("Wrong argument " + typeName);
        }
    }

}
