package lab6.linkedlist;

import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E>, Iterable<E> {
  protected Node<E> head, tail;
  protected int size = 0;

  public MyLinkedList() {}

  public MyLinkedList(E[] objects) {
    for (E e : objects) {
      add(e);
    }
  }

  public E getFirst() {
    return (size == 0) ? null : head.element;
  }

  public E getLast() {
    return (size == 0) ? null : tail.element;
  }

  public void addFirst(E e) {
    Node<E> newNode = new Node<>(e);
    newNode.next = head;
    head = newNode;
    size++;
    if (tail == null) tail = head;
  }

  public void addLast(E e) {
    Node<E> newNode = new Node<>(e);
    if (tail == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  @Override
  public void add(E e) {
    addLast(e);
  }

  @Override
  public void add(int index, E e) {
    if (index == 0) {
      addFirst(e);
    } else if (index >= size) {
      addLast(e);
    } else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node<E> temp = current.next;
      current.next = new Node<>(e);
      current.next.next = temp;
      size++;
    }
  }

  public E removeFirst() {
    if (size == 0) return null;
    E temp = head.element;
    head = head.next;
    size--;
    if (head == null) tail = null;
    return temp;
  }

  public E removeLast() {
    if (size == 0) return null;
    if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    Node<E> current = head;
    for (int i = 0; i < size - 2; i++) {
      current = current.next;
    }
    E temp = tail.element;
    tail = current;
    tail.next = null;
    size--;
    return temp;
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) return null;
    if (index == 0) return removeFirst();
    if (index == size - 1) return removeLast();

    Node<E> previous = head;
    for (int i = 1; i < index; i++) {
      previous = previous.next;
    }
    Node<E> current = previous.next;
    previous.next = current.next;
    size--;
    return current.element;
  }

  @Override
  public boolean remove(E e) {
    if (size == 0) return false;
    if (head.element.equals(e)) {
      removeFirst();
      return true;
    }
    Node<E> previous = head;
    Node<E> current = head.next;
    while (current != null) {
      if (current.element.equals(e)) {
        previous.next = current.next;
        if (current == tail) tail = previous;
        size--;
        return true;
      }
      previous = current;
      current = current.next;
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");
    Node<E> current = head;
    while (current != null) {
      result.append(current.element);
      current = current.next;
      if (current != null) result.append(", ");
    }
    result.append("]");
    return result.toString();
  }

  @Override
  public void clear() {
    head = tail = null;
    size = 0;
  }

  @Override
  public boolean contains(Object e) {
    Node<E> current = head;
    while (current != null) {
      if (current.element.equals(e)) return true;
      current = current.next;
    }
    return false;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) return null;
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.element;
  }

  @Override
  public int indexOf(Object e) {
    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      if (current.element.equals(e)) return i;
      current = current.next;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(E e) {
    int lastIndex = -1;
    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      if (current.element.equals(e)) lastIndex = i;
      current = current.next;
    }
    return lastIndex;
  }

  @Override
  public E set(int index, E e) {
    if (index < 0 || index >= size) return null;
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    E old = current.element;
    current.element = e;
    return old;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements Iterator<E> {
    private Node<E> current = head;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove not supported.");
    }
  }

  protected static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
      this.element = element;
    }
  }

  @Override
  public int size() {
    return size;
  }
}
