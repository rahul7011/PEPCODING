import java.io.*;
import java.util.*;

public class MinimumStack1 {

  public static class MinStack {
    Stack<Integer> allData;
    Stack<Integer> minData;

    public MinStack() {
      allData = new Stack<>();
      minData = new Stack<>();
    }


    int size() {
      return allData.size();
    }

    void push(int val) {
      allData.push(val);
      if(minData.size()>0&&minData.peek()<val)
      {
        return;
      }
      minData.push(val);
    }

    int pop() {
      if(allData.size()==0)
      {
        System.out.println("Stack underflow");
        return -1;
      }
      int val=allData.pop();
      if(val==minData.peek())
      {
        minData.pop();
      }
      return val;
    }

    int top() {
      if(allData.size()==0)
      {
        System.out.println("Stack underflow");
        return -1;
      }
      return allData.peek();
    }

    int min(){
      if(minData.size()==0)
      {
        System.out.println("Stack underflow");
        return -1;
      }
  	  return minData.peek();
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    MinStack st = new MinStack();

    String str = br.readLine();
    while(str.equals("quit") == false){
      if(str.startsWith("push")){
        int val = Integer.parseInt(str.split(" ")[1]);
        st.push(val);
      } else if(str.startsWith("pop")){
        int val = st.pop();
        if(val != -1){
          System.out.println(val);
        }
      } else if(str.startsWith("top")){
        int val = st.top();
        if(val != -1){
          System.out.println(val);
        }
      } else if(str.startsWith("size")){
        System.out.println(st.size());
      } else if(str.startsWith("min")){
        int val = st.min();
        if(val != -1){
          System.out.println(val);
        }
      }
      str = br.readLine();
    }
  }
}