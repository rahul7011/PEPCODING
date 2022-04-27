import java.util.*;
class LRUCache {
    private class Node {
        int key;
        int value;

        Node prev = null, next = null;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> hm;
    private Node head = null, tail = null;
    private int capacity = 0;
    private int linkedlistSize=0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hm = new HashMap<>();
    }

    private void addFirst(Node node){
        if(head==null)
        {
            this.head=this.tail=node;
        }else
        {
            this.head.next=node;
            node.prev=this.head;
            this.head=node;
        }
        this.linkedlistSize++;
        return;
    }

    private void RemoveNode(Node node)
    {
        if(linkedlistSize==1)
        {
            this.head=this.tail=null;
            return;
        }else if(this.head==node)
        {
            //when the node to be removed is head
            Node prevNode=node.prev;
            prevNode.next=null;
            node.prev=null;
            this.head=prevNode;

        }else if(this.tail==node)
        {
            //when the node to be removed is head
            Node nextNode=node.next;
            nextNode.prev=null;
            node.next=null;
            this.tail=nextNode;

        }else
        {
            //when the node to be removed is in the middle
            Node prevNode=node.prev;
            Node nextNode=node.next;

            prevNode.next=nextNode;
            nextNode.prev=prevNode;

            node.prev=null;
            node.next=null; 
        }
        this.linkedlistSize--;
    }

    private void MakeRecentApp(Node node) {
        if (node == this.head) {
            return;
        }

        RemoveNode(node);
        addFirst(node);
    }

    private Node fetchNode(int key) {
        Node node = hm.get(key);
        MakeRecentApp(node);
        return node;
    }

    // Make it recent App and return its state
    public int get(int key) {
        if (hm.containsKey(key) == false) {
            return -1;
        }
        Node node = fetchNode(key);
        return node.value;
    }

    // key : appName, value : stateOfApp
    public void put(int key, int value) {
        if (hm.containsKey(key) == true) {
            // make it recent app and fetch the app
            Node node = fetchNode(key);
            node.value = value;
        } else {
            Node node = new Node(key, value);
            addFirst(node);
            hm.put(key, node);
            if (hm.size() > this.capacity) {
                Node tail = this.tail;
                RemoveNode(tail);
                hm.remove(tail.key);
            }
        }
    }
}