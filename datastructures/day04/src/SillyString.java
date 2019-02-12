import java.util.HashMap;
import java.util.Map;

public class SillyString {
    private final String innerString;

    public SillyString(String innerString) {
        this.innerString = innerString;
    }

    public String toString() {
        return innerString;
    }

    @Override
    public boolean equals(Object other) {
        return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        // TODO What is bad about this hash function??
        int total = 0;
        for (int i=0; i<innerString.length(); i++) {
            // this depends on innerString, which is different based on how sillystring was initialized
            // so this might work if you only instantiate one sillyString object but if you do multiple you lose consistency with different inner strings
            // also the hash code isn't even an int; it's a String
            // you can't really index into an array using a string
            total += innerString.charAt(i);
        }
        return total;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SillyString first = new SillyString("b");
        SillyString second = new SillyString("W");

        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
    }
}
