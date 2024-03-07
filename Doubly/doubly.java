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
class DoublyLinkedList<T> {
    DoublyNode<T> head;
    DoublyNode<T> tail;
    int size;
    public DoublyLinkedList() {
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
        while (tmp!=null) {
            System.out.print(tmp.data + " -- ");
            tmp = tmp.next;
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
            head = baru;
        }
        size++;
    }

    public void printToFirst() {
        if(tail==null) return;
        DoublyNode<T> tmp = tail;
        while (tmp!=null) {
            System.out.print(tmp.data + " -- ");
            tmp = tmp.previous;
        }
        System.out.println("");
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
            size--;
        } else {
        System.out.println("Data is empty!");
        }
    }
    public void makeEmpty() {
        head.next = null;
        tail.previous = null;
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
        }
        size--;
    }    
    public void insert(int index, T data) {
        DoublyNode<T> p = new DoublyNode<>(data);
        DoublyNode<T> tmp = head;
        for (int i=1; i < index-1; i++) {
            tmp=tmp.next;
        }
        p.previous=tmp;
        p.next=tmp.next;
        tmp.next.previous=p;
        tmp.next=p;
        size++;
    }
}