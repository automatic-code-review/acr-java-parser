package br.com.acr.javaparser.service.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;
import br.com.acr.javaparser.domain.parser.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaParserService {

    public static JPJavaDomain parse(String path, String pathSource) throws IOException {

        JPJavaDomain javaDomain = new JPJavaDomain();
        ParseResult<CompilationUnit> compilationUnit = new JavaParser().parse(Files.newInputStream(new File(path).toPath()));

        if (!compilationUnit.getResult().isPresent()) {
            return javaDomain;
        }

        for (TypeDeclaration<?> type : compilationUnit.getResult().get().getTypes()) {

            for (Node node : type.getChildNodes()) {

                if (node instanceof MethodDeclaration) {
                    MethodDeclaration methodDeclaration = (MethodDeclaration) node;

                    JPMethodDomain methodDomain = new JPMethodDomain();
                    methodDomain.setName(methodDeclaration.getName().toString());
                    methodDomain.setReturnType(methodDeclaration.getType().toString());

                    if (methodDeclaration.getRange().isPresent()) {

                        Range range = methodDeclaration.getRange().get();

                        String positionPath = path.replace(pathSource, "");

                        if (positionPath.startsWith("/")) {

                            positionPath = positionPath.substring(1);

                        }

                        ACRPositionDomain position = new ACRPositionDomain();
                        position.setStartInLine(range.begin.line);
                        position.setEndInLine(range.end.line);
                        position.setPath(positionPath);

                        methodDomain.setPosition(position);

                    }

                    javaDomain.addMethod(methodDomain);

                }

            }

            for (AnnotationExpr annotation : type.getAnnotations()) {

                JPAnottationDomain anottationDomain = new JPAnottationDomain();
                anottationDomain.setName(annotation.getName().asString());

                javaDomain.addAnottation(anottationDomain);

            }

            for (BodyDeclaration<?> member : type.getMembers()) {

                JPMemberDomain memberDomain = new JPMemberDomain();

                for (Node node : member.getChildNodes()) {

                    if (node instanceof VariableDeclarator) {
                        VariableDeclarator variable = (VariableDeclarator) node;

                        memberDomain.setType(String.valueOf(variable.getType()));
                        memberDomain.setName(variable.getName().asString());
                        memberDomain.setHasDefault(variable.getInitializer().isPresent());

                        if (memberDomain.isHasDefault() && variable.getInitializer().get() instanceof NullLiteralExpr) {
                            memberDomain.setHasDefault(false);
                        }

                        break;

                    }

                }

                if (member instanceof FieldDeclaration) {

                    FieldDeclaration fieldDeclaration = (FieldDeclaration) member;

                    for (Modifier modifier : fieldDeclaration.getModifiers()) {

                        JPModifierDomain modifierDomain = new JPModifierDomain();
                        modifierDomain.setName(modifier.getKeyword().name());

                        memberDomain.addModifier(modifierDomain);

                    }

                }

                for (AnnotationExpr annotation : member.getAnnotations()) {

                    JPAnottationDomain anottationDomain = new JPAnottationDomain();
                    anottationDomain.setName(annotation.getName().asString());

                    memberDomain.addAnottation(anottationDomain);

                }

                if (member.getRange().isPresent()) {

                    Range range = member.getRange().get();

                    String positionPath = path.replace(pathSource, "");

                    if (positionPath.startsWith("/")) {

                        positionPath = positionPath.substring(1);

                    }

                    ACRPositionDomain position = new ACRPositionDomain();
                    position.setStartInLine(range.begin.line);
                    position.setEndInLine(range.end.line);
                    position.setPath(positionPath);

                    memberDomain.setPosition(position);

                }

                javaDomain.addMember(memberDomain);

            }

        }

        return javaDomain;

    }

}
