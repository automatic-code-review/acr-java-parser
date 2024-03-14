package br.com.acr.javaparser.domain.config;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JPRuleDomain {


    private String comment;

    private JPRuleEnum type;

}
