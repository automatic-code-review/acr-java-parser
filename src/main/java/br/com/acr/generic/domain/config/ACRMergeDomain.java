package br.com.acr.generic.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ACRMergeDomain {

    private List<ACRChangeDomain> changes;


}
