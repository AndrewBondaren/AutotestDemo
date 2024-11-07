package ru.beeline.common.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigFactory;
import ru.beeline.common.config.UserDataConfig;
import ru.beeline.common.guice.ThreadLocalScoped;
import ru.beeline.common.utils.CustomScopeUtils;


public class DataModule extends AbstractModule {

    @Override
    public void configure() {
        bindScope(ThreadLocalScoped.class, CustomScopeUtils.THREAD_LOCAL);
    }


    @Provides
    public UserDataConfig providesUserDataConfig() {
        return ConfigFactory.newInstance().create(UserDataConfig.class, System.getProperties());
    }

}
