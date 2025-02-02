package page;

import browser.BasePage;

public interface MainPage extends BasePage {

    default void openMainPage() {
        openPage("https://playwright.dev/java/docs/locators");
    }

}
