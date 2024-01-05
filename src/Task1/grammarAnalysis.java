package Task1;

import Task2.Declaration;
import Task2.PL0Parser;
import Task2.Quaternion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LW
 * @date 2023/12/25 15:19
 */
public class grammarAnalysis {
    private lexicalAnalysis lexer;

    public grammarAnalysis(Task1.lexicalAnalysis lexicalAnalysis) {
        this.lexer = lexicalAnalysis;
    }

    private int tempVarCounter = 0;

    private java.lang.String newTempVar() {
        return "t" + tempVarCounter++;
    }



    private List<Task2.Declaration> Declaration = new ArrayList<>();


    private Boolean find_declaration(java.lang.String name) {
        for (int i = 0; i < Declaration.size(); i++) {
            if (Declaration.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private List<Quaternion> quaternions = new ArrayList<>();

    private Quaternion tempQuaternion;

    public List<Quaternion> getQuaternions(){
        return quaternions;
    }


    public String visitProgram() {



        visitProgramHeader();
        visitSubroutine();


        return null;
    }

    public String visitProgramHeader() {

        token token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.PROGRAM))
        {
            System.out.println("程序首部缺少PROGRAM");
            return null;
        }
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.IDENTIFIER))
        {
            System.out.println("程序首部缺少标识符");
            return null;
        }
        return null;
    }

    public String visitSubroutine() {

        switch (lexer.watchNextToken().getTokenEnum())
        {
            case CONST:
                visitConstantDeclaration();
                if (lexer.watchNextToken().getTokenEnum().equals(tokenEnum.SEMICOLON))
                {
                    lexer.getNextToken();
                }
            case VAR:
                visitVariableDeclaration();
                if (lexer.watchNextToken().getTokenEnum().equals(tokenEnum.SEMICOLON))
                {
                    lexer.getNextToken();
                }
            default:
                visitStatement();
        }
        return null;
    }


    public String visitConstantDeclaration() {


        token token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.CONST))
        {
            System.out.println("常量定义错误，缺少关键字CONST");
            return null;
        }
        visitConstantDefinition();
        return null;
    }


    public String visitConstantDefinition() {

        token token=lexer.getNextToken();
        String Identifier;
        Integer value;
        if(!token.getTokenEnum().equals(tokenEnum.IDENTIFIER))
        {
            System.out.println("常量定义错误，缺少标识符");
            return null;
        }
        Identifier=token.getTokenValue();
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.ASSIGN))
        {
            System.out.println("常量定义错误，缺少赋值符号");
            return null;
        }
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.INTEGER))
        {
            System.out.println("常量定义错误，缺少数字");
            return null;
        }
        value=Integer.parseInt(token.getTokenValue());
        Declaration.add(new Declaration(Identifier, "Constant", value));
        quaternions.add(new Quaternion(quaternions.size() + 1, ":=", value.toString(), "_", Identifier));
        while (lexer.watchNextToken().getTokenEnum().equals(tokenEnum.COMMA))
        {
            lexer.getNextToken();

            token=lexer.getNextToken();
            if(token.getTokenEnum().equals(tokenEnum.IDENTIFIER))
            {
                System.out.println("常量定义错误，缺少标识符");
                return null;
            }
            Identifier=token.getTokenValue();
            token=lexer.getNextToken();
            if (token.getTokenEnum().equals(tokenEnum.ASSIGN))
            {
                System.out.println("常量定义错误，缺少赋值符号");
                return null;
            }
            token=lexer.getNextToken();
            if (token.getTokenEnum().equals(tokenEnum.INTEGER))
            {
                System.out.println("常量定义错误，缺少数字");
                return null;
            }
            value=Integer.parseInt(token.getTokenValue());
            Declaration.add(new Declaration(Identifier, "Constant", value));
            quaternions.add(new Quaternion(quaternions.size() + 1, ":=", value.toString(), "_", Identifier));
        }
        return null;
    }


    public String visitVariableDeclaration() {

        token token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.VAR))
        {
            System.out.println("变量定义错误，缺少关键字VAR");
            return null;
        }
        String Identifier;
        token=lexer.getNextToken();
        if(!token.getTokenEnum().equals(tokenEnum.IDENTIFIER))
        {
            System.out.println("变量定义错误，缺少标识符");
            return null;
        }
        Identifier=token.getTokenValue();
        Declaration.add(new Declaration(Identifier, "Variable", 0));
        while (lexer.watchNextToken().getTokenEnum().equals(tokenEnum.COMMA))
        {
            lexer.getNextToken();
            token=lexer.getNextToken();
            if(!token.getTokenEnum().equals(tokenEnum.IDENTIFIER))
            {
                System.out.println("变量定义错误，缺少标识符");
                return null;
            }
            Identifier=token.getTokenValue();
            Declaration.add(new Declaration(Identifier, "Variable", 0));
        }
        return null;
    }

    public String visitCompoundStatement() {
        token token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.BEGIN))
        {
            System.out.println("复合语句格式错误，缺少BEGIN");
        }
        visitStatementList();
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.END))
        {
            System.out.println("复合语句格式错误，缺少END");
        }
        return null;
    }


    public String visitStatementList() {
        visitStatement();
        while (lexer.watchNextToken().getTokenEnum().equals(tokenEnum.SEMICOLON)){
            lexer.getNextToken();
            visitStatement();
        }

        return null;
    }


    public String visitStatement() {

        switch (lexer.watchNextToken().getTokenEnum()){
            case BEGIN:
                visitCompoundStatement();
                break;
            case IF:
                visitConditionStatement();
                break;
            case WHILE:
                visitLoopStatement();
                break;
            case IDENTIFIER:
                visitAssignmentStatement();
                break;
            case COMMA:
                visitEmptyStatement();
                break;
            default:
                System.out.println("非法的token类型:"+lexer.watchNextToken().getTokenEnum());
                return null;
        }

        return null;
    }


    public String visitAssignmentStatement() {

        token token=lexer.getNextToken();
        if(find_declaration(token.getTokenValue()))
        {
            tempQuaternion =new Quaternion();
            tempQuaternion.setResult(token.getTokenValue());
            token=lexer.getNextToken();
            if (!token.getTokenEnum().equals(tokenEnum.ASSIGN))
            {
                System.out.println("识别赋值语句时出现错误，应当是:=，实际是"+token.getTokenEnum());
                return null;
            }
            tempQuaternion.setOp(token.getTokenValue());
            String arg1=visitExpression();
            tempQuaternion.setArg1(arg1);
            tempQuaternion.setArg2("_");
            tempQuaternion.setId(quaternions.size()+1);
            quaternions.add(tempQuaternion);
            tempQuaternion =null;
        }
        else{
            System.out.println("变量" + token.getTokenValue() + "未声明");
        }
        return null;
    }


    public String visitExpression() {
        String left=visitTerm();
        if(!lexer.watchNextToken().getTokenEnum().equals(tokenEnum.PLUS)&&!lexer.watchNextToken().getTokenEnum().equals(tokenEnum.MINUS))
        {
            return left;
        }
        else {
            String result="";
            String right;
            String op;
            for (int i=0;lexer.watchNextToken().getTokenEnum().equals(tokenEnum.PLUS)||lexer.watchNextToken().getTokenEnum().equals(tokenEnum.MINUS);i++) {
                op=visitAdditionOperator();
                if(i>=1)
                {
                    left= result;
                }
                right=visitTerm();
                quaternions.add(new Quaternion(quaternions.size()+1,op,left,right,result=newTempVar()));
            }
            return result;
        }
    }


    public String visitTerm() {
        String left=visitFactor();
        if(!lexer.watchNextToken().getTokenEnum().equals(tokenEnum.MULTIPLY)&&!lexer.watchNextToken().getTokenEnum().equals(tokenEnum.DIVIDE))
        {
            return left;
        }
        else {
            String result="";
            String right;
            String op;
            for (int i=0;lexer.watchNextToken().getTokenEnum().equals(tokenEnum.MULTIPLY)||lexer.watchNextToken().getTokenEnum().equals(tokenEnum.DIVIDE);i++) {
                op=visitMultiplicationOperator();
                if(i>=1)
                {
                    left= result;
                }
                right=visitFactor();
                quaternions.add(new Quaternion(quaternions.size()+1,op,left,right,result=newTempVar()));
            }
            return result;
        }
    }


    public String visitFactor() {

        String toReturn;
        token token;
        switch (lexer.watchNextToken().getTokenEnum()){
            case LPAREN:
                lexer.getNextToken();
               toReturn=visitExpression();
               if (lexer.watchNextToken().getTokenEnum().equals(tokenEnum.RPAREN)) {
                   lexer.getNextToken();
               }
               else {
                   System.out.println("因子识别错误,应当是右括号");
                   return null;
               }
               return toReturn;
            case IDENTIFIER:
                token=lexer.getNextToken();
                if (find_declaration(token.getTokenValue())) {
                    return token.getTokenValue();
                }
                else {
                    System.out.println("变量" + token.getTokenValue() + "未声明");
                    return null;
                }
            case INTEGER:
                return lexer.getNextToken().getTokenValue();
            default:
                System.out.println("因子识别错误，非法的token类型:"+lexer.watchNextToken().getTokenEnum());

        }
        return null;
    }


    public String visitAdditionOperator() {

        token token=lexer.watchNextToken();
        if (token.getTokenEnum().equals(tokenEnum.PLUS)|| token.getTokenEnum().equals(tokenEnum.MINUS))
        {
            return lexer.getNextToken().getTokenValue();
        }
        else {
            System.out.println("识别加减算符时出现问题,非法的token类型:"+lexer.watchNextToken().getTokenEnum());
        }
        return null;
    }


    public String visitMultiplicationOperator() {
        token token=lexer.watchNextToken();
        if (token.getTokenEnum().equals(tokenEnum.MULTIPLY)|| token.getTokenEnum().equals(tokenEnum.DIVIDE))
        {
            return lexer.getNextToken().getTokenValue();
        }
        else {
            System.out.println("识别乘法算符时出现问题,非法的token类型:"+lexer.watchNextToken().getTokenEnum());
        }
        return null;
    }


    public String visitConditionStatement() {

        token token;
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.IF))
        {
            System.out.println("识别条件表达式时发生错误，非法的token类型"+token.getTokenEnum());
            return null;
        }
        visitCondition();
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.THEN))
        {
            System.out.println("识别条件表达式时发生错误，非法的token类型"+token.getTokenEnum());
            return null;
        }
        int backFillId= quaternions.size()+1;
        quaternions.add(new Quaternion(quaternions.size()+1,"j","_","_","0"));
        visitStatement();
        quaternions.get(backFillId-1).setResult(quaternions.size()+1+"");


        return null;
    }


    public String visitLoopStatement() {
        token token;
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.WHILE))
        {
            System.out.println("识别循环表达式时发生错误，非法的token类型"+token.getTokenEnum());
            return null;
        }
        int start= quaternions.size()+1;
        visitCondition();
        int jumpTo= quaternions.size()+1;
        quaternions.add(new Quaternion(quaternions.size()+1,"j","_","_",""));
        token=lexer.getNextToken();
        if (!token.getTokenEnum().equals(tokenEnum.DO))
        {
            System.out.println("识别循环表达式时发生错误，非法的token类型"+token.getTokenEnum());
            return null;
        }
        visitStatement();
        quaternions.add(new Quaternion(quaternions.size()+1,"j","_","_",start+""));
        quaternions.get(jumpTo-1).setResult(quaternions.size()+1+"");
        return quaternions.size()+"";
    }


    public String visitCondition() {
        String left=visitExpression();
        String op=visitRelationOperator();
        String right=visitExpression();
        quaternions.add(new Quaternion(quaternions.size()+1,"j"+op,left,right, quaternions.size()+3+""));
        return null;
    }


    public String visitRelationOperator() {

        token token=lexer.watchNextToken();
        tokenEnum tokenEnum=token.getTokenEnum();
        if (tokenEnum.equals(tokenEnum.EQUAL)|| tokenEnum.equals(tokenEnum.NOT_EQUAL)
                ||tokenEnum.equals(tokenEnum.GREATER_THAN)||tokenEnum.equals(tokenEnum.GREATER_THAN_OR_EQUAL)
                ||tokenEnum.equals(tokenEnum.LESS_THAN)||tokenEnum.equals(tokenEnum.LESS_THAN_OR_EQUAL))
        {
            return lexer.getNextToken().getTokenValue();
        }
        else {
            System.out.println("识别关系算符时出现问题,非法的token类型:"+lexer.watchNextToken().getTokenEnum());
        }
        return null;
    }


    public String visitEmptyStatement() {

        return null;
    }

}
