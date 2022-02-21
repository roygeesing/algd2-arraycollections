package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class SortedSet<E extends Comparable<? super E>> extends AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	private int size = 0;

	public SortedSet() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedSet(int capacity) {
		data = (E[])new Comparable[capacity];
	}

	@Override
	public boolean add(E e) {
		Objects.requireNonNull(e);

		int insertIndex = find(e);
		if (insertIndex >= 0) {
			return false;
		}
		insertIndex = Math.abs(insertIndex) - 1;
		if (insertIndex >= data.length) throw new IllegalStateException();

		for (int i = size; i > insertIndex; i--) {
			data[i] = data[i-1];
		}

		data[insertIndex] = e;
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
		return Arrays.binarySearch(data, 0, size, o);
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
		SortedSet<Integer> bag = new SortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
