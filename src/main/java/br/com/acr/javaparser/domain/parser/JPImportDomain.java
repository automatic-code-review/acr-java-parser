package br.com.acr.javaparser.domain.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JPImportDomain implements JPPositionableDomain {

    private String name;

    private Boolean isStatic;

    private Boolean isAsterisk;

    private ACRPositionDomain position;

}
