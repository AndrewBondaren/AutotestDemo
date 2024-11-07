package ru.beeline.common.utils;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;


public final class ApiUtils {

    private ApiUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static MultipartBody.Part createBinaryPart(final File file, final String paramName) {
        return MultipartBody.Part.createFormData(paramName, file.getName(),
                RequestBody.create(MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundaryNOUxXzrsAABLAEqk"), file));
    }

}
