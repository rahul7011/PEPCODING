import java.io.*;
import java.util.*;

public class PrefixEvaluationAndConversions{
  
public static void performCalculations(char ch,Stack<Integer>Value)
{
    int v1=Value.pop();
    int v2=Value.pop();
    if(ch=='+')
    {
        Value.push(v1+v2);
    }else if(ch=='-')
    {
        Value.push(v1-v2);
    }else if(ch=='*')
    {
        Value.push(v1*v2);
    }else{
        Value.push(v1/v2);
    }
}

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    // Write your code here
    
    // HINT

    // The only difference in this question and previous question is that 
    // you have to traverse the expression from the last (i.e start the loop from end/last)
    // and now top of stack will be value 1 instead of value 2
    Stack<Integer>Value=new Stack<>();
    Stack<String>infix=new Stack<>();
    Stack<String>postfix=new Stack<>();
    for(int i=exp.length()-1;i>=0;i--)
    {
        if(exp.charAt(i)>='0'&&exp.charAt(i)<='9')
        {
            Value.push(exp.charAt(i)-'0');
            infix.push(exp.charAt(i)+"");
            postfix.push(exp.charAt(i)+"");
        }else if(exp.charAt(i)=='+'||exp.charAt(i)=='-'
        ||exp.charAt(i)=='*'||exp.charAt(i)=='/')
        {
            performCalculations(exp.charAt(i),Value);
            String v1=infix.pop();
            String v2=infix.pop();
            infix.push("("+v1+exp.charAt(i)+v2+")");

            v1=postfix.pop();
            v2=postfix.pop();
            postfix.push(v1+v2+exp.charAt(i));
        }
    }
    System.out.println(Value.peek());
    System.out.println(infix.peek());
    System.out.println(postfix.peek());
 }
}