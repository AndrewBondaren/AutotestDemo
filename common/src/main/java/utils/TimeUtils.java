package ru.beeline.common.utils;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;


public final class TimeUtils {

    public static final TensOfMinutes TENS_OF_MINUTES = new TensOfMinutes();

    private TimeUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class!");
    }

    /**
     * Class for time rounding.
     */
    private static class TensOfMinutes implements TemporalUnit {

        private final Duration duration = Duration.ofSeconds(600);

        @Override
        public Duration getDuration() {
            return duration;
        }

        @Override
        public boolean isDurationEstimated() {
            return false;
        }

        @Override
        public boolean isDateBased() {
            return false;
        }

        @Override
        public boolean isTimeBased() {
            return true;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R extends Temporal> R addTo(final R temporal, final long amount) {
            return (R) temporal.plus(amount, this);
        }

        @Override
        public long between(final Temporal temporal1Inclusive, final Temporal temporal2Exclusive) {
            return temporal1Inclusive.until(temporal2Exclusive, this);
        }
    }
}
