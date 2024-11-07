package ru.beeline.common.module;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ZonedDateTimeDeserializer extends InstantDeserializer<ZonedDateTime> {
    private static final long serialVersionUID = -237644245579626895L;

    public ZonedDateTimeDeserializer() {
        super(InstantDeserializer.ZONED_DATE_TIME,
                new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        .optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd()
                        .optionalStart().appendOffset("+HHMM", "+0000").optionalEnd()
                        .optionalStart().appendOffset("+HH", "Z").optionalEnd()
                        .toFormatter());
    }

//    @Override
//    public JsonDeserializer<ZonedDateTime> createContextual(DeserializationContext ctxt, BeanProperty property) {
//        // ignore context (i.e. formatting pattern that will be used for serialization)
//        return this;
//    }
}
