package browser;


import com.microsoft.playwright.Browser;

public class DefaultBrowser implements BrowserManager {

    private Browser browser;

    @Override
    public void startBrowser() {
        
    }

    @Override
    public void stopBrowser() {

    }

    @Override
    public Browser getBrowser() {
        return browser;
    }

}
