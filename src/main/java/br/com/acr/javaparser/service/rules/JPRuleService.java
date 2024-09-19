package br.com.acr.javaparser.service.rules;

import br.com.acr.generic.domain.comment.ACRCommentDomain;
import br.com.acr.javaparser.domain.config.JPConfigDomain;
import br.com.acr.javaparser.domain.config.JPRuleDomain;

import java.io.IOException;
import java.util.List;

public interface JPRuleService<T extends JPRuleDomain> {

    List<ACRCommentDomain> review(JPConfigDomain config, T rule) throws IOException;

}
