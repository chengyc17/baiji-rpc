package com.baiji.gen.grammar;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.common.util.StringUtils;
import com.baiji.gen.antlr.BaijiGrammarLexer;
import com.baiji.gen.antlr.BaijiGrammarParser;
import com.baiji.spi.DeployInfo;
import com.baiji.spi.LangHandler;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ServiceLoader;


@Component
public class GrammarParser {

    public BaijiGrammarDefinition parser(String content) throws IOException {
//        String input = CharStreams.fromFileName(path).toString();
//        BaijiGrammarLexer lexer = new BaijiGrammarLexer(CharStreams.fromString(input));
        BaijiGrammarLexer lexer = new BaijiGrammarLexer(CharStreams.fromString(content));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BaijiGrammarParser parser = new BaijiGrammarParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomParserErrorListener());
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomBaijiBrammarListener customBaijiBrammarListener = new CustomBaijiBrammarListener();
        walker.walk(customBaijiBrammarListener, tree);
        return customBaijiBrammarListener.getGrammarDefinition();
    }

    public void codeGenerate(BaijiGrammarDefinition definition, String targetLang, DeployInfo deployInfo) throws Exception {
        ServiceLoader<LangHandler> langHandlers = ServiceLoader.load(LangHandler.class);
        boolean support = false;
        for (LangHandler langHandler : langHandlers) {
            if (!StringUtils.equalsIgnoreCase(targetLang, langHandler.language())) {
                continue;
            }
            support = true;
            langHandler.generate(definition);
            langHandler.deploy(deployInfo);
        }
        if (!support) {
            throw new IllegalArgumentException("不支持的语言类型");
        }
    }

    public void codeGenerate(String content, String targetLang, DeployInfo deployInfo) throws Exception {
        BaijiGrammarDefinition parser = parser(content);
        codeGenerate(parser, targetLang, deployInfo);
    }
}
