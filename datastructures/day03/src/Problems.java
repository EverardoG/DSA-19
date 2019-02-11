import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: your code here
        // ITERATION 3 (the one that works)

        // first turning the array into a linked list
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addLast(0);
        for (int n:A){
            ll.addLast(n);
        }
        ll.addLast(0);

        int count = 0;

        while (count < k){
            // creating iterator objects for traversing list
            ListIterator<Integer> leftInd = ll.listIterator();
            ListIterator<Integer> midInd = ll.listIterator();
            ListIterator<Integer> rightInd = ll.listIterator();

            // moving middle and right indicies over
            midInd.next();
            rightInd.next(); rightInd.next();

            // initializing the values
            int leftVal = leftInd.next();
            int midVal = midInd.next();
            int rightVal = rightInd.next();

            while (midInd.hasNext()){
                if (midVal > rightVal && midVal >= leftVal){
                    midInd.remove();
                    count++;
                    break;
                }
                else{
                    leftVal = leftInd.next();
                }

                midVal = midInd.next();
                rightVal = rightInd.next();
            }

        }

        // get rid of the extra zeros
        ll.removeFirst();
        ll.removeLast();
        return ll;




//        // ITERATION 2
//
//        // initialize indicies, int counter, and queue for equal elements
//        int count = 0;
//        int leftInd = 0;
//        int midInd = 1;
//        int rightInd = 2;
//        Queue<Integer> nextLeft = new LinkedList<>();
//
//        // first set all values we're removing to -1 and make a queue of any equal values
//        while (count < k && rightInd <= (A.length - 1)){
//            // first case where mid index is greater than both left and right
//            if (A[midInd] > A[rightInd] && A[midInd] > A[leftInd]){
//                // set mid value to -1
//
//                A[leftInd] = -1;
//                if (nextLeft.size() > 0){
//                    // set left index to the element in the nextLeft queue if applicable
//                    leftInd = nextLeft.remove();
//                }
//                else{
//                    // otherwise set left index equal to right index
//                    leftInd = rightInd;
//                }
//                if (rightInd == (A.length - 1)){
//                    // if you hit the end of the array, add right index to the nextLeft queue
//                    nextLeft.add(rightInd);
//                }
//                rightInd += 1;
//                count+=1;
//            }
//
//            // second case where left value < right value
//            else if (A[leftInd] < A[rightInd]){
//                A[rightInd] = -1;
//                if (rightInd == A.length - 1){
//                    nextLeft.add(rightInd);
//                }
//                rightInd += 1;
//                count += 1;
//            }
//
//            // third case where left value == right value
//            else{
//                // add the right index to the nextLeft queue and increment
//                nextLeft.add(rightInd);
//                rightInd += 1;
//            }
//        }
//
//        // if there's anything left in the queue, and we still haven't removed enough, set next in queue equal to -1
//        while (count < k && nextLeft.size() > 0){
//            A[nextLeft.remove()] = -1;
//            count += 1;
//        }
//
//        // add all the values that aren't -1 to a linked list
//        List<Integer> finalList = new LinkedList<>();
//        for (int i = 0; i < A.length; i++){
//            if (A[i] != -1){
//                finalList.add(A[i]);
//            }
//        }
//
//
////        // For now, return a List that's correct size, but contains only 0s
////        List<Integer> l = new LinkedList<>();
////        for (int i = 0; i < A.length - k; i++) l.add(0);
//
//        return finalList;
//
//
//        // ITERATION 1
//
//        // initialize indicies, int counter, and queue for equal elements
//        int count = 0;
//        int leftInd = 0;
//        int rightInd = 1;
//        Queue<Integer> nextLeft = new LinkedList<>();
//
//        // first set all values we're removing to -1 and make a queue of any equal values
//        while (count < k && rightInd <= (A.length - 1)){
//            // first case where left value > right value
//            if (A[leftInd] > A[rightInd]){
//                // set left value to -1
//                A[leftInd] = -1;
//                if (nextLeft.size() > 0){
//                    // set left index to the element in the nextLeft queue if applicable
//                    leftInd = nextLeft.remove();
//                }
//                else{
//                    // otherwise set left index equal to right index
//                    leftInd = rightInd;
//                }
//                if (rightInd == (A.length - 1)){
//                    // if you hit the end of the array, add right index to the nextLeft queue
//                    nextLeft.add(rightInd);
//                }
//                rightInd += 1;
//                count+=1;
//            }
//
//            // second case where left value < right value
//            else if (A[leftInd] < A[rightInd]){
//                A[rightInd] = -1;
//                if (rightInd == A.length - 1){
//                    nextLeft.add(rightInd);
//                }
//                rightInd += 1;
//                count += 1;
//            }
//
//            // third case where left value == right value
//            else{
//                // add the right index to the nextLeft queue and increment
//                nextLeft.add(rightInd);
//                rightInd += 1;
//            }
//        }
//
//        // if there's anything left in the queue, and we still haven't removed enough, set next in queue equal to -1
//        while (count < k && nextLeft.size() > 0){
//            A[nextLeft.remove()] = -1;
//            count += 1;
//        }
//
//        // add all the values that aren't -1 to a linked list
//        List<Integer> finalList = new LinkedList<>();
//        for (int i = 0; i < A.length; i++){
//            if (A[i] != -1){
//                finalList.add(A[i]);
//            }
//        }
//
//
////        // For now, return a List that's correct size, but contains only 0s
////        List<Integer> l = new LinkedList<>();
////        for (int i = 0; i < A.length - k; i++) l.add(0);
//
//        return finalList;
    }

    public static boolean isPalindrome(Node n) {
        // TODO: your code here

        // deal with edge cases here
        if (n == null || n.next == null){
            return true;
        }

        Node head = n;

        int count = 1;
        while(n.next != null){
            // keep going until you hit the end
            n = n.next;
            count++;
        }

        // save tail of list
        // and calculate the halfway point
        Node tail = n;
        int length = count;
        int halfway = length/2 + length % 2;

        count = 1;
        n = head;
        while(count != halfway){
            // keep going until you hit halfway
            n = n.next;
            count++;
        }

        // reverse the second half of the list
        Node prev = n;
        while(n != null){
            Node temp = n.next;
            n.next = prev;
            prev = n;
            n = temp;
        }

        // iterate from the front and the back of the list simultaneously
        // return false if any elements don't match
        Node front = head;
        Node back = tail;
        count = 1;

        while(count <= (halfway-halfway%2)){
            if (front.val != back.val){
                return false;
            }
            front = front.next;
            back = back.next;
            count++;
        }

        return true;
    }

    public static String infixToPostfix(String s) {
        // TODO

        String postFix = "";
        Deque<String> operands = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++){
            String c = Character.toString(s.charAt(i));
            if ("1234567890".contains(c)){
                postFix += c;
                postFix += " ";
            }
            else if ("*+/-".contains(c)){
                operands.push(c);
            }
            else if (c.equals(")")){
                postFix += operands.pop();
                postFix += " ";
            }
        }

        return postFix.strip();
    }

}


