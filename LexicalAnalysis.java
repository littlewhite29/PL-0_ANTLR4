import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LexicalAnalysis {
    // 定义 NFA 的状态
    private enum State {
        q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24, q25, q26,
        q_program, q_begin, q_end, q_const, q_var, q_while, q_do, q_if, q_then, q_identifier, q_integer, q_operator_delimiter, q_assign, q_lt, q_gt, q_ADD
,q_MINUS,q_MUL,q_DIVIDE,q_EQUAL,q_NOT_EQUAL,q_LESS_EQUAL,q_GREATER_EQUAL,q_LPAREN,q_RPAREN,q_SEMICOLON,q_COMMA}

    // 用于记录当前的状态
    private State currentState;

    // 初始化
    public LexicalAnalysis() {
        this.currentState = State.q0;
    }

    // 处理输入字符
    private void processInput(char inputChar) {
        currentState = getNextState(currentState, inputChar);
    }

    // 获取下一个状态
    private State getNextState(State currentState, char inputChar) {
        switch (currentState) {
            case q0:
            if (Character.isLetter(inputChar)) {
                switch (inputChar) {
                    case 'P':
                        return State.q1;
                    case 'B':
                        return State.q4;
                    case 'E':
                        return State.q8;
                    case 'C':
                        return State.q10;
                    case 'V':
                        return State.q14;
                    case 'W':
                        return State.q16;
                    case 'D':
                        return State.q20;
                    case 'I':
                        return State.q22;
                    case 'T':
                        return State.q24;
                    default:
                        return State.q_identifier;
                }
            } else if (Character.isDigit(inputChar)) {
                return State.q_integer;
            } else {
                switch (inputChar) {
                    case '+':
                    return State.q_ADD;
                    case '-':
                    return State.q_MINUS;
                    case '*':
                    return State.q_MUL;
                    case '/':
                    return State.q_DIVIDE;
                    case '=':
                    return State.q_EQUAL;
                    case '<':
                    return State.q_lt;
                    case '>':
                    return State.q_gt;
                    case '(':
                    return State.q_LPAREN;
                    case ')':
                    return State.q_RPAREN;
                    case ';':
                    return State.q_SEMICOLON;
                    case ',':
                        return State.q_COMMA;
                    case ':':
                        return State.q_assign;
                    case '\n':
                    case '\r':
                    case '\t':
                    case ' ':
                        return State.q0;  // Ignore white space characters
                    default:
                        return State.q0;  // Return to initial state for other characters (adjust as needed)
                }
            }

    case q1:
    // 处理状态 q1 的转移
    if (inputChar == 'R') {
        return State.q2;
    } else {
        return State.q_identifier;  // 如果不是 'R'，则进入标识符状态
    }
    case q2:
    // 处理状态 q2 的转移
    if (inputChar == 'O') {
        return State.q3;
    } else {
        return State.q_identifier;  // 如果不是 'O'，则进入标识符状态
    }

case q3:
    // 处理状态 q3 的转移
    if (inputChar == 'G') {
        return State.q_program;  // 'P', 'R', 'O', 'G' 连续出现，识别为 "PROGRAM"
    } else {
        return State.q_identifier;  // 如果不是 'G'，则进入标识符状态
    }

case q4:
    if (inputChar == 'E') {
        return State.q5;
    } else {
        return State.q_identifier;
    }
    case q5:
    // 处理状态 q5 的转移
    if (inputChar == 'G') {
        return State.q6;
    } else {
        return State.q_identifier;
    }

case q6:
    // 处理状态 q6 的转移
    if (inputChar == 'I') {
        return State.q7;
    } else {
        return State.q_identifier;
    }

case q7:
    // 处理状态 q7 的转移
    if (inputChar == 'N') {
        return State.q_begin;  // 'B', 'E', 'G', 'I', 'N' 连续出现，识别为 "BEGIN"
    } else {
        return State.q_identifier;  // 如果不是 'N'，则进入标识符状态
    }
case q8:
    if (inputChar == 'N') {
        return State.q9;
    } else {
        return State.q_identifier;
    }

case q9:
    // 处理状态 q9 的转移
    if (inputChar == 'D') {
        return State.q_end;  // 'E', 'N', 'D' 连续出现，识别为 "END"
    } else {
        return State.q_identifier;  // 如果不是 'D'，则进入标识符状态
    }

case q10:
    // 处理状态 q10 的转移
    if (inputChar == 'O') {
        return State.q11;
    } else {
        return State.q_identifier;
    }

case q11:
    // 处理状态 q11 的转移
    if (inputChar == 'N') {
        return State.q12;
    } else {
        return State.q_identifier;
    }

case q12:
    // 处理状态 q12 的转移
    if (inputChar == 'S') {
        return State.q13;
    } else {
        return State.q_identifier;
    }

case q13:
    // 处理状态 q13 的转移
    if (inputChar == 'T') {
        return State.q_const;  // 'C', 'O', 'N', 'S', 'T' 连续出现，识别为 "CONST"
    } else {
        return State.q_identifier;  // 如果不是 'T'，则进入标识符状态
    }

case q14:
    // 处理状态 q14 的转移
    if (inputChar == 'A') {
        return State.q15;
    } else {
        return State.q_identifier;
    }

case q15:
    // 处理状态 q15 的转移
    if (inputChar == 'R') {
        return State.q_var;  // 'V', 'A', 'R' 连续出现，识别为 "VAR"
    } else {
        return State.q_identifier;  // 如果不是 'R'，则进入标识符状态
    }


case q16:
    // 处理状态 q16 的转移
    if (inputChar == 'H') {
        return State.q17;
    } else {
        return State.q_identifier;
    }

case q17:
    // 处理状态 q17 的转移
    if (inputChar == 'I') {
        return State.q18;
    } else {
        return State.q_identifier;
    }

case q18:
    // 处理状态 q18 的转移
    if (inputChar == 'L') {
        return State.q19;
    } else {
        return State.q_identifier;
    }

case q19:
    // 处理状态 q19 的转移
    if (inputChar == 'E') {
        return State.q_while;  // 'W', 'H', 'I', 'L', 'E' 连续出现，识别为 "WHILE"
    } else {
        return State.q_identifier;  // 如果不是 'E'，则进入标识符状态
    }

case q20:
    // 处理状态 q20 的转移
    if (inputChar == 'O') {
        return State.q_do;
    }else {
        return State.q_identifier;
    }
    

case q22:
    // 处理状态 q22 的转移
    if (inputChar == 'F') {
        return State.q_if;
    }else {
        return State.q_identifier;
    }

    case q24:
    // 处理状态 q24 的转移
    if (inputChar == 'H') {
        return State.q25;
    } else {
        return State.q_identifier;
    }

case q25:
    // 处理状态 q25 的转移
    if (inputChar == 'E') {
        return State.q26;
    } else {
        return State.q_identifier;
    }
// 一直到 q26
case q26:
    // 处理状态 q26 的转移
    if (inputChar == 'N') {
        return State.q_then;
    } else {
        return State.q_identifier;
    }

                
case q_identifier:
            // 处理标识符状态的转移
            if (Character.isLetterOrDigit(inputChar)) {
                return State.q_identifier;
            } else {
     return currentState;
            }
            
            case q_integer:
    // 处理整数状态的转移
    if (Character.isDigit(inputChar)) {
        return State.q_integer;  // 数字继续，保持在整数状态
    } else {
       return currentState;
    }

    case q_assign:
    // 处理：=赋值符号状态的转移
    if (inputChar == '=') {
        return State.q_assign;  // 赋值符号结束，转到操作符或分隔符状态
    } else {
         return currentState;
    }

    case q_EQUAL:
    // 处理==符号状态的转移
    if (inputChar == '=') {
        return State.q_EQUAL;  // 赋值符号结束，转到操作符或分隔符状态
    } else {
         return currentState;
    }

    case q_lt:
    // 处理小于号状态的转移
    if (inputChar == '>') {
        return State.q_NOT_EQUAL;  // 小于号后跟着大于号，转到操作符或分隔符状态
    } else if (inputChar == '=') {
        return State.q_LESS_EQUAL;  // 小于号后跟着等于号，转到操作符或分隔符状态
    } else {
        return State.q_lt;  // 其他字符结束，转到操作符或分隔符状态
    }
case q_gt:
    // 处理大于号状态的转移
    if (inputChar == '=') {
        return State.q_GREATER_EQUAL;  // 大于号后跟着等于号，转到操作符或分隔符状态
    } else {
        return State.q_gt;  // 其他字符结束，转到操作符或分隔符状态
    }
            default:
                return currentState;
        }
    }

    // 词法分析主方法
    public void analyze() {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int charCode;
            while ((charCode = reader.read()) != -1) {
                char inputChar = (char) charCode;
                processInput(inputChar);
            }

            // 根据最终状态生成输出
            String tokenType = getTokenType(currentState);
            writer.write(String.format("(%s,)%n", tokenType));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取标识符类型
    private String getTokenType(State state) {
        switch (state) {
            case q_program:
                return "PROGRAM";
            case q_begin:
                return "BEGIN";
            case q_end:
                return "END";
            case q_const:
                return "CONST";
            case q_var:
                return "VAR";
            case q_while:
                return "WHILE";
            case q_do:
                return "DO";
            case q_if:
                return "IF";
            case q_then:
                return "THEN";
            case q_identifier:
                return "IDENT";
            case q_integer:
                return "NUMBER";
            case q_operator_delimiter:
                return "OPERATOR_DELIMITER";
                case q_ADD:
                return "ADD";
                  case q_MINUS:
                return "MINUS";
                  case q_MUL:
                return "MUL";
                   case q_DIVIDE:
                return "DIVIDE";
                   case q_EQUAL:
                return "EQUAL";
                   case q_NOT_EQUAL:
                return "NOT_EQUAL";
                    case q_LESS_EQUAL:
                return "LESS_EQUAL";
                    case q_GREATER_EQUAL:
                return "GREATER_EQUAL";
            case q_assign:
                return "ASSIGN";
            case q_lt:
                return "LESS_THAN";
            case q_gt:
                return "GREATER_THAN";
                 case q_LPAREN:
                return "LPAREN";
                 case q_RPAREN:
                return "RPAREN";
                 case q_SEMICOLON:
                return "SEMICOLON";
            case q_COMMA:
                return "COMMA";
            default:
                return "UNKNOWN";
        }
    }

    // 入口函数
    public static void main(String[] args) {
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        lexicalAnalysis.analyze();
    }
}
