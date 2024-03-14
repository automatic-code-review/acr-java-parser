package br.com.acr.javaparser.service.rules;

import br.com.acr.javaparser.domain.config.JPRuleEnum;

public class JPRuleServiceFactory {

    public static JPRuleService create(JPRuleEnum type) {

        if (type == JPRuleEnum.REQUIRE_ANOTTATION_IF_ATRRIBUTE_HAS_DEFAULT_VALUE) {
            return new RequireAnottationIfAttributeHasDefaultValueService();
        }

        return null;

    }

}
