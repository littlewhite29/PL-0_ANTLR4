import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * 语法分析器
 */
public class Parser {

    private static int id = 1;

    // 当前的token
    private List<token> inputTokens;
    private int currentTokenIndex;
    private token token;

    // 符号表
    private SymbolTable symbolTable;

    private BufferedReader bufferedReader;

    private static ArrayList<Code> codeList = new ArrayList<Code>();  // 生成的output list，即为作业中的code数组

    private List<Quaternion> quaternions = new ArrayList<>();
    private Quaternion tempQuaternion;
    public List<Quaternion> getQuaternions(){
        return quaternions;
    }

    // 新增一个变量，用于生成临时变量的计数
    private static int tempVarCount = 1;

    // 新增方法，用于获取一个新的临时变量名
    private String getTempVar() {
        return "T" + tempVarCount++;
    }

    public static void create_table() throws Exception {
        Code code1 = new Code(id,"hlt","_","_","_");
        codeList.add(code1);
        // 检查output文件夹是否存在
        Path path = Paths.get("output");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        PrintWriter writer = new PrintWriter("output/P-code.txt");
        for (Code code : codeList) {
            writer.println(code.toString());
        }
        writer.close();
    }

    /**
     * 构造函数
     * 
     */
    public Parser(List<token> tokens) {
        this.inputTokens = tokens;
        this.currentTokenIndex = 0;
        this.token=inputTokens.get(currentTokenIndex);

        symbolTable = new SymbolTable();
    }

    /**
     * 从输入流中获取下一个token，并保存在成员变量token中
     */
    private void getNextToken() {
        this.token=inputTokens.get(currentTokenIndex++);
    }

    /**
     * 开始语法分析
     */
    public void startParse() {
        programParser();
    }

    /**
     * 1.<程序> → <程序首部> <分程序>
     */
    private void programParser() {

        // 程序首部
        getNextToken();
        if (token.getTokenEnum() != tokenEnum.PROGRAM) {
           System.out.println("程序首部缺少关键字PROGRAM");
           System.exit(0);
        }
        //2.<程序首部> → PROGRAM <标识符>
        getNextToken();
        if (token.getTokenEnum() != tokenEnum.IDENTIFIER) {
            System.out.println("程序首部缺少标识符");
        }

        // 分程序
        subProgramParser();

    }

    /**
     * 3.<分程序> → [<常量说明>] [<变量说明>] <语句>
     */
    private void subProgramParser() {
        getNextToken();
        if (token.getTokenEnum() == tokenEnum.CONST) {
            constDeclareParser();
            getNextToken();
        }
        if (token.getTokenEnum() == tokenEnum.VAR) {
            varDeclareParser();
            getNextToken();
        }

        statementParser();

    }


    /**
     * 4.<常量说明> → CONST <常量定义> <更多常量定义> ;
     * 5.<更多常量定义> → ε | , <常量定义> <更多常量定义>
     */
    private void constDeclareParser() {
        constDefineParser();
        getNextToken();
        while (true) {
            // 多个逗号分隔的定义
            if (token.getTokenEnum() == tokenEnum.COMMA) {
                constDefineParser();
                getNextToken();
            }
            else break;
        }
        if (token.getTokenEnum() != tokenEnum.SEMICOLON) {
            System.out.println("常量说明格式错误，缺少分号");
        }
    }


    /**
     * 6.<常量定义> → <标识符> := <无符号整数>
     */
    private void constDefineParser() {
        getNextToken();
        if (token.getTokenEnum() != tokenEnum.IDENTIFIER) {
            System.out.println("常量定义格式错误，缺少常量名");
        }
        String name = token.getTokenValue();
        // TODO:语义处理
        Declaration constDeclaration = new Declaration(name, "CONSTANT", "", DeclarationTableParser.getLevel()+"", DeclarationTableParser.getAdr()+"");
        Code code = new Code(id,"=", "_", "_", name);

        getNextToken();
        if (token.getTokenEnum() != tokenEnum.EQUAL) {
            System.out.println("常量定义格式错误，缺少等号");
        }

        getNextToken();
        String valueStr = token.getTokenValue();
        int value = Integer.parseInt(valueStr);
        if (token.getTokenEnum() != tokenEnum.INTEGER) {
            System.out.println("常量定义格式错误，缺少常量值");
        }
        else{
            constDeclaration.setVal(valueStr);
            code.setArg1(valueStr);
        }
        // TODO:语义处理
        DeclarationTableParser.add_declaration(constDeclaration);
        codeList.add(code);
        id++;
    }


