package CircularD;
public class how {
    public static void main(String[] args) {
        DoublyCircular<Integer> y = new DoublyCircular<>();
        // DoublyCircular<Integer> y = new DoublyCircular<>();
        // DoublyLinkedList<Integer> y = new DoublyLinkedList<>();
        y.addFirst(1);
        y.addLast(3);
        y.addFirst(4);
        y.addFirst(10);
        y.insert(10, 5);
        y.printToLast();
        y.printToFirst();
        System.out.println(y.size());
    }
    static void print(DoublyCircular<Integer> x) {
        x.printToLast();
        x.printToFirst();
        System.out.println(x.size());
    }
}
