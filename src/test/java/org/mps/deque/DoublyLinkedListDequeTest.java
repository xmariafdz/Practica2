package org.mps.deque;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Maria Fernández Moreno
 * @author Carlos Castaño Moreno
 */

/*
    Test cases -> Divided into Tests over queues' size, first and last items and deleting items
    -------------------------------------------------------------------------------------------
    1) Tests cases for size() method
        1.1 Size of empty queue -> 0
        1.2 Size of one item queue -> 1
        1.3 Size of three items queue -> 3

    2) Tests for first and last item methods
        2.1 First() of empty list means exception
        2.2 Last() of empty list means exception
        2.3 first() of single item list means that it equals last()
        2.4 First item of {1-2} is 1
        2.5 Last item of {1-2} is 2
        2.6 First item of {1-2-3-4-5} is 1
        2.7 Last item of {1-2-3-4-5} is 5
        2.8 First item of {5}.prepend(6) is 6
        2.9 Last item of {5}.prepend(6) is 5
        2.10 First item of {}.append(1) = last item of {}.append(1)
        2.11 First item of {}.prepend(1) = last item of {}.prepend(1)

    3) Tests cases for deleteFirst() and deleteLast() methods
        3.1 Deleting first item from an empty queue throws an exception.
        3.2 Deleting last item from an empty queue throws an exception.
        3.3 Deleting first item from a single item queue makes an empty queue
        3.4 Deleting last item from a single item queue makes an empty queue
        3.5 Deleting first item from a four item queue makes a 3 items queue and makes next node first
        3.6 Deleting last item from a four item queue makes a 3 items queue and makes next node first

    4) Test cases for get(index) methods
        4.1 First element from an empty list throws an exception
        4.2 Fifth element from 3 items list means exception
        4.3 Third element from [2, 4, 1, 7, 8, 3] (which size is even) is 1
        4.4 Third element from [2, 4, 9, 1, 7, 8, 3] (which size is uneven) is 1
        4.5 Sixth element from [2, 4, 1, 7, 8, 3] is 3
        4.6 First item from single item list is that one item
        4.7 Last item from single item list is that one item
        4.8 You cannot get an item from negative index
        4.9 You cannot get an item from an index bigger than size

    5) Tests cases for contains(value)
        5.1 Check that an empty list does not contain anything
        5.2 Check that 1 is not in {5,6,7}
        5.3 Check that 5 is in {5,6,7}
        5.4 Check that 6 is in [1,5,6,7]
        5.5 Checks that a list contains null value

    6) Tests cases for remove(value)
        6.1 Removes correctly item in first position
        6.2 Removes null value item from not empty list
        6.3 Removes item that is not null from list that contains null items
        6.4 Removes correctly from a 5 items list
        6.5 Cannot remove an item that is not in the list

    7) Test cases for sort() method
        7.1 Result of sorting an empty list is an empty list
        7.2 Result of sorting a single item list is the same list
        7.3 Sorts a 5 items list correctly
        7.4 Sorts a 5 items list correctly when getting null comparator

     ------------------------------------------------------------------------------------------------
 */
public class DoublyLinkedListDequeTest {
    private DoubleEndedQueue<Integer> listDeque;

    @BeforeEach
    public void beforeEach(){
        listDeque = new DoublyLinkedListDeque<>();
    }

    @AfterEach
    void shutdown() {
        listDeque = null;
    }

    @Nested
    @DisplayName("Tests cases for size() method")
    class SizeTests{

        @Test
        @DisplayName("Size of empty queue equals 0")
        public void sizeOfAnEmptyListIsZero(){
            assertEquals(listDeque.size(),0);
        }

        @Test
        @DisplayName("Size of one item queue equals 1")
        public void sizeOfASingleItemQueueIsOne(){

            listDeque.append(1);

            int expected = 1;
            int actual = listDeque.size();

            assertEquals(expected,actual);
        }

        @Test
        @DisplayName("Size of three items queue equals 3")
        public void sizeOfAThreeItemsQueueIsThree(){

            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);

