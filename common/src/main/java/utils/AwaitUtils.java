package utils;

import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.core.ThrowingRunnable;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public final class AwaitUtils {

    private AwaitUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class!");
    }

    public static void awaitCondition(final int timeoutInMinutes,
                                      final int pollInterval,
                                      final ThrowingRunnable condition) {
        try {
            await().atMost(timeoutInMinutes, TimeUnit.MINUTES)
                    .pollInterval(pollInterval, TimeUnit.SECONDS)
                    .untilAsserted(condition);
        } catch (ConditionTimeoutException e) {
            throw new AssertionError(e.getMessage(), e);
        }

    }
}
