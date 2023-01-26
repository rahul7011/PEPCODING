class Solution {
    public static class Node{
        Node[] children=new Node[2];
    }
    public static Node root;
    public static void insert(int num)
    {
        Node node=root;
        for(int i=31;i>=0;i--)
        {
            int bit=(num>>i) & 1;
            if(node.children[bit]==null)
            {
                node.children[bit]=new Node();
            }
            node=node.children[bit];
        }
    }
    public static int getMax(int num)
    {
        Node node=root;
        int ans=0;
        for(int i=31;i>=0;i--)
        {
            int bit=(num>>i) & 1;
            if(node.children[1-bit]!=null)
            {
                ans=(ans|(1<<i));
                node=node.children[1-bit];
            }else
            {
                node=node.children[bit];
            }
        }
        return ans;
    }
    public int findMaximumXOR(int[] nums) {
        root=new Node();
        for(int i=0;i<nums.length;i++)
        {
            insert(nums[i]);
        }
        int max=0;
        for(int i=0;i<nums.length;i++)
        {
            max=Math.max(max,getMax(nums[i]));
        }
        return max;
    }
}