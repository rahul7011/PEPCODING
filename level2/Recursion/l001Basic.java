import java.util.ArrayList;
import java.util.Scanner;
class l001Basic {
    public static void printOddEven(int a, int b) {
        if (a > b) {
            return;
        }
        if ((a & 1) == 1)
            System.out.println(a);
        printOddEven(a + 1, b);
        if ((a & 1) != 1)
            System.out.println(a);

    }

    public static int maximum(int[] a, int idx) {
        if (a.length == idx) {
            return Integer.MIN_VALUE;
        }
        return Math.max(a[idx], maximum(a, idx + 1));
    }

    public static int minimum(int[] a, int idx) {
        if (a.length == idx) {
            return Integer.MAX_VALUE;
        }
        return Math.min(a[idx], minimum(a, idx + 1));
    }

    public static boolean find(int[] a, int idx, int tar) {
        if (a.length == tar) {
            return false;
        }
        return a[idx] == tar || find(a, idx, tar);
    }

    public static int firstIdx(int[] a, int idx, int data) {
        if (a.length == idx) {
            return -1;
        }
        if (a[idx] == data) {
            return idx;
        }
        return firstIdx(a, idx + 1, data);
    }

    public static int lastIdx(int[] a, int idx, int data) {
        if (a.length == idx) {
            return -1;
        }
        int check = lastIdx(a, idx + 1, data);
        if (check != -1) {
            return check;
        } else {

            if (a[idx] == data) {
                return idx;
            } else {
                return -1;
            }
        }
    }

    public static int[] allIndex(int[] a, int idx, int data, int size) {
        if (a.length == idx) {
            return new int[size];
        }
        int[] arr = allIndex(a, idx + 1, data, a[idx] == data ? size + 1 : size);
        if (a[idx] == data) {
            arr[size] = idx;
        }
        return arr;

    }

    public static ArrayList<String> subSeq(String str) {
        if(str.length()==0)
        {
            ArrayList<String>list= new ArrayList<>();
            list.add("");
            return list;
        }
        String remaString=str.substring(0, str.length()-1);
        ArrayList<String>subList=subSeq(remaString);
        ArrayList<String>finList=new ArrayList<>();
        finList.addAll(subList);
        for (int i = 0; i < subList.size(); i++) {
            finList.add(subList.get(i)+str.charAt(str.length()-1));
        }
        return finList;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // int a=scn.nextInt();
        // int b=scn.nextInt();
        // printOddEven(a, b);
        // int[] a = new int[7];
        // for (int i = 0; i < a.length; i++) {
        //     a[i] = scn.nextInt();
        // }
        // int tar = scn.nextInt();
        // System.out.println(maximum(a, 0));
        // System.out.println(minimum(a, 0));
        // System.out.println(find(a, 0, tar));
        // System.out.println(firstIdx(a, 0, tar));
        // System.out.println(lastIdx(a, 0, tar));
        // int[] arr = allIndex(a, 0, tar, 0);
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i]);
        // }
        System.out.println(subSeq("rahul"));
        scn.close();
    }
}