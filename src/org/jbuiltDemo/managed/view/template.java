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

import org.jbuilt.components.html.raw.base.AbstractComponent;
import org.jbuilt.utils.Closure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.controller.PersonController;
import org.jbuiltDemo.managed.model.PersonBean;


public class template extends JsfComponentTreeViewDirector {
	
	private UIComponent tree;
	private static FacesContext staticFacesContext=FacesContext.getCurrentInstance();
	private FacesContext facesContext=FacesContext.getCurrentInstance();

	private PersonController personControllerConfig;
	public PersonBean person;
	private PersonBean joey;
	
	private JsfViewDirector viewDirector;
	private Closure viewClosure;

	public template(){
	}

	public template(UIComponent rootOrComponent) {
		super(rootOrComponent);
//		facesContext.addMessage("", new FacesMessage(" some faces message "));

		if(rootOrComponent instanceof UIViewRoot){
		this.tree = rootOrComponent;
		} else {
			this.tree =  rootOrComponent;
		}
		
		viewClosure = new TemplateView();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIViewRoot newRoot = new UIViewRoot();
		template director = new template(newRoot);
		director.createView(staticFacesContext);
		((AbstractComponent)newRoot.getChildren().get(0)).printToConsole();
	}
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public UIComponent writeView() {
		beforeWriteView();
		UIComponent subTree = (UIComponent) viewClosure.execute();
		afterWriteView();
		return subTree;
				
	}
	
	@Override
  public void beforeWriteView(){

	}
	
	@Override
  public void afterWriteView(){
		
	}
	
	


	@Override
  public UIComponent getTree() {
		return tree;
	}

	@Override
  public void setTree(UIComponent rootOrComponent) {
		if(rootOrComponent instanceof UIViewRoot){
		this.tree = rootOrComponent;
		} else {
			this.tree =  rootOrComponent;
		}

		this.tree = rootOrComponent;
	}


	public Closure getViewClosure() {
		return viewClosure;
	}

	public void setViewClosure(Closure viewClosure) {
		this.viewClosure = viewClosure;
	}

}
