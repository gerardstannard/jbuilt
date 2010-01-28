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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConversionUtils {
	
	public static Map<Class, Closure> converterClosureMap = new HashMap<Class, Closure>(){
		private static final long serialVersionUID = 1L;
		{
			put(String.class, stringConverterClosure);
			put(Integer.class, integerConverterClosure);
			put(Double.class, DoubleConverterClosure);
			put(double.class, doubleConverterClosure);
			put(int.class, intConverterClosure);
			put(Long.class, longConverterClosure);
			put(Float.class, floatConverterClosure);
			put(Byte.class, byteConverterClosure);
			put(Short.class, shortConverterClosure);
			put(Date.class, dateConverterClosure);
			
		}
		
	};


	
	static Closure stringConverterClosure = new Closure(){

		public Object execute(Object... args) {
				return args[0];
		}
	};

	static Closure integerConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
			
			if("".equals(newValue)){
				newValue = 0;
			} else {
				newValue = Integer.valueOf((String) newValue);
			}
			return newValue;
		}
	};
	
	static Closure DoubleConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0;
				} else {
					newValue = Double.valueOf((String) newValue);
				}
				return newValue;
		}
	};
	
	static Closure intConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0;
				} else {
				newValue = Integer.parseInt((String) newValue);
				}
				return newValue;
		}
	};
	
	static Closure doubleConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0.0;
				} else {
				newValue = Double.parseDouble((String) newValue);
				}
				return newValue;
		}
	};
	
	static Closure shortConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0;
				} else {
				newValue = Short.valueOf((String) newValue);
				}
				return newValue;
		}
	};
	static Closure floatConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0.0;
				} else {
					newValue = Float.valueOf((String) newValue);
				}
				return newValue;
		}
	};
	
	static Closure longConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0;
				} else {
				newValue = Long.valueOf((String) newValue);
				}
				return newValue;
		}
	};
	
	static Closure byteConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				if("".equals(newValue)){
					newValue = 0;
				} else {
					newValue = Byte.valueOf((String) newValue);
				}
				return newValue;
		}
	};
	
	static Closure dateConverterClosure = new Closure(){
		
		public Object execute(Object... args) {
			Object newValue = args[0];
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = df.parse((String) newValue);
					// System.out.println("Today = " + df.format(today));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				newValue = date;
				return newValue;
		}
		
	};
	

}
