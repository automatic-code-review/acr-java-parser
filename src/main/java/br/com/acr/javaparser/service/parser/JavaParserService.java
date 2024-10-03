package br.com.acr.javaparser.service.parser;

import br.com.acr.generic.domain.comment.ACRPositionDomain;
import br.com.acr.javaparser.domain.parser.*;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class JavaParserService {

    public static JPJavaDomain parse(String path, String pathSource) throws IOException {

        JPJavaDomain javaDomain = new JPJavaDomain();
        ParseResult<CompilationUnit> compilationUnitParsed = new JavaParser().parse(Files.newInputStream(new File(path).toPath()));
        Optional<CompilationUnit> compilationUnitOpt = compilationUnitParsed.getResult();

        if (!compilationUnitOpt.isPresent()) {
            return javaDomain;
        }

        String positionPath = path.replace(pathSource, "");
        String relativePath = positionPath.startsWith("/") ? positionPath.substring(1) : positionPath;

        CompilationUnit compilationUnit = compilationUnitOpt.get();

        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {

            for (Node node : type.getChildNodes()) {

                if (node instanceof MethodDeclaration) {
                    MethodDeclaration methodDeclaration = (MethodDeclaration) node;

                    JPMethodDomain methodDomain = new JPMethodDomain();
                    methodDomain.setName(methodDeclaration.getName().toString());
                    methodDomain.setReturnType(methodDeclaration.getType().toString());

                    updateDomainPositionFromNode(methodDomain, methodDeclaration, relativePath);

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

                updateDomainPositionFromNode(memberDomain, member, relativePath);

                javaDomain.addMember(memberDomain);

            }

        }

        for (ImportDeclaration importDeclaration : compilationUnit.getImports()) {

            JPImportDomain importDomain = new JPImportDomain();
            importDomain.setName(importDeclaration.getName().asString());
            importDomain.setIsStatic(importDeclaration.isStatic());
            importDomain.setIsAsterisk(importDeclaration.isAsterisk());

            updateDomainPositionFromNode(importDomain, importDeclaration, relativePath);

            javaDomain.addImport(importDomain);
        }

        return javaDomain;

    }

    private static void updateDomainPositionFromNode(JPPositionableDomain positionableDomain, Node node, String relativePath) {

        node.getRange().ifPresent(range -> {

            ACRPositionDomain positionDomain = new ACRPositionDomain();
            positionDomain.setStartInLine(range.begin.line);
            positionDomain.setEndInLine(range.end.line);
            positionDomain.setPath(relativePath);

            positionableDomain.setPosition(positionDomain);
        });
    }

}
