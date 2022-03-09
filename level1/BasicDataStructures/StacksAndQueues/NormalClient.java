public class NormalClient {
    // client for normal Stack Implementation
    public static void main(String[] args) throws Exception {
        // NormalStack st = new NormalStack();

        DynamicStack st=new DynamicStack();
        for (int i = 1; i <= 5; i++)
            st.push(i * 10);

        // st.push(60);

        while (st.size() != 0)
            System.out.print(st.pop() + " ");
        // st.pop();
        // st.top();
    }
}