    /**
     * 9. <变量说明> => VAR<标识符><更多标识符>
     */
    private void varDeclareParser() {
        getNextToken();
        if (token.getTokenEnum() != tokenEnum.IDENTIFIER) {
            System.out.println("变量说明格式错误，缺少变量名");
        }
        String name = token.getTokenValue();
        // TODO:语义处理
        Declaration varDeclaration = new Declaration(name, "VARIABLE", "", DeclarationTableParser.getLevel()+"", DeclarationTableParser.getAdr()+"");
        Code code = new Code(id,"=", "_", "_", name);

        getNextToken();
        while (true) {
            // 多个逗号分隔的定义
            if (token.getTokenEnum() == tokenEnum.COMMA) {
                DeclarationTableParser.add_declaration(varDeclaration);
                getNextToken();
                if (token.getTokenEnum() != tokenEnum.IDENTIFIER) {
                    System.out.println("变量说明格式错误，缺少变量名");
                }
                String name2 = token.getTokenValue();
                // TODO:语义处理
                //11.<标识符> → <字母> <更多字母和数字>
                varDeclaration = new Declaration(name2, "VARIABLE", "", DeclarationTableParser.getLevel()+"", DeclarationTableParser.getAdr()+"");
                code = new Code(id,"=", "_", "_", name2);
                getNextToken();
            }
            else if(token.getTokenEnum() == tokenEnum.EQUAL)
            {
                getNextToken();
                String valueStr = token.getTokenValue();
                int value = Integer.parseInt(valueStr);
                if (token.getTokenEnum() != tokenEnum.INTEGER) {
                    System.out.println("变量定义格式错误，缺少变量值");
                }
                else{
                    varDeclaration.setVal(valueStr);
                    code.setArg1(valueStr);
                    codeList.add(code);
                    id++;
                    getNextToken();
                }
            }
            else {
                DeclarationTableParser.add_declaration(varDeclaration);
                break;
            }
        }
        if (token.getTokenEnum() != tokenEnum.SEMICOLON) {
            System.out.println("变量说明格式错误，缺少分号");
        }
    }

    /**
     * 	<语句> => <赋值语句>|<条件语句>|<循环语句>|<复合语句>|<空语句>
     */
    private void statementParser() {
        switch (token.getTokenEnum()) {
            // 赋值语句
            case IDENTIFIER:
                assignStatParser();
                break;
                
            // 条件语句
            case IF:
                ifStatParser();
                break;

            // 循环语句
            case WHILE:
                whileStatParser();
                break;

            // 复合语句
            case BEGIN:
                compoundStatParser();
                break;

            case SEMICOLON:
            case END:
                break;

            default:
                System.out.println("语句类型错误");
        }
    }


    /**
     * <赋值语句> => <标识符> := <表达式>
     */
    private void assignStatParser() {
        String name = token.getTokenValue();

        getNextToken();
        if (token.getTokenEnum() != tokenEnum.ASSIGN) {
            System.out.println("赋值语句格式错误");
        }

        // 存储目标变量名
        String targetVar = name;

        // 解析表达式
        String resultVar = expressionParser();

        // 生成赋值的四元式
        Code code = new Code(id, ":=", resultVar, "_", targetVar);
        codeList.add(code);
        id++;
    }

    /**
     * <表达式> => [+|-]项|<表达式><加法运算符><项>
     */
    private String expressionParser() {
        getNextToken();
        String resultVar = itemParser(); // 处理项
        while (token.getTokenEnum() == tokenEnum.PLUS || token.getTokenEnum() == tokenEnum.MINUS) {
            // 保存运算符
            tokenEnum operator = token.getTokenEnum();
            getNextToken();

            // 处理下一个项
            String termResult = itemParser();

            // 生成临时变量作为表达式结果
            String tempVar = getTempVar();

            // 根据运算符生成相应的四元式
            if (operator == tokenEnum.PLUS) {
                Code code = new Code(id, "+", resultVar, termResult, tempVar);
                codeList.add(code);
                id++;
            } else if (operator == tokenEnum.MINUS) {
                Code code = new Code(id, "-", resultVar, termResult, tempVar);
                codeList.add(code);
                id++;
            }

            // 更新结果变量为新生成的临时变量
            resultVar = tempVar;
        }

        return resultVar;
    }

    /**
     * <项> => <因子>|<项><乘法运算符><因子>
     */
    private String itemParser() {
        String resultVar = factorParser(); // 处理因子
        getNextToken();
        while (true) {
            // 多个因子的情况
            if (token.getTokenEnum() == tokenEnum.MULTIPLY || token.getTokenEnum() == tokenEnum.DIVIDE) {
                // TODO:语义处理
                // 保存运算符
                tokenEnum operator = token.getTokenEnum();
                getNextToken();

                // 处理下一个因子
                String factorResult = factorParser();

                // 生成临时变量作为项的结果
                String tempVar = getTempVar();

                // 根据运算符生成相应的四元式
                if (operator == tokenEnum.MULTIPLY) {
                    Code code = new Code(id, "*", resultVar, factorResult, tempVar);
                    codeList.add(code);
                    id++;
                } else if (operator == tokenEnum.DIVIDE) {
                    Code code = new Code(id, "/", resultVar, factorResult, tempVar);
                    codeList.add(code);
                    id++;
                }

                // 更新结果变量为新生成的临时变量
                resultVar = tempVar;

                getNextToken();
            } else
                break;
        }
        return resultVar;
    }

