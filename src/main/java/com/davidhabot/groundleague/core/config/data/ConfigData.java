package com.davidhabot.groundleague.core.config.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConfigData implements Serializable {
    public static final long serialVersionUID = 1;

    String title;
    String description;
    char[] keyboard;
}
