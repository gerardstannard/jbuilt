/*
 *   Copyright 2010 Gerard Stannard
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 */

/**
 * 
 * @author Gerard Stannard
 *
 */

package org.jbuilt.utils;
/**
 * A number of classes and interfaces centered around the idea of adding functionality
 * to the ArrayList class without extending it with
 * inheritance or otherwise polluting it.  Most notable is the
 * ability to pass in a "closure" to the new each() method and have that closure do
 * the defined operation on each item in the list
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Although called ExtendedArrayList this class is actually composed with an ArrayList, for all methods in ArrayList, it
 * simply delegates to those. So for those methods, is just as safe as an ArrayList. It also adds some convenience and
 * utility methods for ripping through the list and invoking actions on each item.
 * 
 * @author gerards
 * @param <E>
 */
public class ExtendedArrayList<E> implements ExtendedList<E> {
    private List<E> list = new ArrayList<E>();
    private Ternary<List<E>> listTernary = new Ternary<List<E>>();
    private Ternary<Boolean> booleanTernary = new Ternary<Boolean>();

    private EachClosure<E> eachClosure(List<E> list2) {
        return new EachClosure<E>(list2);
    }

    public ExtendedArrayList(List<E> list) {
        this.list = list;
    }

    public ExtendedArrayList() {
    }

    public void each(Processor<E> processor) {
        for (E item : list) {
            processor.process(item);
        }
    }

    public void each(Process<E> process) {
        EachClosure<E> eachClosure = eachClosure(list);
        eachClosure.setProcess(process);
        eachClosure.execute();
    }
    
    // not complete, doesn't take into account left over items
    public ExtendedList<E> union(List<E> otherList) {

        List<E> iterateList;
        List<E> biggerList;
        iterateList = listTernary.execute(list.size() > otherList.size(), otherList, list);
        biggerList = listTernary.execute(iterateList.equals(list), list, otherList);
        final ExtendedList<E> iterateListTemp = new ExtendedArrayList<E>();
        final ExtendedList<E> biggerListTemp = new ExtendedArrayList<E>();
        for(int i=0; i < iterateListTemp.size(); i++){
        	iterateList.add(iterateList.get(i));
        }
        for(int i=0; i < biggerListTemp.size(); i++){
        	biggerListTemp.add(biggerList.get(i));
        }

        ExtendedArrayList<E> returnList = new ExtendedArrayList<E>();
        int i;
        Predicate<E> anyPredicate = new Predicate<E>(){
            private List<E> otherList;
            {
                otherList = biggerListTemp;
            }

            public boolean evaluate(E item) {
                return item.equals(otherList.get(iterateListTemp.indexOf(item)));
            }

            public List<E> getOtherList() {
                return otherList;
            }

            public void setOtherList(List<E> otherList) {
                this.otherList = otherList;
            }

        };
        
        List<E> sameList = new ExtendedArrayList<E>();
        List<E> differentList = new ExtendedArrayList<E>();
        

        for (E item : iterateList) {
            if (anyPredicate.evaluate(item)) {
                sameList.add(item);
            } else {
            	differentList.add(item);
            }
        }
        return (ExtendedList<E>) differentList;
    }

    // not complete, doesn't take into account left over items
    public ExtendedList<E> intersect(List<E> otherList) {

        List<E> iterateList;
        List<E> biggerList;
        iterateList = listTernary.execute(list.size() > otherList.size(), otherList, list);
        biggerList = listTernary.execute(iterateList.equals(list), list, otherList);
        final ExtendedList<E> iterateListTemp = new ExtendedArrayList<E>();
        final ExtendedList<E> biggerListTemp = new ExtendedArrayList<E>();
        for(int i=0; i < iterateListTemp.size(); i++){
        	iterateList.add(iterateList.get(i));
        }
        for(int i=0; i < biggerListTemp.size(); i++){
        	biggerListTemp.add(biggerList.get(i));
        }

        ExtendedArrayList<E> returnList = new ExtendedArrayList<E>();
        int i;
        Predicate<E> anyPredicate = new Predicate<E>(){
            private List<E> otherList;
            {
                otherList = biggerListTemp;
            }

            public boolean evaluate(E item) {
                return item.equals(otherList.get(iterateListTemp.indexOf(item)));
            }

            public List<E> getOtherList() {
                return otherList;
            }

            public void setOtherList(List<E> otherList) {
                this.otherList = otherList;
            }

        };
        
        List<E> sameList = new ExtendedArrayList<E>();
        List<E> differentList = new ExtendedArrayList<E>();
        

        for (E item : iterateList) {
            if (anyPredicate.evaluate(item)) {
                sameList.add(item);
            } else {
            	differentList.add(item);
            }
        }
        return (ExtendedList<E>) sameList;
    }


