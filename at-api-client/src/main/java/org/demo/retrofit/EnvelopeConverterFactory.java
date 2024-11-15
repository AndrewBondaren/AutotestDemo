package org.demo.retrofit;

import lombok.NoArgsConstructor;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import ru.beeline.api.retrofit.annotation.Enveloped;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;


@NoArgsConstructor(staticName = "create")
public class EnvelopeConverterFactory extends Converter.Factory {

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type,
                                                            final Annotation[] annotations,
                                                            final Retrofit retrofit) {

        final Optional<Annotation> annotation = Arrays.stream(annotations)
                .filter(a -> a.annotationType().equals(Enveloped.class))
                .findFirst();

        if (!annotation.isPresent()) {
            return null;
        }

        final Type envelopedType = new ParameterizedType() {

            @Override
            public Type getRawType() {
                return ((Enveloped) annotation.get()).envelopeType();
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{type, ((Enveloped) annotation.get()).errorType()};
            }
        };

        final Converter<ResponseBody, Envelope<?, ?>> bodyConverter = retrofit
                .nextResponseBodyConverter(this, envelopedType, annotations);

        return new Converter<ResponseBody, Object>() {

            @Nullable
            @Override
            public Object convert(final ResponseBody value) throws IOException {
                final Envelope<?, ?> envelope = bodyConverter.convert(value);

                if (!envelope.isSuccess()) {
                    throw new ConversionException(envelope.getError());
                }

                return envelope.getData();
            }
        };

    }
}
