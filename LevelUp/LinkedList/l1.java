public class l1 {
    public class ListNode {
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

    // 876. Middle of the Linked List
    // Second Mid
    public ListNode middleNode(ListNode head) {
        if (head == null | head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // firstMid
    public ListNode midNode(ListNode head) {
        if (head == null | head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // length of a linkedlist
    public int length(ListNode head) {
        if (head == null)
            return 0;

        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        return len;
    }

    // 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode frwd = curr.next; // backup

            curr.next = prev; // link

            prev = curr;

            curr = frwd;
        }
        return prev;
    }

    // 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode rev = reverseList(mid);
        ListNode curr = head;
        while (rev != null) {
            if (rev.val != curr.val) {
                return false;
            }
            rev = rev.next;
            curr = curr.next;
        }
        return true;
    }

    // 143. Reorder List
    public void reorderList(ListNode head) {
        ListNode mid = midNode(head);
        ListNode rev = reverseList(mid.next);
        mid.next = null;

        ListNode c1 = head, c2 = rev;
        while (c2 != null) {
            ListNode f1 = c1.next; // Backup
            ListNode f2 = c2.next;

            c1.next = c2; // link
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    // 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode c1 = list1, c2 = list2;
        ListNode nHead = new ListNode(-1);
        ListNode temp = nHead;
        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                temp.next = c1;
                c1 = c1.next;
            } else {
                temp.next = c2;
                c2 = c2.next;
            }
            temp = temp.next;
        }
        if (c1 != null) {
            temp.next = c1;
        } else if (c2 != null) {
            temp.next = c2;
        }
        return nHead.next;
    }

    // Unfold Of Linkedlist
    //(https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/unfold-of-linkedlist/ojquestion)
    public void unfold(ListNode head) {
        ListNode h1 = head, h2 = head.next;
        ListNode c1 = h1, c2 = h2;
        while (c2 != null && c2.next != null) {
            ListNode frwd = c2.next;

            c1.next = frwd;
            c2.next = frwd.next;

            c1 = c1.next;
            c2 = c2.next;
        }
        c1.next = null;
        ListNode nh2 = reverseList(h2);
        c1.next = nh2;
    }
}
