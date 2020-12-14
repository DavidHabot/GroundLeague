package com.davidhabot.groundleague.core.config;

import com.davidhabot.groundleague.core.config.data.ConfigData;

public interface ConfigLoader {
    ConfigData config = new ConfigData();

    void loadConfig();
    ConfigData getConfig();
}
