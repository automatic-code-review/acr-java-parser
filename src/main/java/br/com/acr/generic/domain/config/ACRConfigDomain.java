package br.com.acr.generic.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ACRConfigDomain {

    @JsonProperty("path_source")
    private String pathSource;

    @JsonProperty("path_output")
    private String pathOutput;

    private ACRMergeDomain merge;

}
