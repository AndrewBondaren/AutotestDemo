package junit5;

import com.google.inject.Guice;
import com.google.inject.Injector;
import module.BrowserModule;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public interface InjectorExtension extends TestInstancePostProcessor {

    @Override
    default void postProcessTestInstance(final Object instance, final ExtensionContext context) {
        final Injector injector = Guice.createInjector(new BrowserModule());
        injector.injectMembers(this);
    }
}
