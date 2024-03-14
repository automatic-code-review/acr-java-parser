package br.com.acr.generic.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ACRConfigService<T> {

    private final Class<T> clazz;

    public ACRConfigService(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getByConfigPath(HashMap<String, String> params) throws IOException {

        String path = params.getOrDefault("CONFIG_PATH", null);

        if (path == null || path.isEmpty()) {

            throw new RuntimeException("--CONFIG_PATH is required");

        }

        return new ObjectMapper().readValue(new File(path), clazz);

    }

}
