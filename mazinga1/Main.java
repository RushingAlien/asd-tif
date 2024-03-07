import java.util.*;
// Zhafran Rama Azmi 215150207111025
// Gibran Hakim 215150200111020
public class Main {
    static int[] start = new int[2], finish = new int[2];
    static int ukuran;
    static int[][] mazingaArray;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ukuran = in.nextInt();
        in.nextLine();
        String[] setIndex = in.nextLine().split(" ");
        for (int i = 0; i < setIndex.length; i++) {
            String Indexset[] = (setIndex[i].split(","));
            int[] pos = new int[2];
            pos[0] = Integer.parseInt(Indexset[0]);
            pos[1] = Integer.parseInt(Indexset[1]);
            mazingaArray[pos[0]][pos[1]]=1;
        }
        for (int i = 0; i < setIndex.length; i++) {
            
        } 
    }
}

class Labirin {
    int ukuran;
    int labirin;
    
}

class SLL<T> {
    Node<T> head,tail;
    int size;
    int step;
    SLL() {
        head = null;
        size = 0;
        step = 0;
    }
    boolean isEmpty() {
        return size == 0 || head == null;
    }
    void addFirst(T x) {
        Node<T> p = new Node<>();
        p.data=x;
        p.next = head;
        head = p;
        size++;
    } 
    void addLast(T x) {
        Node<T> p = new Node<>(x);
        if (head == null) {
            head = p;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = p;
        size++;
    }
    public int getStep() {
        return step;
    }
    int length() {
        return size;
    }
    void insert(T data, int position) {
        Node<T> node = new Node<>(data);
        if (position > size) {
            addLast(data);
            return;
        }
        else if (position == 1) {
            addFirst(data);
        } else {
            Node<T> previous = head;
            int count = 1;
            while (count < position-1) {
                previous = previous.next;
                count++;
            }
            Node<T> current = previous.next;
            node.next = current;
            previous.next = node;
        }
        size++;
    }
    T next() {
        head=head.next;
        step++;
        return head.data;
    }
    T getData() {
        return head.data;
    }
    void print() {
        Node<T> p = head;
        while (p!=null) {
            System.out.print(p.data + " -- ");
            p = p.next;
        }
        System.out.print("null\n");
    }
    Node<T> removeFirst() {
        if (head == null) {
            return null;
        }
        Node<T> temp = head;
        head = head.next;
        temp.next = null;
        size--;
        return temp;
    }
    Node<T> removeLast() {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (current.next!=null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        size--;
        return current;
    }
    void remove(int position) {
        if (position > size) {
            removeLast();
            return;
        }
        else if (position == 1) {
            head = head.next;
        }
        else {
            Node<T> current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }
}

class Node<T> {
    T data;
    Node<T> next;
    Node() {
        this.data = null;
        this.next = null;
    }
    Node(T data) {
        this.data = data;
    }
    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
