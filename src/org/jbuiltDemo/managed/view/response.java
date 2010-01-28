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
import org.jbuiltDemo.managed.annotations._ResponseView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;

import com.google.inject.Inject;

public class response extends JsfComponentTreeViewDirector {
	
	private UIComponent tree;
	private FacesContext facesContext;

//	private JsfViewDirector viewDirector;
	private ViewClosure viewClosure;

	@Inject
	public response(@_UIViewRoot UIComponent rootOrComponent, @_ResponseView ViewClosure viewClosure,
			FacesContext facesContext) {
		super(rootOrComponent);
//		facesContext.addMessage("", new FacesMessage(" some faces message "));

		if(rootOrComponent instanceof UIViewRoot){
		this.tree = rootOrComponent;
		} else {
			this.tree =  rootOrComponent;
		}
		
		this.viewClosure = viewClosure;
//		this.viewDirector = viewDirector;
		this.facesContext = facesContext;

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public UIComponent writeView() {
		beforeWriteView();
		viewClosure.beforeExecute();
		UIComponent subTree = (UIComponent) viewClosure.execute();
		((ResponseView) viewClosure).afterExecute();
		afterWriteView();
		return subTree;
				
	}
	
	@Override
  public void beforeWriteView(){

	}
	
	@Override
  public void afterWriteView(){
		
	}
	
	public ViewClosure getViewClosure() {
		return viewClosure;
	}

	public void setViewClosure(ViewClosure viewClosure) {
		this.viewClosure = viewClosure;
	}

	
}
