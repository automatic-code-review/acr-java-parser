package br.com.acr.generic.service;

import java.util.HashMap;

public class ACRParamService {

    public static HashMap<String, String> getByArgs(String[] args) {

        HashMap<String, String> params = new HashMap<>();

        for (String arg : args) {

            if (arg.startsWith("--") && arg.contains("=")) {

                String[] parts = arg.split("=", 2);
                String key = parts[0].substring(2);
                String value = parts[1].replaceAll("\"", "");

                params.put(key, value);

            }

        }

        return params;

    }

}
