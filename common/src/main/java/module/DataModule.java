package module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigFactory;
import config.UserDataConfig;
import guice.ThreadLocalScoped;
import utils.CustomScopeUtils;


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
