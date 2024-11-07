package utils;

import com.google.inject.Scope;

public final class CustomScopeUtils {

    public static final Scope THREAD_LOCAL = new ThreadLocalScope();

}
