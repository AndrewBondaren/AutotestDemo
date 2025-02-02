package module;

import browser.BrowserManager;
import browser.DefaultBrowserManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import config.BrowserConfig;
import guice.ThreadLocalScoped;
import org.aeonbits.owner.ConfigFactory;
import utils.CustomScopeUtils;

public class BrowserModule extends AbstractModule {

    @Override
    public void configure() {
        bindScope(ThreadLocalScoped.class, CustomScopeUtils.THREAD_LOCAL);
    }

    @Provides
    @ThreadLocalScoped
    public BrowserManager providesBrowserManager(final BrowserConfig config) {
        return new DefaultBrowserManager(config);
    }

    @Provides
    public BrowserConfig providesBrowserConfig() {
        return ConfigFactory.newInstance().create(BrowserConfig.class, System.getProperties());
    }

    //TODO add listener
    @Provides
    public Playwright providePlaywright() {
        return Playwright.create();
    }

    @Provides
    public Browser provideBrowser(final BrowserManager browserManager) {
        return browserManager.getBrowser();
    }

}
