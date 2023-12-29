grammar antlr_test;

// 定义一个名为 "program" 的解析规则
program
    : 'program' IDENTIFIER ('var' declarations)? 'begin' statements 'end' WS? EOF
    ;

// 定义一个名为 "declarations" 的解析规则
declarations
    : (IDENTIFIER ':=' expression ';')*(WS)*
    ;

// 定义一个名为 "statements" 的解析规则
statements
    : statement* (WS)*
    ;

// 定义一个名为 "statement" 的解析规则
statement
    : (expression | declarations) ';' (WS)*
    ;

// 定义一个名为 "expression" 的解析规则
expression
    : primary (('+' | '-') primary)*
    ;

// 定义一个名为 "primary" 的解析规则
primary
    : INTEGER | IDENTIFIER (WS)*
    ;

// 定义一个名为 "IDENTIFIER" 的词法规则
IDENTIFIER : [a-zA-Z]+ ; // 匹配字母组成的标识符

// 定义一个名为 "INTEGER" 的词法规则
INTEGER : [0-9]+ ; // 匹配整数

// 定义一个名为 "SEMI" 的词法规则
SEMI : ';' ; // 匹配分号字符
WS : [ \t\n\r]+ -> skip ; // 跳过空格、制表符、换行符和回车符
