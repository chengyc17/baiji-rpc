package com.baiji.antlr;
// Generated from BaijiGrammar.g4 by ANTLR 4.13.1

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BaijiGrammarParser}.
 */
public interface BaijiGrammarListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#program}.
     *
     * @param ctx the parse tree
     */
    void enterProgram(BaijiGrammarParser.ProgramContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#program}.
     *
     * @param ctx the parse tree
     */
    void exitProgram(BaijiGrammarParser.ProgramContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#syntaxDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterSyntaxDeclaration(BaijiGrammarParser.SyntaxDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#syntaxDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitSyntaxDeclaration(BaijiGrammarParser.SyntaxDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#packageDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterPackageDeclaration(BaijiGrammarParser.PackageDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#packageDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitPackageDeclaration(BaijiGrammarParser.PackageDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#appIdDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterAppIdDeclaration(BaijiGrammarParser.AppIdDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#appIdDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitAppIdDeclaration(BaijiGrammarParser.AppIdDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#classDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterClassDeclaration(BaijiGrammarParser.ClassDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#classDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitClassDeclaration(BaijiGrammarParser.ClassDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#fieldDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterFieldDeclaration(BaijiGrammarParser.FieldDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#fieldDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitFieldDeclaration(BaijiGrammarParser.FieldDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#type}.
     *
     * @param ctx the parse tree
     */
    void enterType(BaijiGrammarParser.TypeContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#type}.
     *
     * @param ctx the parse tree
     */
    void exitType(BaijiGrammarParser.TypeContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#serviceDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterServiceDeclaration(BaijiGrammarParser.ServiceDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#serviceDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitServiceDeclaration(BaijiGrammarParser.ServiceDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link BaijiGrammarParser#methodDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterMethodDeclaration(BaijiGrammarParser.MethodDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link BaijiGrammarParser#methodDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitMethodDeclaration(BaijiGrammarParser.MethodDeclarationContext ctx);
}