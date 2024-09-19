package br.com.acr.javaparser.service.rules;

import br.com.acr.generic.domain.comment.ACRCommentDomain;
import br.com.acr.generic.domain.comment.ACRPositionDomain;
import br.com.acr.generic.domain.config.ACRChangeDomain;
import br.com.acr.javaparser.domain.config.JPConfigDomain;
import br.com.acr.javaparser.domain.config.PreferPrimitiveBooleanRuleDomain;
import br.com.acr.javaparser.domain.parser.JPJavaDomain;
import br.com.acr.javaparser.domain.parser.JPMemberDomain;
import br.com.acr.javaparser.domain.parser.JPMethodDomain;
import br.com.acr.javaparser.service.parser.JavaParserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreferPrimitiveBooleanService implements JPRuleService<PreferPrimitiveBooleanRuleDomain> {

    @Override
    public List<ACRCommentDomain> review(JPConfigDomain config, PreferPrimitiveBooleanRuleDomain rule) throws IOException {

        List<ACRCommentDomain> comments = new ArrayList<>();

        for (ACRChangeDomain change : config.getMerge().getChanges()) {

            String path = config.getPathSource() + "/" + change.getPath();

            if (!path.endsWith(".java")) {
                continue;
            }

            JPJavaDomain javaDomain = JavaParserService.parse(path, config.getPathSource());

            for (JPMethodDomain method : javaDomain.getMethods()) {

                if (Objects.equals(method.getReturnType(), "Boolean")) {

                    ACRCommentDomain comment = new ACRCommentDomain();
                    comment.setComment(getFormatedComment(rule.getComment(), method));
                    comment.setPosition(method.getPosition());

                    comments.add(comment);

                }

            }

        }

        return comments;

    }

    private static String getFormatedComment(String comment, JPMethodDomain method) {

        ACRPositionDomain position = method.getPosition();

        String formated = comment;
        formated = formated.replace("#{METHOD_NAME}", method.getName());
        formated = formated.replace("#{FILE_NAME}", position.getPath());
        formated = formated.replace("#{LINE_NUMBER}", String.valueOf(position.getStartInLine()));

        return formated;


    }

}
