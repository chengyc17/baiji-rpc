package com.baiji.gen.grammar;

import com.baiji.spi.DeployInfo;
import org.junit.jupiter.api.Test;

public class GrammarParserTest {

    @Test
    public void testParser() throws Exception {
        GrammarParser parser = new GrammarParser();

        String content = "syntax = \"v1.0\";\n" +
                "package com.ecust;\n" +
                "appid = 1002;\n" +
                "\n" +
                "class HelloRequest{\n" +
                "//这是一个单行注释\n" +
                "    string name;\n" +
                "    int16 myInt16;\n" +
                "    int32 myInt32;\n" +
                "    /*这是一个多行注释*/\n" +
                "    float myFloat;\n" +
                "    double myDouble;\n" +
                "    boolean myBoolean;\n" +
                "    Map<int16,double> myMap;\n" +
                "    List<int32> myList;\n" +
                "    Hello2Request req;\n" +
                "}\n" +
                "\n" +
                "class HelloResponse{\n" +
                "    string name;\n" +
                "    int16 myInt16;\n" +
                "    int32 myInt32;\n" +
                "    float myFloat;\n" +
                "    double myDouble;\n" +
                "    boolean myBoolean;\n" +
                "    Map<int16,double> myMap;\n" +
                "    List<int32> myList;\n" +
                "}\n" +
                "\n" +
                "class Hello2Request{\n" +
                "    string name;\n" +
                "    int16 myInt16;\n" +
                "    int32 myInt32;\n" +
                "    float myFloat;\n" +
                "    double myDouble;\n" +
                "    boolean myBoolean;\n" +
                "    Map<int16,double> myMap;\n" +
                "    List<int32> myList;\n" +
                "}\n" +
                "\n" +
                "class Hello2Response{\n" +
                "    string name;\n" +
                "    int16 myInt16;\n" +
                "    int32 myInt32;\n" +
                "    float myFloat;\n" +
                "    double myDouble;\n" +
                "    boolean myBoolean;\n" +
                "    Map<int16,double> myMap;\n" +
                "    List<int32> myList;\n" +
                "    Hello2Request req;\n" +
                "}\n" +
                "\n" +
                "service Greeter{\n" +
                "    HelloResponse sayHello(HelloRequest request);\n" +
                "    Hello2Response sayHello2(Hello2Request request);\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n";

//        String input = CharStreams.fromFileName("classpath:contract.idl").toString();
//        BaijiGrammarLexer lexer = new BaijiGrammarLexer(CharStreams.fromString(input));

        DeployInfo deployInfo = new DeployInfo();
        deployInfo.setArtifactId("myproj");
        deployInfo.setGroupId("com.abc.efg");
        deployInfo.setVersion("1.2");
        parser.codeGenerate(content, "Java", deployInfo);
    }

}