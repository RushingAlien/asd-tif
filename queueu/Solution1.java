import java.io.*;
import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String data = in.nextLine();
        int n = in.nextInt();
        String half[] = new String[2];
        Queue<Character> list = new Queue<>();
        Double indexDouble = data.length() / 2.0;
        int index = (int) Math.round(indexDouble);
        for (int i = 0; i < n; i++) {
            // System.out.println(index);
            half[0] = data.substring(0, index);
            half[1] = data.substring(index, data.length());
            char[] half1 = half[0].toCharArray();
            char[] half2 = half[1].toCharArray();
            // System.out.println(half[0] + "\n" + half[1]);
            for (int j = 0; j < half1.length; j++) {
                if (j <= half1.length) {
                    list.enqueue(half1[j]);
                }
                if (j < half2.length) {
                    list.enqueue(half2[j]);
                }
            }
        // return Queueu sebagai string dan assigne value tersebut ke data
        data = list.getStringValue();
        //reset Queue
        list.makeEmpty();
        }
        System.out.println(data);
    }
}

class Queue<T> {
    Node<T> front,rear;
    int size;

    public Queue() {
        front = rear = null;
        size = 0;
    }

    boolean isEmpty() {
        return front == null || size == 0;
    }
    void enqueue(T data) {
        Node<T> p = new Node<>(data);
        if (isEmpty()) {
            front = rear = p;
        } else {
            rear.next = p;
            p.prev = rear;
            rear = p;
        }
        size++;
    }
    void dequeue() {
        Node<T> p;
        if (isEmpty()) {
            // return null;
        } else if(size == 1) {
            front = rear = null;
            size--;
            // return null;
        } else {
            p = front;
            front = p.next;
            front.prev = null;
            p.next = null;
            size--;
            // return front.data;
        }
    }
    void makeEmpty() {
        front = rear = null;
        size = 0;
    }
    T peek() {
        return front.data;
    }
    void print() {
        Node<T> p = new Node<>();
        p = front;
        while (p!=null) {
            if (p==rear) {
                System.out.print(p.data);
            } else {
                System.out.print(p.data + ", ");
            }
            p=p.next;
        }
        System.out.println("");
    }
    // String getStringValue() {
    //     Node<T> p = front;
    //     String s = "";
    //     while (p != null) {
    //         s += (char)p.data;
    //         p = p.next;
    //     }
    //     return String.valueOf(s);
    // }

    String getStringValue() {
        Node<T> p = front;
        String s = "";
        for (int i = 0; i < size; i++) {
            s += (char)p.data;
            p = p.next;
        }
        return String.valueOf(s);
    }
}

class Node<T> {
    Node<T> next,prev;
    T data;
    public Node() {
        next = prev = null;
        data = null;
    }
    public Node(T data) {
        this.data = data;
    }
    public Node(Node<T> prev,T data, Node<T> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
}