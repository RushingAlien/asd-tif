import java.io.*;
import java.util.*;

public class n_search {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        in.nextLine();
        String[] collection = new String[x]; 
        collection = in.nextLine().split(" ");
        in.close();
        for (int i = 0; i < collection.length; i++) {
            if (collection[i].charAt(collection[i].length()-1) == 'n') {
                System.out.printf("%s " , i);
            }
        }
    }
}