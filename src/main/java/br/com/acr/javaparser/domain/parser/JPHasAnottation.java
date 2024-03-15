package br.com.acr.javaparser.domain.parser;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class JPHasAnottation {

    protected List<JPAnottationDomain> anottations = new ArrayList<>();

    public void addAnottation(JPAnottationDomain anottation) {
        anottations.add(anottation);
    }

    public boolean hasAnottation(String name) {
        return anottations.stream().anyMatch(anottation -> name.equals(anottation.getName()));
    }

}
