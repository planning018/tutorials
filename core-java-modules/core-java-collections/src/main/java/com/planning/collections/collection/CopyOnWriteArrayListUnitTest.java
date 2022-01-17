package com.planning.collections.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

/**
 * 测试 CopyOnWriteArrayList 的功能
 *
 * @author yxc
 * @since 2020-11-06 15:00
 **/
public class CopyOnWriteArrayListUnitTest {

    @Test
    public void givenCopyOnWriteList_whenIterateAndAddElementToUnderneathList_thenShouldNotChangeIterator() {
        // given
        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

        // when
        Iterator<Integer> iterator = numbers.iterator();
        numbers.add(10);

        // then
        List<Integer> result = new LinkedList<>();
        iterator.forEachRemaining(result::add);
        assertEquals(result, Arrays.asList(1, 3, 5, 8));

        // and
        Iterator<Integer> iterator2 = numbers.iterator();
        List<Integer> result2 = new LinkedList<>();
        iterator2.forEachRemaining(result2::add);
        assertEquals(result2, Arrays.asList(1, 3, 5, 8, 10));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void givenCopyOnWriteList_whenIterateOverItAndTryToRemoveElement_thenShouldThrowException(){
        // given
        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

        // when
        Iterator<Integer> iterator = numbers.iterator();
        if(iterator.hasNext()){
            iterator.remove();
        }
    }
}