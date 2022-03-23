public class singly {
    public static void main(String[] args) {
        LL list=new LL();
        list.insertLast(12);
        list.insertFirst(5);
        list.insertLast(2);
        list.insertLast(1);
        list.insertFirst(2);
        list.insertLast(14);
        // list.insert(6,300);
        list.insert(2,300);
        list.display();
        // list.deleteFirst();
        // list.deleteLast();
        // list.deleteLast();
        // list.delete(5);
        list.insertRec(100, 7);
        list.display();
    }
}
