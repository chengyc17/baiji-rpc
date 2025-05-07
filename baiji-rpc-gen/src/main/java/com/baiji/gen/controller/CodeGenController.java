package com.baiji.gen.controller;

import com.baiji.gen.grammar.GrammarParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/baiji/generator")
@ResponseBody
public class CodeGenController {
    @Autowired
    private GrammarParser grammarParser;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("请选择文件上传");
        }

        try {
            // 读取文件内容为字符串
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);

            grammarParser.parser(content);

            return ResponseEntity.ok("文件内容: " + content);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("文件处理失败: " + e.getMessage());
        }
    }
}
