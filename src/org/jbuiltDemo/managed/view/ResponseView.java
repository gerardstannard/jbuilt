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

import static org.jbuilt.utils.TernaryUtil.*;
import static org.jbuilt.utils.ValueClosure.*;
import static org.jbuiltDemo.view.css.CSSBuilder.*;
import static org.jbuiltDemo.view.css.CSSBuilder.Color.*;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.MethodClosure;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.model.NumberBean;

import com.google.inject.Inject;

public class ResponseView extends BaseViewClosure {

	private JsfViewDirector owner;
	private Object delegate;
	
	private UIComponent tree;
	private FacesContext facesContext;
	private NumberBean numberBean;
	private boolean proceed = true;
	
	private String backgroundColor;
	private String fontColor;
	private String fontSize;

	private Map<String, String> cssProfiles; // = CollectionUtils.newHashMap();
	
	
	@Inject
	ResponseView(NumberBean numberBean, FacesContext facesContext, @_UIViewRoot UIComponent tree){
		super(tree, facesContext);
		this.numberBean = numberBean;
		this.facesContext = facesContext;
		this.tree = tree;
	}
	
	@SuppressWarnings("serial")
	Map<String, String> populateCssProfiles(){
		return cssProfiles = new HashMap<String, String>(){
				{
					put("large", rule(
									fontSize("1.75em")
									));
							
				}
			};
	}
	
	
	public String getBackgroundColor() {
		backgroundColor = (String) ternary(numberBean.tooLarge(), red,
				ternary(numberBean.tooSmall(), blue, green) );
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public NumberBean getNumberBean() {
		return numberBean;
	}

	public String getFontColor() {
		fontColor = (String) ternary(numberBean.isCorrect(), darkGray, whiteSmoke);
		return fontColor;
	}


	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}


	public String getFontSize() {
		fontSize = (String) ternary(numberBean.tooLarge(), "1.5em",
										ternary(numberBean.tooSmall(), ".5em", "1em")
								);
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public void setNumberBean(NumberBean numberBean) {
		this.numberBean = numberBean;
	}

	
	public MethodClosure responseAction = new MethodClosure(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Object execute(Object... args) {
			navigateTo(guess.class /*, redirect*/);
			return args;
		}
		
	};
	
	public MethodClosure startOver = new MethodClosure(){
		private static final long serialVersionUID = 1L;
		@Override
		public Object execute(Object... args) {
			numberBean.resetActual();
			numberBean.setGuess(0);
			navigateTo(guess.class);
			return args;
		}
	};
	
	@Override
  public Object execute(Object... args){
		if(proceed){
			return doExecute(args);
		}
		return null;
		
	}
	
	@Override
	public void beforeExecute(){

	}
	@Override
	public void afterExecute(){
		
	}
	
	@Override
  public Object doExecute(Object... args) {
				
	UIComponent html = html(
		id("response"),
		head(
			titleElement("jbuilt: Number Guess, Response"),
			styleElement(
				rule(
					selector("body"),
						color(getFontColor()),
						backgroundColor(getBackgroundColor()),
						fontFamily("Verdana, Arial, Helvetica, sans-serif"),
						fontSize(getFontSize())
				)
			),
            jawrStyle( src("/css/main.css")),
            jawrStyle( src("/css/pie_chart_dynamic.css"))

		),
		body(
			h1(
				span(
					text("Your guess was", v(numberBean, "guess"), ".")
				),
				br(),
				text(
						v(numberBean, "message")//,
//						" Actual is", v(numberBean, "actual"), "."
				)
			),
			p(
				form(
					//FIXME: form must have an attribute for it to be submitted correctly
					id("responseForm"),
//					input(
//						id("hidden"),
//						style("display:none"),
//						type("text"),
//						value(v(numberBean, "guess"))
//					),
					input(
						id("back"),
						type("submit"),
						action(responseAction),
						value("Back")
					),
					input(
						id("startOver"),
						type("submit"),
						action(startOver),
						value("Start Over")
						),
					br()
				)
			)
		)
	);
		
	return html;
	}
	
}
