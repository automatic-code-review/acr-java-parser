package br.com.acr.javaparser.domain.config;

import br.com.acr.generic.domain.config.ACRConfigDomain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JPConfigDomain extends ACRConfigDomain {

    private List<JPRuleDomain> rules;

}
