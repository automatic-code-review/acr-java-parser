package br.com.acr;

import br.com.acr.generic.domain.comment.ACRCommentDomain;
import br.com.acr.generic.service.ACRConfigService;
import br.com.acr.generic.service.ACRParamService;
import br.com.acr.javaparser.domain.config.JPConfigDomain;
import br.com.acr.javaparser.domain.config.JPRuleDomain;
import br.com.acr.javaparser.service.rules.JPRuleService;
import br.com.acr.javaparser.service.rules.JPRuleServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<ACRCommentDomain> comments = new ArrayList<>();
        JPConfigDomain config = new ACRConfigService<>(JPConfigDomain.class).getByConfigPath(ACRParamService.getByArgs(args));

        for (JPRuleDomain rule : config.getRules()) {

            JPRuleService service = JPRuleServiceFactory.create(rule.getType());

            if (service != null) {

                comments.addAll(service.review(config, rule));

            }

        }

        new ObjectMapper().writeValue(new File(config.getPathOutput()), comments);

    }

}