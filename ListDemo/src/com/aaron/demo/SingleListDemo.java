package com.aaron.demo;

public class SingleListDemo {
    public static void main(String args[]){

        SingleListNode head = new SingleListNode();
        SingleListNode node0 = new SingleListNode();
        SingleListNode node1 = new SingleListNode();
        SingleListNode node2 = new SingleListNode();
        SingleListNode node3 = new SingleListNode();

        head.setNext(node0);
        node0.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        head.setData(9);
        node0.setData(0);
        node1.setData(1);
        node2.setData(2);
        node3.setData(3);

        SingleListNode h = head;
        while (h != null){
            System.out.println(h.getData());
            h = h.getNext();
        }

        System.out.println("**********************");

        h = reverseNode(head);
        while (h != null){
            System.out.println(h.getData());
            h = h.getNext();
        }


    }

    private static SingleListNode reverseNode(SingleListNode head){
        if(head == null || head.getNext() == null){
            return head;
        }

        SingleListNode node = reverseNode(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);

        return node;
    }
}
