import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

/**
 * Deque is principally a doubly linked list. It has constant worst time
 * for all adding/removing operations.
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private int n;          // size of the deque
    private Node pre;       // placeholder before first item
    private Node post;      // placeholder after last item

    // linked list node helper data type
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    /**
     * Construct an empty deque
     */
    public Deque() {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
        n = 0;
    }

    /**
     * Is the deque empty?
     * @return true if the deque is empty and false otherwise
     */
    public boolean isEmpty() {
        return (n == 0);
    }

    /**
     * Returns the number of items on the deque
     * @return the number of items on the deque
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to the front of the deque.
     * @param item to be added to the front
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException(
                    "Item should not be null");
        Node first = pre.next;
        Node x = new Node();
        x.item = item;
        x.next = first;
        x.prev = pre;
        pre.next = x;
        first.prev = x;
        n++;
    }

    /**
     * Adds the item to the end of the deque.
     * @param item to be added to the end
     */
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException(
                    "Item should not be null");
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        n++;
    }

    /**
     * Removes and returns the item from the front
     * @return Item removed from the front
     */
    public Item removeFirst()  {
        if (isEmpty())
            throw new java.util.NoSuchElementException(
                    "Cannot remove from an empty deque");
        Node first = pre.next;
        Node newFirst = first.next;
        pre.next = newFirst;
        newFirst.prev = pre;
        n--;
        return first.item;
    }

    /**
     * Removes and returns the item from the end
     * @return Item removed from the end
     */
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException(
                    "Cannot remove from an empty deque");
        Node last = post.prev;
        Node newLast = last.prev;
        newLast.next = post;
        post.prev = newLast;
        n--;
        return last.item;
    }


    /**
     * Returns an iterator over items in order from front to end.
     * @return Iterator over Items
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = pre.next;

            @Override
            public boolean hasNext() {
                return current.item != null;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new java.util.NoSuchElementException(
                            "No more items");
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException(
                        "Unsupported method");
            }
        };
    }

    /**
     * Unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {

        Deque<Integer> d = new Deque<Integer>();
        StdOut.println("? true = " + d.isEmpty());
        StdOut.println("size= " + d.size());
        d.addFirst(1);

        System.out.println("? false = " + d.isEmpty());
        StdOut.println("size= " + d.size());

        d.addFirst(2);
        d.addFirst(3);

        for (Integer i : d)
            StdOut.println(i);

        //////////
        Deque<Integer> d1 = new Deque<Integer>();
        StdOut.println("? true = " + d1.isEmpty());
        StdOut.println("size= " + d1.size());
        d1.addFirst(1);

        System.out.println("? false = " + d1.isEmpty());
        StdOut.println("size= " + d1.size());

        d1.addLast(2);
        d1.addLast(3);

        for (Integer i : d1)
            StdOut.println(i);

        ///////////
        Deque<Integer> d2 = new Deque<Integer>();
        StdOut.println("? true = " + d2.isEmpty());
        StdOut.println("size= " + d2.size());
        d2.addFirst(1);

        System.out.println("? false = " + d2.isEmpty());
        StdOut.println("size= " + d2.size());

        d2.addFirst(2);
        d2.addFirst(3);

        StdOut.println("Removed: " + d2.removeFirst());

        for (Integer i : d2)
            StdOut.println(i);
    }

}