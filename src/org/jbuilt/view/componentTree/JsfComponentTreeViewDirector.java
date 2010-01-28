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

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jbuilt.componentTree.jsf.AbstractJsfComponentTreeBuilder;

public abstract class JsfComponentTreeViewDirector extends AbstractJsfComponentTreeBuilder {

	private UIComponent tree;
	protected Map<String, Object> sessionMap;
	public JsfComponentTreeViewDirector() {}
	
	public JsfComponentTreeViewDirector(UIComponent rootOrComponent) {
		if(rootOrComponent  instanceof UIViewRoot){
			this.tree = rootOrComponent;
			} else {
				this.tree =  rootOrComponent;
			}
//		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

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
		beforeWriteView();
		UIComponent subTree = writeView();
		afterWriteView();
		UIComponent tree = this.tree;
		tree.getChildren().add(subTree);
		return subTree;
	}
	
	public String getFileContents(String path){
		String fileString = "";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			fileString = FileUtils.readFileToString(
					new File(facesContext.getApplication().getViewHandler().getResourceURL(
							facesContext, path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fileString == null){
			return StringUtils.EMPTY;
		}
		return fileString;

	}

	@SuppressWarnings("unchecked")
	@Override
	public  UIComponent writeView(){
		// no op
		return null;
	}
	
	@Override
	public void beforeWriteView(){
		log("before write view");
	}
	
	@Override
	public void afterWriteView(){
		log("after write view");
	}
	
	public void log(String message){
		System.out.println(message);
	}

	


	public void setTree(UIViewRoot tree) {
		this.tree = tree;
	}



	
}
