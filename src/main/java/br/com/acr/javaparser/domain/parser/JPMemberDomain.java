package br.com.acr.javaparser.domain.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JPMemberDomain extends JPHasAnottation {

    private String name;

    private boolean hasDefault;

    private ACRPositionDomain position;

}