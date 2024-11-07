package org.demo.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.nonNull;


@AllArgsConstructor(staticName = "create")
public final class DefaultCallAdapterFactory<T> extends CallAdapter.Factory {

    private static final String RESPONSE_ADAPT_ERROR = "Could not execute request";

    private final ObjectMapper mapper;

    //CHECKSTYLE:OFF
    @Override
    public CallAdapter<T, ?> get(final Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        if (returnType.getTypeName().startsWith(Call.class.getName())) {
            return null;
        }
        if (returnType.getTypeName().startsWith(Response.class.getName())) {
            return new ResponseCallAdapter(((ParameterizedType) returnType).getActualTypeArguments()[0]);
        }
        return new InstanceCallAdapter(returnType);
    }
    //CHECKSTYLE:ON

    /**
     * Call adapter factory for Response<...>.
     */
    private class ResponseCallAdapter implements CallAdapter<T, Response<T>> {

        private final Type returnType;

        ResponseCallAdapter(final Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public Response<T> adapt(final Call<T> call) {
            final Response<T> response;
            try {
                response = call.execute();
            } catch (ConversionException | IOException e) {
                throw new RetrofitException(e);
            }

            return response;
        }
    }

    /**
     * Call adapter factory for instances.
     */
    private class InstanceCallAdapter implements CallAdapter<T, Object> {

        private final Type returnType;

        InstanceCallAdapter(final Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public Object adapt(final Call<T> call) {
            final Response<T> response;
            try {
                response = call.execute();

                if (!response.isSuccessful()) {
                    String rawError = "тело ответа не получено.";

                    if (nonNull(response.errorBody())) {
                        rawError = StringEscapeUtils
                                .unescapeJson(new String(response.errorBody().bytes(), StandardCharsets.UTF_8));
                    }

                    throw new UnexpectedResponseException(rawError);
                }
            } catch (ConversionException | IOException e) {
                throw new RetrofitException(e);
            }

            return response.body();
        }


    }

}
