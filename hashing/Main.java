import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        in.nextLine();
        String[] keyArr = new String[size];
        String data = in.nextLine();
        int index = hashTruncate.Truncate(data, size);
        System.out.printf("Data : %s\nDisimpan di index : %s\n", data, index );
    }
}

class hashTruncate {
    
    static int Truncate(String key, int size) {
        int index = 0;
        if (key.length() >= 0) {
            // System.out.println((int)key.length()/2);
            String truncIn = key.substring((int) key.length()-3, key.length());
            for (int i = 0; i < truncIn.length(); i++) {
                index += (int) truncIn.charAt(i);
                // System.out.println(truncIn.charAt(i));
                // System.out.println((int) truncIn.charAt(i));
            }
        }
        // if (index % size > size) {
        //     index=size-1;
        // }
        return index%size;
    }
}