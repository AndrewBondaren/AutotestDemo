package steps;

import browser.BrowserManager;
import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import config.BrowserConfig;
import lombok.Getter;
import module.BrowserStandController;

@Getter
public class BaseBrowserSteps {

    private final static String PLAYWRIGHT = "Playwright";
    private final static String BROWSER = "Browser";

    private final BrowserConfig browserConfig;
    private final BrowserStandController browserStandController;
    private final BrowserManager browserManager;

    @Inject
    public BaseBrowserSteps(final BrowserConfig browserConfig,
                            final BrowserStandController browserStandController,
                            final BrowserManager browserManager) {
        this.browserConfig = browserConfig;
        this.browserStandController = browserStandController;
        this.browserManager = browserManager;
    }

    public void testStep(String url) {
        openPage(url).locator("button").click();
    }

    public Page openPage(String url) {
        Page page = getBrowserManager().getDriver().chromium().launch().newPage();
        page.navigate(url);
        return page;
    }

}
