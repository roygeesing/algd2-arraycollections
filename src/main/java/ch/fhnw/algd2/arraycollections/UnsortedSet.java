package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class UnsortedSet<E> extends AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	private int size = 0;

	public UnsortedSet() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedSet(int capacity) {
		data = (E[])new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		Objects.requireNonNull(e);

		if (find(e) >= 0) {
			return false;
		}

		if (size == data.length) {
			throw new IllegalStateException();
		}

		data[size] = e;
		size++;

		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = find(o);

		if (index < 0) {
			return false;
		}

		for (int i = index; i < size - 1; i++) {
			data[i] = data[i+1];
		}

		size--;
		data[size] = null;

		return true;
	}

	@Override
	public boolean contains(Object o) {
		return find(o) >= 0;
	}

	private int find(Object o) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		UnsortedSet<Integer> bag = new UnsortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
