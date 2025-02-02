package config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:~/AutotestDemo/${environment}.properties",
        "file:~/AutotestDemo/${USER}.properties",
        "classpath:testing.properties"})
public interface BrowserConfig extends Config {



}
