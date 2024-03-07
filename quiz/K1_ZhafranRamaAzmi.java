class Node {
    String data;
    Node next;
    Node previous;
    Node() {
        next = previous = null;
        data = "";
    }
    Node(String data) {
        this.data=data;
    }
    Node(String data, Node previous, Node next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }
}
class DoublyLinkedList {
    Node head, tail;
    int size;
    // Node p;
    DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }
    void printToLast() {
        if (head==null && tail == null) return;
        Node tmp = head;
        while (tmp!=null) {
            System.out.print(tmp.data + " -- ");
            tmp = tmp.next;
        }
        System.out.println("");
    }
    void removeFirst() {
        Node temp = head;
        if (temp != null) {
            if (head == tail) {
                head = tail = null;
            } else {
                head.next.previous = null;
                head = temp.next;
            }
            size--;
        } else {
            System.out.println("Data is empty!");
        }
    }
    void add (String x) {
        Node p = new Node(x);
        if (size == 0) {
            head = tail = p;
            size++;
        } else if (size == 1) {
            if (x.compareTo(head.data) == -1) {
                p.next = head;
                head.previous=p;
                head=p;
                size++;
            } else if (x.compareTo(head.data) >= 0) {
                p.previous = head;
                head.next= p;
                size++;
            }
        } else if (size >= 2) {
            Node tmp = head;
            while (tmp!=null) {
                if (x.compareTo(tmp.data) == -1) {
                    p.next = head;
                    head.previous=p;
                    head=p;
                    size++;
                } else if (x.compareTo(tmp.data) == 0 ) {
                    p.previous = head;
                    p.next = head.next;
                    if (head.next!=null) {
                        head.next.previous = p;
                    }
                    head.next = p;
                    size++;
                } else if (x.compareTo(tmp.data) == 1) {
                    p.previous = head;
                    p.next = head.next;
                    if (head.next!=null) {
                        head.next.previous = p;
                    }
                    head.next = p;
                    size++;
                }
                tmp = tmp.next;
            }
        }
        //  else if (size >= 3) {
        //     Node tmp = head;
        //     while (tmp!=null) {
        //         if (x.compareTo(tmp.data) == -1 && x.compareTo(tmp.next.data) >= 0) {

        //         } else if (x.compareTo(tmp.data) == 1 && x.compareTo(tmp.previous.data) <= 0) {

        //         } else if (x.compareTo(tmp.data) == 0 && x.compareTo(tmp.previous.data) == -1 && x.compareTo(tmp.next.data) == 1) {
        //             p.previous=tmp.previous.next;
        //             p.next=tmp;
        //             tmp.previous=p;
        //             tmp=p;
        //             size++;
        //             break;
        //         }
        //         tmp = tmp.next;
        //     }
        // }    
    }
}
public class K1_ZhafranRamaAzmi {
    public static void main(String[] args) {
        DoublyLinkedList x = new DoublyLinkedList();
        x.add("AB");
        x.printToLast();
        x.add("AC");
        x.add("AA");
        x.printToLast();
        System.out.println(x.size);
    }
}