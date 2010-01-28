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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jbuilt.utils.Closure;
import org.jbuilt.utils.ValueClosure;

import com.google.inject.Inject;

//@ManagedBean(name = "numberBean", scope = Scope.SESSION)
public class NumberBean implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected final static Random rand = new Random();

  protected int min;
  protected int max;
  protected int guess;
  protected int actual;
  private String message;
  private Map<String, ValueClosure> valueClosures;
    
  // Default Constructor
  @Inject
  public NumberBean() {
    this.min = 1;
    this.max = 10;
  }
  
   
  // called by JSF to validate user input
  public void validate(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {
      
    // coerce the value to an int
    try {
      int param = Integer.parseInt(value.toString());
    
      // validate param
      if (param > this.max || param < this.min) {
          FacesMessage msg = new FacesMessage("Guess must be between "+this.min+" and "+this.max);
          throw new ValidatorException(msg);
      }
    } catch (NumberFormatException e) {
      FacesMessage msg = new FacesMessage("Must be a number");
      throw new ValidatorException(msg);
    }
  }
    
  // lazy generate our actual value
  public synchronized int getActual() {
    if (this.actual == 0 ) {
        this.actual = rand.nextInt(this.max-this.min);
        this.actual += this.min;
    }
    return this.actual;
  }

  public synchronized void resetActual() {
	        this.actual = rand.nextInt(this.max-this.min);
	        this.actual += this.min;
	  }
	  

  // our message for the response
  public String getMessage() {
    if (isCorrect()) {
    	message = "Sweet, you got it right! ";
    	return message;
    } else if (tooSmall()) {
    	message = "Sorry, try something higher. ";
      return message;
    } else if(tooLarge()) {
    	message =  "Too bad, go lower. ";
      return message;
    } else {
    	return "your guess is out of range. ";
    }
  }
  
  public boolean isCorrect(){
	  return this.getGuess() == this.getActual();
  }
  public boolean tooLarge(){
	  return this.getGuess() > this.getActual();
  }
  
  public boolean tooSmall(){
	  return this.getGuess() < this.getActual();
  }
  
  
  public void setMessage(String message){
	  this.message = message;
  }
  
  // other bean properties
  public int getMin() { return this.min; }
  public int getMax() { return this.max; }
  public int getGuess() { return this.guess; }
    
  public void setMin(int min) { this.min = min; }
  public void setMax(int max) { this.max = max; }
  public void setGuess(int guess) { this.guess = guess; }

public void setActual(int actual2) {
	// no op
	// required to satisfy introspector
//	try {
//		throw new OperationNotSupportedException("use \"getActual()\" to generate this value");
//	} catch (OperationNotSupportedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
}

@PostConstruct
public void populateValueClosures(){
	valueClosures = new HashMap<String, ValueClosure>();
	for(Field field : this.getClass().getDeclaredFields()){
		String fieldName = field.getName();
			valueClosures.put(fieldName, new ValueClosure(this, fieldName));
	}
	valueClosures.remove("valueClosures");
}

public ValueClosure value(String fieldName){
	return valueClosures.get(fieldName);
}

public Closure min(){
	return new ValueClosure(this, "min");
}

public Closure max(){
	return new ValueClosure(this, "max");
}

public Closure guess(){
	return new ValueClosure(this, "guess");
}

public Closure actual(){
	return new ValueClosure(this, "actual");
}

public Closure message(){
	return new ValueClosure(this, "message");
}


private boolean checkArgs(Object... args){
	if(args != null && args.length > 0) {
		return true;
	}
	return false;
}
  
    
}
