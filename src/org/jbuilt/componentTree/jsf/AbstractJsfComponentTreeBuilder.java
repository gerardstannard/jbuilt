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

package org.jbuilt.componentTree.jsf;




import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.component.UIColumn;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIData;
import javax.faces.component.UIForm;
import javax.faces.component.UIGraphic;
import javax.faces.component.UIInput;
import javax.faces.component.UIMessage;
import javax.faces.component.UIMessages;
import javax.faces.component.UIOutput;
import javax.faces.component.UIPanel;
import javax.faces.component.UISelectBoolean;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlOutputFormat;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectManyListbox;
import javax.faces.component.html.HtmlSelectManyMenu;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.validator.Validator;

import org.apache.commons.beanutils.PropertyUtils;
import org.jbuilt.componentTree.AbstractComponentTreeBuilder;
import org.jbuilt.components.jsf.Facet;
import org.jbuilt.utils.ComponentAttribute;


public abstract class AbstractJsfComponentTreeBuilder extends AbstractComponentTreeBuilder {

		private UIComponent tree;
		public int count = 0;
		protected int indentCount;
		protected String indent = "....|";
		protected StringBuilder currentIndent = new StringBuilder();
		protected FacesContext context;

		public AbstractJsfComponentTreeBuilder(UIComponent rootOrComponent) {
			if(rootOrComponent instanceof UIViewRoot){
				this.tree = rootOrComponent;
				} else {
					this.tree =  rootOrComponent;
				}
			}

		public AbstractJsfComponentTreeBuilder() {}

		@Override
    @SuppressWarnings("unchecked")
		public UIComponent buildView(FacesContext context) {
			this.context = context;
			UIComponent subTree = writeView();
			this.tree.getChildren().add(subTree);
			return tree;
		}
		
		/**
		 * @param args
		 */
		@Override
    public void createView(FacesContext context) {
			this.buildView(context);
		}

			
		@Override
    public abstract UIComponent writeView();


		@Override
    public String buildViewId(){
			String viewId = getClass().getSimpleName();
			String firstLetter = viewId.substring(0,1).toLowerCase();
			String theRest = viewId.substring(1);
			viewId = firstLetter + theRest + ".xhtml";
			return viewId;
		}

		@Override
    public void incrementIndent() {
			count++;
			for(int i=0; i<= count; i++){
				System.out.print(indent);
			}
		}

		@Override
    public void decrementIndent() {
			count--;
			for(int i=0; i<= count; i++){
				System.out.println(indent);
			}
		}



		@Override
    public UIComponent getTree() {
			return tree;
		}

		@Override
    public void setTree(UIComponent tree) {
			this.tree = tree;
		}

	@SuppressWarnings("unchecked")
	public UIForm hForm(Object... args) {
		UIForm form = new HtmlForm();
		System.out.print("form");
		incrementIndent();
		System.out.println(" ");
		processArgs(form, args);

//		for(Object arg : args){
//			if(arg instanceof ComponentAttribute){
//				((UIComponent)form).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
//			} else if(arg instanceof UIComponent){
//				if(((UIComponent)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
//					((UIForm)form).getChildren().add((UIComponent) arg);
//				} else {
//					((Map<String, UIComponent>) ((UIForm)form).getFacets()).put((String)((UIForm)arg).getAttributes().get("name"),(UIComponent) arg);
//				}
//			} else if(arg instanceof Converter){
//				try {
//					if(form.getClass().getDeclaredMethod("setConverter", form.getClass()) != null){
//						form.getClass().getDeclaredMethod("setConverter", form.getClass()).invoke(form, (Converter) arg);
//					}
//				} catch (SecurityException e) {
//
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//
//					e.printStackTrace();
//				}
//			} else if(arg instanceof Validator){
//				try {
//					if(form.getClass().getDeclaredMethod("addValidator", form.getClass()) != null){
//						form.getClass().getDeclaredMethod("addValidator", form.getClass()).invoke(form, (Validator) arg);
//					}
//				} catch (SecurityException e) {
//
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//
//					e.printStackTrace();
//				}
//			}
//		}
		
		decrementIndent();
		System.out.print("end_form\n");


		return form;
	}

 

