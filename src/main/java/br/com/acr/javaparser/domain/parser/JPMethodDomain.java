package br.com.acr.javaparser.domain.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JPMethodDomain {

    private String name;
    private String returnType;
    private ACRPositionDomain position;

}
