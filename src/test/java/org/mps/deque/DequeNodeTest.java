package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
  Test cases -> Divided into Tests over a single node, and nodes that have double connections.
  ---------------------------------------------------------------------------------------------
  1) Tests for a single node.
      1.1   Testing a single node containing an integer number.
      1.2   Get method returns the right value.
      1.3   A single node is a terminal one.
      1.4   SetItem() method is correct.
      1.5   A single node is the first and last node.

  2) Test for nodes that have connections.
      2.1   Check that node is not a terminal node.
      2.2   Middle Node is not neither first nor last.
      2.3   Test that a last node is terminal.
  ----------------------------------------------------------------------------------------------
 */

/**
 * @author Maria Fernández Moreno
 * @author Carlos Castaño Moreno
 */


public class DequeNodeTest {

    @Nested
    @DisplayName("Testing a single node containing an integer number")
    public class SingleIntegerNodeTest{
        private DequeNode<Integer> node;

        @BeforeEach
        public void initNode(){
            node = new DequeNode<>(5, null, null);
        }

        @Test
        @DisplayName("Get method returns the right value")
        public void returnedItemByGetMethodIsCorrect(){
            assertEquals(5, node.getItem());
        }

        @Test
        @DisplayName("A single node is a terminal one")
        public void singleNodeImpliesTerminalNode(){
            assertFalse(node.isNotATerminalNode());
            assertNull(node.getPrevious());
            assertNull(node.getNext());
        }

        @Test
        @DisplayName("SetItem() method is correct")
        public void SetItemIsCorrect(){
            int newValue = 7;
            node.setItem(newValue);
            assertThat(node.getItem()).isEqualTo(newValue);

            newValue = 10;
            node.setItem(newValue);
            assertThat(node.getItem()).isEqualTo(newValue);
        }

        @Test
        @DisplayName("A single node is the first and last node")
        public void SingleNodeMeansFirstAndLast(){
            assertTrue(node.isFirstNode());
            assertTrue(node.isLastNode());
        }
    }

    @Nested
    @DisplayName("Tests for a middle node")
    public class MultipleNodeTest {
        private DequeNode<Integer> n1, node, n3;
        @BeforeEach
        public void initialize(){
            /*n1<>n2<>n3*/
            n1 = new DequeNode<>(1, null, null);
            node = new DequeNode<>(2, null, null);
            n3 = new DequeNode<>(3, null, null);

            n1.setNext(node);
            node.setPrevious(n1); node.setNext(n3);
            n3.setPrevious(node);
        }

        @Test
        @DisplayName("Check that node is not a terminal node")
        public void testThatNodeIsNotTerminal(){
            assertTrue(node.isNotATerminalNode());
        }

        @Test
        @DisplayName("Middle Node is not neither first nor last")
        public void middleNodeIsNotNeitherFirstNorLast(){
            assertFalse(node.isFirstNode());
            assertFalse(node.isLastNode());
        }

        @Test
        @DisplayName("Test that a last node is terminal")
        public void testThatALastNodeisTerminal(){
            assertFalse(n3.isNotATerminalNode());
        }
    }
}
