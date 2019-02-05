package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxElems;

    public MyStack() {
        ll = new LinkedList<>();
        maxElems = new LinkedList<>();
        maxElems.addFirst(null);
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (maxElems.getFirst() == null || e > maxElems.getFirst()){
            maxElems.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        // pop off the max element if it matches the element popped off ll
        if (maxElems.getFirst()==pop){
            // pop off the top
            int tempElem = maxElems.removeFirst();

            // check if the next element is the same
            if(!ll.isEmpty() && tempElem == ll.getFirst()){
                System.out.println("hit conditional");
                // put the element back on top of maxElems if so
                maxElems.addFirst(tempElem);
            }
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        // TODO: make this return the max element in the stack with O(1) time
        // track the maxElement somewhere
        return maxElems.getFirst();
    }
}
