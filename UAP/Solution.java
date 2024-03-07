import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import javax.print.attribute.standard.RequestingUserName;

class Node {
    int data;
    Node next;
    LinkedList<Node> requests = new LinkedList<>();
    ArrayList<Node> follower = new ArrayList<>();

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    Node (int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

class Graph {
    Node[] nodes;
    int length;
    // LinkedList<Node> requests = new LinkedList<>(); //Terserah mau pake library atau bikin sendiri.

    Graph(int length) {
        this.length = length;
        nodes = new Node[length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
    }

    void requestFollow(int follower, int followed) {
        nodes[followed].requests.addLast(nodes[follower]);
    }

    void seeRequests(int userId) {
        for (Node node : nodes[userId].requests) {
            System.out.printf("%d " , node.data);
        }
        System.out.println();
    }

    void acceptFollow(int userId) {
        int status = 0;
        for (int i = 0; i < nodes.length; i++) {
            for (Node node : nodes[i].requests) {
                if (node.data == userId) {
                    nodes[i].follower.add(node);
                    nodes[i].requests.remove(node);
                    System.out.println(nodes[i].follower.size());
                    return;
                }
            }
        }
        System.out.println("No requests");
    }

    void checkFollowers(int userId) {
        // ArrayList<Integer> followers = new ArrayList<>();
        
        if (nodes[userId].follower.size() == 0) {
            System.out.println("No followers");
            return;
        } else {
            for (Node node : nodes[userId].follower) {
                System.out.printf("%s " , node.data);
            }
            System.out.println();
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int graphLength = in.nextInt();
        Graph graph = new Graph(graphLength);
        int actions = in.nextInt();
        in.nextLine();
        for (int i = 0; i < actions; i++) {
            String action = in.nextLine();
            String[] actionList = action.split(" ");
            switch (actionList[0]) {
                case "FOLLOW":
                    graph.requestFollow(Integer.parseInt(actionList[1]), Integer.parseInt(actionList[2]));
                    break;
                case "SEEREQUESTS":
                    graph.seeRequests(Integer.parseInt(actionList[1]));
                    break;
                case "ACCEPT":
                    graph.acceptFollow(Integer.parseInt(actionList[1]));
                    break;
                case "FOLLOWERS":
                    graph.checkFollowers(Integer.parseInt(actionList[1]));
                    break;
            }
        }
    }
}