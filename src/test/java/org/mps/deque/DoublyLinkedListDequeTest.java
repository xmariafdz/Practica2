package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Maria Fernández Moreno
 * @author Carlos Castaño Moreno
 */

/*
    Test cases -> Divided into Tests over queues' size, first and last items and deleting items
    -------------------------------------------------------------------------------------------
    1) Tests for queues' size.
        1.1 Size of empty queue -> 0
        1.2 Size of one item queue -> 1
        1.3 Size of three items queue -> 3

    2) Tests for first and last items
        2.1 First item of empty queue -> exception
        2.2 Last item of empty queue -> exception
        2.3 When one item queue, first item = last item
        2.4 First item of {1-2} is 1
        2.5 Last item of {1-2} is 2
        2.6 First item of {1-2-3-4-5} is 1
        2.7 Last item of {1-2-3-4-5} is 5
        2.8 First item of {5}.prepend(6) is 6
        2.9 Last item of {5}.prepend(6) is 5
        2.10 First item of {}.append(1) = last item of {}.append(1)
        2.11 First item of {}.prepend(1) = last item of {}.prepend(1)

    3) Tests for deleting items
        3.1 Deleting first item from an empty queue throws an exception.
        3.2 Deleting last item from an empty queue throws an exception.
        3.3 Deleting first item from a single item queue makes an empty queue
        3.4 Deleting last item from a single item queue makes an empty queue
        3.5 Deleting first item from a four item queue makes a 3 items queue and makes next node first
        3.6 Deleting last item from a four item queue makes a 3 items queue and makes next node first

     ------------------------------------------------------------------------------------------------
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
        @DisplayName("Size of empty queue -> 0")
        public void sizeOfAnEmptyListIsZero(){
            assertEquals(listDeque.size(),0);
        }

        @Test
        @DisplayName("Size of one item queue -> 1")
        public void sizeOfASingleItemQueueIsOne(){
            listDeque.append(1);
            assertEquals(listDeque.size(),1);
        }

        @Test
        @DisplayName("Size of three items queue -> 3")
        public void sizeOfAThreeItemsQueueIsThree(){
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
        @DisplayName("First item of empty queue -> exception")
        public void firstItemOfAnEmptyQueueIsNull(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.first());
        }

        @Test
        @DisplayName("Last item of empty queue -> exception")
        public void lastItemOfAnEmptyQueueIsNull(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.last());
        }

        @Test
        @DisplayName("When one item queue, first item = last item")
        public void firstAndlastItemOfASingleItemQueueAreTheSame(){
            listDeque.append(1);
            assertEquals(listDeque.first(),listDeque.last());
        }

        @Test
        @DisplayName("First item of {1-2} is 1")
        public void firstItemOnATwoItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            assertEquals(listDeque.first(),1);
        }

        @Test
        @DisplayName("Last item of {1-2} is 2")
        public void lastItemOnATwoItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            assertEquals(listDeque.last(),2);
        }
        @Test
        @DisplayName("First item of {1-2-3-4-5} is 1")
        public void firstItemOnAFiveItemsList(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            listDeque.append(4);
            listDeque.append(5);
            assertEquals(listDeque.first(),1);
        }

        @Test
        @DisplayName("Last item of {1-2-3-4-5} is 1")
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
    @DisplayName("Deleting items tests")
    public class deletingItemsTests{

        @Test
        @DisplayName("Deleting first item from an empty queue throws an exception.")
        public void deletingFirstItemFromAnEmptyList(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.deleteFirst());
        }

        @Test
        @DisplayName("Deleting last item from an empty queue throws an exception.")
        public void deletingLastItemFromAnEmptyList(){
            assertThrows(DoubleEndedQueueException.class,() -> listDeque.deleteLast());
        }

        @Test
        @DisplayName("Deleting first item from a single item queue makes an empty queue")
        public void deletingFirstItemFromASingleItemQueue(){
            listDeque.append(1);
            listDeque.deleteFirst();
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.last());
        }

        @Test
        @DisplayName("Deleting last item from a single item queue makes an empty queue")
        public void deletingLastItemFromASingleItemQueue(){
            listDeque.append(1);
            listDeque.deleteLast();
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> listDeque.last());
        }

        @Test
        @DisplayName("Deleting first item from a four item queue makes a 3 items queue and makes next node first")
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
        @DisplayName("Deleting last item from a four item queue makes a 3 items queue and makes next node first")
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
