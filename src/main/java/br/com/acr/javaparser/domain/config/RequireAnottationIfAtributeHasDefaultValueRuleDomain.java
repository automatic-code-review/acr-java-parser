package br.com.acr.javaparser.domain.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequireAnottationIfAtributeHasDefaultValueRuleDomain extends JPRuleDomain {

    @Override
    public JPRuleEnum getType() {
        return JPRuleEnum.REQUIRE_ANOTTATION_IF_ATRRIBUTE_HAS_DEFAULT_VALUE;
    }


}
