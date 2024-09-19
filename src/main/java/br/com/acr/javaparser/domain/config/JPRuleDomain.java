package br.com.acr.javaparser.domain.config;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequireAnottationIfAtributeHasDefaultValueRuleDomain.class, name = "REQUIRE_ANOTTATION_IF_ATRRIBUTE_HAS_DEFAULT_VALUE"),
        @JsonSubTypes.Type(value = RequireAnottationIfAttributeHasDefaultValueRuleDomain.class, name = "REQUIRE_PREFIX_ATTRIBUTE"),
        @JsonSubTypes.Type(value = PreferPrimitiveBooleanRuleDomain.class, name = "PREFER_PRIMITIVE_BOOLEAN"),
})
public abstract class JPRuleDomain {

    private String comment;

    public abstract JPRuleEnum getType();

}
