import sun.jvm.hotspot.debugger.windbg.DLL;

import java.util.List;

class LinkedListDeque<T>{

    private int size;
    private DLListNode sentinel;

    class DLListNode {
        T item;
        DLListNode prev, next;

        DLListNode(T val) {
            item = val;
            prev = next = null;
        }
    }

    /** Constructor */
    public LinkedListDeque() {
        this.size = 0;
        sentinel = new DLListNode(null);
        sentinel.prev = sentinel.next = sentinel;
    }

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        DLListNode curt = new DLListNode(item);
        DLListNode next = sentinel.next;
        sentinel.next = curt;
        curt.prev = sentinel;

        curt.next = next;
        next.prev = curt;

        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        DLListNode curt = new DLListNode(item);
        DLListNode prev = sentinel.prev;
        sentinel.prev = curt;
        curt.next = sentinel;

        prev.next = curt;
        curt.prev = prev;

        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. Take constant time. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. Take constant time. */
    public int size() {
        return this.size;
    }

    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        DLListNode temp = sentinel.next;
        while(temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null
     */
    public T removeFirst() {
        if(sentinel.next.equals(sentinel)) return null;
        DLListNode curt = sentinel.next;
        sentinel.next = curt.next;
        curt.next.prev = sentinel;
        size = size - 1;

        return curt.item;
    }
    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * */
    public T removeLast() {
        if(sentinel.next.equals(sentinel)) return null;
        DLListNode curt = sentinel.prev;
        sentinel.prev = curt.prev;
        curt.prev.next = sentinel;

        size = size - 1;
        return curt.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * Must use iteration, not recursion
     * */
    public T get(int index) {
        if(index >= size) return null;

        DLListNode temp = sentinel.next;
        if(temp == null) {
            return null;
        }

        for(int i=0; i<index; i++) {
            temp = temp.next;
        }

        return temp.item;
    }

    /** Same as get() function, but uses recursion */
    public T getRecursive(int index) {
        return recursion(sentinel.next, index);
    }

    private T recursion(DLListNode node, int index) {
        if(index >= size) {
            return null;
        }
        if(index == 0) return node.item;
        return recursion(node.next, index - 1);
    }
}