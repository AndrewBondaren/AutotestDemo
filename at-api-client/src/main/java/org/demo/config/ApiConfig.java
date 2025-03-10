package org.demo.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:~/AutotestDemo/${environment}.properties",
        "file:~/AutotestDemo/${USER}.properties",
        "classpath:testing.properties"})
public interface ApiConfig extends Config {

    @Config.Key("api")
    String getApi();

}
