import java.util.Scanner;

public class PBrokenEconomy {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int find = scn.nextInt();
        binarySearch(arr, find);
        scn.close();
    }

    public static void binarySearch(int[] arr, int find) {
        boolean flag = false;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == find) {
                flag = true;
                break;
            } else if (arr[mid] < find) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (flag == true) {
            System.out.println(find);
        } else {
            if (low >= arr.length) {
                System.out.println(arr[high]);
                System.out.println(arr[high]);
            } else if (high < 0) {
                System.out.println(arr[low]);
                System.out.println(arr[low]);
            } else {
                System.out.println(arr[low]);
                System.out.println(arr[high]);
            }
        }
    }
}
