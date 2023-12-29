package Task1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LW
 * @date 2023/12/25 15:18
 */
public class lexicalAnalysis {

    String input;
    List<String> tokens=new ArrayList<>();

    public void process(String input)
    {
        this.input=input;
        char ch;
        String word="";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<this.input.length();i++)
        {
            ch=this.input.charAt(i);
            if(!lexicalAnalysis.isWS(ch))
            {
                sb.append(ch);
            }
            else
            {
                if(!sb.toString().equals(""))
                {
                    System.out.println(sb);
                    sb.setLength(0);
                }
                else
                {
                    continue;
                }
            }
        }
    }

    public String getNextToken()
    {
        String token="";
        return token;
    }

    private void nextToken()
    {

    }
    public static boolean isWS(char ch)
    {
        if(ch=='\n'||ch==' '||ch=='\t'||ch=='\r')
        {
            return true;
        }
        else return false;
    }



}
