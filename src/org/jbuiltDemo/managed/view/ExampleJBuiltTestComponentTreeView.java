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
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.jbuilt.components.html.raw.base.AbstractComponent;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

public class ExampleJBuiltTestComponentTreeView extends JsfComponentTreeViewDirector {

	private UIComponent tree;
	private static FacesContext context;


	public ExampleJBuiltTestComponentTreeView(UIComponent rootOrComponent) {
		super(rootOrComponent);
		if(rootOrComponent instanceof UIViewRoot){
		this.tree = rootOrComponent;
		} else {
			this.tree =  rootOrComponent;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIViewRoot newRoot = new UIViewRoot();
		ExampleJBuiltTestComponentTreeView director = new ExampleJBuiltTestComponentTreeView(newRoot);
		director.createView(context);
		((AbstractComponent)newRoot.getChildren().get(0)).printToConsole();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public UIComponent writeView() {
		
		UIComponent labelAndInput =
			panelGroup(
				id("aPanelGroupId"),
				outputText(
					id("aLabelId"),
					value("aLabelId value"),
					style("background-color:blue;")
					),
				inputText(
					id("anInputTextId"),
					value("anInputTextId value"),
					style("background-color:yellow; color:blue;")
				)/*,
				inputText(
						id("anOtherInputTextId"),
						value("anOtherInputTextId value")
					)*/
			);
		ActionListener listener = new ActionListener(){
			public void processAction(ActionEvent event)
			throws AbortProcessingException {
				event.getComponent().getAttributes().put("value", "this button was clicked");
			}
		};
		
		UIComponent form =
				panelGroup(
					id("aFormPanelGroupContainerId"),
					form(
						id("aFormId"),
						outputLabel(
							id("aFormLabelId"),
							value("aFormLabelId value")
							),
						inputText(
							id("aFormInputTextId"),
							value("aFormInputTextId value")
						),
						commandButton(
							id("aFormCommandButtonId"),
							value("submit aFormId form"),
							style("font-size:16px;"),
							jBuiltActionListener(listener)
						)
					)
				);

		
		UIComponent html =
			html(
				id("htmlId"),
				styleClass("htmlStyleClass"),
			  	head(
		  			id("headId"),
			  		styleElement(
			  			id("someStyle"),
			  			text("#aDiv{color:yellow;}")
			  		)
			  	),
		 
		 		body(
		 			id("bodyId"),
		 			div(
		 				id("aDiv"),
		 				style("width:200; height:200; background-color:blue;")
		 			),
		 			labelAndInput,
		 			outputText(
		 				id("anOutputTextId"),
		 				value("the value of 'anOutputTextId'")
					),
	 				text("this is text", "this is some more text"),
	 				form
	 			)
	 		);
		return html;
	}

	@Override
  public UIComponent getTree() {
		return tree;
	}

	@Override
  public void setTree(UIComponent tree) {
		this.tree = tree;
	}


	

}
