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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;


/**
 * Jbuilt utilities to make life easier
 *
 * @author Gerard Stannard
 */

public final class JbuiltUtils
{
	
	
	
	
	/********************************
	 * Functors
	 */
	
    /**
     * Executes the given closure on each element in the collection.
     * <p>
     * If the input collection or closure is null, there is no change made.
     * 
     * @param collection  the collection to get the input from, may be null
     * @param closure  the closure to perform, may be null
     */
    public static void forEach(Collection collection, Closure closure) {
        if (collection != null && closure != null) {
            for (Iterator it = collection.iterator(); it.hasNext();) {
                closure.execute(it.next());
            }
        }
    }

    /**
     * Executes the given closure on each element in the collection.
     * <p>
     * If the input collection or closure is null, there is no change made.
     * @param <K>
     * @param <V>
     * 
     * @param collection  the collection to get the input from, may be null
     * @param closure  the closure to perform, may be null
     */
    public static <K, V> void forEach(Map<K,V> map, Closure closure) {
    	if (map != null && closure != null) {
    		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
    			closure.execute(it.next());
    		}
    	}
    }

    
	/********************************
	 * Collection initializers that shield us a bit from generic syntax prickleys
	 */
	


	public static final <T> ArrayList<T> newArrayList()
	{
		return new ArrayList<T>();
	}

	/**
	 * 
	 */

	public static final <T> ArrayList<T> newArrayList( Collection<T> collection )
	{
		return new ArrayList<T>( collection );
	}

	/**
	 * 
	 */

	public static final <T> ArrayList<T> newArrayList( int capacity )
	{
		return new ArrayList<T>( capacity );
	}

	/**
	 * 
	 */

	public static final <T> ArrayList<T> newArrayList( T... array )
	{
		if ( array == null ) {
      return new ArrayList<T>();
    }

		return new ArrayList<T>( Arrays.asList( array ) );
	}

	/**
	 * 
	 */

	public static final <K> HashSet<K> newHashSet()
	{
		return new HashSet<K>();
	}

	/**
	 * 
	 */

	public static final <K> HashSet<K> newHashSet( Collection<K> set )
	{
		return new HashSet<K>( set );
	}

	/**
	 * 
	 */

	public static final <K> HashSet<K> newHashSet( K... array )
	{
		return new HashSet<K>( Arrays.asList( array ));
	}

	/**
	 * 
	 */

	public static final <K> Stack<K> newStack()
	{
		return new Stack<K>();
	}

	/**
	 * 
	 */

	public static final <K, V> HashMap<K, V> newHashMap()
	{
		return new HashMap<K, V>();
	}

	/**
	 * 
	 */

	public static final <K, V> HashMap<K, V> newHashMap( Map<K, V> map )
	{
		return new HashMap<K, V>( map );
	}

	/**
	 * 
	 */

	public static final <K, V> HashMap<K, V> newHashMap( int size )
	{
		return new HashMap<K, V>( size );
	}

	/**
	 * 
	 */

	public static final <K, V> LinkedHashMap<K, V> newLinkedHashMap()
	{
		return new LinkedHashMap<K, V>();
	}

	/**
	 * 
	 */

	public static final <K, V> LinkedHashMap<K, V> newLinkedHashMap( Map<K, V> map )
	{
		return new LinkedHashMap<K, V>( map );
	}

	/**
	 * 
	 */

	public static final <K, V> TreeMap<K, V> newTreeMap()
	{
		return new TreeMap<K, V>();
	}

	public static <T> List<T> unmodifiableList( T... array )
	{
		return Collections.unmodifiableList( Arrays.asList( array ) );
	}
	
	
    
}

