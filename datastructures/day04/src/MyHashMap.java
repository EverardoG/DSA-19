import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0; // total number of entries

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        // TODO
        // hint: use key.hashCode() to calculate the key's hashCode using its built in hash function
        // then use % to choose which bucket to return.

        // bucket index = hashCode % num_buckets
        int index = key.hashCode() % buckets.length;

        // return the bucket at the specified index
        return buckets[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        // TODO
        // convert key to a bucket to search in
        // iterate through the elements in that bucket
        //     return true if you find that key

        // convert key to a bucket to search in
        LinkedList<Entry> desiredBucket = chooseBucket(key);

        // iterate through elements in that bucket
        Iterator<Entry> entryNode = desiredBucket.iterator();
        while (entryNode.hasNext()){
            if (entryNode.next().getKey() == key){
                // return true if you find that key
                return true;
            }
        }


        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        // TODO
        // iterate through each bucket in the bucket array
        //      iterate through each entry in the bucket
        //          if that's the value you want, return true

        for (LinkedList<Entry> bucket: buckets){
            // make a new iterator
            Iterator<Entry> entryNode = bucket.iterator();
            while (entryNode.hasNext()){
                if (entryNode.next().getValue() == value){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        // TODO:
        //  figure out which bucket to go to,
        //  iterate through that bucket until you find the entry with that key
        //  if you don't find it, return null, or throw an exception or something
        LinkedList<Entry> desiredBucket = chooseBucket(key);
        ListIterator<Entry> entryNode = desiredBucket.listIterator();
        while (entryNode.hasNext()){
            if (entryNode.next().getKey().equals(key)){
                return entryNode.previous().getValue();
            }
        }

        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        // TODO: Complete this method
        // hint: use chooseBucket() to determine which bucket to place the pair in
        // hint: use rehash() to appropriately grow the hashmap if needed

        double entryRatio = (double) size/ (double) buckets.length;
        //check if we need to make the hashmap bigger
        if (entryRatio > ALPHA){
            rehash(GROWTH_FACTOR);
        }
        // create a new entry object
        Entry newEntry = new Entry(key,value);
        // figure out which bucket to put it in
        LinkedList<Entry> desiredBucket = chooseBucket(key);

        // iterate through bucket to make sure it doesn't already exist
        ListIterator<Entry> entryNode = desiredBucket.listIterator();
        while (entryNode.hasNext()){
            if (entryNode.next().getKey() == key){
                // if it does exist, save the value, set the value to the new value, and return the old value
                V returnVal = entryNode.previous().getValue();
                entryNode.next().setValue(value);
                return returnVal;
            }
        }

        // put in the k v pair as a new entry if no old one is found
        desiredBucket.add(newEntry);
        // update number of entries
        this.size++;

        return null;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        // TODO
        // hint: use chooseBucket() to determine which bucket the key would be
        // hint: use rehash() to appropriately grow the hashmap if needed
        double entryRatio = (double) size/ (double) buckets.length;
        //check if we need to make the hashmap smaller
        if (entryRatio < BETA && buckets.length >= MIN_BUCKETS*2){
            rehash(SHRINK_FACTOR);
        }
        // figure out which bucket to remove from
        LinkedList<Entry> removeBucket = chooseBucket(key);

        // iterate through bucket to see if this value exists
        ListIterator<Entry> entryNode = removeBucket.listIterator();
        while (entryNode.hasNext()){
            if (entryNode.next().getKey().equals(key)){
                // if it does exist, save the value, remove the entry, and return the old value
                V returnVal = entryNode.previous().getValue();
                entryNode.next();
                entryNode.remove();
//                entryNode.next().setValue();
                size--;
                return returnVal;
            }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        // TODO
        // hint: once you have removed all values from the buckets, use put(k, v) to add them back in the correct place

        // iterate through entire hashmap, making a stack of all the values
        // initialize buckets with the new size
        // keeping popping off the stack into buckets until stack is empty

        // initialize a stack
        Stack<Entry> entryStack = new Stack<>();

        // put everything in the hashmap onto the stack
        for (LinkedList<Entry> bucket: buckets){
            Iterator<Entry> entryNode = bucket.iterator();
            while(entryNode.hasNext()){
                entryStack.push(entryNode.next());
            }
        }

        // reset size to 0 because it is empty
        size = 0;

        // create a new hashmap of appropriate size
        this.initBuckets((int)(buckets.length*growthFactor));

        // pop all entries off the stack and into the hashmap
        while (!entryStack.empty()){
            Entry newEntry = entryStack.pop();
            this.put(newEntry.getKey(),newEntry.getValue());
        }

    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