            int expected = 3;
            int actual = listDeque.size();
            assertEquals(expected,actual);
        }
    }

    @Nested
    @DisplayName("Tests for first and last item methods")
    class FirstAndLastItemTests{

        @Test
        @DisplayName("first() of empty list means exception")
        public void firstItemOfAnEmptyListThrowsAnException(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.first());
        }

        @Test
        @DisplayName("last() of empty list means exception")
        public void lastItemOfAnEmptyListThrowsAnException(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.last());
        }

        @Test
        @DisplayName("first() of single item list means that it equals last()")
        public void firstAndlastItemOfASingleItemListAreTheSame(){

            listDeque.append(1);

            int expected = 1;
            int actualFirst = listDeque.first();
            int actualLast = listDeque.last();

            assertEquals(expected,actualFirst);
            assertEquals(expected,actualLast);
        }

        @Test
        @DisplayName("First item of {1,2} is 1")
        public void firstItemOfATwoItemsList(){
            listDeque.append(1);
            listDeque.append(2);

            assertEquals(listDeque.first(),1);
        }

        @Test
        @DisplayName("Last item of {1,2} is 2")
        public void lastItemOfATwoItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            assertEquals(listDeque.last(),2);
        }
        @Test
        @DisplayName("First item of {1,2,3,4,5} is 1")
        public void firstItemOnAFiveItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.append(5);
            assertEquals(listDeque.first(),1);
        }

        @Test
        @DisplayName("Last item of {1,2,3,4,5} is 1")
        public void lastItemOnAFiveItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.append(5);
            assertEquals(listDeque.last(),5);
        }

        @Test
        @DisplayName("First item of {5}.prepend(6) is 6")
        public void firstItemWhenPrepending(){
            listDeque.append(5);
            listDeque.prepend(6);
            assertEquals(listDeque.first(),6);
        }

        @Test
        @DisplayName("Last item of {5}.prepend(6) is 6")
        public void lastItemWhenPrepending(){
            listDeque.append(5);
            listDeque.prepend(6);
            assertEquals(listDeque.last(),5);
        }

        @Test
        @DisplayName("First item of {}.append(1) = last item of {}.append(1)")
        public void firstItemEqualsLastItemWhenAppendingToAEmptyList(){
            listDeque.append(1);
            assertEquals(listDeque.first(),listDeque.last());
        }

        @Test
        @DisplayName("First item of {}.prepend(1) = last item of {}.prepend(1)")
        public void firstItemEqualsLastItemWhenPreppendingToAEmptyList(){
            listDeque.prepend(1);
            assertEquals(listDeque.first(),listDeque.last());
        }
    }

    @Nested
    @DisplayName("Tests cases for deleteFirst() and deleteLast() methods")
    public class deletingItemsTests{

        @Test
        @DisplayName("Deleting first item from empty queue means exception.")
        public void deletingFirstItemFromAnEmptyList(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.deleteFirst());
        }

        @Test
        @DisplayName("Deleting last item from empty queue means exception.")
        public void deletingLastItemFromAnEmptyList(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.deleteLast());
        }

        @Test
        @DisplayName("Deleting first item from single item queue means empty queue")
        public void deletingFirstItemFromASingleItemQueue(){
            listDeque.append(1);
            listDeque.deleteFirst();
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.last());
        }

        @Test
        @DisplayName("Deleting last item from single item queue means empty queue")
        public void deletingLastItemFromASingleItemQueue(){
            listDeque.append(1);
            listDeque.deleteLast();
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.last());
        }

        @Test
        @DisplayName("Deleting first item from 4 item queue makes 3 items queue")
        public void deletingFirstItemFromAFourItemsQueue(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.deleteFirst();
            assertEquals(listDeque.first(),2);
            assertEquals(listDeque.size(),3);
        }

        @Test
        @DisplayName("Deleting last item from four item queue makes 3 items queue")
        public void deletingLastItemFromAFourItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.deleteLast();
            assertEquals(listDeque.last(),3);
            assertEquals(listDeque.size(),3);
        }
    }

    @Nested
    @DisplayName("Test cases for get(index) methods")
    class getMethodTests{

        @Test
        @DisplayName("First element from an empty list throws an exception")
        public void checkIfFirstItemFromAnEmptyListThrowsAnException(){
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.get(1));
        }

        @Test
        @DisplayName("Fifth element from 3 items list means exception")
        public void checkIfFifthItemFromThreeItemsListThrowsAnException(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.get(5));
        }

        @Test
        @DisplayName("Third element from [2, 4, 1, 7, 8, 3] (which size is even) is 1")
        public void checkThirdElementFromAnEvenSizeListIsCorrect(){
            listDeque.append(2);
            listDeque.append(4);
            listDeque.append(1);
            listDeque.append(7);
            listDeque.append(8);
            listDeque.append(3);

            int expected = 1;
            int actual = listDeque.get(2);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Third element from [2, 4, 9, 1, 7, 8, 3] (which size is uneven) is 1")
        public void checkFourthElementFromAnUnevenSizeListIsCorrect(){
            listDeque.append(2);
            listDeque.append(4);
            listDeque.append(9);
            listDeque.append(1);
            listDeque.append(7);
            listDeque.append(8);
            listDeque.append(3);

            int expected = 1;
            int actual = listDeque.get(3);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Sixth element from [2, 4, 1, 7, 8, 3] is 3")
        public void checkWhetherItReturnsSixthItemCorrectly(){
            listDeque.append(2);
            listDeque.append(4);
            listDeque.append(1);
            listDeque.append(7);
            listDeque.append(8);
            listDeque.append(3);

            int expected = 3;
            int actual = listDeque.get(5);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("First item from single item list is that one item")
        public void checkIfFirstItemFromASingleItemListIsThatOneItem(){
            listDeque.append(3);

            int expected = listDeque.first();
            int actual = listDeque.get(0);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Last item from single item list is that one item")
        public void checkIfLastItemFromASingleItemListIsThatOneItem(){
            listDeque.append(3);

            int expected = listDeque.last();
            int actual = listDeque.get(0);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("You cannot get an item from negative index")
        public void checkThatNegativeIndexIsInvalid(){
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.get(-1));
        }

        @Test
        @DisplayName("You cannot get an item from an index bigger than size")
        public void checkThatIndexBiggerThanSizeIsInvalid(){
            listDeque.append(1);
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.get(3));
        }
    }

    @Nested
    @DisplayName("Tests cases for contains(value)")
    class checkingWhetherAListContainsAnItem{

        @Test
        @DisplayName("Check that an empty list does not contain anything")
        public void checkEmptyListContainsNothing(){
            boolean actual = listDeque.contains(1);

            boolean expected = false;

            assertEquals(expected,actual);

        }

        @Test
        @DisplayName("Check that 1 is not in {5,6,7}")
        public void check3ItemsListDoesNotContainAnItem(){
            listDeque.append(5);
            listDeque.append(6);
            listDeque.append(7);

            boolean actual = listDeque.contains(1);

            boolean expected = false;

            assertEquals(expected,actual);

        }

        @Test
        @DisplayName("Check that 5 is in {5,6,7}")
        public void check3ItemsListContainsAnItem(){
            listDeque.append(5);
            listDeque.append(6);
            listDeque.append(7);

            boolean actual = listDeque.contains(5);

            boolean expected = true;

            assertEquals(expected,actual);

        }

        @Test
        @DisplayName("Check that 6 is in [1,5,6,7]")
        public void checkIfAnItemIsInAFourElementsList(){
            listDeque.append(1);
            listDeque.append(5);
            listDeque.append(6);
            listDeque.append(7);

            assertTrue(listDeque.contains(6));
        }

        @Test
        @DisplayName("Checks that a list contains null value")
        public void checkIfAListContainsNullValue(){
            listDeque.append(null); listDeque.append(null);
            listDeque.append(1);

            assertTrue(listDeque.contains(null));
        }
    }

    @Nested
    @DisplayName("Tests cases for remove(value)")
    class checkingWhetherItRemovesAnItemCorrectly{

        @Test
        @DisplayName("Removes correctly item in first position")
        void removesCorrectlyAnItemInFirstPosition(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);

            listDeque.remove(1);
            assertEquals(2, listDeque.get(0));
            assertEquals(3, listDeque.get(1));
        }

        @Test
        @DisplayName("Removes null value item from a non empty list")
        void checksIfItRemovesNullValueItemFromANonEmptyList(){
            listDeque.append(8);
            listDeque.append(null);
            listDeque.append(1);

            DoubleEndedQueue<Integer> actual = listDeque;
            actual.remove(null);

            int[] expected = new int[2];
            expected[0] = 8;
            expected[1] = 1;

            assertEquals(expected[0], actual.get(0));
            assertEquals(expected[1], actual.get(1));
        }

        @Test
        @DisplayName("Removes item that is not null from list that contains null items")
        void checkItRemovesANonNullItemFromAListThatContainsNullItems(){
            listDeque.append(null);
            listDeque.append(null);
            listDeque.append(1);

            listDeque.remove(1);

            assertNull(listDeque.get(0));
            assertNull(listDeque.get(1));
        }

        @Test
        @DisplayName("Removes correctly from a 5 items list")
        void checksIfItRemovesAnItemFromA5itemsListCorrectly(){
            listDeque.append(1);
            listDeque.append(5);
            listDeque.append(6);
            listDeque.append(7);
            listDeque.append(8);

            DoubleEndedQueue<Integer> actual = listDeque;
            actual.remove(6);


            DoubleEndedQueue<Integer> expected = new DoublyLinkedListDeque<>();
            expected.append(1);
            expected.append(5);
            expected.append(7);
            expected.append(8);


            for(int i = 0; i < actual.size(); i++){
                assertEquals(expected.get(i), actual.get(i));
            }
        }

        @Test
        @DisplayName("Cannot remove an item that is not in the list")
        void anItemWhichIsNotInTheListCantBeRemoved(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);

            DoubleEndedQueue<Integer> actual = listDeque;
            actual.remove(4);

            int[] expected = new int[3];
            expected[0] = 1;
            expected[1] = 2;
            expected[2] = 3;

            assertEquals(expected[0],actual.get(0));
            assertEquals(expected[1],actual.get(1));
            assertEquals(expected[2],actual.get(2));
        }
    }

    @Nested
    @DisplayName("Test cases for sort() method")
    class checkingIfA5ItemsListSortsCorrectly{

        @Test
        @DisplayName("Result of sorting an empty list is an empty list")
        void checkIfSortingAnEmptyListResultsInAnEmptyList(){
            listDeque.sort(Comparator.naturalOrder());

            assertEquals(0,listDeque.size());
        }

        @Test
        @DisplayName("Result of sorting a single item list is the same list")
        void checkIfSortingAnSingleItemListResultsTheSameList(){
            listDeque.append(1);
            listDeque.sort(Comparator.naturalOrder());
            DoubleEndedQueue<Integer> actual = listDeque;

            DoubleEndedQueue<Integer> expected = new DoublyLinkedListDeque<>();
            expected.append(1);

            assertEquals(expected.get(0),actual.get(0));
        }
        @Test
        @DisplayName("Sorts a 5 items list correctly")
        void checkIfItSortsA5ItemsListCorrectly(){
            listDeque.append(2);
            listDeque.append(5);
            listDeque.append(3);
            listDeque.append(1);
            listDeque.append(4);

            listDeque.sort(Comparator.naturalOrder());

            DoubleEndedQueue<Integer> actual = listDeque;


            int[] expected = new int[5];
            expected[0] = 1;
            expected[1] = 2;
            expected[2] = 3;
            expected[3] = 4;
            expected[4] = 5;

            assertEquals(expected[0],actual.get(0));
            assertEquals(expected[1],actual.get(1));
            assertEquals(expected[2],actual.get(2));
            assertEquals(expected[3],actual.get(3));
            assertEquals(expected[4],actual.get(4));
        }

        @Test
        @DisplayName("Sorts a 5 items list correctly when getting null comparator")
        void checkIfItSortsA5ItemsListCorrectlyWhenArgumentIsNull(){
            listDeque.append(2);
            listDeque.append(5);
            listDeque.append(3);
            listDeque.append(1);
            listDeque.append(4);

            listDeque.sort(null);

            DoubleEndedQueue<Integer> actual = listDeque;


            int[] expected = new int[5];
            expected[0] = 1;
            expected[1] = 2;
            expected[2] = 3;
            expected[3] = 4;
            expected[4] = 5;

            assertEquals(expected[0],actual.get(0));
            assertEquals(expected[1],actual.get(1));
            assertEquals(expected[2],actual.get(2));
            assertEquals(expected[3],actual.get(3));
            assertEquals(expected[4],actual.get(4));
        }
    }
}
