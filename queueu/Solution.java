import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numInput = in.nextInt();
        Queue<Integer> x = new Queue<>();
        in.nextLine();
        for (int i = 0; i < numInput; i++) {
            String[] input = in.nextLine().split(" ");
            if (input[0].equals("TARUH")) {
                x.enqueue(Integer.parseInt(input[1]));
            } else if (input[0].equals("AMBIL")) {
                x.dequeue();
            } else if (input[0].equals("LIHAT")) {
                x.print();
            }
        }
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
    Node<T> dequeue() {
        Node<T> p;
        if (isEmpty()) {
            return null;
        } else if(size == 1) {
            p = front;
            front = rear = null;
            size--;
            return p;
        } else {
            p = front;
            front = p.next;
            front.prev = null;
            p.next = null;
            size--;
            return front;
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