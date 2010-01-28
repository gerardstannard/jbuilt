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

import static org.jbuiltDemo.managed.model.ObjectMother.*;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._ResponseView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._guess;
import org.jbuiltDemo.managed.model.Company;
import org.jbuiltDemo.managed.model.NumberBean;
import org.jbuiltDemo.view.css.CSSBuilder.BorderStyle;

import com.google.inject.Inject;

public class LayoutView extends BaseViewClosure implements Layout  {

	private JsfViewDirector owner;
	private Object delegate;
	
	private UIComponent tree;
	private FacesContext facesContext;
	private NumberBean numberBean;
	private boolean proceed = true;
	private ViewClosure guessView = this;
	private ViewClosure responseView;
	private List<DataModel<Company, String>> companyModelDataList;
	
	@Inject
	LayoutView(@_UIViewRoot UIComponent tree, FacesContext facesContext, NumberBean numberBean,
			@_guess JsfViewDirector owner, @_ResponseView ViewClosure responseView){
		super(tree, facesContext);
		this.owner = owner;
		this.tree = tree;
		this.facesContext = facesContext;
		this.numberBean = numberBean;
		this.responseView = responseView;
		this.companyModelDataList = createCompanyModelData();
	}


	public MethodClosure defaultAction = new MethodClosure(){
		private static final long serialVersionUID = 2L;
		@Override
		public Object execute(Object... args) {
			System.out.println("printing default action");
			return args;
		}
	};
	
	
	@Override
	public Object execute(Object... args){
		UIComponent view = null;
		beforeExecute();
		if(proceed){
			view = (UIComponent) doExecute(args);
		}
		afterExecute();
		return view;
		
	}
	@Override
	public void beforeExecute(){
		proceed = true;
	}
	@Override
	public void afterExecute(){
	}

	@Override
  public Object doExecute(Object... args) {
				
		UIComponent html =
		html(
			head(
				title("YUI Base Page"),
				link(
					rel("stylesheet"),
					href("http://yui.yahooapis.com/2.7.0/build/reset-fonts-grids/reset-fonts-grids.css"),
					type("text/css")
				),
				styleElement(
						border(BorderStyle.groove)
						)
			),
			body(
				div(id("doc"), styleClass("yui-t7"),
					div(id("hd"), h1("Header")),
					div(id("bd"),
						div(styleClass("yui-g"),
							// your data goes here
								iframe(src("http://www.google.com"), width(600), height(600))
						),
						div(styleClass("yui-g"),
							div(
								styleClass("yui-first"),
								// your data goes here
								iframe(src("http://www.bing.com"), width(600), height(600))

							),
							div(
								styleClass("yui-u"),
								// your data goes here
								iframe(src("http://www.yahoo.com"), width(600), height(600))

							)
						)
					)
				),
				div(id("ft"), p("footer"))
			)
		);
			
			
		/*
		<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
 								"http://www.w3.org/TR/html4/strict.dtd">
		<html>
			<head>
   				<title>YUI Base Page</title>
   				<link rel="stylesheet" href="http://yui.yahooapis.com/2.7.0/build/reset-fonts-grids/reset-fonts-grids.css" type="text/css">
			</head>
			<body>
			<div id="doc" class="yui-t7">
			   <div id="hd"><h1>Header</h1></div>
			   <div id="bd">
					<div class="yui-g">
						<!-- YOUR DATA GOES HERE -->
					</div>
					<div class="yui-g">
			    		<div class="yui-u first">
							<!-- YOUR DATA GOES HERE -->
				    	</div>
			    		<div class="yui-u">
							<!-- YOUR DATA GOES HERE -->
						</div>
					</div>
				</div>
			   	<div id="ft"><p>Footer</p></div>
			</div>
		</body>
		</html>
		
		*/
			
	return html;
	}


	public JsfViewDirector getOwner() {
		return owner;
	}

	public void setOwner(JsfViewDirector owner) {
		this.owner = owner;
	}
	
}
