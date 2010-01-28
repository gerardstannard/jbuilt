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

package org.jbuiltDemo.managed.controller;

import org.jbuiltDemo.managed.model.PersonBean;

public class PersonController {
	private PersonBean joe;
	private PersonBean person;
	
	private int onePlusOne;

		
	public boolean isMale(PersonBean person){
		return person.getGender().equals("male");
	}

	public PersonBean getJoe() {
		return joe;
	}

	public void setJoe(PersonBean person) {
		this.joe = person;
	}
	
	public int getOnePlusOne() {
		return onePlusOne;
	}

	public void setOnePlusOne(int onePlusOne) {
		this.onePlusOne = onePlusOne;
	}
	
	public int addOnePlusOne(){
		onePlusOne = 1 + 1;
		return onePlusOne;
	}
	public int add(int add1, int add2){
		int add = add1 + add2;
		return add;
	}

	public PersonBean getPerson() {
		return person;
	}

	public void setPerson(PersonBean person) {
		this.person = person;
	}



}
