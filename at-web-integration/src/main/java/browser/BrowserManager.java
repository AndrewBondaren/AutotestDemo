package browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import driver.DriverManager;

public interface BrowserManager extends DriverManager<Playwright> {

    @Override
    void startDriver();

    @Override
    void stopDriver();

    @Override
    Playwright getDriver();

    Browser getBrowser();

}