    /**
     * <因子> => <标识符>|<无符号整数>|(<表达式>)
     */
    private String factorParser() {
        String resultVar = ""; // 用于存储因子的结果
        // 标识符
        if (token.getTokenEnum() == tokenEnum.IDENTIFIER) {
            // TODO:语义处理
            // 获取标识符名称
            String identifier = token.getTokenValue();
            resultVar = identifier;
        }
        // 数字
        else if (token.getTokenEnum() == tokenEnum.INTEGER) {
            // TODO:语义处理
            // 获取数字的值
            String number = token.getTokenValue();
            resultVar = number;
        }
        // 表达式
        else if (token.getTokenEnum() == tokenEnum.LPAREN) {
            // 处理表达式
            resultVar = expressionParser();

            if (token.getTokenEnum() != tokenEnum.RPAREN) {
                System.out.println("表达式缺少右括号");
            }

        }
        else {
            System.out.println("表达式缺少因子或因子格式错误");
        }
        return resultVar;
    }


    /**
     * <条件语句> => IF <条件> THEN <语句>
     */
    private void ifStatParser() {
        int idBeforeCondition = id; // 记录条件前的标识符生成器的值
        conditionParser(); // 处理条件

        if (token.getTokenEnum() != tokenEnum.THEN) {
            System.out.println("条件语句缺少THEN");
        }
        int conditionJumpId = id; // 记录条件跳转的标识符生成器的值
        // 生成条件为假时的跳转语句，条件跳转的目标标识符暂时设置为0
        Code code = new Code(id, "j", "_", "_", "0");
        codeList.add(code);
        id++;
        getNextToken();
        statementParser();

        // 更新条件跳转的目标标识符为当前标识符生成器的值
        codeList.get(conditionJumpId - 1).setResult(String.valueOf(id));

        // 回填条件跳转的目标标识符
        backPatch(id,idBeforeCondition);
    }

    /**
     * <条件> => <表达式><关系运算符><表达式>
     */
    private void conditionParser() {
        // 解析左侧表达式
        String leftExprResult = expressionParser();
        // 获取关系运算符
        String relationOperator = token.getTokenValue();
        switch (token.getTokenEnum()) {
            case EQUAL:
            case NOT_EQUAL:
            case LESS_THAN:
            case LESS_THAN_OR_EQUAL:
            case GREATER_THAN:
            case GREATER_THAN_OR_EQUAL:
                break;

            default:
                System.out.println("条件语句缺少关系运算符");
                break;
        }
        // 解析右侧表达式
        String rightExprResult = expressionParser();
        // 生成比较关系的四元式
        String s = String.valueOf(id+2);
        Code conditionCode = new Code(id, "j"+relationOperator, leftExprResult, rightExprResult, s);
        codeList.add(conditionCode);
        id++;
    }


    /**
     * <循环语句> => WHILE <条件> DO <语句>
     */
    private void whileStatParser() {
        int startLabel = id; // 记录循环开始的标签
        // 解析循环条件，得到条件的临时变量
        conditionParser();

        if (token.getTokenEnum() != tokenEnum.DO) {
            System.out.println("循环语句缺少DO");
        }

        int conditionJumpId = id; // 记录条件跳转的标识符生成器的值
        // 生成条件为假时跳出循环的四元式，标识符暂时设置为0
        Code exitCode = new Code(id, "j", "_", "_", "0");
        codeList.add(exitCode);
        id++;
        getNextToken();
        statementParser();

        // 生成跳回循环开始的四元式
        Code jumpBackCode = new Code(id, "j", "_", "_", String.valueOf(startLabel));
        codeList.add(jumpBackCode);
        id++;

        // 更新条件跳转的目标标识符为当前标识符生成器的值
        codeList.get(conditionJumpId - 1).setResult(String.valueOf(id));
        backPatch(id,startLabel);
    }


    /**
     * <复合语句> => BEGIN <语句>{;<语句>}END
     */
    private void compoundStatParser() {
        getNextToken();
        statementParser();
        while (true) {
            // 分号
            if (token.getTokenEnum() == tokenEnum.SEMICOLON) {
                getNextToken();
                statementParser();
            }
            // END
            else if (token.getTokenEnum() == tokenEnum.END) {
                break;
            }
            // 多个语句
            else {
                getNextToken();
                if (token.getTokenEnum() == tokenEnum.SEMICOLON) {
                    getNextToken();
                    statementParser();
                }
                else if (token.getTokenEnum() == tokenEnum.END) {
                    break;
                }
                else {
                    System.out.println("复合语句格式错误");
                }
            }
        }
    }

    // 回填方法，将目标标识符填充为当前标识符生成器的值
    private void backPatch(int id, int targetId) {
        for (int i = id; i < codeList.size(); i++) {
            Code code = codeList.get(i);
            if (code.getResult().equals(String.valueOf(targetId))) {
                code.setResult(String.valueOf(id));
            }
        }
    }
}