public class l4 {
    public static class ListNode {
        int val;
        ListNode next;
    
        ListNode() {}
    
        ListNod(int val) {
          this.val = val;
        }
    
        ListNode(int val, ListNode next) {
          this.val = val;
          this.next = next;
        }
      }
    // leetcode 141 (Linked List Cycle)
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)
        {
            return false;
        }
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
            {
                break;
            }
        }
        return slow==fast;
    }
    // leetcode 142 (Linked List Cycle II)
    public ListNode detectCycle(ListNode head) {
      if(head==null||head.next==null)
      {
          return null;
      }
      ListNode slow=head;
      ListNode fast=head;
      while(fast!=null&&fast.next!=null)
      {
          slow=slow.next;
          fast=fast.next.next;
          if(slow==fast)
          {
              break;
          }
      }
      if(slow!=fast)
      {
          //no cycle is found
          return null;
      }
      slow=head;
      while(slow!=fast)
      {
          slow=slow.next;
          fast=fast.next;
      }
      return slow;
  }
  // leetcode 160 (Intersection of Two Linked Lists)
  private static ListNode getTail(ListNode head)
  {
      if(head==null||head.next==null)
      {
          return head;
      }
      ListNode temp=head;
      while(temp.next!=null)
      {
          temp=temp.next;
      }
      return temp;
  }
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      if(headA==null||headB==null)
      {
          return null;
      }
      ListNode tail=getTail(headA);
      tail.next=headB;
      ListNode intersectionPoint=detectCycle(headA);
      tail.next=null;
      return intersectionPoint;
  }
}
