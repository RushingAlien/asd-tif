class Node<T> {
    T data;
    Node<T> next;
    Node() {}
    Node(T newData) {
        data = newData;
    }
    Node (T newData, Node<T> newNext) {
        next = newNext;
        data = newData;
    }
}
public class singly<T> {
    Node<T> head,tail;
    int size=0;
    singly() {
        head = null;
        tail =null;
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
    int length() {
        return size;
    }
    void insert(T data, int position) {
        Node<T> node = new Node<>(data);
        if (position > size) {
            System.out.println("Out of bounds, inserting to last node");
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
            System.out.println("Out of bounds, removing last node");
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
