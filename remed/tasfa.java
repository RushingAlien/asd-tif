import java.util.Scanner;

public class T2_215150201111041_ANANDAFITRADIRAJA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tree fileSystem = new Tree();
        
        while (sc.hasNext()) {
            String cmd1 = sc.next();

            if (cmd1.equals("TAMBAH")) {
                sc.next(); // JUST "FOLDER" STRING BUFFER, SKIP IT
                String folderName = sc.next();
                long val = sc.nextLong();

                String out = fileSystem.addFolder(folderName, val);
                System.out.printf("TAMBAH %s PADA %s%n", folderName, out);
            }
            else if (cmd1.equals("HITUNG")) {
                String path = sc.next();
                TreeNode subTreeNode = fileSystem.findNodeByPath(path);
                if (subTreeNode == null) {
                    System.out.printf("PATH %s tidak ditemukan%n", path);
                } else {
                    long out = fileSystem.countValueFolder(subTreeNode);
                    System.out.printf("UKURAN %s = %d%n", path, out);
                }
            }
            else if (cmd1.equals("CETAK")) {
                String path = sc.next();
                TreeNode subTreeNode = fileSystem.findNodeByPath(path);
                if (subTreeNode == null) {
                    System.out.printf("PATH %s tidak ditemukan%n", path);
                } else {
                    fileSystem.printRecursive(subTreeNode, 0);
                    System.out.println();
                }
            }
            else if (cmd1.equals("HAPUS")) {
                String path = sc.next();
                TreeNode targetNode = fileSystem.findNodeByPath(path);
                if (targetNode == null) {
                    System.out.printf("PATH %s tidak ditemukan untuk dihapus%n", path);
                } else {
                    fileSystem.deleteFolder(targetNode);
                    System.out.printf("PATH %s berhasil dihapus%n", path);
                }
            }
        }
    }

    public static int maxValue(int a, int b) {
        return (a > b) ? a : b;
    }
}

class Tree {
    TreeNode root;
    int height;

    Tree() {
        root = new TreeNode("data", -1, null);
        height = 0;
    }

    String addFolder(String name, long val) {
        return this.addFolder(name, val, root, 0);
    }

    String addFolder(String name, long val, TreeNode cur, int posHeight) {
        String pathResult = "/data";

        LLNode iter = cur.child.head;

        while (iter != null) {
            if (name.length() >= iter.node.name.length()) {
                if (iter.node.name.equals(name.substring(0, iter.node.name.length()))) {
                    pathResult += "/" + iter.node.name;
                    cur = iter.node;
                    iter = cur.child.head;
                    posHeight++;
                    continue;
                }
            }
            iter = iter.next;
        }
        // ADD IN HERE
        cur.child.add(name, val, cur);
        this.height = T2_215150201111041_ANANDAFITRADIRAJA.maxValue(this.height, posHeight);
        return pathResult;
    }

    TreeNode findNodeByPath(String path) {
        String[] pathSplit = path.split("/");
        int posHeight = 1;

        TreeNode current = root;

        if (current.name.equals(pathSplit[posHeight]) && posHeight == pathSplit.length - 1) {
            return current;
        }

        LLNode iter = current.child.head;

        while (iter != null) {
            if (iter.node.name.equals(pathSplit[posHeight+1])) {
                if (posHeight+1 == pathSplit.length - 1) return iter.node;
                else {
                    current = iter.node;
                    iter = current.child.head;
                    posHeight++;
                    continue;
                }
            }
            iter = iter.next;
        }
        return null;
    }
    
    long countValueFolder(TreeNode curr) {
        long tmp = (curr.size > -1) ? curr.size : 0;
        LLNode iter = curr.child.head;

        while (iter != null) {
            tmp += countValueFolder(iter.node);
            iter = iter.next;
        }
        return tmp;
    }

    void printRecursive(TreeNode cur, int posHeight) {
        // SUB-TREE TRAVERSAL PRE-ORDER

        if (cur == root) System.out.println(cur.name);
        else {
            String ln = "";
            for (int i = 0; i < posHeight; i++)
                ln += "--";
            System.out.print(ln);
    
            if (ln.length() > 0) {
                System.out.printf(" %s (%d)", cur.name, cur.size);
            } else {
                System.out.printf("%s (%d)", cur.name, cur.size);
            }
            System.out.println();
        }

        LLNode iter = cur.child.head;

        while (iter != null) {
            printRecursive(iter.node, posHeight+1);
            iter = iter.next;
        }
    }

    void deleteFolder(TreeNode node) {
        node.parent.child.remove(node.name);
        node.parent = null;
    }
}

class LinkedList {
    LLNode head, tail;
    int length;

    LinkedList() {
        this.length = 0;
    }

    void add(String name, long val, TreeNode parent) {
        LLNode newNode = new LLNode(new TreeNode(name, val, parent));

        if (this.isEmpty()) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        this.length++;
    }

    boolean remove(String name) {
        LLNode iter = head;

        while (iter != null) {
            if (iter.node.name.equals(name)) {
                if (iter == head) {
                    // REMOVE FIRST
                    head.next.prev = null;
                    head = head.next;
                } else if (iter == tail) {
                    // REMOVE LAST
                    tail.prev.next = null;
                    tail = tail.prev;
                } else {
                    iter.prev.next = iter.next;
                    iter.next.prev = iter.prev;
                }

                this.length--;
                return true;
            }
            iter = iter.next;
        }
        return false;
    }

    boolean isEmpty() {
        return this.length == 0;
    }
}

class LLNode {
    TreeNode node;
    LLNode prev, next;

    LLNode(TreeNode node) {
        this.node = node;
    }
}

class TreeNode {
    TreeNode parent;
    LinkedList child;
    long size;
    String name;

    TreeNode(String name, long size, TreeNode parent) {
        this.name = name;
        this.size = size;
        this.parent = parent;
        this.child = new LinkedList();
    }
}