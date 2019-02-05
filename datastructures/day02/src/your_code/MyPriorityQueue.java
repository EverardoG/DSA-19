package your_code;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private MyStack enqueStack = new MyStack();
    private MyStack dequeStack = new MyStack();


    public void enqueue(int item) {
        // TODO
        enqueStack.push(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        // TODO

        int maxElem = enqueStack.maxElement();

        // move elements to the deque stack until you hit the max
        if (dequeStack.isEmpty()){
            while (enqueStack.peek() != maxElem){
                dequeStack.push(enqueStack.pop());
            }
        }
        // top element of enque stack is the max - get rid of it
        enqueStack.pop();

        // move elements back to enque stack
        while (!dequeStack.isEmpty()){
            enqueStack.push(dequeStack.pop());
        }

        return maxElem;
    }

}