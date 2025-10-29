package lab6.linkedlist;

public interface MyList<E> extends java.lang.Iterable<E> {
  public void add(E e);
  public void add(int index, E e);
  public void clear();
  public boolean contains(Object e);
  public E get(int index);
  public int indexOf(Object e);
  public int lastIndexOf(E e);
  public boolean remove(E e);
  public E remove(int index);
  public E set(int index, E e);
  public int size();
}


