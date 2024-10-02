package br.com.acr.javaparser.service.rules;

import br.com.acr.generic.domain.comment.ACRCommentDomain;
import br.com.acr.generic.domain.comment.ACRPositionDomain;
import br.com.acr.generic.domain.config.ACRChangeDomain;
import br.com.acr.javaparser.domain.config.JPConfigDomain;
import br.com.acr.javaparser.domain.config.RequireAnottationIfAtributeHasDefaultValueRuleDomain;
import br.com.acr.javaparser.domain.parser.JPImportDomain;
import br.com.acr.javaparser.domain.parser.JPJavaDomain;
import br.com.acr.javaparser.domain.parser.JPMemberDomain;
import br.com.acr.javaparser.service.parser.JavaParserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequireAnottationIfAttributeHasDefaultValueService implements JPRuleService<RequireAnottationIfAtributeHasDefaultValueRuleDomain> {

    public List<ACRCommentDomain> review(JPConfigDomain config, RequireAnottationIfAtributeHasDefaultValueRuleDomain rule) throws IOException {

        List<ACRCommentDomain> comments = new ArrayList<>();

        for (ACRChangeDomain change : config.getMerge().getChanges()) {

            String path = config.getPathSource() + "/" + change.getPath();

            if (!path.endsWith(".java")) {
                continue;
            }

            JPJavaDomain javaDomain = JavaParserService.parse(path, config.getPathSource());

            boolean containsLombokBuilderDefault = javaDomain.getImports().stream()
                    .map(JPImportDomain::getName)
                    .anyMatch(importName -> importName.equals("lombok.Builder.Default"));

            if (!javaDomain.hasAnottation("Builder")) {
                continue;
            }

            for (JPMemberDomain member : javaDomain.getMembers()) {

                if (!member.isHasDefault()) {

                    continue;
                }

                if (member.hasModifier("FINAL")) {
                    continue;
                }

                if (member.hasAnottation("Builder.Default")) {

                    continue;
                }

                if (member.hasAnottation("Default") && containsLombokBuilderDefault) {

                    continue;
                }

                ACRCommentDomain comment = new ACRCommentDomain();
                comment.setComment(getFormatedComment(rule.getComment(), member));
                comment.setPosition(member.getPosition());

                comments.add(comment);

            }

        }

        return comments;

    }

    private static String getFormatedComment(String comment, JPMemberDomain member) {

        ACRPositionDomain position = member.getPosition();

        String formated = comment;
        formated = formated.replace("#{VARIAVEL_NAME}", member.getName());
        formated = formated.replace("#{FILE_NAME}", position.getPath());
        formated = formated.replace("#{LINE_NUMBER}", String.valueOf(position.getStartInLine()));

        return formated;


    }

}
