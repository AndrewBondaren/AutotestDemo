package module;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import ru.beeline.common.utils.JsonHelper;

import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;


public class JsonHelperModule extends AbstractModule {

    @Provides
    public ObjectMapper providesDefaultObjectMapper() {
        final SimpleModule timeModule = new JavaTimeModule()
                .addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());

        return (new ObjectMapper())
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .registerModule(timeModule)
                .registerModule(new ParameterNamesModule(PROPERTIES))
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Provides
    public JsonHelper providesJsonHelper(final ObjectMapper defaultObjectMapper) {
        return new JsonHelper(defaultObjectMapper);
    }

}
