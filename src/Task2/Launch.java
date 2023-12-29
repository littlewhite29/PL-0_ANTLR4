package Task2;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

/**
 * @author LW
 * @date 2023/12/29 14:15
 */
public class Launch {
    public static void main(String[] args) {
        try{
            String source="testInput.txt";
            CharStream cs=fromFileName(source);
            PL0Lexer lexer=new PL0Lexer(cs);
            CommonTokenStream token=new CommonTokenStream(lexer);
            PL0Parser parser=new PL0Parser(token);
            ParseTree tree=parser.program();

            MyVisitor<Object> visitor=new MyVisitor();
            visitor.visit(tree);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
