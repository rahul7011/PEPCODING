class MyLinkedList {
    // a linked list consists of nodes where each node contains a data field and a
    // reference(link) to the next node in the list.
    // or
    // a linked list is a linear collection of data elements whose order is not
    // given by their physical placement in memory. Instead, each element points to
    // the next.
    private class Node {
        int val = 0;
        Node next = null;

        Node(int val) {
            this.val = val;
        }
    }

    private Node head = null, tail = null;
    private int size = 0;

    public MyLinkedList() {
        this.size = 0;
    }

    // for debugging
    private void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    private Node getNodeAt(int idx) {
        Node curr = head;
        while (idx-- > 0) {
            curr = curr.next;
        }
        return curr;
    }

    public int get(int index) {
        if (index < 0 || index >= this.size) {
            return -1;
        }
        return getNodeAt(index).val;
    }

    public void addAtHead(int val) {
        if (head == null) {
            this.head = this.tail = new Node(val);
        } else {
            Node node = new Node(val);
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            this.head = this.tail = new Node(val);
        } else {
            Node node = new Node(val);
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
        } else if (index == this.size) {
            addAtTail(val);
        } else {
            Node prevNode = getNodeAt(index - 1);
            Node nextNode = prevNode.next;

            Node node = new Node(val);
            prevNode.next = node;
            node.next = nextNode;

            this.size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            return;
        }
        if (this.size == 1) {
            this.head = this.tail = null;
        } else if (index == 0) {
            Node rn = this.head;
            head = rn.next;

            rn.next = null;
        } else if (index == this.size - 1) {
            Node prevNode = getNodeAt(index - 1);
            this.tail = prevNode;
            prevNode.next = null;
        } else {
            Node prevNode = getNodeAt(index - 1);
            Node rn = prevNode.next;

            prevNode.next = rn.next;
            rn.next = null;
        }
        this.size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */