package Task2;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

            MyVisitor visitor=new MyVisitor();
            System.out.println("开始编译");
            visitor.visit(tree);
            List<Quaternion> Quaternions= visitor.getQuaternions();
            System.out.println("编译完成");
            for (Quaternion Quaternion :
                 Quaternions) {
                System.out.println(Quaternion);
            }
            String fileName="testOutput_task2.txt";
            System.out.println("开始将数据写入文件"+fileName);
            String content="";
            for (Quaternion quaternion : Quaternions){
                content +=quaternion.getId()+".("+quaternion.getOp()+","+quaternion.getArg1()+","+quaternion.getArg2()+","+quaternion.getResult()+")\n";
            }
            Path path = Paths.get(fileName);
            if(!Files.exists(path))
            {
                Files.createFile(path);
            }
            FileWriter fileWriter=new FileWriter(fileName,false);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            System.out.println("写入完成");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
