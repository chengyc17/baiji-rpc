package com.baiji.gen.grammar;

import com.baiji.gen.antlr.BaijiGrammarBaseListener;
import com.baiji.gen.antlr.BaijiGrammarParser;
import com.baiji.common.ForbiddenMethodName;
import com.baiji.common.dtype.BaijiIDLDataType;
import com.baiji.common.grammar.definition.*;
import com.baiji.common.util.StringUtils;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomBaijiBrammarListener extends BaijiGrammarBaseListener {

    private BaijiGrammarDefinition grammarDefinition = new BaijiGrammarDefinition();
    private List<ClassInfoDefinition> clsInfos = new ArrayList<>();
    private ServiceInfoDefinition serviceInfo = new ServiceInfoDefinition();

    @Override
    public void enterSyntaxDeclaration(BaijiGrammarParser.SyntaxDeclarationContext ctx) {
        String syntax = ctx.STRING_LITERAL().getText();
        grammarDefinition.setSyntax(syntax);
    }

    @Override
    public void enterPackageDeclaration(BaijiGrammarParser.PackageDeclarationContext ctx) {
        String packageName = ctx.PACKAGE_IDENTIFIER().getText();
        grammarDefinition.setPackageName(packageName);
    }

    @Override
    public void enterAppIdDeclaration(BaijiGrammarParser.AppIdDeclarationContext ctx) {
        int appid = Integer.parseInt(ctx.INTEGER_LITERAL().getText());
        grammarDefinition.setAppid(appid);
    }

    @Override
    public void enterClassDeclaration(BaijiGrammarParser.ClassDeclarationContext ctx) {
        ClassInfoDefinition classInfo = new ClassInfoDefinition();
        classInfo.setClsName(ctx.IDENTIFIER().getText());

        List<FieldInfoDefinition> fields = new ArrayList<>();
        for (BaijiGrammarParser.FieldDeclarationContext fieldCtx : ctx.fieldDeclaration()) {
            FieldInfoDefinition fieldInfo = new FieldInfoDefinition();
            String type = fieldCtx.type().getText();
            if (StringUtils.startsWithIgnoreCase(type, BaijiIDLDataType.Baiji_Map) || StringUtils.startsWithIgnoreCase(type, BaijiIDLDataType.Baiji_List)) {
                fieldInfo.setDataType(StringUtils.startsWithIgnoreCase(type, BaijiIDLDataType.Baiji_Map) ? BaijiIDLDataType.Baiji_Map : BaijiIDLDataType.Baiji_List);
                List<BaijiGrammarParser.TypeContext> type1 = fieldCtx.type().type();
                fieldInfo.setGenerics(type1.stream().map(x -> x.getText()).collect(Collectors.toList()));
            } else {
                fieldInfo.setDataType(type);

            }
            fieldInfo.setFieldName(fieldCtx.IDENTIFIER().getText());
            fieldInfo.setComment(StringUtils.join(StringUtils.Empty,
                    Optional.ofNullable(fieldCtx.SINGLE_LINE_COMMENT()).map(ParseTree::getText).orElse(StringUtils.Empty),
                    Optional.ofNullable(fieldCtx.MULTI_LINE_COMMENT()).map(ParseTree::getText).orElse(StringUtils.Empty))
            );
            fields.add(fieldInfo);
        }
        classInfo.setFieldInfos(fields);
        clsInfos.add(classInfo);
    }

    @Override
    public void enterServiceDeclaration(BaijiGrammarParser.ServiceDeclarationContext ctx) {
        serviceInfo.setServiceName(ctx.IDENTIFIER().getText());
        List<MethodDefinition> methods = new ArrayList<>();
        for (BaijiGrammarParser.MethodDeclarationContext methodCtx : ctx.methodDeclaration()) {
            MethodDefinition methodInfo = new MethodDefinition();
            methodInfo.setResType(methodCtx.type(0).getText());
            String methodName = methodCtx.IDENTIFIER(0).getText();
            if (ForbiddenMethodName.inForbidNames(methodName)) {
                throw new RuntimeException("\"checkHealth\" and \"all\" are reserved keywords, do not use");
            }
            methodInfo.setMethodName(methodName);
            methodInfo.setReqType(methodCtx.type(1).getText());
            methodInfo.setReqName(methodCtx.IDENTIFIER(1).getText());
            methods.add(methodInfo);
        }
        serviceInfo.setMethodDefinitionList(methods);
    }

    @Override
    public void exitProgram(BaijiGrammarParser.ProgramContext ctx) {
        grammarDefinition.setClsInfo(clsInfos);
        grammarDefinition.setServiceInfo(serviceInfo);
    }

    public BaijiGrammarDefinition getGrammarDefinition() {
        return grammarDefinition;
    }
}
