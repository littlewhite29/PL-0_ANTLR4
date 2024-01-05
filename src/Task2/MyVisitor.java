package Task2;

/**
 * @author LW
 * @date 2023/12/29 14:05
 */
// Generated from D:/Desktop/ANTLR4_test/ANTLR4_test/src/PL0.g4 by ANTLR 4.13.1

import java.util.*;

public class MyVisitor extends PL0BaseVisitor<String> {

    private int tempVarCounter = 0;

    private java.lang.String newTempVar() {
        return "t" + tempVarCounter++;
    }



    private List<Declaration> Declaration = new ArrayList<>();


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


    @Override
    public String visitProgram(PL0Parser.ProgramContext ctx) {
//        System.out.println("visitProgram");
//        System.out.println(ctx.programHeader().getText());
//        System.out.println(ctx.subroutine().getText());
//        System.out.println(ctx.children.get(1).getText());
//        System.out.println(ctx.children.get(1).getChild(1).getText());
//        System.out.println(ctx.children.get(2).getText());
        visitProgramHeader(ctx.programHeader());
        visitSubroutine(ctx.subroutine());
//        return visitChildren(ctx);
        return null;
    }

    @Override
    public String visitProgramHeader(PL0Parser.ProgramHeaderContext ctx) {
//        System.out.println("\nvisitProgramHeader");
//        System.out.println(ctx.PROGRAM().getText());
//        System.out.println(ctx.IDENTIFIER().getText());
        return null;
    }

    @Override
    public String visitSubroutine(PL0Parser.SubroutineContext ctx) {
//        System.out.println("\nvisitSubroutine");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        if (ctx.constantDeclaration()!=null)
        {
            visit(ctx.constantDeclaration());
        }
        if(ctx.variableDeclaration()!=null)
        {
            visit(ctx.variableDeclaration());
        }
        visit(ctx.statement());
        return null;
    }

    @Override
    public String visitConstantDeclaration(PL0Parser.ConstantDeclarationContext ctx) {
//        System.out.println("\nvisitConstantDeclaration");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        for (int i = 0; i < ctx.constantDefinition().size(); i++) {
            visit(ctx.constantDefinition().get(i));
        }
        return null;
    }

    @Override
    public String visitConstantDefinition(PL0Parser.ConstantDefinitionContext ctx) {
//        System.out.println("\nvisitConstantDefinition");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        Declaration.add(new Declaration(ctx.IDENTIFIER().toString(), "Constant", Integer.parseInt(ctx.INTEGER().getText())));
//        System.out.println(Declaration);
        quaternions.add(new Quaternion(quaternions.size() + 1, ":=", ctx.INTEGER().getText(), "_", ctx.IDENTIFIER().toString()));
        return null;
    }

    @Override
    public String visitVariableDeclaration(PL0Parser.VariableDeclarationContext ctx) {
//        System.out.println("\nvisitVariableDeclaration");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
            Declaration.add(new Declaration(ctx.IDENTIFIER().get(i).toString(), "Variable", 0));
        }
