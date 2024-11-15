package org.demo.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import io.qameta.allure.util.ResultsUtils;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.UUID;

import static io.qameta.allure.model.Status.PASSED;
import static java.util.Optional.ofNullable;
import static org.apache.commons.text.StringEscapeUtils.unescapeJson;


public class AllureOkHttp3WithSteps extends AllureOkHttp3 {

    private final AllureLifecycle lifecycle;

    public AllureOkHttp3WithSteps() {
        this.lifecycle = Allure.getLifecycle();
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();
        final String uuid = UUID.randomUUID().toString();
        lifecycle.startStep(
                uuid,
                new StepResult().setStatus(PASSED).setName(String.format("%s: %s", request.method(), request.url()))
        );
        try {
            return super.intercept(chain);
        } catch (IOException origin) {
            lifecycle.updateStep(uuid, stepResult -> {
                stepResult.setStatus(ResultsUtils.getStatus(origin).orElse(Status.BROKEN));
                stepResult.setStatusDetails(new StatusDetails()
                        .setMessage(unescapeJson(ofNullable(origin.getMessage())
                                .orElse(origin.getClass().getName()))));
            });
            throw origin;
        } finally {
            lifecycle.stopStep(uuid);
        }
    }

}
