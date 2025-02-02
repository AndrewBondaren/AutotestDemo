package browser;


import com.google.inject.Inject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import config.BrowserConfig;
import lombok.Getter;

@Getter
public class DefaultBrowserManager implements BrowserManager {

    private Browser browser;
    private Playwright playwright;
    private final BrowserConfig config;

    @Inject
    public DefaultBrowserManager(final BrowserConfig config) {
        this.config = config;
    }

    @Override
    public void startDriver() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @Override
    public void stopDriver() {
        playwright.close();
    }

    @Override
    public Playwright getDriver() {
        return playwright;
    }

    @Override
    public Browser getBrowser() {
        return browser;
    }

}