//        System.out.println(Declaration);
        return null;
    }

    @Override
    public String visitCompoundStatement(PL0Parser.CompoundStatementContext ctx) {
//        System.out.println("\nvisitCompoundStatement");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        visit(ctx.statementList());
        return null;
    }

    @Override
    public String visitStatementList(PL0Parser.StatementListContext ctx) {
//        System.out.println("\nvisitStatementList");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        for (int i = 0; i < ctx.statement().size(); i++) {
            visit(ctx.statement().get(i));
        }
        return null;
    }

    @Override
    public String visitStatement(PL0Parser.StatementContext ctx) {
//        System.out.println("\nvisitStatement");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        return visitChildren(ctx);
    }

    @Override
    public String visitAssignmentStatement(PL0Parser.AssignmentStatementContext ctx) {
//        System.out.println("\nAssignmentStatement");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());

//        if (ctx.depth()>6)
//        {
//            System.out.println("深度为"+ctx.depth()+",判断此语句处于复合语句中,不执行此次语句语句");
//            return ctx.IDENTIFIER().getText();
//        }
        if (find_declaration(ctx.IDENTIFIER().toString())) {
//          System.out.println(ctx.IDENTIFIER().getText());
            tempQuaternion =new Quaternion();
            tempQuaternion.setOp(":=");
            tempQuaternion.setResult(ctx.IDENTIFIER().toString());
//            System.out.println("打印ctx");
//            System.out.println(visitChildren(ctx));
            String arg1=visitExpression(ctx.expression());
            tempQuaternion.setArg1(arg1);
            tempQuaternion.setArg2("_");
            tempQuaternion.setId(quaternions.size()+1);
            quaternions.add(tempQuaternion);
            tempQuaternion =null;
        } else {
            System.out.println("变量" + ctx.IDENTIFIER().getText() + "未声明");
        }
//        System.out.println(quaternions);
        return ctx.IDENTIFIER().getText();
    }

    @Override
    public String visitExpression(PL0Parser.ExpressionContext ctx) {
//        System.out.println("\nvisitExpression");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
//        System.out.println(ctx.getText());
        String left=visit(ctx.term(0));
        if(ctx.term().size()==1)
        {
            return left;
        }
        else {
            String result="";
            String right;
            for (int i = 0; i < ctx.term().size()-1; i++) {
                left=visit(ctx.term(i));
                if(i>=1)
                {
                    left= result;
                }
                String op=ctx.additionOperator().get(i).getText();
                right=visit(ctx.term(i+1));
                quaternions.add(new Quaternion(quaternions.size()+1,op,left,right,result=newTempVar()));
            }
            return result;
        }
        /*String right="";
        String op=ctx.additionOperator().get(0).toString();
        if(ctx.term().size()>1) {

            right = visit(ctx.term(1));
            for (int i=2;i<ctx.term().size();i++){
                String tempLeft=right;
                String tempOp=visit(ctx.additionOperator(i));
                String tempRight=visit(ctx.term(i));
                right=newTempVar();
                quadruples.add(new Quaternion(quadruples.size() +1,tempOp,tempLeft,tempRight,right));
                System.out.println(quadruples.get(quadruples.size()-1));
            }

        }

        String tempVar=newTempVar();
        quadruples.add(new Quaternion(quadruples.size() +1,op,left,right,tempVar));*/


//        return visitChildren(ctx);
    }

    @Override
    public String visitTerm(PL0Parser.TermContext ctx) {
//        System.out.println("\nvisitTerm");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        String result="";
        if (ctx.factor().size()==1) {
            return visitFactor(ctx.factor(0));
        }
        else {
            String left;
            String right;
            for (int i = 0; i < ctx.factor().size()-1; i++) {
                left=visit(ctx.factor(i));
                if(i>=1)
                {
                    left= result;
                }
                String op=ctx.multiplicationOperator().get(i).getText();
                right=visit(ctx.factor(i+1));
                quaternions.add(new Quaternion(quaternions.size()+1,op,left,right,result=newTempVar()));
            }
        }

        return result;
    }

    @Override
    public String visitFactor(PL0Parser.FactorContext ctx) {
//        System.out.println("\nvisitFactor");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        if (ctx.IDENTIFIER()!=null)
        {
            return ctx.IDENTIFIER().toString();
        }
        else if (ctx.INTEGER()!=null){
            return ctx.INTEGER().toString();
        }
        else if(ctx.expression()!=null){
            return visitExpression(ctx.expression());
        }

        return "error";
    }

    @Override
    public String visitAdditionOperator(PL0Parser.AdditionOperatorContext ctx) {
//        System.out.println("\nvisitAdditionOperator");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        return visitChildren(ctx);
    }

    @Override
    public String visitMultiplicationOperator(PL0Parser.MultiplicationOperatorContext ctx) {
//        System.out.println("\nvisitMultiplicationOperator");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        return visitChildren(ctx);
    }

    @Override
    public String visitConditionStatement(PL0Parser.ConditionStatementContext ctx) {
//        System.out.println("\nvisitConditionStatement");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        visit(ctx.condition());
        int backFillId= quaternions.size()+1;
        quaternions.add(new Quaternion(quaternions.size()+1,"j","_","_","0"));
        visit(ctx.statement());
        quaternions.get(backFillId-1).setResult(quaternions.size()+1+"");


        return null;
    }

    @Override
    public String visitLoopStatement(PL0Parser.LoopStatementContext ctx) {
//        System.out.println("\nvisitLoopStatement");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        int start= quaternions.size()+1;
        visit(ctx.condition());
        int jumpTo= quaternions.size()+1;
        quaternions.add(new Quaternion(quaternions.size()+1,"j","_","_",""));
        visit(ctx.statement());
        quaternions.add(new Quaternion(quaternions.size()+1,"j","_","_",start+""));
        quaternions.get(jumpTo-1).setResult(quaternions.size()+1+"");
        return quaternions.size()+"";
    }

    @Override
    public String visitCondition(PL0Parser.ConditionContext ctx) {
//        System.out.println("\nvisitCondition");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        String left=ctx.expression().get(0).getText();
        String right=ctx.expression().get(1).getText();
        String op=visit(ctx.relationOperator());
        quaternions.add(new Quaternion(quaternions.size()+1,"j"+op,left,right, quaternions.size()+3+""));

        return visitChildren(ctx);
    }

    @Override
    public String visitRelationOperator(PL0Parser.RelationOperatorContext ctx) {
//        System.out.println("\nvisitRelationOperator");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        if (ctx.EQUAL()!=null)
        {
            return ctx.EQUAL().getText();
        }
        else if (ctx.NOT_EQUAL()!=null)
        {
            return ctx.NOT_EQUAL().getText();
        }
        else if(ctx.LESS_THAN()!=null)
        {
            return ctx.LESS_THAN().getText();
        }
        else if (ctx.GREATER_THAN()!=null) {
            return ctx.GREATER_THAN().getText();
        }
        else if(ctx.GREATER_THAN_OR_EQUAL()!=null)
        {
            return ctx.GREATER_THAN_OR_EQUAL().getText();
        }


        return "error";
    }

    @Override
    public String visitEmptyStatement(PL0Parser.EmptyStatementContext ctx) {
//        System.out.println("\nvisitEmptyStatement");
//        System.out.println(ctx.start.getText());
//        System.out.println(ctx.start.toString());
        return null;
    }
}
