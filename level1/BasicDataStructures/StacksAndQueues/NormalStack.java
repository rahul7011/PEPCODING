public class NormalStack {
    // class variables
    private int tos = -1;
    private int sizeOfArray = 0;
    private int sizeOfStack = 0;
    private int[] arr;

    // Constructor
    public NormalStack() {
        // default capacity will be 5
        initialize(5);
    }

    public NormalStack(int size) {
        initialize(size);
    }

    private void initialize(int size) {
        this.sizeOfArray = size;
        this.arr = new int[size];
        this.sizeOfStack = 0;
        this.tos = -1;

    }

    private void StackIsFullException() throws Exception {
        if (this.sizeOfArray == this.sizeOfStack) {
            throw new Exception("Stack is full");
        }
    }

    private void StackIsEmptyException() throws Exception {
        if (this.sizeOfStack == 0) {
            throw new Exception("Stack is Empty");
        }
    }

    public void push(int data) throws Exception {
        StackIsFullException();
        this.tos++;
        this.arr[this.tos] = data;
        this.sizeOfStack++;
    }

    public int pop() throws Exception {
        StackIsEmptyException();
        int val = this.top();
        this.arr[this.tos] = 0;
        this.tos--;
        this.sizeOfStack--;
        return val;
    }

    public int top() throws Exception {
        StackIsEmptyException();
        return this.arr[this.tos];
    }

    public boolean isEmpty() {
        return this.sizeOfStack == 0 ? true : false;
    }
    public int size(){
        return this.sizeOfStack;
    }
}
