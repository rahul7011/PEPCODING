public class l2 {
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

    // length of a linkedlist
    public static int len(ListNode head) {
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

    public static ListNode reverseList(ListNode head) {
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

    // 19. Remove Nth Node From End of List
    public void removeNthFromEnd_followUp(ListNode head, int n) {
        if (head == null)
            return;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            head.val = head.next.val;
            ListNode rn = slow.next;
            slow.next = rn.next;
            rn.next = null;
            return;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rn = slow.next;
        slow.next = rn.next;
        rn.next = null;
    }

    // 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode dummy = new ListNode(-1), prev = dummy, c1 = l1, c2 = l2;

        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = sum / 10;
            prev.next = new ListNode(sum % 10);

            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode head = dummy.next;
        dummy.next = null;

        head = reverseList(head);
        return head;
    }

    // pepcoding
    // Subtract Two Numbers
    // (https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/subtract-two-linkedlist/ojquestion)
    public static int isBiggerList(ListNode l1, ListNode l2) {
        int len1 = len(l1), len2 = len(l2);
        if (len1 == len2) {
            ListNode c1 = l1, c2 = l2;
            while (c1 != null) {
                if (c1.val != c2.val)
                    return c1.val - c2.val;
                c1 = c1.next;
                c2 = c2.next;
            }
        }

        return len1 - len2;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (isBiggerList(l1, l2) < 0) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode dummy = new ListNode(-1), prev = dummy, c1 = l1, c2 = l2;
        int borrow = 0;
        while (c1 != null || c2 != null) {
            int val = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
            if (val < 0) {
                val += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }

            prev.next = new ListNode(val);

            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode head = dummy.next;
        dummy.next = null;

        head = reverseList(head);
        c1 = head;

        while (c1.next != null) {
            if (c1.val != 0) {
                break;
            }
            c1 = c1.next;
        }

        return c1;
    }

    // pepcoding
    // Remove Duplicate From Sorted Linkedlist
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/remove-duplicate-from-sorted-linkedlist/ojquestion
    public static ListNode removeDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode curr = head;
        if (head == null || head.next == null) {
            return head;
        }
        while (curr.next != null) {
            if (curr.val != curr.next.val) {
                ListNode nNode = new ListNode(curr.val);
                temp.next = nNode;
                temp = temp.next;
            }
            curr = curr.next;
        }
        temp.next = new ListNode(curr.val);
        return dummy.next;
    }

    // pepcoding
    // Remove All Duplicates From Sorted Linkedlist
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/remove-all-duplicates-from-sorted-linkedlist/ojquestion
    public static ListNode removeDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode curr = head;
        ListNode prev = null;
        if (head == null || head.next == null) {
            return head;
        }
        while (curr.next != null) {
            if (prev == null) {

                if (curr.val != curr.next.val) {
                    ListNode nNode = new ListNode(curr.val);
                    temp.next = nNode;
                    temp = temp.next;
                }
            } else {
                if (prev.val != curr.val && curr.val != curr.next.val) {
                    ListNode nNode = new ListNode(curr.val);
                    temp.next = nNode;
                    temp = temp.next;
                }
            }
            prev = curr;
            curr = curr.next;
        }
        if (prev.val != curr.val)
            temp.next = new ListNode(curr.val);
        return dummy.next;
    }

    // pepcoding
    // Segregate Even And Odd Nodes In A Linkedlist
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/segregate-even-and-odd-nodes-in-a-link-list/ojquestion
    public static ListNode segregateEvenOdd(ListNode head) {
        ListNode dum1 = new ListNode(-1);
        ListNode dum2 = new ListNode(-1);
        ListNode even = dum1, odd = dum2, temp = head;
        while (temp != null) {
            if ((temp.val & 1) == 0) {
                even.next = temp;
                even = even.next;
            } else {
                odd.next = temp;
                odd = odd.next;
            }
            temp = temp.next;
        }
        odd.next = null;
        even.next = dum2.next;
        return dum1.next;
    }
}