	@SuppressWarnings("unchecked")
	public UIInput inputText(Object... args) {
		UIInput inputText = new HtmlInputText();
		System.out.print("inputText");
		incrementIndent();
		System.out.println(" ");
		processArgs(inputText, args);

//		for(Object arg : args){
//			if(arg instanceof ComponentAttribute){
//				((UIComponent)inputText).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
//			} else if(arg instanceof UIComponent){
//				if(((UIInput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
//					((UIInput)inputText).getChildren().add((UIComponent) arg);
//				} else {
//					((Map<String, UIComponent>) ((UIInput)inputText).getFacets()).put((String)((UIInput)arg).getAttributes().get("name"),(UIComponent) arg);
//				}
//			} else if(arg instanceof Converter){
//				try {
//					if(inputText.getClass().getDeclaredMethod("setConverter", inputText.getClass()) != null){
//						inputText.getClass().getDeclaredMethod("setConverter", inputText.getClass()).invoke(inputText, (Converter) arg);
//					}
//				} catch (SecurityException e) {
//
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//
//					e.printStackTrace();
//				}
//			} else if(arg instanceof Validator){
//				try {
//					if(inputText.getClass().getDeclaredMethod("addValidator", inputText.getClass()) != null){
//						inputText.getClass().getDeclaredMethod("addValidator", inputText.getClass()).invoke(inputText, (Validator) arg);
//					}
//				} catch (SecurityException e) {
//
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//
//					e.printStackTrace();
//				}
//			}
//		}
		
		decrementIndent();
		System.out.print("end_inputText\n");


		return inputText;
	}

 

