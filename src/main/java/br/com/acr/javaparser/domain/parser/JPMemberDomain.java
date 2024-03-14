package br.com.acr.javaparser.domain.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JPMemberDomain {

    private String name;

    private boolean hasDefault;

    private ACRPositionDomain position;

    private List<JPAnottationDomain> anottations = new ArrayList<>();

    public void addAnotation(JPAnottationDomain anottation) {
        anottations.add(anottation);
    }

}