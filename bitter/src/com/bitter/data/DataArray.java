package com.bitter.data;

import java.io.DataInputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.bitter.data.interfaces.IDataProgress;
import com.bitter.data.interfaces.IFastArrayTransform;

public class DataArray<T> implements List<T>, IFastArrayTransform<T> {

	private final int MinimumSize = 1024;
	private final int MaximumSize = 8192;

	@Override
	public Object[] addRow(T row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean WriteValue(Writer writer, Object row, int chunckCount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] CompleteTransformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	private class ChunkDataReadOnlyList implements IChunkData<T> {

		private List<T> data = new ArrayList<T>(MaximumSize);

		public ChunkDataReadOnlyList(DataInputStream reader, DataSerialize serializer, int count){
			for (int i = 0; i < count; i++) {
				data.add((T)serializer.Deserialize(reader));
			}
		}
		
		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return data.iterator();
		}

		@Override
		public void Delete() {
			// TODO Auto-generated method stub

		}

		@Override
		public void Save() {
			// TODO Auto-generated method stub

		}

		@Override
		public void Add(Object val) {
			// TODO Auto-generated method stub

		}

		@Override
		public void RemoveAt(int index) {
			// TODO Auto-generated method stub

		}

		@Override
		public void Insert(int index, Object item) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean Contains(Object item) {
			// TODO Auto-generated method stub
			return data.contains(item);
		}

		@Override
		public void AddRange(Iterable data) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean Remove(Object item) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int IndexOf(Object item) {
			// TODO Auto-generated method stub
			return data.indexOf(item);
		}

		@Override
		public void Sort(Comparator comparer) {
			// TODO Auto-generated method stub

		}

		@Override
		public void RemoveAfter(int size) {
			// TODO Auto-generated method stub

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public boolean getChanged() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T get(int index) {
			// TODO Auto-generated method stub
			return data.get(index);
		}

		@Override
		public void set(int index, Object value) {
			// TODO Auto-generated method stub
		}

	}

	private class ChunkDataList implements IChunkData<T>{

		private List<T> data = new ArrayList<T>(MaximumSize);
		private DataSerialize serializer;
		private String fileName;
		private boolean sorted;
		private boolean changed;
		private boolean unregistered;		
		
		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void Add(Object arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void AddRange(Iterable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean Contains(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void Delete() {
			// TODO Auto-generated method stub
			if(fileName!=null)
				fileName = null;
			if(!unregistered)
			{
				try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				unregistered = true;
			}
		}

		@Override
		public int IndexOf(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void Insert(int arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean Remove(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void RemoveAfter(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void RemoveAt(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void Save() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void Sort(Comparator arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public T get(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean getChanged() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void set(int arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private interface IChunkData<T> extends Iterable<T> {
		void Delete();

		void Save();

		void Add(T val);

		void RemoveAt(int index);

		void Insert(int index, T item);

		boolean Contains(T item);

		void AddRange(Iterable data);

		boolean Remove(T item);

		int IndexOf(T item);

		void Sort(Comparator<T> comparer);

		void RemoveAfter(int size);

		int getCount();

		boolean getChanged();

		T get(int index);

		void set(int index, Object value);
	}
}

interface IDataArraySortProgress<T> extends IDataProgress {
	boolean continueMerging(List<Iterable<T>> remainingChunks);
}
