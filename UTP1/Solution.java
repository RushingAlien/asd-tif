import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        DLL<String> Playlist = new DLL<>();
        int x = in.nextInt();
        String action[] = new String[x];
        in.nextLine();
        for (int i = 0; i < action.length; i++) {
            action[i] = in.nextLine();
            if (action[i].equals("ADDSONG")) {
                Playlist.addLast(in.nextLine());
                Playlist.addLast(in.nextLine());
            } else if(action[i].equals("PLAYSONG")) {
                Playlist.getData(1);
                Playlist.getData(2);
            } else if(action[i].equals("NEXTSONG")) {
                Playlist.getNext();
                Playlist.getNext();
            } else if(action[i].equals("PREVSONG")) {
                Playlist.getPrev();
                Playlist.getPrev();
                Playlist.getNext();
            }
        }
    }
}
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;
    Node() {
        data = null;
        next = null;
        prev = null;
    }
    Node(T data) {
        this.data = data;
    }
}

class DLL<T> {
    Node<T> head, tail;
    int size;
    DLL() {
        head = tail = null;
        size = 0;
    }
    boolean isEmpty() {
        return head == null;
    }
    void addFirst(T data) {
        Node<T> tmp = new Node<>(data);
        if (isEmpty()) {
            head = tail = tmp;
            size++;
        } else {
            tmp.next = head;
            head.prev = tmp;
            tmp = head;
            size++;
        }
    }
    void addLast(T data) {
        Node<T> tmp = new Node<>(data);
        if (isEmpty()) {
            head = tail = tmp;
            size++;
        } else {
            tail.next = tmp;
            tmp.prev = tail;
            tail = tmp;
        }
    }
    void add(T data,int index) {
        Node<T> tmp = new Node<>(data);
        if (isEmpty()) {
            head = tail = tmp;
            size++;
        } else if (index == 1) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node<T> counter = head;
            for (int i = 1; i < index - 1; i++) {
                counter = counter.next;
            }
            tmp.prev = counter.prev;
            counter.prev.next = tmp;
            tmp.next = counter;
            counter.prev = tmp; 
            size++;
        }
    }
    void print() {
        Node<T> counter = head;
        while (counter != null) {
            System.out.println(counter.data + "--");
            counter = counter.next;
        }
    }
    T getFirstData() {
        if (!isEmpty()) {
            return head.data;
        } else {
            return null;
        }    
    }
    T getData(int index) {
        if (!isEmpty()) {
            Node<T> counter = head;
                for (int i = 1; i < index - 1; i++) {
                    counter = counter.next;
                }
            return counter.data;
        } else if(index <= 0 || isEmpty() || index > size) {
            return null;
        } else {
            return null;
        }
    }
    T getNext() {
        if (!isEmpty()) {
            if (head.next != null) {
                head = head.next;
                return head.data;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    T getPrev() {
        if (!isEmpty()) {
            if (head.prev != null) {
                head = head.prev;
                return head.data;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}