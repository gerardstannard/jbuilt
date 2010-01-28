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

import java.util.Collection;

import org.apache.commons.collections.Closure;

public class StringFromCollection implements Closure {
	String string;
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	Collection collection;
	public StringFromCollection(Collection collection){
		this.collection = collection;
		execute(collection);
		
	}

	public void execute(Object collection) {
		StringBuilder sb;
			sb = new StringBuilder();
				if(collection != null){
					for(Object text : (Iterable)collection){
						if(text instanceof String) {
              sb.append((String) text);
            }
					}
			}
				this.string = sb.toString();
	}
		

}
