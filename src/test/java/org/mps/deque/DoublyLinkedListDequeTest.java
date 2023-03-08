package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Maria Fernández Moreno
 * @author Carlos Castaño Moreno
 */

public class DoublyLinkedListDequeTest {
    DoublyLinkedListDeque listDeque;
    @BeforeEach
    public void beforeEach(){
        listDeque = new DoublyLinkedListDeque();
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
        public void sizeOfATwoItemsListIsThree(){
            listDeque.append(1);
            listDeque.append(2);
            listDeque.append(3);
            assertEquals(listDeque.size(),3);
        }
    }
}
