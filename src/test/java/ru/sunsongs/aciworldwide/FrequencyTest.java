package ru.sunsongs.aciworldwide;


import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class FrequencyTest {

    @Test
    public void testString(){
        List<String> words = new ArrayList<String>(){{
            add("word1");
            add("word1");
            add("word2");
            add("word1");
            add("word2");
            add("word3");
            add("word2");
            add("word2");
            add("word2");
        }};
        Frequency frequency = new Frequency(words.iterator());
        List<String> frequentWords = frequency.getMostFrequentElements(2);
        Assert.assertTrue("Most frequent must be word2", "word2".equals(frequentWords.get(0)));
        Assert.assertTrue("Next frequent must be word1", "word1".equals(frequentWords.get(1)));
    }

    @Test
    public void testAdditional(){

        Integer[] integers = new Integer[]{1, 2, 3, 4, 4, 3, 3, 2, 2 ,2, 1, 1, 1, 1};
        List<Integer> integerList = Arrays.asList(integers);

        Frequency frequency = new Frequency(integerList.iterator());
        List<String> frequentWords = frequency.getMostFrequentElements(4);
        Assert.assertTrue(new Integer(1).equals(integerList.get(0)));

        Assert.assertTrue(new Integer(2).equals(integerList.get(1)));
        Assert.assertTrue(new Integer(3).equals(integerList.get(2)));
        Assert.assertTrue(new Integer(4).equals(integerList.get(3)));
    }
}
