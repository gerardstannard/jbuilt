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

public 	class Fruit {
	private String color;
	private String name;
	private String shape;
	private String texture;
	public String getColor() {
		return color;
	}
	public String getName() {
		return name;
	}
	public String getShape() {
		return shape;
	}
	public String getTexture() {
		return texture;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	public static List<Fruit> buildFruitList(){
		Fruit apple = new Fruit();
		Fruit orange = new Fruit();
		Fruit banana = new Fruit();
		apple.setColor("red");
		apple.setName("apple");
		apple.setShape("appleShaped");
		apple.setTexture("waxy smooth");
		
		orange.setColor("orange");
		orange.setTexture("bumpy");
		orange.setName("orange");
		orange.setShape("round");
		
		banana.setColor("yellow");
		banana.setTexture("smooth");
		banana.setName("banana");
		banana.setShape("curved");
		
		return Arrays.asList(new Fruit[]{apple, orange, banana});
	}

}

