package br.com.acr.javaparser.service.rules;

import br.com.acr.generic.domain.comment.ACRCommentDomain;
import br.com.acr.generic.domain.comment.ACRPositionDomain;
import br.com.acr.generic.domain.config.ACRChangeDomain;
import br.com.acr.javaparser.domain.config.JPConfigDomain;
import br.com.acr.javaparser.domain.config.RequireAnottationIfAttributeHasDefaultValueRuleDomain;
import br.com.acr.javaparser.domain.parser.JPJavaDomain;
import br.com.acr.javaparser.domain.parser.JPMemberDomain;
import br.com.acr.javaparser.service.parser.JavaParserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RequirePrefixAttributeService implements JPRuleService<RequireAnottationIfAttributeHasDefaultValueRuleDomain> {

    @Override
    public List<ACRCommentDomain> review(JPConfigDomain config, RequireAnottationIfAttributeHasDefaultValueRuleDomain rule) throws IOException {

        List<ACRCommentDomain> comments = new ArrayList<>();

        for (ACRChangeDomain change : config.getMerge().getChanges()) {

            if (change.isDeletedFile()) {
                continue;
            }

            String path = config.getPathSource() + "/" + change.getPath();

            if (!path.endsWith(".java")) {
                continue;
            }

            JPJavaDomain javaDomain = JavaParserService.parse(path, config.getPathSource());

            for (JPMemberDomain member : javaDomain.getMembers()) {

                String name = member.getName();
                if (name == null) {
                    continue;
                }

                List<String> regexList = rule.getRegexByType(member.getType());

                if (regexList.isEmpty()) {
                    System.out.printf("Type %s not found%n", member.getType());
                    continue;
                }

                if (isRegexOk(regexList, name)) {
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

    private boolean isRegexOk(List<String> regexs, String text) {

        return regexs.stream().anyMatch(regex -> Pattern.matches(regex, text));

    }

    private List<String> getRegexList(String type, Map<String, String> regex) {

        return regex.keySet().stream().filter(key -> Pattern.matches(key, type)).map(regex::get).collect(Collectors.toList());

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
