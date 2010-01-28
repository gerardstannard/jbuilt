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

public class TernaryUtil {
	
	public static Object ternary(boolean test, Object resultIfTrue, Object resultIfFalse){
		if(test) {
			return resultIfTrue;
		} else {
			return resultIfFalse;
		}
		
	}

	public static void main(String[] args){
		String result = (String)ternary(1==1, "testing 1==1, result 1==1", "testing 1==1, result 1!=1");
		
		String result2 = (String) ternary(true,
							ternary(1==1, "testing 1==1, result 1==1", "testing 1==1, result 1!=1"),
							ternary(1==0, "testing 1==0, result 1==0", "testing 1==0, result 1!=0")
								);
		
		String result3 = (String) ternary(false,
							ternary(1==1, "testing 1==1, result 1==1", "testing 1==1, result 1!=1"),
							ternary(1==0, "testing 1==0, result 1==0", "testing 1==0, result 1!=0")
								);
		
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
	}
	
}
