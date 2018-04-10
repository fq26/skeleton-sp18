class ArrayDeque<T>{
    private int size;
    private T[] arr;
    private int front, rear;

    /** Constructor */
    public ArrayDeque() {
        size    = 0;
        front   = rear = 0;
        arr     = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if(isEmpty()) {
            arr[front] = item;
        }else{
            front = ArrayDeque.decrease(front, arr.length);
            arr[front] = item;
        }
        size += 1;
        if(size == arr.length) {
            resize(size * 2);
        }
    }

    public void addLast(T item) {
        if(isEmpty()) {
            arr[rear] = item;
        }else {
            rear = (rear + 1) % arr.length;
            arr[rear] = item;
        }
        size += 1;
        if(size == arr.length) {
            resize(size * 2);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if(isEmpty()) {
            System.out.println("The ArrayDeque is empty!");
        }else {
            for(int i=front; i != rear; i = (i + 1) % arr.length) {
                System.out.print(arr[i] + " ");
            }
            System.out.print(arr[rear] + " ");
            System.out.println();
        }
    }

    public T removeFirst() {
        if(isEmpty()) return null;
        T curt = arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        size -= 1;
        // For arrays of length 16 or more, your usage factor should always be at least 25%
        if(size < arr.length / 4 && arr.length >= 16) {
            resize(arr.length / 2);
        }

        return curt;
    }

    public T removeLast() {
        if(isEmpty()) return null;
        T curt = arr[rear];
        arr[rear] = null;
        rear = ArrayDeque.decrease(rear, arr.length);
        size -= 1;

        if(size < arr.length / 4 && arr.length >= 16) {
            resize(arr.length / 2);
        }

        return curt;
    }

    private void resize(int len) {
        T[] temp = (T[])new Object[len];
        int j = 0;
        for(int i=front; i != rear; i = (i + 1) % arr.length) {
            temp[j] = arr[i];
            j = (j + 1) % temp.length;
        }
        temp[j] = arr[rear];
        front = 0;
        rear = j;
        this.arr = temp;
    }

    /** get element by index; return null if index is out of bound*/
    public T get(int index) {
        if(index >= size) {
            return null;
        }
        return arr[(index + front) % arr.length];
    }

    private static int decrease(int curt, int len) {
        if(curt == 0) {
            return len - 1;
        }else return curt - 1;
    }
//    public void pointer() {
//        System.out.println(front + " , " + rear);
//    }
}