package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        // TODO
        // initializing head, tail, and size
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        // TODO
        // handling case where size is 0
        if (size==0){
            //create a new node that is both the head and the tail
            Node newTail = new Node(c,null,null);
            head = newTail;
            tail = newTail;
            size++;
        }
        else if (size >0){
            // adds to the tail of the linked list

            // create a new node with a prev reference to the tail
            Node newTail = new Node(c, tail, null);
            // set the tail's next ref to the new node
            tail.next = newTail;
            //set our tail to the new node
            tail = newTail;
            // whoa everything just got bigger
            size++;
        }

    }

    public void addFirst(Chicken c) {
        // TODO
        // handling case where size is 0
        if (size==0){
            //create a new node that is both the head and the tail
            Node newTail = new Node(c,null,null);
            head = newTail;
            tail = newTail;
            size++;
        }
        else if (size>0){
            //create a new node with a next reference to the head
            Node newHead = new Node(c,null,head);
            // set the head's prev reference to the new node
            head.prev = newHead;
            //set our head to the new node
            head = newHead;
            //whoa everything just got bigger
            size++;
        }
    }


    public Chicken get(int index) {
        // TODO: return a chicken given the index of the chicken

        if (index >= size){
            // that index is out of bounds
            return null;
        }

        // index is in bounds

        //initialize our counter
        int counter = 0;
        // create a node reference for us to update
        Node currentRef = head;
        // keep incrementing counter and the reference until we get to our index
        while (counter != index){
            counter++;
            currentRef = currentRef.next;
        }
        //return that reference
        return currentRef.val;
    }

    public Chicken remove(int index) {
        // TODO: we can remove a chicken at the given index
        if (index == 0){
            return removeFirst();
        }

        else if (index == size-1){
            return removeLast();
        }

        else {
            //initialize our chicken variable
            Chicken c = null;

            //initialize our counter
            int counter = 0;
            // create a node reference for us to update
            Node currentRef = head;
            Node prevRef = null;
            // keep incrementing counter and the reference until we get to our index
            while (counter != index) {
                counter++;
                prevRef = currentRef;
                currentRef = currentRef.next;
                c = currentRef.val;
            }

            // set the previous node Reference to the next node instead of the current node
            prevRef.next = currentRef.next;
            size--;

            return c;
        }
    }

    public Chicken removeFirst() {
        // TODO: removes chicken at the head
        Chicken c = head.val;
        head = head.next;
        size--;
        return c;
    }

    public Chicken removeLast() {
        // TODO
        Chicken c = tail.val;
        tail = tail.prev;
        size--;
        return c;
    }
}