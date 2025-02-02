package module;

import com.google.inject.Inject;
import config.BrowserConfig;
import stand.StandController;

public class BrowserStandController implements StandController<BrowserConfig> {

    private final BrowserConfig browserConfig;

    @Inject
    BrowserStandController(final BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
    }

    public BrowserConfig getConfig() {
        return this.browserConfig;
    }

}
