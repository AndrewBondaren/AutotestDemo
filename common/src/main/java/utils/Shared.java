package utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public final class Shared<T> extends ArrayList<T> {

    private static AtomicInteger counter = new AtomicInteger(0);

    @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
    public synchronized T getFree() {
        final ThreadLocalCache cache = ThreadLocalCache.getInstance();
        T value = cache.get(Thread.currentThread());
        if (value == null) {
            if (counter.get() >= this.size()) {
                throw new AssertionError("No free values left!");
            }
            value = this.get(counter.getAndIncrement());
            cache.add(Thread.currentThread(), value);
        }
        return value;
    }

    /**
     * Javadoc.
     */
    private static final class ThreadLocalCache {

        private static final ThreadLocal<Shared.ThreadLocalCache> THREAD_LOCAL =
                ThreadLocal.withInitial(Shared.ThreadLocalCache::new);

        private final Map<Thread, Object> map = new ConcurrentHashMap<>();

        static Shared.ThreadLocalCache getInstance() {
            return THREAD_LOCAL.get();
        }

        @SuppressWarnings("unchecked")
        <T> T get(final Thread key) {
            return (T) map.get(key);
        }

        <T> void add(final Thread key, final T value) {
            map.put(key, value);
        }
    }

}
