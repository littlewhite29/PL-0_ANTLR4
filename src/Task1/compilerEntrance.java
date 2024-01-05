package Task1;

import Task2.Quaternion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


/**
 * @author LW
 * @date 2023/12/25 15:2    0
 */
public class compilerEntrance {


    public static void main(String[] args) {
        String path="";
        System.out.println("请输入文件路径");
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            path = sc.nextLine();
            if(new File(path).isFile())
            {
                break;
            }
            else
            {
                System.out.println("找不到文件，请重新输入");
            }
        }
        String fileContent = readFile(path);
        System.out.println(fileContent);
        System.out.println("开始编译");
        System.out.println("开始生成token");
        lexicalAnalysis lexicalAnalysis=new lexicalAnalysis();
        lexicalAnalysis.process(fileContent);
        System.out.println("开始语法分析");
        grammarAnalysis grammarAnalysis=new grammarAnalysis(lexicalAnalysis);
        grammarAnalysis.visitProgram();
        List<Quaternion> Quaternions=grammarAnalysis.getQuaternions();
        System.out.println("编译完成");
        for (Quaternion Quaternion :
                Quaternions) {
            System.out.println(Quaternion);
        }
        String fileName="testOutput_task1.txt";
        System.out.println("开始将数据写入文件"+fileName);
        String content="";
        for (Quaternion quaternion : Quaternions){
            content +=quaternion.getId()+".("+quaternion.getOp()+","+quaternion.getArg1()+","+quaternion.getArg2()+","+quaternion.getResult()+")\n";
        }
        try
        {
            Path path1 = Paths.get(fileName);
            if (!Files.exists(path1)) {
                Files.createFile(path1);
            }
            FileWriter fileWriter = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            System.out.println("写入完成");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static String readFile(String filePath) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
