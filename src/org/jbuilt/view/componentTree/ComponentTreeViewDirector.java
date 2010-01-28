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

package org.jbuilt.view.componentTree;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuilt.componentTree.AbstractComponentTreeBuilder;
import org.jbuilt.utils.ViewClosure;

public class ComponentTreeViewDirector extends AbstractComponentTreeBuilder {

	private UIComponent tree;
	
	public ComponentTreeViewDirector() {}
	
	public ComponentTreeViewDirector(UIComponent rootOrComponent) {
		if(rootOrComponent instanceof UIViewRoot){
			this.tree = rootOrComponent;
			} else {
				this.tree =  rootOrComponent;
			}
		}
	
	private static ComponentTreeViewDirector instance;
	
	public static ComponentTreeViewDirector getInstance(UIComponent rootOrComponent){
		if(instance == null){
			return new ComponentTreeViewDirector(rootOrComponent);
		} else {
			return instance;
		}
	}

	/**
	 * @param args
	 */
	@Override
  public void createView(FacesContext context) {
		this.tree =  this.buildView(context);
//			for(UIComponent child : this.tree.getChildren()){
//				System.out.println("printing child " + child + " of " + child.getParent().getClass().getSimpleName());
//				((AbstractTag)child).printToConsole();
//			}
	}


	@SuppressWarnings("unchecked")
	@Override
	public UIComponent buildView(FacesContext context) {
		this.context = context;
		UIComponent subTree = writeView();
		UIComponent tree = this.tree;
		tree.getChildren().add(subTree);
		return subTree;
	}

	@Override
	public UIComponent writeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
  public UIComponent getTree() {
		return tree;
	}


	public void setTree(UIViewRoot viewRoot) {
		// TODO Auto-generated method stub
		
	}

	public ViewClosure getViewClosure() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setViewClosure(ViewClosure responseView) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void afterWriteView() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void beforeWriteView() {
        // TODO Auto-generated method stub
        
    }



	
}
