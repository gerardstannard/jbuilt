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

import static org.jbuilt.utils.ValueClosure.*;
import static org.jbuiltDemo.view.css.CSSBuilder.*;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._ResponseView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._guess;
import org.jbuiltDemo.managed.model.NumberBean;

import com.google.inject.Inject;

public class GuessView extends BaseViewClosure  {

	private NumberBean numberBean;
	private ViewClosure guessView = this;
	private ViewClosure responseView;
	
	@Inject
	GuessView(@_UIViewRoot UIComponent tree, FacesContext facesContext, NumberBean numberBean,
			@_guess JsfViewDirector owner, @_ResponseView ViewClosure responseView){
		super(tree, facesContext);
		this.owner = owner;
		this.tree = tree;
		this.facesContext = facesContext;
		this.numberBean = numberBean;
		this.responseView = responseView;
	}

	public NumberBean getNumberBean() {
		return numberBean;
	}

	public void setNumberBean(NumberBean numberBean) {
		this.numberBean = numberBean;
	}
	
	/**
	 * JSF action that gets executed during the InvokeApplication Phase
	 */
	public MethodClosure guessAction = new MethodClosure(){
		private static final long serialVersionUID = 1L;
		@Override
		public Object execute(Object... args) {
			//TODO: keep a history map for smoother navigation with less code
			navigateTo(response.class);
			return args;
		}
	};
	
    /**
     * JSF action that gets executed during the InvokeApplication Phase
     * resets the user's current actual guess to 0
     */
	public MethodClosure startOver = new MethodClosure(){
		private static final long serialVersionUID = 1L;
		@Override
		public Object execute(Object... args) {
            numberBean.resetActual();
			numberBean.setGuess(0);
			return args;
		}
	};

	/**
	 * convenience action to call for development and debugging purposes
	 */
	public MethodClosure defaultAction = new MethodClosure(){
		private static final long serialVersionUID = 2L;
		@Override
		public Object execute(Object... args) {
			System.out.println("printing default action");
			return args;
		}
	};
	
	
	@Override
	public void beforeExecute(){
		proceed = true;
		// any code you want executed before the component tree is built
	}
	@Override
	public void afterExecute(){
       // any code you want executed after the component tree is built
	   // ie, removing or adding components based on some condition, decorating
	   // components with styles, anything else you may need to do
	}

	@Override
  public Object doExecute(Object... args) {
		
		UIComponent html =
		html(
			id("guess"),
			head(
			    // renamed to "titleElement" because "title" is also an attribute
				titleElement("jbuilt: Number Guess"),
				styleElement(
					rule(
						selector("body"),
						cssBackgroundImage("/img/bg_blue_2.gif"),
						fontFamily("Verdana, Arial, Helvetica, sans-serif"),
						fontSize("small")
					)
				),
				// optionally using jawr for providing css resource
				jawrStyle( src("/css/main.css"))

			),
			body(
				div(
					h1(
						text("I'm thinking of a number from",
						        // referring to a value closure optionally predefined in numberBean
								numberBean.min(),
								" to ",
								// creating value closure on the fly with v factory method
								v(numberBean, "max"),
								".  Can you guess it?")
					),
						form(
							id("guessForm"),
							input(
								type("text"),
								id("userNo"),
								value(
							        // factory method for creating a value closure
							        v(numberBean, "guess")
						        )
							),
							br(),
							br(),
							// submit guess to response page
							input(
								id("submit"),
								type("submit"),
								// refer to method closure definition above
								action(guessAction),
								value("Submit")
							),
							// resets and starts over the game
							input(
								id("startOver"),
								type("submit"),
                                // refer to method closure definition above
								action(startOver),
								value("Start Over")
							)
						)
					)
				)
		);
		
			
	return html;
	}
	
}
