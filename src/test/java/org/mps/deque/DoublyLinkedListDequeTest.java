package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Maria Fernández Moreno
 * @author Carlos Castaño Moreno
 */

/*
    Test cases
    1. size of empty queue -> 0
    2. size of one item queue -> 1
    3. size of three items queue -> 3
    4. first item of empty queue -> exception
    5. last item of empty queue -> exception
    6. when one item list, first item = last item
    7. first item of {1-2} is 1
    8. last item of {1-2} is 2
    9. first item of {1-2-3-4-5}


 */
public class DoublyLinkedListDequeTest {
    DoublyLinkedListDeque listDeque;
    @BeforeEach
    public void beforeEach(){
        listDeque = new DoublyLinkedListDeque<>();
    }
    @AfterEach
    void shutdown() {
        listDeque = new DoublyLinkedListDeque<>();
    }
    @Nested
    @DisplayName("Tests for size method")
    class QueuesSizeTests{

        @Test
        public void sizeOfAnEmptyListIsZero(){
            assertEquals(listDeque.size(),0);
        }

        @Test
        public void sizeOfAnOneItemListIsOne(){
            listDeque.append(1);
            assertEquals(listDeque.size(),1);
        }

        @Test
        public void sizeOfAThreeItemsListIsThree(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            assertEquals(listDeque.size(),3);
        }
    }

    @Nested
    @DisplayName("Tests for first and last item methods")
    class FirstAndLastItemTests{

        @Test
        public void firstItemOfAnEmptyQueueIsNull(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.first());
        }

        @Test
        public void lastItemOfAnEmptyQueueIsNull(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.last());
        }

        @Test
        public void firstAndlastItemOfAnOneItemListAreTheSame(){
            listDeque.append(1);
            assertEquals(listDeque.first(),listDeque.last());
        }

        @Test
        public void firstItemOnATwoItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            assertEquals(listDeque.first(),1);
        }

        @Test
        public void lastItemOnATwoItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            assertEquals(listDeque.last(),2);
        }
        @Test
        public void firstItemOnAFiveItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.append(5);
            assertEquals(listDeque.first(),1);
        }

        @Test
        public void lastItemOnAFiveItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.append(5);
            assertEquals(listDeque.last(),5);
        }

        @Test
        public void firstItemWhenPrepending(){
            listDeque.append(5);
            listDeque.prepend(6);
            assertEquals(listDeque.first(),6);
        }

        @Test
        public void lastItemWhenPrepending(){
            listDeque.append(5);
            listDeque.prepend(6);
            assertEquals(listDeque.last(),5);
        }

        @Test
        public void firstItemEqualsLastItemWhenAppendingToAEmptyList(){
            listDeque.append(1);
            assertEquals(listDeque.first(),listDeque.last());
        }

        @Test
        public void firstItemEqualsLastItemWhenPreppendingToAEmptyList(){
            listDeque.prepend(1);
            assertEquals(listDeque.first(),listDeque.last());
        }
    }

    @Nested
    @DisplayName("Deleting items tests")
    public class deletingItemsTests{

        @Test
        public void deletingFirstItemFromAnEmptyList(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.deleteFirst());
        }

        @Test
        public void deletingLastItemFromAnEmptyList(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.deleteLast());
        }

        @Test
        public void deletingFirstItemFromAOneItemList(){
            listDeque.append(1);
            listDeque.deleteFirst();
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.last());
        }

        @Test
        public void deletingLastItemFromAOneItemList(){
            listDeque.append(1);
            listDeque.deleteLast();
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.last());
        }

        @Test
        public void deletingFirstItemFromAFourItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.deleteFirst();
            assertEquals(listDeque.first(),2);
            assertEquals(listDeque.size(),3);
        }

        @Test
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
}