    public Boolean any(Predicate<E> predicate) {
        for (E item : list) {
            Boolean returnValue =
            	booleanTernary.execute(predicate.evaluate(item), Boolean.TRUE, Boolean.FALSE);
            return returnValue;
        }
        return false;
    }
    
    public boolean every(Predicate<E> predicate) {
        for (E item : list) {
            Boolean returnValue =
            	booleanTernary.execute(!predicate.evaluate(item), Boolean.TRUE, Boolean.FALSE);
            if(returnValue == false) {
                return returnValue;
            }
        }
        return true;
    }

    public ExtendedList<E> minus(E item) {
        list.remove(item);
        ExtendedArrayList<E> newList = new ExtendedArrayList<E>(list);
        list = null;
        return newList;
    }

    public ExtendedList<E> plus(E item) {
        list.add(item);
        return (ExtendedList<E>) list;
    }

    public E first() {
        return list.get(0);
    }

    public E last() {
        return list.get(list.size() - 1);
    }

    /********************************************************
     * Closures ie. inner classes that either directly or indirectly implement Closure and have an execute() method, for
     * test and demonstration purposes
     ********************************************************/

    @SuppressWarnings("unchecked")
    private static class PrintToStringProcess implements Process {
        Object item;

        public PrintToStringProcess(Object item) {
            this.item = item;
        }

        public PrintToStringProcess() {
        }

        public void execute(Object item) {
            ConsolePrinter.println(item.toString());
        }

        public void execute() {
            if (item != null) {
                execute(item);
            }
        }

		public Object execute(Object... args) {
			// TODO Auto-generated method stub
			return null;
		}
    }

    private static class ToUpperCaseProcess<T> implements Process<T> {
        T item;
        String state = "Alive!";

        public ToUpperCaseProcess(T item) {
            this.item = item;
        }

        public ToUpperCaseProcess() {
        }

        public void execute(T item) {
            System.out.println("item was " + item + " but now is " + ((String) item).toUpperCase());
        }

        public void execute() {
            if (item != null) {
                execute(item);
            }
        }

        @Override
        public String toString() {
            return "ToUpperCaseProcess's state is " + state;
        }

		public Object execute(Object... args) {
			// TODO Auto-generated method stub
			return null;
		}
    }

    /********************************************************
     * Factory methods that create closures *
     ********************************************************/

    @SuppressWarnings("unchecked")
    public static Process printToStringProcess() {
        return new PrintToStringProcess();
    }

    public static Process<String> toUpperCaseProcess() {
        return new ToUpperCaseProcess<String>();
    }

    @SuppressWarnings("unchecked")
    public static Processor processor(final Process process) {
        return new Processor(){
            public void process(Object item) {
                process.execute(item);
            }

			public Object execute(Object... args) {
				// TODO Auto-generated method stub
				return null;
			}
        };
    }

    @SuppressWarnings("unchecked")
    public static void printAll(ExtendedList list) {
        list.each(printToStringProcess());
    }

    /********************************************************
     * Methods that currently exist in the List interface *
     ********************************************************/

    public boolean addAll(E... elements) {
        return Collections.addAll(list, elements);

    }

    public ExtendedList<E> addAll(List<E> otherList) {
        for (E item : otherList) {
            list.add(item);
        }
        return (ExtendedList<E>) list;
    }

    public void shuffle() {
        Collections.shuffle(list);
    }

    public boolean add(E o) {
        return list.add(o);
    }

    public void add(int index, E element) {
        list.add(index, element);
    }

    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    public void clear() {
        list.clear();
    }

