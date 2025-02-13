package com.baiji.grammar;

import com.baiji.antlr.BaijiGrammarBaseListener;
import com.baiji.antlr.BaijiGrammarParser;

import java.util.ArrayList;
import java.util.List;

public class CustomBaijiBrammarListener extends BaijiGrammarBaseListener {

    private BaijiGrammarDefinition baijiGrammarDefinition = new BaijiGrammarDefinition();

    @Override
    public void enterSyntaxDeclaration(BaijiGrammarParser.SyntaxDeclarationContext ctx) {
        baijiGrammarDefinition.setSyntax(ctx.STRING_LITERAL().getText());
    }

    @Override
    public void enterPackageDeclaration(BaijiGrammarParser.PackageDeclarationContext ctx) {
        baijiGrammarDefinition.setPackageName(ctx.IDENTIFIER().getText());
    }

    @Override
    public void enterAppIdDeclaration(BaijiGrammarParser.AppIdDeclarationContext ctx) {
        baijiGrammarDefinition.setAppid(Integer.valueOf(ctx.INTEGER_LITERAL().getText()));
    }

    @Override
    public void enterClassDeclaration(BaijiGrammarParser.ClassDeclarationContext ctx) {
        String clsName = ctx.IDENTIFIER().getText();

        List<ClassInfoDefinition> clsInfo = new ArrayList<>();
//        ctx.children.forEach(x->x.);



    }
}
