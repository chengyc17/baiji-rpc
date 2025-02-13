package com.baiji.grammar;

import com.baiji.antlr.BaijiGrammarLexer;
import com.baiji.antlr.BaijiGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class GrammarParser {

    //    public BaijiGrammarDefinition parser(String path) throws IOException {
    public void parser(String path) throws IOException {
        String input = CharStreams.fromFileName(path).toString();
        BaijiGrammarLexer lexer = new BaijiGrammarLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BaijiGrammarParser parser = new BaijiGrammarParser(tokens);
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(new CustomBaijiBrammarListener(), tree);

//        System.out.println(tree.toStringTree(parser));
    }

    public static void main(String[] args) throws IOException {
        GrammarParser parser = new GrammarParser();

        String path = GrammarParser.class.getClassLoader().getResource("contract.idl").getPath();

        String path1 = "D:/IdeaProject/baiji-rpc/baiji-rpc-gen/target/classes/contract.idl";
        parser.parser(path1);
    }
}
