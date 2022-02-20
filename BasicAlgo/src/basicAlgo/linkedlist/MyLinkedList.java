package basicAlgo.linkedlist;

public class MyLinkedList {
    public static Node<Integer> reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while( head != null){
            next = head.next;
            head.next  = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    public static Node<Integer> reverseDoubleLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;

    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        node1.next = node2;
        node2.next = node3;
        node2.last = node1;
        node3.last = node2;
        System.out.println(node1.value);
        node1 = reverseLinkedList(node1);
        System.out.println(node1.value);
        while(node1 != null){
            System.out.print(node1.value);
            node1 = node1.next;
        }


    }

}
