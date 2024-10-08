package browser;

import com.microsoft.playwright.Browser;

public interface BrowserManager {

    void startBrowser();

    void stopBrowser();

    Browser getBrowser();

}
