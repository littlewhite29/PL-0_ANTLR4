grammar PL0;

// 词法规则
PROGRAM: 'PROGRAM';
BEGIN: 'BEGIN';
END: 'END';
CONST: 'CONST';
VAR: 'VAR';
WHILE: 'WHILE';
DO: 'DO';
IF: 'IF';
THEN: 'THEN';
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
INTEGER: [0-9]+;
ASSIGN: ':=';
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
EQUAL: '=';
NOT_EQUAL: '<>';
LESS_THAN: '<';
LESS_THAN_OR_EQUAL: '<=';
GREATER_THAN: '>';
GREATER_THAN_OR_EQUAL: '>=';
LPAREN: '(';
RPAREN: ')';
SEMICOLON: ';';
COMMA: ',';
WS: [ \t\n\r]+ -> skip; // 跳过空格、制表符和换行符

// 语法规则
program: programHeader subroutine EOF; // 添加EOF来表示输入结束
programHeader: PROGRAM IDENTIFIER;
subroutine: (constantDeclaration)? (variableDeclaration)? statement EOF; // 添加EOF来表示输入结束
constantDeclaration: CONST constantDefinition (COMMA constantDefinition)* SEMICOLON;
constantDefinition: IDENTIFIER ASSIGN INTEGER;
variableDeclaration: VAR IDENTIFIER (COMMA IDENTIFIER)* SEMICOLON;
compoundStatement: BEGIN statementList END;
statementList: statement (SEMICOLON statement)* ;
statement: assignmentStatement | conditionStatement | loopStatement | compoundStatement| emptyStatement;
assignmentStatement: IDENTIFIER ASSIGN expression ;
expression: term (additionOperator term)*;
term: factor (multiplicationOperator factor)*;
factor: IDENTIFIER | INTEGER | LPAREN expression RPAREN;
additionOperator: PLUS | MINUS;
multiplicationOperator: MULTIPLY | DIVIDE;
conditionStatement: IF condition THEN statement;
loopStatement: WHILE condition DO statement ;
condition: expression relationOperator expression;
relationOperator: EQUAL | NOT_EQUAL | LESS_THAN | LESS_THAN_OR_EQUAL | GREATER_THAN | GREATER_THAN_OR_EQUAL;
emptyStatement: SEMICOLON; // 空语句不需要做任何操作，所以这里只是用分号表示。