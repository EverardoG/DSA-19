import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO

        // split the string
        String[] words= s.split(" ");

        // build up a dictionary of word: count of that word
        HashMap<String, Integer> wordDict = new HashMap<>();
        for (String word: words){
            if (!wordDict.containsKey(word)){
                wordDict.put(word, 1);
            }
            else {
                int count = wordDict.get(word);
                wordDict.put(word, count+1);
            }
        }

        // build up dictionary of count to word stack
        HashMap<Integer, Stack<String> > countDict = new HashMap<>();
        wordDict.forEach((word,count) -> {
            if (!countDict.containsKey(count)){
                Stack<String> wordStack = new Stack<>();
                wordStack.push(word);
                countDict.put(count, wordStack);
            }
            else{
                Stack<String> wordStack = countDict.get(count);
                wordStack.push(word);
                countDict.put(count, wordStack);
            }
        });

        // get an array of all keys and organize them in ascending order
        Set<Integer> tempAllCounts = countDict.keySet();
        Integer[] allCounts = tempAllCounts.toArray(new Integer[tempAllCounts.size()]);
        Arrays.sort(allCounts);

        // go through all keys in countDict, popping off all words in
        // the appropriate order into our new string

        String orderedString = "";
        for (Integer count: allCounts){
            while (!countDict.get(count).isEmpty()){
                for (int i = 0; i < count; i++) {
                    orderedString += (countDict.get(count).peek() + " ");
                }
                countDict.get(count).pop();
            }
        }

        return orderedString;
    }

}
