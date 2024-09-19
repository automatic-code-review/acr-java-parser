package br.com.acr.javaparser.domain.config;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
public class RequireAnottationIfAttributeHasDefaultValueRuleDomain extends JPRuleDomain {

    @Override
    public JPRuleEnum getType() {
        return JPRuleEnum.REQUIRE_PREFIX_ATTRIBUTE;
    }

    @Getter
    @Setter
    public static class Config {
        private String type;
        private List<String> name;
    }

    private List<Config> configs;

    public List<String> getRegexByType(String type) {

        for (Config config : configs) {
            if (Pattern.matches(config.getType(), type)) {
                return config.getName();
            }
        }

        return new ArrayList<>();

    }

}
