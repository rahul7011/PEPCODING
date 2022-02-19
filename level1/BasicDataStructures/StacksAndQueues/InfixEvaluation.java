import java.io.*;
import java.util.*;

public class InfixEvaluation{
  
public static int precedence(Character ch)
{
    if(ch=='+'||ch=='-')
    {
        return 1;

    }else
    {
        //for * and /
        return 2;
    }
}

public static void doInfixEvaluation(Stack<Character>Operator,Stack<Integer>Value)
{
    Character Opt=Operator.pop();
    int v2=Value.pop();
    int v1=Value.pop();
    int res=PerformCalculation(v1,v2,Opt);
    Value.push(res);
}
public static int PerformCalculation(int v1,int v2,Character op)
{
    if(op=='+')
    {
        return v1+v2;
    }else if(op=='-')
    {
        return v1-v2;
    }else if(op=='*')
    {
        return v1*v2;
    }else{
        //for divide
        return v1/v2;
    }
}

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    // code
    Stack<Integer>Value=new Stack<>();
    Stack<Character>Operator=new Stack<>();
    for(int i=0;i<exp.length();i++)
    {
        char ch=exp.charAt(i);
        if(ch=='(')
        {
            Operator.push(ch);
        }else if(ch>='0'&&ch<='9')
        {
            Value.push(ch-'0');
        }else if(ch==')')
        {
            while(Operator.size()>0&&Operator.peek()!='(')
            {
                doInfixEvaluation(Operator,Value);
            }
            Operator.pop();
        }else if(ch=='+'||ch=='-'||ch=='/'||ch=='*')
        {
            while(Operator.size()>0&&Operator.peek()!='('&&precedence(Operator.peek())>=precedence(ch))
            {
                doInfixEvaluation(Operator,Value);
            }
            Operator.push(ch);
        }
    }
    while(Operator.size()>0)
    {
        doInfixEvaluation(Operator,Value);
    }
    System.out.println(Value.peek());
 }
}