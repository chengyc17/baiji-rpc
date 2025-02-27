package com.baiji.gen.java;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.common.grammar.definition.ClassInfoDefinition;
import com.baiji.common.grammar.definition.FieldInfoDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaCodeGenTest {
    private BaijiGrammarDefinition definition = null;

    @BeforeAll
    public void before() {
        definition = new BaijiGrammarDefinition();

        definition.setAppid(100007343);
        definition.setSyntax("v1.0");
        definition.setPackageName("com.baiji.gen.demo");

        ClassInfoDefinition classInfoDefinition = new ClassInfoDefinition();
        classInfoDefinition.setClsName("SayHelloRequest");

        List<FieldInfoDefinition> fieldInfos = new ArrayList<>();
        FieldInfoDefinition fieldInfoDefinition = new FieldInfoDefinition();
        fieldInfoDefinition.setFieldName("myInt");
        fieldInfoDefinition.setDataType("int32");
        fieldInfoDefinition.setComment("just a test");

        FieldInfoDefinition fieldInfoDefinition2 = new FieldInfoDefinition();
        fieldInfoDefinition2.setFieldName("myString");
        fieldInfoDefinition2.setDataType("string");
        fieldInfoDefinition2.setComment("just a test2");

        fieldInfos.add(fieldInfoDefinition);
        fieldInfos.add(fieldInfoDefinition2);

        classInfoDefinition.setFieldInfos(fieldInfos);
        List<ClassInfoDefinition> clsInfos = new ArrayList<>();
        clsInfos.add(classInfoDefinition);
        definition.setClsInfo(clsInfos);
    }

    @Test
    public void testGenerate() {
        JavaCodeGen javaCodeGen = new JavaCodeGen();

//        javaCodeGen.generate();
    }

}