package junit5;

import browser.BrowserManager;
import com.google.inject.Inject;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BrowserExtension implements BeforeEachCallback, AfterEachCallback, InjectorExtension {

    @Inject
    private BrowserManager manager;

    @Override
    public void beforeEach(final ExtensionContext context) {
        manager.startDriver();
    }

    @Override
    public void afterEach(final ExtensionContext context) {
        manager.stopDriver();
    }
}
