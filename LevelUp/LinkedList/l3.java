public class l3 {
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
  
    //  leetcode 148 (Sort List)
    public ListNode sortList(ListNode head) {
      if (head == null || head.next == null) {
        return head;
      }
      ListNode mid = midNode(head);
      ListNode nHead = mid.next;
      mid.next = null;
      head = sortList(head);
      nHead = sortList(nHead);
      return mergeTwoLists(head, nHead);
    }

    //leetcode 23 (Merge k Sorted Lists)

    //Method : bruteforce
    //Time Complexity:(l^2logk)
    //where l is the avg length of linkedlists present inside the lists
    //ans k is the length of the lists
   public ListNode mergeKLists_bruteforce(ListNode[] lists) {
       ListNode ans=null;
       for(ListNode list:lists)
       {
           ans=mergeTwoLists(ans,list);
       }
       return ans;
   }
      
    //Method : Optimal
    //Time Complexity:(lklogk)
    //where l is the avg length of linkedlists present inside the lists
    //ans k is the length of the lists
    private ListNode mergeKLists_optimal(ListNode[] lists,int start,int end) {
        if(start>=end)
        {
            return start>end?null:lists[start];
        }
        int mid=start+(end-start)/2;
        ListNode list1=mergeKLists_optimal(lists,start,mid);
        ListNode list2=mergeKLists_optimal(lists,mid+1,end);
        ListNode mergedList = mergeTwoLists(list1,list2);
        return mergedList;
    }
    public ListNode mergeKLists_optimal(ListNode[] lists) {
        return mergeKLists_optimal(lists,0,lists.length-1);
    }

    //better alternative of reverse
    //New Concept (ActualHead and ActualTail  TempHead and TempTail Curr and frwd)
    // leetcode 25 (Reverse Nodes in k-Group)
    ListNode tempHead=null,tempTail=null;
    private void addFirst(ListNode node)
    {
     if(tempHead==null)
     {
         tempHead=tempTail=node;
     }else
     {
         node.next=tempHead;
         tempHead=node;
     }
    }
    private int length(ListNode head)
    {
        if(head==null)
        {
            return 0;
        }
        int count=0;
        ListNode node=head;
        while(node!=null)
        {
            count++;
            node=node.next;
        }
        return count;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null||k==1)
        {
            return head;
        }
        int len=length(head);
        ListNode actualHead=null,actualTail=null,curr=head;
        while(len >= k)
        {
            int tempK=k;
            //reversed
            while(tempK-- > 0)
            {
                ListNode frwd=curr.next;
                curr.next=null;
                addFirst(curr);
                curr=frwd;
            }
            if(actualHead==null)
            {
                actualHead=tempHead;
                actualTail=tempTail;
            }else
            {
                actualTail.next=tempHead;
                actualTail=tempTail;
            }
            tempHead=null;
            tempTail=null;
            len-=k;
        }
        actualTail.next=curr;
        return actualHead;
    }
    // leetcode 92 (Reverse Linked List II)
    public ListNode reverseBetween(ListNode head, int n, int m) {
        ListNode prev=null,curr=head;
        int idx=1;
        while(curr!=null)
        {
            while(curr!=null&&idx>=n&&idx<=m)
            {
                ListNode frwd=curr.next;
                curr.next=null;
                addFirst(curr);
                curr=frwd;
                idx++;
            }
            if(idx>m)
            {
                if(prev!=null)
                {
                    prev.next=tempHead;
                    tempTail.next=curr;
                    return head;
                }else
                {
                    tempTail.next=curr;
                    return tempHead;
                }
            }
            idx++;
            prev=curr;
            curr=curr.next;
        }
        return head;
    }

    // leetcode 203 (Remove Linked List Elements)
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;
        ListNode curr=head;
        while(curr!=null)
        {
            if(curr.val==val)
            {
                while(curr!=null&&curr.val==val)
                {
                    curr=curr.next;
                }
            }
            prev.next=curr;
            prev=prev.next;
            if(curr==null)
            {
                break;
            }
            curr=curr.next;
        }
        return dummy.next;
    }

    // leetcode 817 (Linked List Components)
    public int numComponents(ListNode head, int[] nums) {
        if(head==null)
        {
            return 0;
        }
        HashSet<Integer>hs=new HashSet<>();
        for(int val:nums)
        {
            hs.add(val);
        }
        ListNode curr=head;
        int ans=0;
        while(curr!=null)
        {
            if(hs.contains(curr.val)==true)
            {
                while(curr!=null&&hs.contains(curr.val)==true)
                {
                    curr=curr.next;
                }
                ans++;
            }else
            {
                curr=curr.next;
            }
        }
        return ans;
    }


    // Teaches new Concept of prefixSum with HashMap
    // leetcode 1171 (Remove Zero Sum Consecutive Nodes from Linked List)
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy=new ListNode(0);
        ListNode curr=dummy;
        dummy.next=head;
        HashMap<Integer,ListNode>hm=new HashMap<>();
        int prefix=0;
        while(curr!=null)
        {
            prefix+=curr.val;
            hm.put(prefix,curr);
            curr=curr.next;
        }
        prefix=0;
        curr=dummy;
        while(curr!=null)
        {
            prefix+=curr.val;
            curr.next=hm.get(prefix).next;
            curr=curr.next;
        }
        return dummy.next;
    }
    //leetcode 138 (Copy List with Random Pointer)
        //Good Question
    public Node copyRandomList(Node head) {
        HashMap<Node,Node>hm=new HashMap<>();
        Node temp=head;
        Node dummy=new Node(-1);
        Node curr=dummy;
        while(temp!=null)
        {
            Node nNode=new Node(temp.val);
            hm.put(temp,nNode);
            curr.next=nNode;
            curr=curr.next;
            temp=temp.next;
        }
        temp=head;
        curr=dummy.next;
        while(temp!=null)
        {
            curr.random=hm.get(temp.random);
            curr=curr.next;
            temp=temp.next;
        }
        return dummy.next;
    }
}