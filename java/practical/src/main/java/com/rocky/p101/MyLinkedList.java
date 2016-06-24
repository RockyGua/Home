package com.rocky.p101;

public class MyLinkedList {

    Node first;
    Node last;

    int size;

    public int size(){
        return size;
    }

    public void add(Object o){
        Node node = new Node();
        node.item = o;
        if (first == null){
            first = node;
            last = node;
        }else {
            node.prev = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    public Object get(int index){
        if (index<0 || index>=size){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Node temp;

        if (first != null){
            temp = first;
            for (int i=0; i<index; i++){
                temp = temp.next;
            }
            return temp.item;
        }

        return null;
    }

    class Node{
        Object item;
        Node prev;
        Node next;

        public Node(){

        }
        public Node(Object item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("aaa");
        myLinkedList.add("bbb");
        myLinkedList.add("ccc");
        myLinkedList.add("ddd");

        System.out.println(myLinkedList.get(2));
    }
}
