

import java.io.*;
import java.util.Scanner;


/**
 * @author LW
 * @date 2023/12/25 15:2    0
 */
public class compilerEntrance {


    public static void main(String[] args) {
        String path="src/test2.txt";
        /*
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
         * 
         */
        String fileContent = readFile(path);
        System.out.println(fileContent);
        System.out.println("开始编译");
        //词法分析
        lexicalAnalysis lexicalAnalysis=new lexicalAnalysis();
        lexicalAnalysis.process(fileContent);
        //语法分析
        Parser parser=new Parser(lexicalAnalysis.tokens);
        parser.startParse();
        try {
            Parser.create_table();
        } catch (Exception e) {
            // TODO: handle exception
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
