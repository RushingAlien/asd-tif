package CircularD;
class DoublyNode<T> {
    T data;
    DoublyNode<T> next;
    DoublyNode<T> previous;
    DoublyNode() {}
    DoublyNode(T theData) {
        data = theData;
    }
    DoublyNode(T theData, DoublyNode<T> thePrevious,
    DoublyNode<T> theNext) {
        data = theData;
        previous = thePrevious;
        next = theNext;
    }
}
public class DoublyCircular<T> {
    DoublyNode<T> head;
    DoublyNode<T> tail;
    int size;
    public DoublyCircular() {
        this.head=null;
        this.tail=null;
        this.size = 0;
    }
    public boolean isEmpty() {
        return size == 0 || head == null;
    }
    public int size() {
        return size;
    }
    public void printToLast() {
        if (isEmpty()) return;
        DoublyNode<T> tmp = head;
        for (int i=0; i < size; i++) {
            System.out.print(tmp.data + " -- ");
            tmp = tmp.next;
        }
        System.out.println("");
    }
    public void printToFirst() {
        if(tail==null) return;
        DoublyNode<T> tmp = tail;
        for (int i=0; i < size; i++) {
            System.out.print(" -- " + tmp.data);
            tmp = tmp.previous;
        }
        System.out.println("");
    }
    void addFirst(T data) {
        DoublyNode<T> baru = new DoublyNode<>(data);
        if (isEmpty()) {
            head = baru;
            tail = baru;
        } else {
            baru.next = head;
            head.previous = baru;
            head = head.previous;
        }
        head.previous = tail;
        tail.next = head;
        size++;
    }
    public void addLast(T data) {
        DoublyNode<T> baru = new DoublyNode<>(data);
        if (isEmpty()) {
            head = baru;
            tail = baru;
        } else {
            baru.previous = tail;
            tail.next = baru;
            tail = baru;
        }
        head.previous=tail;
        tail.next=head;
        size++;
    }
    public void removeFirst() {
        DoublyNode<T> temp = head;
        if (!isEmpty()) {
            if (head == tail) {
                head = tail = null;
            } else {
                head.next.previous = null;
                head = temp.next;
            }
            tail.next = head;
            head.previous = tail;
            size--;
        } else {
            System.out.println("Data is empty!");
        }
    }
    public void removeLast() {
        DoublyNode<T> temp = tail;
        if (!isEmpty()) {
            if (tail == head) {
                head = tail = null;
            } else {
                tail.previous.next = null;
                tail = temp.previous;
            }
            tail.next = head;
            head.previous=tail;
            size--;
        } else {
        System.out.println("Data is empty!");
        }
    }
    public void makeEmpty() {
        head.next = null;
        tail.previous = null;
        size=0;
    }
    public void remove(int index) {
        if (index == 1) {
            removeFirst();
        } else if (index == size) {
            removeLast();
        } else {
            DoublyNode<T> tmp = head;
            for (int i=1; i < index; i++) {
                tmp = tmp.next;
            }
            tmp.next.previous=tmp.previous;
            tmp.previous.next=tmp.next;
            head.previous=tail;
            tail.next=head;
            size--;
        }
    }    
    public void insert(int index, T data) {
        DoublyNode<T> p = new DoublyNode<>(data);
        if (index == 1) {
            addFirst(data);
        }
        else if (index == size+1) {
            addLast(data);
        } 
        else {
            DoublyNode<T> tmp = head;
            for (int i=1; i < index-1; i++) {
                tmp=tmp.next;
            }
            p.previous=tmp;
            p.next=tmp.next;
            tmp.next.previous=p;
            tmp.next=p;
            head.previous=tail;
            tail.next=head;
            size++;
        }
    }
}