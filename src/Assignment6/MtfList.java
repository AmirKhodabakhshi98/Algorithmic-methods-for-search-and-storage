package Assignment6;

import java.util.LinkedList;

public class MtfList {

    private Node head;
    private Node tail;

    private class Node{
        byte b;
        Node next;
        Node prev;

        Node(byte b){
            this.b=b;
        }

    }


    protected void add(byte b){
        if (head == null){
            head = new Node(b);
        }
        else{
            Node node = head;
            while (node.next != null){
                node = node.next;
            }

            node.next = new Node(b);
        }
    }


    //behövs inte egentligen
    protected byte get(int pos){
        if (pos==0){
            return head.b;
        }


        int i = 0;
        Node currentNode = head;
        Node prevNode = null;


        while (i!=pos){
            prevNode = currentNode;
            currentNode = currentNode.next;
            i++;

        }

        moveToFront(currentNode, prevNode);

        return currentNode.b;
    }

    protected int get(byte b){
        Node current = head;
        Node prev = null;

        int pos = 0;
        while (current.b != b){
            prev = current;
            current = current.next;
            pos++;
        }

        if (current!= head){

            moveToFront(current,prev);
        }

        return pos;


    }

    private void moveToFront(Node current, Node prev){
        if (current.next != null){
        prev.next = current.next;
        }
        else {
            prev.next = null;
        }


        Node temp = head;
        current.next = temp;
        head = current;


    }


}
