package com.mossad.kafka.security;

import java.security.Provider;

public class CustomProvider extends Provider {


    public CustomProvider(String name, String versionStr, String info) {
        super(name, versionStr, info);
    }
}
