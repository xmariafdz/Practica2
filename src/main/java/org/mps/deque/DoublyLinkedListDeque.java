package org.mps.deque;

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
}
