package org.demo.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import ru.beeline.api.retrofit.annotation.Json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;


@RequiredArgsConstructor(staticName = "create")
public class ToJsonStringConverterFactory extends Converter.Factory {

    @NonNull private final ObjectMapper objectMapper;

    @Override
    public Converter<?, String> stringConverter(final Type type,
                                                final Annotation[] annotations,
                                                final Retrofit retrofit) {
        Converter<?, String> converter = null;
        if (Arrays.stream(annotations).anyMatch(a -> a.annotationType().equals(Json.class))) {
            converter = objectMapper::writeValueAsString;
        }
        return converter;
    }
}
