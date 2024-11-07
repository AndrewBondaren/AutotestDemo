package org.demo.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/"
})
public interface ApiConfig extends Config {

    @Config.Key("api")
    String getApi();

}
