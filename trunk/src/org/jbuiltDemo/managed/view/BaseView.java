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
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;


//import com.google.inject.Inject;
//import com.google.inject.assistedinject.Assisted;

public class BaseView extends JsfComponentTreeViewDirector {
	
	protected UIComponent tree;
	protected FacesContext facesContext;

	protected ViewClosure viewClosure;
	
	public ViewClosure getViewClosure(){
		return viewClosure;
	}

	public void setViewClosure(ViewClosure viewClosure) {
		this.viewClosure = viewClosure;
	}

//	@Inject
	public BaseView( ViewClosure viewClosure,
			FacesContext facesContext, UIComponent rootOrComponent) {
		super(rootOrComponent);

		if(rootOrComponent instanceof UIViewRoot){
		this.tree = rootOrComponent;
		} else {
			this.tree =  rootOrComponent;
		}
		this.viewClosure = viewClosure;
		this.facesContext = facesContext;

	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public UIComponent writeView() {
		beforeWriteView();
		UIComponent subTree = (UIComponent) getViewClosure().execute();
		afterWriteView();
		return subTree;
				
	}
	
	@Override
	public void beforeWriteView(){
		log("before write view");
	}
	
	@Override
	public void afterWriteView(){
		log("after write view");
	}
	
	@Override
  public void log(String message){
		System.out.println(message);
	}
	
}
