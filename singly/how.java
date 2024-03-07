public class how {
    public static void main(String[] args) {
        singly<Integer> y = new singly<>();
        y.addFirst(1);
        y.addLast(3);
        y.addFirst(4);
        y.addFirst(10);
        y.insert(3, 5);
        y.print();
        y.remove(2);
        y.print();
    }
}