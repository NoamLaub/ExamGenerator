package module;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList implements Cloneable{
	private int max = 8;
	private Test a[] = new Test[max];
	private int count = 0;
	private HashSet<Observer> set = new HashSet<Observer>();

	public void add(Test value) {
		if (count == max)
			a = Arrays.copyOf(a, a.length * 2);
		a[count++] = value;
		max = a.length;
	}

	public int getMax() {
		return max;
	}

	public Iterator<Test> iterator() {
		myNotify();
		return new ConcreteIterator();
	}
	// addListener

	public void attach(Observer o) {
		set.add(o);
	}

	public void detach(Observer o) {
		set.remove(o);
	}

	public void myNotify() {
		for (Observer o : set) {
			o.update();
		}
	}
	public MyArrayList clone() throws CloneNotSupportedException {
		MyArrayList o = null;
		o = (MyArrayList) super.clone();
		return o;
	}

	private class ConcreteIterator implements Iterator<Test> {
		private int cur = -1;
		private boolean ableToRemove = false;

		@Override
		public boolean hasNext() {
			return cur < count-1;
		}

		@Override
		public Test next() {
			if (!hasNext())
				throw new NoSuchElementException();
			ableToRemove = true;
			return a[++cur];
		}

		public void remove() {
			if (!ableToRemove)
				throw new IllegalStateException();
			
			for (int i = cur; i < count - 1; i++) {
				a[i] = a[i + 1];
			}
			count--;
			cur--;
			
			ableToRemove = false;
		}

	}

}
