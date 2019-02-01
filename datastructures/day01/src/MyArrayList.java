public class MyArrayList {
    private Cow[] elems;
    private int size;
    // this class stores cows, and nothing else

    // TODO: Runtime: O(1)
    public MyArrayList() {
        // initializes a MyArrayList of length 10
        this.elems = new Cow[10];
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        // initializes a MyArrayList of length capacity
        this.elems = new Cow[capacity]; // creates array of len capacity and populates with
                                        // cow references
    }

    // TODO: Runtime: O(N)
    public void add(Cow c) {
        // TODO
        // keep going through array until you find a cow reference that points to nothing
        // create a cow object for that reference
        boolean cow_added = false;
        boolean resize = false;
        int count = 0;
        while (cow_added == false){
            if (count == this.elems.length){
                resize = true;
                break;
            }
            if (this.elems[count] == null){
                this.elems[count] = c;
                cow_added = true;
            }// end of if
            count = count + 1;
        }//end of while

        if (resize == true){
            Cow[] new_array = new Cow[1+this.size()];
            int counter = 0;
            for (Cow cow:this.elems){
                new_array[counter] = this.elems[counter];
                counter = counter + 1;
            }
            this.elems = new_array;
            this.add(c);
        }

    }

    // TODO: Runtime: O(N)
    public int size() {
        // TODO: rewrite to just count all cows in array
        // iterates through array
        // increments counter if reference is not null

        int counter = 0;
        for (Cow cow:this.elems){
            if (cow != null) {
                counter = counter + 1;
            }
        }
    return counter;
    }

    public void print_cows(){
        int index = 0;
        boolean finished = false;
        while (finished == false){
            if (index == this.elems.length){
                break;
            }
            System.out.println("Index: " + index + " | Cow: " + this.elems[index]);
            index = index + 1;
        }
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        // TODO
        // this will try to return the cow at a given index
        // unless that cow doesn't exist, in which case it throws an IndexOuOfBoundsException
        try { // return the cow at the index
            return this.elems[index];
        }
        catch (IndexOutOfBoundsException error) { // unless there is no cow
            System.out.println(error);
        }
        return null; // return null if no cow
    }

    // TODO: Runtime: O(1)
    public Cow remove(int index) {
        // TODO
//        try{
            Cow removed_cow = this.elems[index];
            this.elems[index] = null;

            boolean end = false;
            while (end == false){
                index = index + 1;
                System.out.println("index: "+index);
                this.elems[index-1] = this.elems[index];
                if (index == this.size()-1){
                    this.elems[index] = null;
                    System.out.println("last element:"+this.elems[index]);
                    end = true;
                }
            }


            return removed_cow;
//        }
//        catch (IndexOutOfBoundsException error) { // unless there is no cow
//            System.out.println(error);
//        }

//        return null;
    }

    // TODO: Runtime: O(?)
    public void add(int index, Cow c) {
        // TODO

        // if the index doesn't exist
        if (index == this.elems.length){
            // resize the array and add it
            Cow[] new_array = new Cow[1+this.size()];
            int counter = 0;
            for (Cow cow:this.elems){
                new_array[counter] = this.elems[counter];
                counter = counter + 1;
            }
            this.elems = new_array;
            this.add(index, c);
        }

        else if (index > this.elems.length){
            throw new IndexOutOfBoundsException();
        }

        // check if there's an empty spot
        else if (this.elems[index] == null){
            this.elems[index] = c;
        }

        // shift over everything if there is no empty spot
        else{
            try {
                int current_ind = index;
                boolean null_found = false;
                // iterate until you find an empty spot
                while (null_found == false) {
                    if (this.elems[current_ind] != null) {
                        current_ind = current_ind + 1;
                    } else {
                        null_found = true;
                    }
                }
                // iterate backwards, shifting over elements as you go
                boolean cows_done = false;
                while (cows_done == false){
                    this.elems[current_ind] = this.elems[current_ind-1];
                    current_ind = current_ind -1;
                    if (current_ind == index){
                        this.elems[index] = c;
                        cows_done = true;
                    }
                }

            }//try

            catch (IndexOutOfBoundsException error){
                Cow[] new_array = new Cow[2*this.size()];

                int counter = 0;
                for (Cow cow:this.elems){
                    new_array[counter] = this.elems[counter];
                    counter = counter + 1;
                }
                this.elems = new_array;
//                for (int i = 0; i < new_array.length; i++){
//                    System.out.println("Thing "+i+" is "+new_array[i]);
//                }
                this.add(index, c);
            }
        }

//        // try putting a cow in an empty spot
//        if (this.elems[index] == null) {
//            this.elems[index] = c;
//        }
//        // if there's a cow there, shift all cows over
//        else{
//            try {
//                int current_ind = index;
//                boolean null_found = false;
//                // iterate until you find an empty spot
//                while (null_found == false) {
//                    if (this.elems[current_ind] != null) {
//                        current_ind = current_ind + 1;
//                    } else {
//                        null_found = true;
//                    }
//                }
//                // iterate backwards, shifting over elements as you go
//                boolean cows_done = false;
//                while (cows_done == false){
//                    this.elems[current_ind] = this.elems[current_ind-1];
//                    current_ind = current_ind -1;
//                    if (current_ind == index){
//                        this.elems[index] = c;
//                        cows_done = true;
//                    }
//                }
//
//            }//try
//
//            catch (IndexOutOfBoundsException error){
//                Cow[] new_array = new Cow[2*this.size()];
//
//                int counter = 0;
//                for (Cow cow:this.elems){
//                    new_array[counter] = this.elems[counter];
//                    counter = counter + 1;
//                }
//                this.elems = new_array;
////                for (int i = 0; i < new_array.length; i++){
////                    System.out.println("Thing "+i+" is "+new_array[i]);
////                }
//                this.add(index, c);
//            }
//        }//else
    }
}