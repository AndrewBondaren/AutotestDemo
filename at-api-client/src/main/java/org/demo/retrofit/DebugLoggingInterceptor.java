package org.demo.retrofit;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;


@Slf4j(topic = "API")
@NoArgsConstructor(staticName = "create")
@SuppressWarnings({"PMD.NcssCount", "PMD.CloseResource"})
public class DebugLoggingInterceptor implements Interceptor {

    private static final String BINARY = "(binary)";

    //TODO Refactor
    //CHECKSTYLE:OFF
    @Override
    public Response intercept(final Chain chain) throws IOException {
        final StringBuilder logInfo = new StringBuilder(System.lineSeparator());
        final Request request = chain.request();
        final String requestUrl = request.url().toString();
        final String body = "Body: ";

        logInfo.append("Request: ")
                .append(request.method())
                .append(' ')
                .append(requestUrl)
                .append(System.lineSeparator());
        if (request.headers().size() > 0) {
            logInfo.append(request.headers().toString());
        }

        final RequestBody requestBody = request.body();
        if (nonNull(requestBody) && requestBody.contentLength() > 0) {

            String requestBodyString;

            try {
                requestBodyString = readRequestBody(requestBody);
            } catch (UnsupportedEncodingException e) {
                requestBodyString = BINARY;
            }

            logInfo.append(body)
                    .append(requestBodyString)
                    .append(System.lineSeparator());

        }

        final Map<String, String> paramsMap = new HashMap<>();
        request.url().queryParameterNames().forEach(param -> paramsMap.put(param, request.url().queryParameter(param)));
        logInfo.append("Parameters: ")
                .append(paramsMap.toString())
                .append(System.lineSeparator());

        final Response response = chain.proceed(request);

        final Response.Builder responseBuilder = response.newBuilder();

        final ResponseBody responseBody = response.body();

        logInfo.append("Response code: ")
                .append(response.code())
                .append(" Response time: ")
                .append(response.receivedResponseAtMillis() - response.sentRequestAtMillis())
                .append(" ms")
                .append(System.lineSeparator());
        if (nonNull(responseBody)) {

            String responseBodyString;

            final byte[] bytes = responseBody.bytes();


            try {
                responseBodyString = StringEscapeUtils
                        .unescapeJson(new String(bytes, StandardCharsets.UTF_8));
            } catch (IllegalArgumentException e) {
                responseBodyString = BINARY;
            }

            logInfo.append(body)
                    .append(responseBodyString);

            responseBuilder.body(ResponseBody.create(responseBody.contentType(), bytes));
        }

        log.info(logInfo.toString());

        return responseBuilder.build();
    }

    private static String readRequestBody(final RequestBody requestBody) throws IOException {
        try (Buffer buffer = new Buffer()) {
            requestBody.writeTo(buffer);
            return buffer.readString(StandardCharsets.UTF_8);
        }
    }
}
