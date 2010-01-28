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

package org.jbuiltDemo.managed.model;

import java.util.Arrays;
import java.util.List;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.utils.ValueClosure;

public class CompanyModel<T> implements DataModel<Company, T> {
	
	public Company company;
	public String id;
	public String name;
	public T quarter1;
	public T dollars1;
	public T quarter2;
	public T dollars2;
	public T quarter3;
	public T dollars3;
	public T quarter4;
	public T dollars4;
	
    ValueClosure vDollars1 = new ValueClosure(this, "dollars1");

		
//	public ParamaterizedClosure<Company> company(){
//		return new ParameterizedClosure<>;
//
//	}
	
	public String id(){
		return id;}
	public String name(){
		return name;}
	public T quarter1(){
		return dollars1;}
//	public T dollars1(){
//		return dollars1;}
	public T quarter2(){
		return dollars1;}
	public T dollars2(){
		return dollars1;}
	public T quarter3(){
		return dollars1;}
	public T dollars3(){
		return dollars1;}
	public T quarter4(){
		return dollars1;}
	public T dollars4(){
		return dollars1;}
	
	
	public ValueClosure dollars1(){
	    return  vDollars1;
	}
		
	
	public List getRowData() {
		return Arrays.asList(new Object[]{name, dollars1, dollars2, dollars3, dollars4});
	}

	public List getRowLabels() {
		return Arrays.asList(new Object[]{"Companies", quarter1, quarter2, quarter3, quarter4});
	}

	public T getDataObject() {
		return (T) company;
	}
	
	public String getId(){
		return id;
	}
	
	
	public T getDollars1(){
	    return dollars1;
	}
	public void setDollars1(T dollars1){
	    this.dollars1 = dollars1;
	}

	   public ValueClosure getVDollars1(){
	        return vDollars1;
	    }
	    public void setVDollars1(ValueClosure vDollars1){
	        this.vDollars1 = vDollars1;
	    }
	    

}
