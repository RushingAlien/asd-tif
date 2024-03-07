import java.lang.reflect.Constructor;

public class Mainn {
    public static void main(String[] args) {
        
    }
}

// class NodeTree<T> {
//     NodeTree<T> parent;
//     T data;
//     int level;
//     NodeTree() {
//         this.parent = null;
//         this.data = null;
//     }
//     NodeTree(T data) {
//         this.data = data;
//     }
//     NodeTree(T data, Node<T> parent) {
//         this.parent = parent;
//         this.data = data;
//     }
// }

// class NodeLinkedList<T> {

// }

// class Tree<T> {
//     Node<T> root;
//     int size;
//     int height;
    
    
// }  

class TreeNode {
    TreeNode parent;
    LinkedList child;
    long size;
    String name;

    TreeNode() {
        this.parent=null;
        this.child = new LinkedList();
        this.size = -1;
        this.name = "";

    }
    TreeNode(TreeNode parent, long size, String name) {
        this.parent = parent;
        this.child = new LinkedList();
        this.size = size;
        this.name = name;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    public TreeNode getParent() {
        return parent;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public long getSize() {
        return size;
    }
    public void setChild(LinkedList child) {
        this.child = child;
    }
    public LinkedList getChild() {
        return child;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

class ListNode {
    ListNode next, prev;
    TreeNode node;

    ListNode() {
        this.next = this.prev = null;
        this.node = null;
    }
    ListNode(TreeNode node) {
        this.node = node;
    }
    ListNode(TreeNode node, ListNode next, ListNode prev) {
        this.node = node;
        this.next = next;
        this.prev = prev;
    }

}


class Tree {
    TreeNode root;
    int height;

    Tree() {
        this.root = new TreeNode();
        this.root.setParent(null);
        this.root.setName("data");
        this.root.setSize(-1);
        this.height = 0;
    }
    
    String add(String name, long data) {
        return this.add(name, data, root, 0);
    }

    String add(String name, long data, TreeNode curr, int currHeight) {
        String path = "/data";

        ListNode p = curr.getChild().head;

        while (p != null) {
            if (name.length() >= p.node.name.length()) {
                if (p.node.getName().equals(name.substring(0, p.node.name.length()))) {
                    path += "/" + p.node.getName();
                    curr = p.node;
                    currHeight++;
                    continue;
                }
            }
            p = p.next;
        }
        curr.child.add(name, data, curr);
        if (this.height > currHeight) {
            this.height = this.height;
        } else {
            this.height = currHeight;
        }
        return path;
    }
    
    TreeNode findWithPath(String path) {
        String[] pathHalf = path.split("/");
        int currHeight = 1;

        TreeNode p = root;

        if (p.getName().equals(pathHalf[currHeight]) && currHeight == pathHalf.length - 1) {
            return p;
        }

        ListNode po = p.child.head;

        while (po != null) {
            if (po.node.getName().equals(pathHalf[currHeight])) {
                if (currHeight + 1 == pathHalf.length - 1) {
                    return po.node;
                } else {
                    p = po.node;
                    po = p.child.head;
                    currHeight++;
                    continue;
                }
            }
            po = po.next;
        }
        return null;
    }

    void print(TreeNode cur, int posY) {
        if (cur == root) {
            System.out.println(cur.name);
        } else {
            String indent = "";
            for (int i = 0; i < posY; i++) {
                indent += "--";
            }
            System.out.println(indent);
            if (indent.length() > 0) {
                System.out.printf(" %s (%d)" , cur.getName() , cur.getSize());
            } else {
                System.out.printf("%s (%d)" , cur.getName() , cur.getSize());
            }

            ListNode iter = cur.getChild().head;

            while (iter != null) {
                print(iter.node, posY + 1);
                iter = iter.next;
            }
        }
    }

    void delete(TreeNode node) {
        node.getParent().getChild().remove(node.getName());
        node.setParent(null);
    }
}

class LinkedList {
    ListNode head, tail;
    int size;

    LinkedList(){
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void removeFirst(){
        if(isEmpty()){
            System.out.println("Data is empty !");
        }
        else{
            ListNode temp = head;
            if(head == tail){
                head = tail = null;
            }
            else{
                head.next.prev = null;
                head = temp.next;
            }
            size--;
        }
    }

    void removeLast(){
        if(this.isEmpty()){
            System.out.println("Data is empty !");
        }
        else{
            ListNode temp = tail;
            if(head == tail){
                head = tail = null;
            } else {
                tail.prev.next = null;
                tail = temp.prev;
            }
            size--;
        }
    }

    void remove(String name){
        ListNode temp = head;
        if(this.isEmpty()){
            System.out.println("Data is empty !");
        }
        else{
            while(temp != null){
                if(temp.node.name.equals(name)){
                    if(temp == head){
                        this.removeFirst();
                    }
                    else if(temp == tail){
                        this.removeLast();
                    }
                    else{
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev;
                        size--;
                    }
                    break;
                }
                temp = temp.next;
            }
        }
    }

    void add(String name,  long val, TreeNode parent){
        ListNode curr = new ListNode(new TreeNode(parent, val, name));
        if(this.isEmpty()){
            head = tail = curr;
        }
        else{
            curr.prev = tail;
            tail.next = curr;
            tail = curr;
        }
        size++;
    }
}

