package br.com.acr.javaparser.domain.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;

public interface JPPositionableDomain {

    ACRPositionDomain getPosition();

    void setPosition(ACRPositionDomain position);
}