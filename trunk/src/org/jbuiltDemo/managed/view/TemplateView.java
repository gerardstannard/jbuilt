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

import static org.jbuiltDemo.view.css.CSSBuilder.*;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jbuilt.utils.Closure;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;
import org.jbuilt.view.componentTree.JsfViewDirector;

public class TemplateView extends JsfComponentTreeViewDirector implements Closure {

	private JsfViewDirector owner;
	private Object delegate;
	
	
	private UIComponent tree;
	private static FacesContext staticFacesContext=FacesContext.getCurrentInstance();
	private FacesContext facesContext=FacesContext.getCurrentInstance();
	

	public MethodClosure add = new MethodClosure(){

		@Override
		public Object execute(Object... args) {
			ActionEvent event;
			if(args.length > 0){
			event = (ActionEvent) args[0];
			}
			return null;
		}
		
	};
	


	
	
	
	
	public Object execute(Object... args){
		
		return doExecute(args);
		
	}
	
	public void beforeExecute(){
		
	}
	
	public void afterExecute(){
		
	}
	



	public Object doExecute(Object... args) {
		
		
		UIComponent html = html(
		
		head(
			title("jbuilt: Number Guess Tutorial"),
			styleElement(
				rule(
					selector("body"),
					fontFamily("Verdana, Arial, Helvetica, sans-serif"),
					fontSize("small")
				)
			)
				),
		body(
			h1(
//				insert(
//					name("title"),
					text("Default Title")
//				)
			),
			p(
//				insert(
//						name("body"),
						text("Default Body")
//				)

			)
		)
		
		);
			
		return html;
	}
	
	
	@Override
	public UIComponent writeView() {
		// TODO Auto-generated method stub
		return null;
	}

}
