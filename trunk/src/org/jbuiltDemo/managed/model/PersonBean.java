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


import org.jbuilt.utils.ValueClosure;

public class PersonBean {
	private String name;
	private int age;
	private String gender;
	private int addResult;
	private ValueClosure nameClosure = new ValueClosure(this, "name");
	private ValueClosure genderClosure;
	private int add1;
	private int add2;
	private int add3;

	public PersonBean() {}

	public PersonBean(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object name(String...name){
		if(name != null && name.length > 0){
			setName(name[0]);
		} else {
			return getName();
		}
		return this;
	}
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAddResult() {
		return addResult;
	}

	public void setAddResult(int addResult) {
		this.addResult = addResult;
	}

	public ValueClosure getNameClosure() {
		return nameClosure;
	}

	public void setNameClosure(ValueClosure nameClosure) {
		this.nameClosure = nameClosure;
	}

	public ValueClosure getGenderClosure() {
		return genderClosure;
	}

	public void setGenderClosure(ValueClosure genderClosure) {
		this.genderClosure = genderClosure;
	}

	public int getAdd1() {
		return add1;
	}

	public int getAdd2() {
		return add2;
	}

	public int getAdd3() {
		return add3;
	}
	
	public void setAdd1(int add1) {
		this.add1 = add1;
	}

	public void setAdd2(int add2) {
		this.add2 = add2;
	}
	public void setAdd3(int add3) {
		this.add3 = add3;
	}
}