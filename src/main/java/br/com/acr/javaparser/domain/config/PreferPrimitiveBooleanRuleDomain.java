package br.com.acr.javaparser.domain.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferPrimitiveBooleanRuleDomain extends JPRuleDomain {

    @Override
    public JPRuleEnum getType() {
        return JPRuleEnum.PREFER_PRIMITIVE_BOOLEAN;
    }

}
