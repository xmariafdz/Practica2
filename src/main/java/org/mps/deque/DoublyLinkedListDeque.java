package org.mps.deque;

import java.util.Comparator;
import java.util.Objects;

public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque() {
        first = last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        DequeNode<T> firstNode = new DequeNode<>(value, null, this.first);
        if(this.first != null){
            this.first.setPrevious(firstNode);
        }
        if(this.first == null){
            this.last = firstNode;
        }
        this.first = firstNode;
        size++;
    }

    @Override
    public void append(T value) {
        DequeNode<T> lastNode = new DequeNode<>(value, this.last, null);
        if(this.last != null){
            this.last.setNext(lastNode);
        }
        if(this.first == null){
            this.first = lastNode;
        }
        this.last = lastNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        if(this.first == null) throw new DoubleEndedQueueException("La lista está vacía");
        this.first = this.first.getNext();
        size--;
        if(size == 0){
            this.last = null;
        }
    }

    @Override
    public void deleteLast() {
        if(this.first == null) throw new DoubleEndedQueueException("La lista está vacía");
        this.last = this.last.getPrevious();
        size--;
        if(size == 0){
            this.first = null;
        }
    }

    @Override
    public T first() {
        if(this.first == null) throw new DoubleEndedQueueException("La lista está vacía");
        return this.first.getItem();
    }
    public T last() {
        if(this.first == null) throw new DoubleEndedQueueException("La lista está vacía");
        return this.last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size()) throw new DoubleEndedQueueException("Invalid index");

        int half = size()/2;
        DequeNode<T> node;

        if(index <= half){
            node = first;
            for (int i = 0; i < index; i++)  node = node.getNext();
        } else {
            node = last;
            for(int i = (size()-1); i > index; i--) node = node.getPrevious();
        }
        return node.getItem();
    }

    private DequeNode<T> search(T value){
        DequeNode<T> node = first;
        boolean stop = false;
        while(node != null && !stop){
           // stop = node.getItem().equals(value);
            stop = Objects.equals(node.getItem(),value);
            if(!stop) node = node.getNext();
        }
        return node;
    }
    @Override
    public boolean contains(T value) {
        return search(value) != null;
    }

    @Override
    public void remove(T value) {
        DequeNode node = search(value);
        if(node != null){
            if(node.getPrevious() == null){
                deleteFirst();
            }else if(node.getNext() == null){
                deleteLast();
            } else {
                DequeNode nodoAnterior = node.getPrevious();
                DequeNode nodoPosterior = node.getNext();

                nodoAnterior.setNext(nodoPosterior);
                nodoPosterior.setPrevious(nodoAnterior);

            }
            size--;
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size() <= 1) {
            return;
        }
        if(comparator == null){
            comparator = (Comparator<? super T>) Comparator.naturalOrder();
        }
        DequeNode<T> pivot = first;
        DoublyLinkedListDeque<T> lowerHalf = new DoublyLinkedListDeque<>();
        DoublyLinkedListDeque<T> greaterHalf = new DoublyLinkedListDeque<>();
        DequeNode<T> currentNode = first.getNext();

        while (currentNode != null) {
            int cmp = comparator.compare(currentNode.getItem(), pivot.getItem());
            if (cmp >= 0) {
                greaterHalf.append(currentNode.getItem());
            } else {
                lowerHalf.append(currentNode.getItem());
            }
            currentNode = currentNode.getNext();
        }

        lowerHalf.sort(comparator);
        greaterHalf.sort(comparator);

        if (lowerHalf.size() > 0) {
            first = lowerHalf.first;
            lowerHalf.last.setNext(pivot);
            pivot.setPrevious(lowerHalf.last);
        } else {
            first = pivot;
        }

        if (greaterHalf.size() > 0) {
            pivot.setNext(greaterHalf.first);
            greaterHalf.first.setPrevious(pivot);
            last = greaterHalf.last;
        } else {
            last = pivot;
        }
    }
}
