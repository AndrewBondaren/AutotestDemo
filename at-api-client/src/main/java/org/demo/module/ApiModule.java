package org.demo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import guice.ThreadLocalScoped;
import okhttp3.OkHttpClient;
import org.aeonbits.owner.ConfigFactory;
import org.demo.allure.AllureOkHttp3WithSteps;
import org.demo.config.ApiConfig;
import org.demo.retrofit.DebugLoggingInterceptor;
import utils.CustomScopeUtils;

import java.util.concurrent.TimeUnit;

public class ApiModule extends AbstractModule {

    @Override
    public void configure() {
        bindScope(ThreadLocalScoped.class, CustomScopeUtils.THREAD_LOCAL);
    }

    @Provides
    protected ApiConfig provideApiConfig() {
        return ConfigFactory.newInstance().create(ApiConfig.class, System.getProperties());
    }

    @Provides
    protected OkHttpClient providesDefaultOkHttpClient(final ApiConfig apiConfig) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AllureOkHttp3WithSteps())
                .addInterceptor(DebugLoggingInterceptor.create())
                .callTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
    }
}
