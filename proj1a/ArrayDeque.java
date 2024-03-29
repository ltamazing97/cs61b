/**
 * ArrayDeque implemented in circular way.
 * @param <T>
 * @author Tao Liu
 */

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /*public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }*/

    private int pulsOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private boolean isSparse() {
        return (items.length >= 16) && (size < (items.length / 4));
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldIndex = pulsOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            a[newIndex] = items[oldIndex];
            oldIndex = pulsOne(oldIndex);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = pulsOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = pulsOne(nextFirst);
        for (; i != nextLast; i = pulsOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isSparse()) {
            resize(items.length / 2);
        }
        T p = items[pulsOne(nextFirst)];
        items[pulsOne(nextFirst)] = null;
        nextFirst = pulsOne(nextFirst);
        if (size != 0) {
            size -= 1;
        }
        return p;
    }

    public T removeLast() {
        if (isSparse()) {
            resize(items.length / 2);
        }
        T p = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        if (size != 0) {
            size -= 1;
        }
        return p;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        int start = pulsOne(nextFirst);
        return items[(start + index) % items.length];
    }

    /*public static void main(String[] args) {
        ArrayDeque deque = new ArrayDeque();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);
        deque.addLast(40);
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);
        deque.addLast(40);
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);
        deque.addLast(40);
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);
        deque.addLast(40);
        deque.addLast(40);
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);
        deque.addLast(40);
        boolean p1 = deque.isEmpty();
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.get(0));
        deque.printDeque();
    }*/
}
