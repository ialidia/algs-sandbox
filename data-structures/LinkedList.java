/**
 * LinkedList implementation testing for cycles in a linked list.
 *
 * @see <a href="https://www.hackerrank.com/challenges/ctci-linked-list-cycle">Linked Lists: Detect a Cycle</a>
 */


public class LinkedList{
    Node head;

    class Node {
        int data;
        Node next;

        Node(int data) {
            data = data;
            next = null;
        }
    }

    /* Adds a new Node at front of the list. */
    public void add(int data)
    {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    /**
     * Floyd's cycle finding algorithm. Move one pointer through a list by one and another by two.
     * If they ever meet, there is a cycle.
     */
    boolean hasCycle() {
        if (head == null){
            return false;
        }
        Node slow = head;
        Node fast = head;
        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    /* Drier program to test above functions */
    public static void main(String args[])
    {
        LinkedList list = new LinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // loop
        list.head.next.next.next = list.head;

        System.out.println(list.hasCycle());
    }

}
