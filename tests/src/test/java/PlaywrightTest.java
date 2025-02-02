import com.google.inject.Inject;
import junit5.Browser;
import module.BrowserModule;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.BaseBrowserSteps;

@ExtendWith({GuiceExtension.class})
@IncludeModule({BrowserModule.class})
public class PlaywrightTest {

    @Inject
    private BaseBrowserSteps baseBrowserSteps;

    @Test
    @Browser
    public void shouldClickButton() {
        baseBrowserSteps.testStep("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
    }

}
