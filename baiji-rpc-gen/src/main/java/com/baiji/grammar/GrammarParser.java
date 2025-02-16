package com.baiji.grammar;

import com.baiji.antlr.BaijiGrammarLexer;
import com.baiji.antlr.BaijiGrammarParser;
import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.common.util.StringUtils;
import com.baiji.spi.DeployInfo;
import com.baiji.spi.LangHandler;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.ServiceLoader;

public class GrammarParser {

    public BaijiGrammarDefinition parser(String content) throws IOException {
//        String input = CharStreams.fromFileName(path).toString();
//        BaijiGrammarLexer lexer = new BaijiGrammarLexer(CharStreams.fromString(input));
        BaijiGrammarLexer lexer = new BaijiGrammarLexer(CharStreams.fromString(content));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BaijiGrammarParser parser = new BaijiGrammarParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomBaijiBrammarListener customBaijiBrammarListener = new CustomBaijiBrammarListener();
        walker.walk(customBaijiBrammarListener, tree);
        return customBaijiBrammarListener.getGrammarDefinition();
    }

    public void codeGenerate(BaijiGrammarDefinition definition, String targetLang, String generateCodeLocation, DeployInfo deployInfo) throws Exception {
        ServiceLoader<LangHandler> langHandlers = ServiceLoader.load(LangHandler.class);
        for (LangHandler langHandler : langHandlers) {
            if (!StringUtils.equalsIgnoreCase(targetLang, langHandler.language())) {
                continue;
            }
            langHandler.generate(definition);
            langHandler.deploy(generateCodeLocation, deployInfo);
        }
        throw new IllegalArgumentException("不支持的语言类型");
    }
}
