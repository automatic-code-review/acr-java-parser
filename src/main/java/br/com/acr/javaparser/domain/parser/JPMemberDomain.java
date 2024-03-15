package br.com.acr.javaparser.domain.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JPMemberDomain extends JPHasAnottation {

    private String name;

    private boolean hasDefault;

    private ACRPositionDomain position;

    private List<JPModifierDomain> modifiers = new ArrayList<>();

    public void addModifier(JPModifierDomain modifier) {
        modifiers.add(modifier);
    }

    public boolean hasModifier(String name) {
        return modifiers.stream().anyMatch(anottation -> name.equals(anottation.getName()));
    }

}