    // public void clone() {
    // if(!list.isEmpty())
    // list.clone();
    // }
    public boolean contains(Object elem) {
        return list.contains(elem);
    }

    public void ensureCapacity(int minCapacity) {
        ((ArrayList<E>) list).ensureCapacity(minCapacity);
    }

    public E get(int index) {
        return list.get(index);
    }

    public int indexOf(Object elem) {
        return list.indexOf(elem);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int lastIndexOf(Object elem) {
        return list.lastIndexOf(elem);
    }

    public E remove(int index) {
        return list.remove(index);
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    // public void removeRange(int fromIndex, int toIndex){
    // ((ArrayList<E>) list).removeRange(fromIndex, toIndex);
    // }
    public E set(int index, E element) {
        return list.set(index, element);
    }

    public int size() {
        return list.size();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public void trimToSize() {
        ((ArrayList<E>) list).trimToSize();
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(int num, Collection<? extends E> c) {
        return list.addAll(num, c);
    }

    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    public List<E> subList(int begin, int end) {
        return list.subList(begin, end);
    }

    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    public ListIterator<E> listIterator(int num) {
        return list.listIterator(num);
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
    	Printer printer = new Printer(true, true);
        ExtendedList<String> stringList = new ExtendedArrayList<String>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        stringList.add("four");
        stringList.add("five");

        ExtendedList<String> otherStringList = new ExtendedArrayList<String>();
        stringList.add("a");
        stringList.add("three");
        stringList.add("b");
        stringList.add("four");
        stringList.add("e");
        
       
//        List<String> union = stringList.union(otherStringList);
//        printer.execute("this is a union of stringList and otherStringList");
//        for(String string : union){
//        	System.out.println(string);
//        }


        // stringList.each(new CollectionProcessor<String>(){
        // public void process(String item){
        // System.out.println("item was " + item + " but now is " + item.toUpperCase());
        // }
        // });

        // stringList.each( processor( toUpperCaseProcess() ) );
        Process toUpperCaseProcess = toUpperCaseProcess();
        stringList.each(toUpperCaseProcess);
        ConsolePrinter.println(toUpperCaseProcess.toString());

        printer.execute("list.first() should be one and is ", stringList.first());
        printer.execute("list.last() should be three and is ",  stringList.last());

        ExtendedList<String> newList = stringList.minus("one");
        System.out.println("after minus()");

        newList.each(new Processor<String>(){
            public void process(String item) {
                System.out.println("item was " + item.toUpperCase() + " but now is " + item.toLowerCase());
            }

			public Object execute(Object... args) {
				// TODO Auto-generated method stub
				return null;
			}
        });

        ExtendedList<Integer> intList = new ExtendedArrayList<Integer>();
        intList.addAll(1, 2, 3, 117, 445);
        ConsolePrinter.println("before shuffle");
        intList.each(new Processor<Integer>(){
            public void process(Integer item) {
                ConsolePrinter.println(item.toString());
            }

			public Object execute(Object... args) {
				// TODO Auto-generated method stub
				return null;
			}
        });

        intList.shuffle();
        ConsolePrinter.println("after shuffle");
        intList.each(printToStringProcess());

    }

}

/********************************************************
 * Interface that adds methods for ExtendedList to implement
 ********************************************************/

interface ExtendedList<E> extends java.util.List<E> {

    public void each(Processor<E> processor);

    public ExtendedList<E> minus(E item);

    public ExtendedList<E> union(List<E> list2);

    public Boolean any(Predicate<E> predicate);

    public E first();

    public E last();

    public boolean addAll(E... element);

    public ExtendedList<E> addAll(List<E> otherList);

    public void shuffle();

    // public <T>T as(T collection);
    public void each(Process<E> process);

}

/********************************************************
 * Process used internally by EachClosure for iterating through the List
 ********************************************************/

@SuppressWarnings("unchecked")
class EachClosure<E> implements ParameterizedClosure {
    private Collection<E> eachList;
    private Processor<E> processor = new Processor(){
        public void process(Object item) {
            process.execute((E) item);
        }

		public Object execute(Object... args) {
			// TODO Auto-generated method stub
			return null;
		}
    };

    private Process<E> process = new IterateProcess<E>(eachList, processor);

    public EachClosure(Collection<E> eList) {
        this.eachList = eList;
    }

    public EachClosure() {
    }

    public void execute(E obj) {
        process.execute(obj);
    }

    public void execute() {
        if (eachList != null) {
            for (E item : eachList) {
                process.execute(item);
            }
        } else {
            ConsolePrinter.println("list was null");
        }
    }

    public Collection<E> getEachList() {
        return eachList;
    }

    public Processor<E> getProcessor() {
        return processor;
    }

    public Process<E> getProcess() {
        return process;
    }

    public void setEachList(Collection<E> eachList) {
        this.eachList = eachList;
    }

    public void setProcessor(Processor<E> processor) {
        this.processor = processor;
    }

    public void setProcess(Process<E> process) {
        this.process = process;
    }

    public Object execute(Object... obj) {
        throw new UnsupportedOperationException("included to satisfy interface, use execute() instead");
    }

}

/********************************************************
 * Process used internally by EachClosure for iterating through the List
 ********************************************************/

class IterateProcess<E> implements Process<E> {
    private Collection<E> iterateList;
    private Processor<E> processor;

    public IterateProcess(Collection<E> aList, Processor<E> processor) {
        this.iterateList = aList;
        this.processor = processor;
    }

    public IterateProcess() {
    }

    public void execute(Collection<E> collection) {
        this.iterateList = collection;
        if (iterateList != null) {
            if (processor != null) {
                for (E iterateItem : iterateList) {
                    execute(iterateItem);
                }
            } else {
                ConsolePrinter.println("processor was null");
            }
        } else {
            ConsolePrinter.println("iterateList was null");
        }
    }

    public void execute(E itemToProcess) {
        processor.process(itemToProcess);
    }

    public Collection<E> getIterateList() {
        return iterateList;
    }

    public Processor<E> getProcessor() {
        return processor;
    }

    public void setIterateList(Collection<E> iterateList) {
        this.iterateList = iterateList;
    }

    public void setProcessor(Processor<E> processor) {
        this.processor = processor;
    }

	public Object execute(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}

/********************************************************
 * Closure interfaces that define execute actions to call on processes, or process actions to call on processors *
 ********************************************************/

interface ParameterizedClosure<E> extends Closure {
    public Object execute(E... obj);
}

interface Processor<E> extends Closure {
    public void process(E itemToProcess);
}

interface Process<E> extends Closure {
    public void execute(E itemToProcess);
}

interface Predicate<E> {
    public boolean evaluate(E object);
}

/********************************************************
 * Utilities
 ********************************************************/

class ConsolePrinter {
    public static void print(String... strings) {
        for (String str : strings) {
            System.out.print(str);
        }
    }

    public static void println(String... strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }
}

class Printer implements Closure {
	public static final String SPACE = " ";
	private Boolean linebreak;
	private Boolean space;
	
	public Printer(){
		super();
	}
	
	public Printer(Boolean linebreak, Boolean space){
		this.linebreak = linebreak;
		this.space = space;
	}
	public void execute(String... strings){
		StringBuilder sb = new StringBuilder();
		for(String string : strings){
			if(linebreak && space){
				sb.append(string).append(SPACE);
				System.out.println(sb.toString());
			} else if(linebreak && !space){
				sb.append(string);
				System.out.println(sb.toString());
			} else if(!linebreak && !space){
				sb.append(string);
				System.out.print(sb.toString());
			} else if(!linebreak && space){
				sb.append(string).append(SPACE);
				System.out.print(sb.toString());
			}
		}
	}

	public Object execute(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
}

class Println implements Closure {
	public void execute(String... strings){
		StringBuilder sb = new StringBuilder();
		for(String string : strings){
			sb.append(string);
		System.out.println(sb.toString());
		}
	}

	public Object execute(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
}


class Ternary<T> implements Closure {

    public T execute(boolean test, T resultIfTrue, T resultIfFalse) {
        if (test) {
            return resultIfTrue;
        } else {
            return resultIfFalse;
        }

    }

	public Object execute(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
}
