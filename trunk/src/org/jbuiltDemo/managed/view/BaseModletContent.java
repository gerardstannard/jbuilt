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

package org.jbuiltDemo.managed.view;

import javax.faces.component.UIComponent;

import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;
import org.jbuilt.view.componentTree.JsfViewDirector;

public class BaseModletContent extends JsfComponentTreeViewDirector implements ViewClosure  {

	protected JsfViewDirector owner;
	protected Object delegate;
	
	protected UIComponent contentContainer;
	protected String id;
	protected String styleClass;
	protected boolean proceed = true;
	
	public BaseModletContent(){}
	
	 protected MethodClosure defaultAction = new MethodClosure(){
	    private static final long serialVersionUID = 2L;
	    private String value = "search term";
	    @Override
	    public Object execute(Object... args) {
	      System.out.println("new value is " + getValue());
	      this.value="";
	      return args;
	    }
      /**
       * @return the value
       */
      public String getValue() {
        return value;
      }
      /**
       * @param value the value to set
       */
      public void setValue(String value) {
        this.value = value;
      }
	    
	  };

    public Object execute(Object... args){
        UIComponent view = null;
        if(proceed){
            view = (UIComponent) doExecute(args);
        } else {
            throw new RuntimeException("couldn't proceed, proceed evaluated to false");
        }
        return view;
        
    }
	
	public void beforeExecute(){

	}
	
	public void afterExecute(){
		
	}
	
	protected Object doExecute(Object... args) {
		// no op for sub classes
		return null;
	}
	
	public void aroundExecute() {
		// no op for sub classes
		
	}

}
