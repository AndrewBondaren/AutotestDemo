package utils;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalScope implements Scope {

    @Override
    public <T> Provider<T> scope(final Key<T> key, final Provider<T> creator) {
        return () -> {
            final ThreadLocalCache cache = ThreadLocalCache.getInstance();
            T value = cache.get(key);
            if (value == null) {
                value = creator.get();
                cache.add(key, value);
            }
            return value;
        };
    }

    private static final class ThreadLocalCache {

        private static final ThreadLocal<ThreadLocalCache> THREAD_LOCAL =
                ThreadLocal.withInitial(ThreadLocalCache::new);

        private final Map<Key<?>, Object> map = new HashMap<>();

        <T> T get(final Key<T> key) {
            return (T) map.get(key);
        }

        <T> void add(final Key<T> key, final T value) {
            map.put(key, value);
        }

        static ThreadLocalCache getInstance() {
            return THREAD_LOCAL.get();
        }

    }
}
