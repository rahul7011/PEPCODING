import java.util.Scanner;

public class revision {
    public static Scanner scn = new Scanner(System.in);

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static ListNode reverse(ListNode l1) {
        ListNode curr = l1;
        ListNode prev = null;
        while (curr != null) {
            ListNode frwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = frwd;
        }
        return prev;
    }

    private static int size(ListNode l1) {
        ListNode curr = l1;
        int size = 0;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
            c1 = c1.next;
            c2 = c2.next;
        }
        if (c1 == null && c2 != null) {
            c1 = l2;
            c2 = l1;
        } else {
            c1 = l1;
            c2 = l2;
        }
        int borrow = 0;
        // ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (c1 != null || c2 != null || borrow != 0) {
            int val = 0;
            if (c1 != null) {
                val = c1.val;
                // System.out.println("c1" + c1.val);
                c1 = c1.next;
            }
            if (c2 != null) {
                val -= c2.val;
                // System.out.println("c2" + c2.val);
                c2 = c2.next;
            }
            val -= borrow;
            if (val < 0) {
                val += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            // System.out.println(val + " " + borrow);
            temp.next = new ListNode(val);
            temp = temp.next;
        }
        ListNode head = dummy.next;
        head = reverse(head);
        while (head.next != null && head.val == 0) {
            head = head.next;
        }
        return head;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static ListNode makeList(int n) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        // System.out.println("I was here");
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1 = makeList(scn.nextInt());
        ListNode head2 = makeList(scn.nextInt());
        ListNode ans = subtractTwoNumbers(head1, head2);
        printList(ans);
    }
}


