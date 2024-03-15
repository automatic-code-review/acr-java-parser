package br.com.acr.javaparser.domain.parser;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JPJavaDomain extends JPHasAnottation {

    private List<JPMemberDomain> members = new ArrayList<>();

    public void addMember(JPMemberDomain member) {
        members.add(member);
    }

}
