package com.davidhabot.groundleague.core.config;

public interface ConfigLoader {
    ConfigData config = new ConfigData();

    void loadConfig();
    ConfigData getConfig();
}
