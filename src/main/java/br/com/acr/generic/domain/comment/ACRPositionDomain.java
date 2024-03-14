package br.com.acr.generic.domain.comment;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ACRPositionDomain {

    private String path;

    private int startInLine;

    private int endInLine;

    private String language = "java";

    private boolean snipset = true;

}