	@SuppressWarnings("unchecked")
	public UIInput inputTextArea(Object... args) {
		UIInput inputTextArea = new HtmlInputTextarea();
		System.out.print("inputTextArea");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)inputTextArea).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIInput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(inputTextArea).getChildren().add((UIComponent) arg);
				} else {
					((inputTextArea).getFacets()).put((String)((UIInput)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(inputTextArea.getClass().getDeclaredMethod("setConverter", inputTextArea.getClass()) != null){
						inputTextArea.getClass().getDeclaredMethod("setConverter", inputTextArea.getClass()).invoke(inputTextArea, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(inputTextArea.getClass().getDeclaredMethod("addValidator", inputTextArea.getClass()) != null){
						inputTextArea.getClass().getDeclaredMethod("addValidator", inputTextArea.getClass()).invoke(inputTextArea, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_inputTextArea\n");


		return inputTextArea;
	}

 

	@SuppressWarnings("unchecked")
	public UIInput inputSecret(Object... args) {
		UIInput inputSecret = new HtmlInputSecret();
		System.out.print("inputSecret");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)inputSecret).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIInput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(inputSecret).getChildren().add((UIComponent) arg);
				} else {
					((inputSecret).getFacets()).put((String)((UIInput)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(inputSecret.getClass().getDeclaredMethod("setConverter", inputSecret.getClass()) != null){
						inputSecret.getClass().getDeclaredMethod("setConverter", inputSecret.getClass()).invoke(inputSecret, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(inputSecret.getClass().getDeclaredMethod("addValidator", inputSecret.getClass()) != null){
						inputSecret.getClass().getDeclaredMethod("addValidator", inputSecret.getClass()).invoke(inputSecret, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_inputSecret\n");


		return inputSecret;
	}

 

	@SuppressWarnings("unchecked")
	public UIInput inputHidden(Object... args) {
		UIInput inputHidden = new HtmlInputHidden();
		System.out.print("inputHidden");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)inputHidden).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIInput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(inputHidden).getChildren().add((UIComponent) arg);
				} else {
					((inputHidden).getFacets()).put((String)((UIInput)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(inputHidden.getClass().getDeclaredMethod("setConverter", inputHidden.getClass()) != null){
						inputHidden.getClass().getDeclaredMethod("setConverter", inputHidden.getClass()).invoke(inputHidden, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(inputHidden.getClass().getDeclaredMethod("addValidator", inputHidden.getClass()) != null){
						inputHidden.getClass().getDeclaredMethod("addValidator", inputHidden.getClass()).invoke(inputHidden, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_inputHidden\n");


		return inputHidden;
	}

 

	@SuppressWarnings("unchecked")
	public UIOutput outputFormat(Object... args) {
		UIOutput outputFormat = new HtmlOutputFormat();
		System.out.print("outputFormat");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)outputFormat).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIOutput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(outputFormat).getChildren().add((UIComponent) arg);
				} else {
					((outputFormat).getFacets()).put((String)((UIOutput)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(outputFormat.getClass().getDeclaredMethod("setConverter", outputFormat.getClass()) != null){
						outputFormat.getClass().getDeclaredMethod("setConverter", outputFormat.getClass()).invoke(outputFormat, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(outputFormat.getClass().getDeclaredMethod("addValidator", outputFormat.getClass()) != null){
						outputFormat.getClass().getDeclaredMethod("addValidator", outputFormat.getClass()).invoke(outputFormat, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_outputFormat\n");


		return outputFormat;
	}

 

	@SuppressWarnings("unchecked")
	public UIOutput outputText(Object... args) {
		UIOutput outputText = new HtmlOutputText();
		System.out.print("outputText");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)outputText).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(),
						((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIOutput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(outputText).getChildren().add((UIComponent) arg);
				} else {
					((outputText).getFacets()).put((String)((UIOutput)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(outputText.getClass().getDeclaredMethod("setConverter", outputText.getClass()) != null){
						outputText.getClass().getDeclaredMethod("setConverter", outputText.getClass()).invoke(outputText, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(outputText.getClass().getDeclaredMethod("addValidator", outputText.getClass()) != null){
						outputText.getClass().getDeclaredMethod("addValidator", outputText.getClass()).invoke(outputText, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_outputText\n");


		return outputText;
	}

 

	@SuppressWarnings("unchecked")
	public UIOutput outputLabel(Object... args) {
		UIOutput outputLabel = new HtmlOutputLabel();
		System.out.print("outputLabel");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)outputLabel).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(),
						((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIOutput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(outputLabel).getChildren().add((UIComponent) arg);
				} else {
					((outputLabel).getFacets()).put((String)((UIOutput)arg).getAttributes().get("name"),
							(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(outputLabel.getClass().getDeclaredMethod("setConverter", outputLabel.getClass()) != null){
						outputLabel.getClass().getDeclaredMethod("setConverter", outputLabel.getClass()).invoke(outputLabel, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(outputLabel.getClass().getDeclaredMethod("addValidator", outputLabel.getClass()) != null){
						outputLabel.getClass().getDeclaredMethod("addValidator", outputLabel.getClass()).invoke(outputLabel, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_outputLabel\n");


		return outputLabel;
	}

 

	@SuppressWarnings("unchecked")
	public UIGraphic grapicImage(Object... args) {
		UIGraphic grapicImage = new HtmlGraphicImage();
		System.out.print("grapicImage");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)grapicImage).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(),
						((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIGraphic)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(grapicImage).getChildren().add((UIComponent) arg);
				} else {
					((grapicImage).getFacets()).put((String)((UIGraphic)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(grapicImage.getClass().getDeclaredMethod("setConverter", grapicImage.getClass()) != null){
						grapicImage.getClass().getDeclaredMethod("setConverter", grapicImage.getClass()).invoke(grapicImage, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(grapicImage.getClass().getDeclaredMethod("addValidator", grapicImage.getClass()) != null){
						grapicImage.getClass().getDeclaredMethod("addValidator", grapicImage.getClass()).invoke(grapicImage, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_grapicImage\n");


		return grapicImage;
	}

 

	@SuppressWarnings("unchecked")
	public UICommand commandLink(Object... args) {
		UICommand commandLink = new HtmlCommandLink();
		System.out.print("commandLink");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)commandLink).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UICommand)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(commandLink).getChildren().add((UIComponent) arg);
				} else {
					((commandLink).getFacets()).put((String)((UICommand)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(commandLink.getClass().getDeclaredMethod("setConverter", commandLink.getClass()) != null){
						commandLink.getClass().getDeclaredMethod("setConverter", commandLink.getClass()).invoke(commandLink, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(commandLink.getClass().getDeclaredMethod("addValidator", commandLink.getClass()) != null){
						commandLink.getClass().getDeclaredMethod("addValidator", commandLink.getClass()).invoke(commandLink, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_commandLink\n");


		return commandLink;
	}

 

	@SuppressWarnings("unchecked")
	public UICommand commandButton(Object... args) {
		HtmlCommandButton commandButton = new HtmlCommandButton();
		System.out.print("commandButton");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)commandButton).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UICommand)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					((UICommand)commandButton).getChildren().add((UIComponent) arg);
				} else {
					(((UICommand)commandButton).getFacets()).put((String)((UICommand)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(commandButton.getClass().getDeclaredMethod("setConverter", commandButton.getClass()) != null){
						commandButton.getClass().getDeclaredMethod("setConverter", commandButton.getClass()).invoke(commandButton, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(commandButton.getClass().getDeclaredMethod("addValidator", commandButton.getClass()) != null){
						commandButton.getClass().getDeclaredMethod("addValidator", commandButton.getClass()).invoke(commandButton, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_commandButton\n");


		return  commandButton;
	}

 

	@SuppressWarnings("unchecked")
	public UIOutput outputLink(Object... args) {
		UIOutput outputLink = new HtmlOutputLink();
		System.out.print("outputLink");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)outputLink).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIOutput)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(outputLink).getChildren().add((UIComponent) arg);
				} else {
					((outputLink).getFacets()).put((String)((UIOutput)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(outputLink.getClass().getDeclaredMethod("setConverter", outputLink.getClass()) != null){
						outputLink.getClass().getDeclaredMethod("setConverter", outputLink.getClass()).invoke(outputLink, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(outputLink.getClass().getDeclaredMethod("addValidator", outputLink.getClass()) != null){
						outputLink.getClass().getDeclaredMethod("addValidator", outputLink.getClass()).invoke(outputLink, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_outputLink\n");


		return outputLink;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectOne selectOneListbox(Object... args) {
		UISelectOne selectOneListbox = new HtmlSelectOneListbox();
		System.out.print("selectOneListbox");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectOneListbox).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectOne)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectOneListbox).getChildren().add((UIComponent) arg);
				} else {
					((selectOneListbox).getFacets()).put((String)((UISelectOne)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectOneListbox.getClass().getDeclaredMethod("setConverter", selectOneListbox.getClass()) != null){
						selectOneListbox.getClass().getDeclaredMethod("setConverter", selectOneListbox.getClass()).invoke(selectOneListbox, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectOneListbox.getClass().getDeclaredMethod("addValidator", selectOneListbox.getClass()) != null){
						selectOneListbox.getClass().getDeclaredMethod("addValidator", selectOneListbox.getClass()).invoke(selectOneListbox, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectOneListbox\n");


		return selectOneListbox;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectOne selectOneMenu(Object... args) {
		UISelectOne selectOneMenu = new HtmlSelectOneMenu();
		System.out.print("selectOneMenu");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectOneMenu).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectOne)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectOneMenu).getChildren().add((UIComponent) arg);
				} else {
					((selectOneMenu).getFacets()).put((String)((UISelectOne)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectOneMenu.getClass().getDeclaredMethod("setConverter", selectOneMenu.getClass()) != null){
						selectOneMenu.getClass().getDeclaredMethod("setConverter", selectOneMenu.getClass()).invoke(selectOneMenu, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectOneMenu.getClass().getDeclaredMethod("addValidator", selectOneMenu.getClass()) != null){
						selectOneMenu.getClass().getDeclaredMethod("addValidator", selectOneMenu.getClass()).invoke(selectOneMenu, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectOneMenu\n");


		return selectOneMenu;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectOne selectOneRadio(Object... args) {
		UISelectOne selectOneRadio = new HtmlSelectOneRadio();
		System.out.print("selectOneRadio");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectOneRadio).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectOne)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectOneRadio).getChildren().add((UIComponent) arg);
				} else {
					((selectOneRadio).getFacets()).put((String)((UISelectOne)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectOneRadio.getClass().getDeclaredMethod("setConverter", selectOneRadio.getClass()) != null){
						selectOneRadio.getClass().getDeclaredMethod("setConverter", selectOneRadio.getClass()).invoke(selectOneRadio, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectOneRadio.getClass().getDeclaredMethod("addValidator", selectOneRadio.getClass()) != null){
						selectOneRadio.getClass().getDeclaredMethod("addValidator", selectOneRadio.getClass()).invoke(selectOneRadio, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectOneRadio\n");


		return selectOneRadio;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectBoolean selectBooleanCheckbox(Object... args) {
		UISelectBoolean selectBooleanCheckbox = new HtmlSelectBooleanCheckbox();
		System.out.print("selectBooleanCheckbox");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectBooleanCheckbox).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectBoolean)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectBooleanCheckbox).getChildren().add((UIComponent) arg);
				} else {
					((selectBooleanCheckbox).getFacets()).put((String)((UISelectBoolean)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectBooleanCheckbox.getClass().getDeclaredMethod("setConverter", selectBooleanCheckbox.getClass()) != null){
						selectBooleanCheckbox.getClass().getDeclaredMethod("setConverter", selectBooleanCheckbox.getClass()).invoke(selectBooleanCheckbox, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectBooleanCheckbox.getClass().getDeclaredMethod("addValidator", selectBooleanCheckbox.getClass()) != null){
						selectBooleanCheckbox.getClass().getDeclaredMethod("addValidator", selectBooleanCheckbox.getClass()).invoke(selectBooleanCheckbox, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectBooleanCheckbox\n");


		return selectBooleanCheckbox;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectMany selectManyCheckbox(Object... args) {
		UISelectMany selectManyCheckbox = new HtmlSelectManyCheckbox();
		System.out.print("selectManyCheckbox");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectManyCheckbox).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectMany)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectManyCheckbox).getChildren().add((UIComponent) arg);
				} else {
					((selectManyCheckbox).getFacets()).put((String)((UISelectMany)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectManyCheckbox.getClass().getDeclaredMethod("setConverter", selectManyCheckbox.getClass()) != null){
						selectManyCheckbox.getClass().getDeclaredMethod("setConverter", selectManyCheckbox.getClass()).invoke(selectManyCheckbox, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectManyCheckbox.getClass().getDeclaredMethod("addValidator", selectManyCheckbox.getClass()) != null){
						selectManyCheckbox.getClass().getDeclaredMethod("addValidator", selectManyCheckbox.getClass()).invoke(selectManyCheckbox, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectManyCheckbox\n");


		return selectManyCheckbox;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectMany selectManyListbox(Object... args) {
		UISelectMany selectManyListbox = new HtmlSelectManyListbox();
		System.out.print("selectManyListbox");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectManyListbox).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectMany)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectManyListbox).getChildren().add((UIComponent) arg);
				} else {
					((selectManyListbox).getFacets()).put((String)((UISelectMany)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectManyListbox.getClass().getDeclaredMethod("setConverter", selectManyListbox.getClass()) != null){
						selectManyListbox.getClass().getDeclaredMethod("setConverter", selectManyListbox.getClass()).invoke(selectManyListbox, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectManyListbox.getClass().getDeclaredMethod("addValidator", selectManyListbox.getClass()) != null){
						selectManyListbox.getClass().getDeclaredMethod("addValidator", selectManyListbox.getClass()).invoke(selectManyListbox, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectManyListbox\n");


		return selectManyListbox;
	}

 

	@SuppressWarnings("unchecked")
	public UISelectMany selectManyMenu(Object... args) {
		UISelectMany selectManyMenu = new HtmlSelectManyMenu();
		System.out.print("selectManyMenu");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)selectManyMenu).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UISelectMany)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(selectManyMenu).getChildren().add((UIComponent) arg);
				} else {
					((selectManyMenu).getFacets()).put((String)((UISelectMany)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(selectManyMenu.getClass().getDeclaredMethod("setConverter", selectManyMenu.getClass()) != null){
						selectManyMenu.getClass().getDeclaredMethod("setConverter", selectManyMenu.getClass()).invoke(selectManyMenu, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(selectManyMenu.getClass().getDeclaredMethod("addValidator", selectManyMenu.getClass()) != null){
						selectManyMenu.getClass().getDeclaredMethod("addValidator", selectManyMenu.getClass()).invoke(selectManyMenu, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_selectManyMenu\n");


		return selectManyMenu;
	}

 

	@SuppressWarnings("unchecked")
	public UIMessage message(Object... args) {
		UIMessage message = new HtmlMessage();
		System.out.print("message");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)message).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIMessage)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(message).getChildren().add((UIComponent) arg);
				} else {
					((message).getFacets()).put((String)((UIMessage)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(message.getClass().getDeclaredMethod("setConverter", message.getClass()) != null){
						message.getClass().getDeclaredMethod("setConverter", message.getClass()).invoke(message, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(message.getClass().getDeclaredMethod("addValidator", message.getClass()) != null){
						message.getClass().getDeclaredMethod("addValidator", message.getClass()).invoke(message, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_message\n");


		return message;
	}

 

	@SuppressWarnings("unchecked")
	public UIMessages messages(Object... args) {
		UIMessages messages = new HtmlMessages();
		System.out.print("messages");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)messages).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIMessages)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(messages).getChildren().add((UIComponent) arg);
				} else {
					((messages).getFacets()).put((String)((UIMessages)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(messages.getClass().getDeclaredMethod("setConverter", messages.getClass()) != null){
						messages.getClass().getDeclaredMethod("setConverter", messages.getClass()).invoke(messages, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(messages.getClass().getDeclaredMethod("addValidator", messages.getClass()) != null){
						messages.getClass().getDeclaredMethod("addValidator", messages.getClass()).invoke(messages, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_messages\n");


		return messages;
	}

 

	@SuppressWarnings("unchecked")
	public UIPanel panelGrid(Object... args) {
		UIPanel panelGrid = new HtmlPanelGrid();
		System.out.print("panelGrid");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)panelGrid).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIPanel)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(panelGrid).getChildren().add((UIComponent) arg);
				} else {
					((panelGrid).getFacets()).put((String)((UIPanel)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(panelGrid.getClass().getDeclaredMethod("setConverter", panelGrid.getClass()) != null){
						panelGrid.getClass().getDeclaredMethod("setConverter", panelGrid.getClass()).invoke(panelGrid, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(panelGrid.getClass().getDeclaredMethod("addValidator", panelGrid.getClass()) != null){
						panelGrid.getClass().getDeclaredMethod("addValidator", panelGrid.getClass()).invoke(panelGrid, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_panelGrid\n");


		return panelGrid;
	}

 

	@SuppressWarnings("unchecked")
	public UIPanel panelGroup(Object... args) {
		UIPanel panelGroup = new HtmlPanelGroup();
		System.out.print("panelGroup");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)panelGroup).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIComponent)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(panelGroup).getChildren().add((UIComponent) arg);
				} else {
					((panelGroup).getFacets()).put((String)((UIPanel)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(panelGroup.getClass().getDeclaredMethod("setConverter", panelGroup.getClass()) != null){
						panelGroup.getClass().getDeclaredMethod("setConverter", panelGroup.getClass()).invoke(panelGroup, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(panelGroup.getClass().getDeclaredMethod("addValidator", panelGroup.getClass()) != null){
						panelGroup.getClass().getDeclaredMethod("addValidator", panelGroup.getClass()).invoke(panelGroup, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_panelGroup\n");


		return panelGroup;
	}

 

	@SuppressWarnings("unchecked")
	public UIData dataTable(Object... args) {
		UIData dataTable = new HtmlDataTable();
		System.out.print("dataTable");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)dataTable).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIData)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(dataTable).getChildren().add((UIComponent) arg);
				} else {
					((dataTable).getFacets()).put((String)((UIData)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(dataTable.getClass().getDeclaredMethod("setConverter", dataTable.getClass()) != null){
						dataTable.getClass().getDeclaredMethod("setConverter", dataTable.getClass()).invoke(dataTable, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(dataTable.getClass().getDeclaredMethod("addValidator", dataTable.getClass()) != null){
						dataTable.getClass().getDeclaredMethod("addValidator", dataTable.getClass()).invoke(dataTable, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_dataTable\n");


		return dataTable;
	}

 

	@SuppressWarnings("unchecked")
	public UIColumn column(Object... args) {
		UIColumn column = new HtmlColumn();
		System.out.print("column");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)column).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIColumn)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					(column).getChildren().add((UIComponent) arg);
				} else {
					((column).getFacets()).put((String)((UIColumn)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(column.getClass().getDeclaredMethod("setConverter", column.getClass()) != null){
						column.getClass().getDeclaredMethod("setConverter", column.getClass()).invoke(column, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(column.getClass().getDeclaredMethod("addValidator", column.getClass()) != null){
						column.getClass().getDeclaredMethod("addValidator", column.getClass()).invoke(column, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_column\n");


		return column;
	}

 

	@SuppressWarnings("unchecked")
	public UIComponent facet(Object... args) {
		UIComponent facet = new Facet();
		System.out.print("facet");
		incrementIndent();
		System.out.println(" ");

		for(Object arg : args){
			if(arg instanceof ComponentAttribute){
				((UIComponent)facet).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
			} else if(arg instanceof UIComponent){
				if(((UIComponent)arg).getFacet((String)((UIComponent)arg).getAttributes().get("name")) == null){
					((UIComponent)facet).getChildren().add((UIComponent) arg);
				} else {
					((Map<String, UIComponent>) ((UIComponent)facet).getFacets()).put((String)((UIComponent)arg).getAttributes().get("name"),(UIComponent) arg);
				}
			} else if(arg instanceof Converter){
				try {
					if(facet.getClass().getDeclaredMethod("setConverter", facet.getClass()) != null){
						facet.getClass().getDeclaredMethod("setConverter", facet.getClass()).invoke(facet, (Converter) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			} else if(arg instanceof Validator){
				try {
					if(facet.getClass().getDeclaredMethod("addValidator", facet.getClass()) != null){
						facet.getClass().getDeclaredMethod("addValidator", facet.getClass()).invoke(facet, (Validator) arg);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
			}
		}
		
		decrementIndent();
		System.out.print("end_facet\n");


		return (UIComponent) facet;
	}

 
			
			@SuppressWarnings("unchecked")
			public Converter convertNumber(Object... args) {
				Converter convertNumber = new NumberConverter();
				System.out.print("convertNumber");
				incrementIndent();
				System.out.println(" ");
				
				decrementIndent();
				System.out.print("end_convertNumber\n");
					
				return (Converter) convertNumber;
			}
 
			
			@SuppressWarnings("unchecked")
			public Converter convertDateTime(Object... args) {
				Converter convertDateTime = new DateTimeConverter();
				System.out.print("convertDateTime");
				incrementIndent();
				System.out.println(" ");
				
				for(Object arg : args){
					if(arg instanceof ComponentAttribute){
						((UIComponentBase) convertDateTime).getAttributes().put(((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
					} else {
					throw new IllegalArgumentException("convertDateTime method can only accept ComponentAttributes as arguments");
				}
				decrementIndent();
				System.out.print("end_convertDateTime\n");
					
				}
				return (Converter) convertDateTime;
			}


			@SuppressWarnings("unchecked")
			public UIComponent facet(String name) {
				
				UIComponent facet = createFacet(name);

				System.out.print("facet");
				incrementIndent();
				System.out.println(" ");
								
				decrementIndent();
				System.out.print("end_facet\n");
				
				return (UIComponent) facet;
			}


			@SuppressWarnings("unchecked")
			public SelectItem selectItems(Object... args) {
				SelectItem selectItemGroup =  new SelectItemGroup();
				System.out.print("selectItemGroup");
				incrementIndent();
				System.out.println(" ");
				
				for(Object arg : args){
					if(arg instanceof ComponentAttribute){
						try {
							PropertyUtils.setProperty(selectItemGroup, ((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
						} catch (IllegalAccessException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							
							e.printStackTrace();
						}
					} else {
					throw new IllegalArgumentException("selectItemGroup method can only accept ComponentAttributes as arguments");
				}
				decrementIndent();
				System.out.print("end_selectItemGroup\n");
					
				}
				return  selectItemGroup;
			}

			@SuppressWarnings("unchecked")
			public SelectItem selectItem(Object... args) {
				SelectItem selectItem =  new SelectItem();
				System.out.print("selectItem");
				incrementIndent();
				System.out.println(" ");
		
				for(Object arg : args){
				if(arg instanceof ComponentAttribute){
						try {
							PropertyUtils.setProperty(selectItem, ((ComponentAttribute<String, Object>) arg).getKey(), ((ComponentAttribute<String, Object>) arg).getValue());
						} catch (IllegalAccessException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							
							e.printStackTrace();
						}
				} else {
					throw new IllegalArgumentException("selectItem method can only accept ComponentAttributes as arguments");
				}
				decrementIndent();
				System.out.print("end_selectItem\n");
		
				}
				return selectItem;
			}

		protected UIOutput createVerbatim() {
			assert null != getFacesContext();
			UIOutput verbatim = new HtmlOutputText();
			verbatim.setTransient(true);
			verbatim.getAttributes().put("escape", Boolean.FALSE);
			verbatim.setId(getFacesContext().getViewRoot().createUniqueId());
			return verbatim;
		}

		protected UIComponent createFacet(String name)  {

        	UIComponent component = new HtmlOutputText();
        	return component;
		}

		protected UIComponent createComponent(FacesContext context, String newId){
			Application app = context.getApplication();
			UIComponent component = app.createComponent(newId);
			return component;
		}

		    /**
     * <p>Create and return a new {@link Converter} to be registered
     * on our surrounding {@link UIComponent}.</p>
     *
     */
    protected Converter createConverter(String converterIdVal) {
        Converter converter = null;
			converter = context.getApplication().createConverter(converterIdVal);
			if(converter == null){
				throw new RuntimeException("unable to create converter with id " + converterIdVal);
			}
        return converter;
    }


		protected FacesContext getFacesContext() {
			if(this.context == null){
				throw new NullPointerException("facesContext must not be null");
			} else {
				return this.context;
			}
		}





	public ComponentAttribute<String, Object> summary(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("summary", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> disabledClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("disabledClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> showSummary(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("showSummary", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> accept(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("accept", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> forComponent(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("forComponent", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> columns(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("columns", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> bgcolor(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("bgcolor", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> border(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("border", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> ondblclick(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("ondblclick", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> timeStyle(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("timeStyle", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> charset(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("charset", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> image(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("image", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> showDetail(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("showDetail", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> rendered(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("rendered", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> longdesc(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("longdesc", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> errorStyle(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("errorStyle", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> columnClasses(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("columnClasses", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> binding(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("binding", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onmousedown(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onmousedown", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> warnStyle(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("warnStyle", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> actionListener(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("actionListener", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> jBuiltActionListener(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("jBuiltActionListener", value);
		return attribute;
	}
	public ComponentAttribute<String, Object> converterId(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("converterId", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> rev(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("rev", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> validator(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("validator", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> maxFractionDigits(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("maxFractionDigits", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> warnClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("warnClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> accesskey(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("accesskey", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> escape(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("escape", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> title(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("title", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> converter(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("converter", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> enctype(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("enctype", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> fatalClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("fatalClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> acceptcharset(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("acceptcharset", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> validateLength(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("validateLength", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> enabledClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("enabledClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onkeypress(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onkeypress", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onkeydown(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onkeydown", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> fatalStyle(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("fatalStyle", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onsubmit(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onsubmit", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> pattern(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("pattern", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> currencySymbol(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("currencySymbol", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> rel(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("rel", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> readonly(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("readonly", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> minFractionDigits(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("minFractionDigits", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> cols(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("cols", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> globalOnly(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("globalOnly", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> cellpadding(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("cellpadding", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> type(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("type", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> cellspacing(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("cellspacing", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> hreflang(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("hreflang", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> errorClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("errorClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> valueChangeListener(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("valueChangeListener", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> maxlength(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("maxlength", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> height(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("height", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> headerClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("headerClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onblur(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onblur", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> value(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("value", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> action(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("action", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> required(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("required", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> integerOnly(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("integerOnly", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onmouseout(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onmouseout", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> styleClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("styleClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> width(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("width", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onkeyup(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onkeyup", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onfocus(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onfocus", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> shape(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("shape", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> validateDoubleRange(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("validateDoubleRange", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> url(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("url", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> rows(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("rows", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onreset(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onreset", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> size(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("size", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> groupingUsed(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("groupingUsed", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> frame(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("frame", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onselect(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onselect", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> rowClasses(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("rowClasses", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> validateLongRange(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("validateLongRange", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> usemap(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("usemap", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> redisplay(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("redisplay", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> var(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("var", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> locale(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("locale", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> timeZone(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("timeZone", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> lang(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("lang", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> id(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("id", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> ismap(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("ismap", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> style(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("style", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> dir(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("dir", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> dateStyle(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("dateStyle", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> alt(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("alt", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> name(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("name", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> minIntegerDigits(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("minIntegerDigits", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onmouseup(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onmouseup", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> layout(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("layout", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> maxIntegerDigits(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("maxIntegerDigits", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> footerClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("footerClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onmousemove(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onmousemove", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> validatorId(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("validatorId", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> currencyCode(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("currencyCode", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> tabindex(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("tabindex", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> rules(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("rules", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onchange(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onchange", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onmouseover(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onmouseover", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> infoStyle(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("infoStyle", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> immediate(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("immediate", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> coords(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("coords", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> target(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("target", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> onclick(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("onclick", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> first(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("first", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> infoClass(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("infoClass", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> disabled(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("disabled", value);
		 return attribute;
	}
	public ComponentAttribute<String, Object> tooltip(final Object value) {
		ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("tooltip", value);
		 return attribute;
	}

	public ComponentAttribute<String, Boolean> prependId(final Boolean value) {
		ComponentAttribute<String, Boolean> attribute = new ComponentAttribute<String, Boolean>("prependId", value);
		 return attribute;
	}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getIndentCount() {
			return indentCount;
		}

		public void setIndentCount(int indentCount) {
			this.indentCount = indentCount;
		}

		public String getIndent() {
			return indent;
		}

		public void setIndent(String indent) {
			this.indent = indent;
		}

		public StringBuilder getCurrentIndent() {
			return currentIndent;
		}

		public void setCurrentIndent(StringBuilder currentIndent) {
			this.currentIndent = currentIndent;
		}

		
}