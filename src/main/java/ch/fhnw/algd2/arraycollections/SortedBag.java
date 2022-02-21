package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Objects;

public class SortedBag<E extends Comparable<? super E>> extends AbstractArrayCollection<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	int size = 0;

	public SortedBag() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedBag(int capacity) {
		data = (E[])new Comparable[capacity];
	}

	@Override
	public boolean add(E e) {
		Objects.requireNonNull(e);

		int insertIndex = find(e);
		if (insertIndex < 0) {
			insertIndex = Math.abs(insertIndex) - 1;
		}

		if (insertIndex >= data.length) {
			throw new IllegalStateException();
		}

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
		SortedBag<Integer> bag = new SortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
