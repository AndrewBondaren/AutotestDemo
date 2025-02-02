package browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public interface BasePage {

    @DriverProvider
    Playwright getPlayWright();

    @DriverProvider
    Browser getBrowser();

    default void openPage(final String url) {
        this.getBrowser().newPage().navigate(url);
    }

}
