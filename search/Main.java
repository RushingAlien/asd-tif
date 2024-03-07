import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] a = new int[in.nextInt()];
        binarySearch search = new binarySearch();
        in.nextLine();
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        in.nextLine();
        int[] q = new int[in.nextInt()];
        in.nextLine();
        in.close();
        for (int i = 0; i < q.length; i++) {
            q[i] = in.nextInt();
        }
        int[] result = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            result[i] = search.search(a, q[i]);
        }
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

class binarySearch {
    int search(int arr[], int x) {
        int l,m,r;
        l = 0;
        r = arr.length - 1;

        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] == x) {
                return m;
            }
            if (arr[m] <= x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}