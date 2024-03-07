import java.util.Scanner;
public class FinalMain {
    // FORMAT INDEX ARRAY ACCESS ADALAH [X][Y]
    // 0 : TDK BISA DILEWATI
    // 1 : BISA DILEWATI
    // 2 : START
    // 3 : FINISH
    public static int[][] access, history;
    public static int xStart, yStart, langkah, size;
    public static boolean finish;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        access = new int[size][size];
        sc.nextLine();
        String[] coords = sc.nextLine().split(" ");
        int coLen = coords.length;
        for (int i = 0; i < coLen; i++) {
            int commaPos = coords[i].indexOf(",");
            int x = Integer.parseInt(coords[i].substring(0, commaPos));
            int y = Integer.parseInt(coords[i].substring(commaPos+1,coords[i].length()));
            if (i == 0) {
                access[x][y] = 2;
                xStart = x;
                yStart = y;
            } else if (i == coLen-1) access[x][y] = 3;
            else access[x][y] = 1;
        }
        int peserta = sc.nextInt();
        MazingaPriorityQueue mpq = new MazingaPriorityQueue();for (int i = 0; i < peserta; i++) {
            String namaPeserta = sc.next();
            String[] move = new String[4];
            for (int k = 0; k < 4; k++) {
                move[k] = sc.next();
            }
        // RESET
            langkah = 0;
            finish = false;
            history = new int[size][size];
            findPath(xStart, yStart, move);
            mpq.add(namaPeserta, history, langkah);
        }
        mpq.printHasil();
        sc.close();
    }
    
    public static boolean isSafe(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && access[x][y] > 0 &&
        history[x][y] == 0;
    }
    public static void findPath(int x, int y, String[] moves) {
        history[x][y] = ++langkah;
        if (access[x][y] == 3) finish = true;
        if (!finish) {
            for (int i = 0; i < 4 && !finish; i++) {
                if (moves[i].equals("UP")) {
                    if (isSafe(x, y+1)) {
                        findPath(x, y+1, moves);
                    }
                } else if (moves[i].equals("LEFT")) {
                    if (isSafe(x-1, y)) {
                        findPath(x-1, y, moves);
                    }
                } else if (moves[i].equals("RIGHT")) {
                    if (isSafe(x+1, y)) {
                        findPath(x+1, y, moves);
                    }
                } else if (moves[i].equals("DOWN")) {
                    if (isSafe(x, y-1)) {
                        findPath(x, y-1, moves);
                    }
                }
            }       
        }
    }
    public static void printSteps(String namaPeserta, int[][] steps, int langkah) {
        System.out.println(namaPeserta);
        for (int l = size-1; l >= 0; l--) {
            for (int m = 0; m < size; m++) {
                if (access[m][l] > 0) {
                    if (steps[m][l] > 0) {
                        System.out.printf("[%02d]", steps[m][l]);
                    } else System.out.print("[OO]");
                } else System.out.print("[XX]");
            }
            System.out.println();
        }
    }
}

class MazingaPriorityQueue {
// PQUEUE USING DOUBLY LINKED LIST
    private Node head, tail;
    private int size = 0;
    public void add(String nama, int[][] steps, int langkah) {
        if (isEmpty()) {
            head = tail = new Node(nama, steps, langkah, null, null);
        } else {
            if (langkah < head.langkah || (langkah == head.langkah && nama.compareTo(head.nama) < 0)) {
                // ADD FIRST
                Node nodeBaru = new Node(nama, steps, langkah, null, head);
                head.prev = nodeBaru;
                head = nodeBaru;
            }
            else if (langkah > tail.langkah || (langkah == tail.langkah && nama.compareTo(tail.nama) > 0)) {
                // ADD LAST
                Node nodeBaru = new Node(nama, steps, langkah, tail, null);
                tail.next = nodeBaru;tail = nodeBaru;
            } else {
                Node iter = head;
                while (langkah > iter.langkah || (langkah == iter.langkah && nama.compareTo(iter.nama) > 0)) {
                iter = iter.next;
                }
                Node nodeBaru = new Node(nama, steps, langkah, iter.prev, iter);
                iter.prev.next = nodeBaru;
                iter.prev = nodeBaru;
            }
        }
        this.size++;
    }
    public void printHasil() {
        Node iter = head;
        // OUTPUT STEPS
        while (iter != tail) {
            FinalMain.printSteps(iter.nama, iter.steps, iter.langkah);
            iter = iter.next;
        }
        FinalMain.printSteps(iter.nama, iter.steps, iter.langkah);
        System.out.println();
        // OUTPUT HASIL AKHIR
        iter = head;
        while (iter != tail) {
            if (iter == head) System.out.print("WINNER ");
            System.out.println(iter.nama + ' ' + iter.langkah + " steps");
            iter = iter.next;
        }
        System.out.println(iter.nama + ' ' + iter.langkah + " steps");
    }
    public Node peekFront() {
        return head;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
}
class Node {
    // ISI LANGKAH
    public int[][] steps;
    public int langkah;
    public String nama;
    public Node prev, next;
    public Node(String nama, int[][] steps, int langkah, Node prev, Node next) {
        this.nama = nama;
        this.langkah = langkah;
        this.steps = steps;
        this.prev = prev;
        this.next = next;
    }
}