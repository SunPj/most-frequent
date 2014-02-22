package ru.sunsongs.aciworldwide;

import java.util.*;

/**
 * Algorithm of selection most frequent elements.
 * Uses a HashMap for building a map with a element as a key and its
 * frequency as a value. Why HashMap? Because on iterate and counting a frequencies
 * we often use a `get` and `put` methods, where a HashMap has O(1)
 *
 * Then we set up a sorted tree with appropriate comparator, and
 * select needed count of elements.
 *
 * @param <T>
 */
class Frequency<T> {

    private Iterator<T> iterator;
    /**
     * size used for creating a HashMap
     */
    private int size = 0;

    public Frequency(Iterator<T> iterator, int size){
        if (size < 0){
            throw new IllegalArgumentException("Size must be no less than zero");
        }

        this.iterator = iterator;
        this.size = size;
    }

    public Frequency(Iterator<T> iterator){
        this(iterator, 0);
    }

    /**
     * Setup a map and counts items
     *
     * @return Map with a element as a key and its frequency as a value
     */
    private Map<T, Integer> getItemsFrequencyMap(){
        // frequency of element Map(String -> Integer)
        Map<T, Integer> frequency;
        if (size != 0){
            frequency = new HashMap<T, Integer>(size, 1.1f);
        } else {
            frequency = new HashMap<T, Integer>();
        }

        while(iterator.hasNext()){
            T el = iterator.next();
            if (frequency.containsKey(el)){
                frequency.put(el, frequency.get(el) + 1);
            } else {
                frequency.put(el, 1);
            }
        }

        return frequency;
    }

    /**
     * Sorted map for selecting items by frequency
     *
     * @return a sorted map by desc
     */
    private TreeMap<Integer, List<T>> getSortedMap(){
        // frequency of element Map(String -> Integer)
        Map<T, Integer> frequency = getItemsFrequencyMap();

        // declare a tree map with reversed comparator
        TreeMap<Integer, List<T>> freqTree = new TreeMap<Integer, List<T>>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a, b) * -1;
            }
        });

        // fill tree map
        for (final Map.Entry<T, Integer> entry: frequency.entrySet()){
            if (freqTree.containsKey(entry.getValue())){
                freqTree.get(entry.getValue()).add(entry.getKey());
            } else {
                freqTree.put(entry.getValue(), new ArrayList<T>(1){{ add(entry.getKey());}});
            }
        }

        return freqTree;
    }

    /**
     * @param count
     * @return a top 'count' most frequent
     */
    public List<T> getMostFrequentElements(int count){
        // sorted map
        TreeMap<Integer, List<T>> freqTree = getSortedMap();

        // select first count elements
        List<T> resultStrings = new ArrayList<T>(count);
        while(resultStrings.size() < count){
            if (freqTree.size() != 0){
                resultStrings.addAll(freqTree.pollFirstEntry().getValue());
            } else {
                // check if we have a required count after iteration
                throw new IndexOutOfBoundsException("Argument count must be equal or less than count of iterable elements");
            }
        }

        return resultStrings.subList(0, count);
    }
}
