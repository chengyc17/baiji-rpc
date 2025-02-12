grammar BaijiGrammar;

// 解析器规则
program: syntaxDeclaration packageDeclaration appIdDeclaration classDeclaration* serviceDeclaration;

syntaxDeclaration: 'syntax' '=' STRING_LITERAL ';';
packageDeclaration: 'package' IDENTIFIER ';';
appIdDeclaration: 'appid' '=' INTEGER_LITERAL ';';

classDeclaration: 'class' IDENTIFIER '{' fieldDeclaration* '}';
fieldDeclaration: (SINGLE_LINE_COMMENT | MULTI_LINE_COMMENT)? type IDENTIFIER ';';

type: 'string'
    | 'int16'
    | 'int32'
    | 'float'
    | 'double'
    | 'boolean'
    | 'Map' '<' type ',' type '>'
    | 'List' '<' type '>'
    | IDENTIFIER
    ;

serviceDeclaration: 'service' IDENTIFIER '{' methodDeclaration* '}';
methodDeclaration: type IDENTIFIER '(' type IDENTIFIER ')' ';';

// 词法规则
SINGLE_LINE_COMMENT: '//' ~[\r\n]* -> skip;
MULTI_LINE_COMMENT: '/*' .*? '*/' -> skip;

STRING_LITERAL: '"' ~'"'* '"';
INTEGER_LITERAL: [0-9]+;
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
WS: [ \t\r\n]+ -> skip;