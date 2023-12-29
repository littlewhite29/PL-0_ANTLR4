package Task2;

/**
 * @author LW
 * @date 2023/12/29 14:05
 */
// Generated from D:/Desktop/ANTLR4_test/ANTLR4_test/src/PL0.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.*;

public class MyVisitor<T> extends PL0BaseVisitor<T> {

    @Override public T visitProgram(PL0Parser.ProgramContext ctx) { return visitChildren(ctx); }

    @Override public T visitProgramHeader(PL0Parser.ProgramHeaderContext ctx) { return visitChildren(ctx); }

    @Override public T visitSubroutine(PL0Parser.SubroutineContext ctx) { return visitChildren(ctx); }

    @Override public T visitConstantDeclaration(PL0Parser.ConstantDeclarationContext ctx) { return visitChildren(ctx); }

    @Override public T visitConstantDefinition(PL0Parser.ConstantDefinitionContext ctx) { return visitChildren(ctx); }

    @Override public T visitVariableDeclaration(PL0Parser.VariableDeclarationContext ctx) { return visitChildren(ctx); }

    @Override public T visitCompoundStatement(PL0Parser.CompoundStatementContext ctx) { return visitChildren(ctx); }

    @Override public T visitStatementList(PL0Parser.StatementListContext ctx) { return visitChildren(ctx); }

    @Override public T visitStatement(PL0Parser.StatementContext ctx) { return visitChildren(ctx); }

    @Override public T visitAssignmentStatement(PL0Parser.AssignmentStatementContext ctx) { return visitChildren(ctx); }

    @Override public T visitExpression(PL0Parser.ExpressionContext ctx) { return visitChildren(ctx); }

    @Override public T visitTerm(PL0Parser.TermContext ctx) { return visitChildren(ctx); }

    @Override public T visitFactor(PL0Parser.FactorContext ctx) { return visitChildren(ctx); }

    @Override public T visitAdditionOperator(PL0Parser.AdditionOperatorContext ctx) { return visitChildren(ctx); }

    @Override public T visitMultiplicationOperator(PL0Parser.MultiplicationOperatorContext ctx) { return visitChildren(ctx); }

    @Override public T visitConditionStatement(PL0Parser.ConditionStatementContext ctx) { return visitChildren(ctx); }

    @Override public T visitLoopStatement(PL0Parser.LoopStatementContext ctx) { return visitChildren(ctx); }

    @Override public T visitCondition(PL0Parser.ConditionContext ctx) { return visitChildren(ctx); }

    @Override public T visitRelationOperator(PL0Parser.RelationOperatorContext ctx) { return visitChildren(ctx); }

    @Override public T visitEmptyStatement(PL0Parser.EmptyStatementContext ctx) { return visitChildren(ctx); }
}
