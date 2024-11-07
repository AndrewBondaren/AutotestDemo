package ru.beeline.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;


public final class ResourceUtils {

    private static final String CANNOT_READ_MESSAGE = "Cannot read resource: %s, reason: %s";

    private ResourceUtils() throws IllegalAccessException {
       throw new IllegalAccessException("Utils class!");
    }

    public static InputStream getResourceAsStream(final String resourceName) {
        return ResourceUtils.class.getClassLoader().getResourceAsStream(resourceName);
    }

    public static File getResourceAsFile(final String resourceName) {
        return new File(requireNonNull(ResourceUtils.class.getClassLoader().getResource(resourceName)).getFile());
    }

    public static String getResourceAsBase64(final String resourceName) {
        try {
            return Base64.getEncoder().encodeToString(IOUtils.toByteArray(getResourceAsStream(resourceName)));
        } catch (IOException e) {
            throw new AssertionError(
                    String.format(CANNOT_READ_MESSAGE, resourceName, e.getMessage()), e);
        }
    }

    public static String getResourceAsString(final String resourceName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getResourceAsStream(resourceName), Charset.defaultCharset()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new AssertionError(
                    String.format(CANNOT_READ_MESSAGE, resourceName, e.getMessage()), e);
        }
    }

    public static File writeStreamToFile(final InputStream stream, final String filename) {
        try {
            final File file = new File("out/test/resources/tmp" + filename);
            FileUtils.copyInputStreamToFile(stream, file);
            return file;
        } catch (IOException e) {
            throw new AssertionError(
                    String.format("Cannot create resource: %s, reason: %s", filename, e.getMessage()), e);
        }
    }

    public static byte[] getBinaryContent(final File file) {
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new AssertionError(
                    String.format(CANNOT_READ_MESSAGE, file.getName(), e.getMessage()), e);
        }
    }
}
