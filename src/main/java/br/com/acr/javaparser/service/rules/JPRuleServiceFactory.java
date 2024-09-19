package br.com.acr.javaparser.service.rules;

import br.com.acr.javaparser.domain.config.JPRuleEnum;

public class JPRuleServiceFactory {

    public static JPRuleService create(JPRuleEnum type) {

        switch (type) {
            case REQUIRE_ANOTTATION_IF_ATRRIBUTE_HAS_DEFAULT_VALUE:
                return new RequireAnottationIfAttributeHasDefaultValueService();
            case REQUIRE_PREFIX_ATTRIBUTE:
                return new RequirePrefixAttributeService();
            case PREFER_PRIMITIVE_BOOLEAN:
                return new PreferPrimitiveBooleanService();

        }

        return null;

    }

}
