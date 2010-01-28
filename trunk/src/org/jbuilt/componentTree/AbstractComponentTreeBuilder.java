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

package org.jbuilt.componentTree;


import static org.jbuilt.utils.IdGenerator.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionListener;
import javax.faces.validator.Validator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jawr.web.taglib.jsf.CSSBundleTag;
import net.jawr.web.taglib.jsf.ImgHtmlTag;
import net.jawr.web.taglib.jsf.JavascriptBundleTag;

import org.jbuilt.components.custom.Chart;
import org.jbuilt.components.custom.PieChart;
import org.jbuilt.components.html.raw.A;
import org.jbuilt.components.html.raw.Blockquote;
import org.jbuilt.components.html.raw.Body;
import org.jbuilt.components.html.raw.Br;
import org.jbuilt.components.html.raw.Button;
import org.jbuilt.components.html.raw.Canvas;
import org.jbuilt.components.html.raw.Caption;
import org.jbuilt.components.html.raw.Code;
import org.jbuilt.components.html.raw.Col;
import org.jbuilt.components.html.raw.Colgroup;
import org.jbuilt.components.html.raw.Dd;
import org.jbuilt.components.html.raw.Div;
import org.jbuilt.components.html.raw.Dl;
import org.jbuilt.components.html.raw.Dt;
import org.jbuilt.components.html.raw.Em;
import org.jbuilt.components.html.raw.Fieldset;
import org.jbuilt.components.html.raw.Form;
import org.jbuilt.components.html.raw.Frame;
import org.jbuilt.components.html.raw.Frameset;
import org.jbuilt.components.html.raw.H1;
import org.jbuilt.components.html.raw.H2;
import org.jbuilt.components.html.raw.H3;
import org.jbuilt.components.html.raw.H4;
import org.jbuilt.components.html.raw.H5;
import org.jbuilt.components.html.raw.H6;
import org.jbuilt.components.html.raw.Head;
import org.jbuilt.components.html.raw.Hr;
import org.jbuilt.components.html.raw.Html;
import org.jbuilt.components.html.raw.Iframe;
import org.jbuilt.components.html.raw.Input;
import org.jbuilt.components.html.raw.Label;
import org.jbuilt.components.html.raw.Legend;
import org.jbuilt.components.html.raw.Li;
import org.jbuilt.components.html.raw.Link;
import org.jbuilt.components.html.raw.Meta;
import org.jbuilt.components.html.raw.ObjectElement;
import org.jbuilt.components.html.raw.Ol;
import org.jbuilt.components.html.raw.Optgroup;
import org.jbuilt.components.html.raw.Option;
import org.jbuilt.components.html.raw.P;
import org.jbuilt.components.html.raw.Param;
import org.jbuilt.components.html.raw.Pre;
import org.jbuilt.components.html.raw.Script;
import org.jbuilt.components.html.raw.Select;
import org.jbuilt.components.html.raw.Span;
import org.jbuilt.components.html.raw.Strong;
import org.jbuilt.components.html.raw.StyleElement;
import org.jbuilt.components.html.raw.Table;
import org.jbuilt.components.html.raw.Tbody;
import org.jbuilt.components.html.raw.Td;
import org.jbuilt.components.html.raw.Text;
import org.jbuilt.components.html.raw.TextArea;
import org.jbuilt.components.html.raw.Tfoot;
import org.jbuilt.components.html.raw.Th;
import org.jbuilt.components.html.raw.Thead;
import org.jbuilt.components.html.raw.TitleElement;
import org.jbuilt.components.html.raw.Tr;
import org.jbuilt.components.html.raw.Ul;
import org.jbuilt.components.html.raw.base.AbstractInputComponent;
import org.jbuilt.utils.Attribute;
import org.jbuilt.utils.ClassClassMapEntry;
import org.jbuilt.utils.Closure;
import org.jbuilt.utils.ComponentAttribute;
import org.jbuilt.utils.JsfUtil;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;
import org.jbuilt.view.AbstractViewBuilder;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

import com.sun.faces.extensions.avatar.components.AjaxZone;
import com.sun.faces.util.Util;



public abstract class AbstractComponentTreeBuilder extends AbstractViewBuilder {

		private UIComponent tree;
		public int count = 0;
		protected int indentCount;
		protected String indent = "....|";
		protected StringBuilder currentIndent = new StringBuilder();
		protected FacesContext context;
		protected UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		private Map<ClassClassMapEntry, Closure> argTypeMap;
		private List<UIComponent> componentsToExclude = new ArrayList<UIComponent>();
		protected boolean redirect = true;
		// not necessary to add this to navigateTo() method but can be used for emphasis
		protected boolean dontRedirect = false;
		protected HttpServletRequest request;
		protected HttpServletResponse response;
		protected String userAgent;
	    protected Boolean isFirefox;
	    protected Boolean isMSIE;
		


		public List<UIComponent> getComponentsToExclude() {
			return componentsToExclude;
		}

		public void setComponentsToExclude(List<UIComponent> componentsToExclude) {
			this.componentsToExclude = componentsToExclude;
		}

		public AbstractComponentTreeBuilder(UIComponent rootOrComponent) {
			if(rootOrComponent instanceof UIViewRoot){
				this.tree = rootOrComponent;
			} else {
				this.tree =  rootOrComponent;
			}
		}

		public AbstractComponentTreeBuilder() {}

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
		public void createView(FacesContext context) {
			this.buildView(context);
//				for(UIComponent child : view.getChildren()){
//					System.out.println("printing child " + child + " of " + child.getParent().getClass().getSimpleName());
//					((AbstractComponent)child).printToConsole();
//				}
		}

			
		public abstract UIComponent writeView();
		
		public abstract void beforeWriteView();
	
		public abstract void afterWriteView();
	


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



		public UIComponent getTree() {
			return tree;
		}

		public void setTree(UIComponent tree) {
			this.tree = tree;
		}


 
			public Map<ClassClassMapEntry, Closure> getArgTypeMap() {
			return argTypeMap;
		}

		public void setArgTypeMap(Map<ClassClassMapEntry, Closure> localArgTypeMap) {
			this.argTypeMap = localArgTypeMap;
		}
		
		public void generateIdIfNecessary(UIComponent component){
			if(component.getId() == null || component.getAttributes().get("id")== null){
				String generatedId = generateId(component.getClass().getSimpleName().toLowerCase());
				component.setId(generatedId);
				component.getAttributes().put("id", generatedId);
			}

		}

			@SuppressWarnings("unchecked")
			public UIComponent a(Object... args) {
				UIComponent a = new A();
				processArgs(a, args);
				return a;
			}
			@SuppressWarnings("unchecked")
			public UIComponent blockquote(Object... args) {
				UIComponent blockquote = new Blockquote();
				processArgs(blockquote, args);
				return blockquote;
			}

		 
			@SuppressWarnings("unchecked")
			public UIComponent body(Object... args) {
				UIComponent body = new Body();
				processArgs(body, args);
				return body;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent br(Object... args) {
				UIComponent br = new Br();
				processArgs(br, args);
				return br;
			}

			@SuppressWarnings("unchecked")
			public UIComponent button(Object... args) {
				UIComponent button = new Button();
				processArgs(button, args);
				return button;
			}

			@SuppressWarnings("unchecked")
			public UIComponent canvas(Object... args) {
				UIComponent canvas = new Canvas();
				processArgs(canvas, args);
				return canvas;
			}

			@SuppressWarnings("unchecked")
			public UIComponent caption(Object... args) {
				UIComponent caption = new Caption();
				processArgs(caption, args);
				return caption;
			}

			@SuppressWarnings("unchecked")
			public UIComponent code(Object... args) {
				UIComponent code = new Code();
				processArgs(code, args);
				return code;
			}

			@SuppressWarnings("unchecked")
			public UIComponent col(Object... args) {
				UIComponent col = new Col();
				processArgs(col, args);
				return col;
			}


			@SuppressWarnings("unchecked")
			public UIComponent colgroup(Object... args) {
				UIComponent colgroup = new Colgroup();
				processArgs(colgroup, args);
				return colgroup;
			}


			@SuppressWarnings("unchecked")
			public UIComponent dd(Object... args) {
				UIComponent dd = new Dd();
				processArgs(dd, args);
				return dd;
			}


			@SuppressWarnings("unchecked")
			public UIComponent dl(Object... args) {
				UIComponent dl = new Dl();
				processArgs(dl, args);
				return dl;
			}


			@SuppressWarnings("unchecked")
			public UIComponent dt(Object... args) {
				UIComponent dt = new Dt();
				processArgs(dt, args);
				return dt;
			}


			@SuppressWarnings("unchecked")
			public UIComponent em(Object... args) {
				UIComponent em = new Em();
				processArgs(em, args);
				return em;
			}


			@SuppressWarnings("unchecked")
			public UIComponent fieldset(Object... args) {
				UIComponent fieldset = new Fieldset();
				processArgs(fieldset, args);
				return fieldset;
			}


			@SuppressWarnings("unchecked")
			public UIComponent frame(Object... args) {
				UIComponent frame = new Frame();
				processArgs(frame, args);
				return frame;
			}


			@SuppressWarnings("unchecked")
			public UIComponent frameset(Object... args) {
				UIComponent frameset = new Frameset();
				processArgs(frameset, args);
				return frameset;
			}


			@SuppressWarnings("unchecked")
			public UIComponent iframe(Object... args) {
				UIComponent iframe = new Iframe();
				processArgs(iframe, args);
				return iframe;
			}


			@SuppressWarnings("unchecked")
			public UIComponent hr(Object... args) {
				UIComponent hr = new Hr();
				processArgs(hr, args);
				return hr;
			}


			@SuppressWarnings("unchecked")
			public UIComponent img(Object... args) {
//				UIComponent img = new Img();
			  // use Jawr image for now
        UIComponent img = new ImgHtmlTag();
				processArgs(img, args);
				return img;
			}


			@SuppressWarnings("unchecked")
			public UIComponent legend(Object... args) {
				UIComponent legend = new Legend();
				processArgs(legend, args);
				return legend;
			}


			@SuppressWarnings("unchecked")
			public UIComponent link(Object... args) {
				UIComponent link = new Link();
				processArgs(link, args);
				return link;
			}


			@SuppressWarnings("unchecked")
			public UIComponent meta(Object... args) {
				UIComponent meta = new Meta();
				processArgs(meta, args);
				return meta;
			}


			@SuppressWarnings("unchecked")
			public UIComponent objectElement(Object... args) {
				UIComponent objectElement = new ObjectElement();
				processArgs(objectElement, args);
				return objectElement;
			}


			@SuppressWarnings("unchecked")
			public UIComponent ol(Object... args) {
				UIComponent ol = new Ol();
				processArgs(ol, args);
				return ol;
			}


			@SuppressWarnings("unchecked")
			public UIComponent optgroup(Object... args) {
				UIComponent optgroup = new Optgroup();
				processArgs(optgroup, args);
				return optgroup;
			}


			@SuppressWarnings("unchecked")
			public UIComponent param(Object... args) {
				UIComponent param = new Param();
				processArgs(param, args);
				return param;
			}

		 
			@SuppressWarnings("unchecked")
			public UIComponent div(Object... args) {
				UIComponent div = new Div();
				processArgs(div, args);
				return div;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent form(Object... args) {
				UIComponent form = new Form();
				processArgs(form, args);
//				String generatedId = generateId(form.getClass().getSimpleName().toLowerCase());
//				form.setId(generatedId);
//				form.getAttributes().put("id", generatedId);
				return form;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent h1(Object... args) {
				UIComponent h1 = new H1();
				processArgs(h1, args);
				return h1;
			}

			@SuppressWarnings("unchecked")
			public UIComponent h2(Object... args) {
				UIComponent h2 = new H2();
				processArgs(h2, args);
				return h2;
			}

			@SuppressWarnings("unchecked")
			public UIComponent h3(Object... args) {
				UIComponent h3 = new H3();
				processArgs(h3, args);
				return h3;
			}
			@SuppressWarnings("unchecked")
			public UIComponent h4(Object... args) {
				UIComponent h4 = new H4();
				processArgs(h4, args);
				return h4;
			}


			@SuppressWarnings("unchecked")
			public UIComponent h5(Object... args) {
				UIComponent h5 = new H5();
				processArgs(h5, args);
				return h5;
			}

			@SuppressWarnings("unchecked")
			public UIComponent h6(Object... args) {
				UIComponent h6 = new H6();
				processArgs(h6, args);
				return h6;
			}


		 
			@SuppressWarnings("unchecked")
			public UIComponent head(Object... args) {
				UIComponent head = new Head();
				processArgs(head, args);
				return head;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent html(Object... args) {
				UIComponent html = new Html();
				processArgs(html, args);
				return html;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent input(Object... args) {
				UIComponent input = new Input();
				processArgs(input, args);
				return input;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent label(Attribute<String, String> id, Attribute<String, String> forAttribute, Attribute<String, String> title, UIComponent text) {
				UIComponent label = new Label();
						Map<String, Object> attributes = label.getAttributes();
							attributes.put((String) ((Attribute) forAttribute).getKey(), (forAttribute).getValue());
							attributes.put((String) ((Attribute) id).getKey(), (id).getValue());
							attributes.put((String) ((Attribute) title).getKey(), (title).getValue());
							label.getChildren().add(text);

							return label;
			}
			
      @SuppressWarnings("unchecked")
      public UIComponent labelElement(Object... args) {
        UIComponent label = new Label();
        processArgs(label, args);
        return label;
      }
      
			@SuppressWarnings("unchecked")
			public UIComponent li(Object... args) {
				UIComponent li = new Li();
				processArgs(li, args);
				return li;
			}
			
 
			@SuppressWarnings("unchecked")
			public UIComponent option(Object... args) {
				UIComponent option = new Option();
				processArgs(option, args);
				return option;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent p(Object... args) {
				UIComponent p = new P();
				processArgs(p, args);
				return p;
			}

			@SuppressWarnings("unchecked")
			public UIComponent pre(Object... args) {
				UIComponent pre = new Pre();
				processArgs(pre, args);
				return pre;
			}


			@SuppressWarnings("unchecked")
			public UIComponent strong(Object... args) {
				UIComponent strong = new Strong();
				processArgs(strong, args);
				return strong;
			}


			@SuppressWarnings("unchecked")
			public UIComponent tfoot(Object... args) {
				UIComponent tfoot = new Tfoot();
				processArgs(tfoot, args);
				return tfoot;
			}


			@SuppressWarnings("unchecked")
			public UIComponent thead(Object... args) {
				UIComponent thead = new Thead();
				processArgs(thead, args);
				return thead;
			}

			@SuppressWarnings("unchecked")
			public UIComponent tbody(Object... args) {
				UIComponent tbody = new Tbody();
				processArgs(tbody, args);
				return tbody;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent span(Object... args) {
				UIComponent span = new Span();
				processArgs(span, args);
				return span;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent select(Object... args) {
				UIComponent select = new Select();
				processArgs(select, args);
				return select;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent textArea(Object... args) {
				UIComponent textArea = new TextArea();
				processArgs(textArea, args);
				return textArea;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent script(Object... args) {
				UIComponent script = new Script();
				processArgs(/*(Script)*/ script, args);
				if(((Script) script).getType() == null){
					((Script) script).setType("text/javascript");
				}
				return script;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent styleElement(Object... args) {
				UIComponent styleElement = new StyleElement();
				processArgs(styleElement, args);
				if(((StyleElement) styleElement).getType() == null){
					((StyleElement) styleElement).setType("text/css");
				}
				return styleElement;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent table(Object... args) {
				UIComponent table = new Table();
				processArgs(table, args);
				return table;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent th(Object... args) {
				UIComponent th = new Th();
				processArgs(th, args);
				return th;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent td(Object... args) {
				UIComponent td = new Td();
				processArgs(td, args);
				return td;
			}
		
			@SuppressWarnings("unchecked")
			public UIComponent text(Object... args) {
				UIComponent text = new Text();
				StringBuilder sb;
				if(args != null){
					sb = new StringBuilder();
					Map<String, Object> attributes = text.getAttributes();
					for(Object arg : args){
						if(arg == null){
							System.out.println("arg for " + attributes + " was null");
							continue;
						}
//						if(!(arg instanceof String) || !(arg instanceof ValueClosure)){
//						}
						Object value = null;
						if(!(arg instanceof String) && !(arg instanceof ValueClosure)){
						value = ((Attribute) arg).getValue();
						}
						if(arg instanceof String){
							if(((String) arg).startsWith("#{")){
								ValueExpression ve = FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
								.createValueExpression(FacesContext.getCurrentInstance().getELContext(), (String) arg, String.class);
								sb.append((String) ve.getValue(FacesContext.getCurrentInstance().getELContext()));
							} else {
								sb.append((String) arg);
							}
							continue;
						} else if(arg instanceof ValueClosure) {
							((Text)text).getValueClosures().add((ValueClosure) arg);
							sb.append(" valueClosure");
							continue;
						} else if(value instanceof Boolean){
							attributes.put(((Attribute<String, Boolean>) arg).getKey(),
									((Attribute<String, Boolean>) arg).getValue());
							continue;
						} else if(value instanceof Integer){
							attributes.put(((Attribute<String, Integer>) arg).getKey(),
									((Attribute<String, Integer>) arg).getValue());
							continue;
						} else if(value instanceof ValueExpression){
							attributes.put(((Attribute<String, ValueExpression>) arg).getKey(),
									((Attribute<String, ValueExpression>) arg).getValue());
							continue;
						} else if(value instanceof MethodBinding){
							attributes.put(((Attribute<String, MethodBinding>) arg).getKey(),
									((Attribute<String, MethodBinding>) arg).getValue());
							continue;
						} else if(value instanceof MethodExpression){
							attributes.put(((Attribute<String, MethodExpression>) arg).getKey(),
									((Attribute<String, MethodExpression>) arg).getValue());
							continue;
						} else if(value instanceof Object){
							attributes.put(((Attribute<String, Object>) arg).getKey(),
									((Attribute<String, Object>) arg).getValue());
						}
					}
					((Text) text).setTextString(sb.toString());
				}
				return text;
			}
			
			private UIComponent setTextString(Text text, String string){
				text.setTextString(string);
				return text;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent tr(Object... args) {
				UIComponent tr = new Tr();
				processArgs(tr, args);
				return tr;
			}
		 
			@SuppressWarnings("unchecked")
			public UIComponent ul(Object... args) {
				UIComponent ul = new Ul();
				processArgs(ul, args);
				return ul;
			}
			
			@SuppressWarnings("unchecked")
			public UIComponent titleElement(Object... args) {
				UIComponent titleElement = new TitleElement();
				processArgs(titleElement, args);
				return titleElement;
			}
			
			/* *******************************************************************************
			 * Jawr components
			 ******************************************************************************* */
			
			@SuppressWarnings("unchecked")
			public UIComponent jawrScript(Object... args) {
				UIComponent jawrScript = new JavascriptBundleTag();
				processArgs(jawrScript, args);
				return jawrScript;
			}

			@SuppressWarnings("unchecked")
			public UIComponent jawrStyle(Object... args) {
				UIComponent jawrStyle = new CSSBundleTag();
				processArgs(jawrStyle, args);
				return jawrStyle;
			}

			@SuppressWarnings("unchecked")
			public UIComponent jawrImg(Object... args) {
				UIComponent jawrImg = new ImgHtmlTag();
				processArgs(jawrImg, args);
				return jawrImg;
			}
			
			/* *******************************************************************************
			 * DynamicFaces Ajax components
			 ******************************************************************************* */

			@SuppressWarnings("unchecked")
			public UIComponent ajaxZone(Object... args) {
				UIComponent ajaxZone = new AjaxZone();
				processArgs(ajaxZone, args);
				return ajaxZone;
			}
			
			
			/* *******************************************************************************
			 * Custom composite component factory methods
			 ******************************************************************************* */


			/**
			 * pieChart()
			 * @param T extends Map<String, Integer>
			 * @param Object... args
			 */
			@SuppressWarnings("unchecked")
			public <T> Chart<T> pieChart(Map<String, Integer> chartData,
					String title, String containerId, String chartId, String formId,
					Integer width, Integer height, Object... args) {
				
				if(chartData == null){
					chartData = new HashMap<String, Integer>();
					chartData.put("data1", 100);
					chartData.put("data2", 56);
					chartData.put("data3", 23);
					chartData.put("data4", 75);
					chartData.put("data5", 115);
					chartData.put("data6", 30);
				}

				Chart<PieChart> pieChart = new PieChart(chartData);
				((PieChart) pieChart).setChartId(chartId);
				((PieChart) pieChart).setTitle(title);
				((PieChart) pieChart).setFormId(formId);
				((PieChart) pieChart).setContainerId(containerId);
				((PieChart) pieChart).setWidth(width);
				((PieChart) pieChart).setHeight(height);
				
//				final UIComponent chartComponent =
//					((JsfComponentTreeViewDirector) pieChart).writeView();
//
//				Closure populateFieldSet = new Closure() {
//					public Object execute(Object... args) {
//						Map.Entry dataPoint = (Entry) args[0];
//						chartComponent.getChildren().add(
//								input(id((String) dataPoint.getKey()), styleClass("slider"),
//										type("text"), value(dataPoint.getValue())));
//						return null;
//					}
//				};
				
//				forEach(chartData, populateFieldSet);

//				processArgs(chartComponent, args);

				return (Chart<T>) pieChart;
			}
			
			/* *******************************************************************************
			 * Process Arguments
			 ******************************************************************************* */
			
			public void processStringArg(UIComponent component, String arg){
				StringBuilder sb = new StringBuilder();
				if(arg.startsWith("#{")){
					ValueExpression ve = FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
					.createValueExpression(FacesContext.getCurrentInstance().getELContext(),  arg, String.class);
					sb.append((String) ve.getValue(FacesContext.getCurrentInstance().getELContext()));
				} else {
					sb.append( arg);
				}
				if(component instanceof HasStringBody){
					((HasStringBody)component).getStringBuilder().append(sb.toString());
				}

			}
		
			public Attribute<String, Boolean> addToTree(final Boolean value) {
				Attribute<String, Boolean> attribute = new ComponentAttribute<String, Boolean>("addToTree", value);
				 return attribute;
			}
			public Attribute<String, String> id(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("id", value);
				 return attribute;
			}
		  public ComponentAttribute<String, Object> alt(final Object value) {
		    ComponentAttribute<String, Object> attribute = new ComponentAttribute<String, Object>("alt", value);
		     return attribute;
		  }
			public Attribute<String, String> href(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("href", value);
				return attribute;
			}
			public Attribute<String, String> disabled(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("disabled", value);
				return attribute;
			}
			public Attribute<String, String> name(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("name", value);
				 return attribute;
			}
			public Attribute<String, Object> value(final Object value) {
				Attribute<String, Object> attribute = new ComponentAttribute<String, Object>("value", value);
				 return attribute;
			}
			public Attribute<String, String> method(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("method", value);
				 return attribute;
			}
			public Attribute<String, Object> action(final Object value) {
				Attribute<String, Object> attribute = new ComponentAttribute<String, Object>("action", value);
				 return attribute;
			}
			public Attribute<String, Integer> colspan(final Integer value) {
				Attribute<String, Integer> attribute = new ComponentAttribute<String, Integer>("colspan", value);
				 return attribute;
			}
			public Attribute<String, String> scope(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("scope", value);
				 return attribute;
			}
			public Attribute<String, String> labelAttribute(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("label", value);
				 return attribute;
			}
			public Attribute<String, Boolean> selected(final Boolean value) {
				Attribute<String, Boolean> attribute = new ComponentAttribute<String, Boolean>("selected", value);
				 return attribute;
			}
			public Attribute<String, String> dir(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("dir", value);
				 return attribute;
			}
			public Attribute<String, String> lang(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("lang", value);
				 return attribute;
			}
			public Attribute<String, String> title(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("title", value);
				 return attribute;
			}

			public Attribute<String, String> style(String... values) {
				StringBuilder sb = new StringBuilder();
				for(String value : values){
					sb.append(value);
				}
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("style", sb.toString());
				return attribute;
			}


			public Attribute<String, String> styleClass(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("styleClass", value);
				 return attribute;
			}
			public Attribute<String, String> ondblclick(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("ondblclick", value);
				 return attribute;
			}
			public Attribute<String, String> onmousedown(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onmousedown", value);
				 return attribute;
			}
			public Attribute<String, String> onmouseup(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onmouseup", value);
				 return attribute;
			}
			public Attribute<String, String> onmouseover(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onmouseover", value);
				 return attribute;
			}
			public Attribute<String, String> onmousemove(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onmousemove", value);
				 return attribute;
			}
			public Attribute<String, String> onmouseout(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onmouseout", value);
				 return attribute;
			}
			public Attribute<String, String> onkeypress(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onkeypress", value);
				 return attribute;
			}
			public Attribute<String, String> onkeydown(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onkeydown", value);
				 return attribute;
			}
			public Attribute<String, String> onkeyup(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onkeyup", value);
				 return attribute;
			}
			public Attribute<String, String> onclick(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onclick", value);
				 return attribute;
			}
			public Attribute<String, String> accept(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("accept", value);
				 return attribute;
			}
			public Attribute<String, String> accept_charset(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("accept_charset", value);
				 return attribute;
			}
			public Attribute<String, String> enctype(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("enctype", value);
				 return attribute;
			}
			public Attribute<String, String> onreset(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onreset", value);
				 return attribute;
			}
			public Attribute<String, String> onsubmit(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onsubmit", value);
				 return attribute;
			}
			public Attribute<String, String> target(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("target", value);
				 return attribute;
			}
			public Attribute<String, String> xmlns(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("xmlns", value);
				 return attribute;
			}
			public Attribute<String, String> onfocus(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onfocus", value);
				 return attribute;
			}
			public Attribute<String, String> onblur(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onblur", value);
				 return attribute;
			}
			public Attribute<String, String> onselect(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onselect", value);
				 return attribute;
			}
			public Attribute<String, String> onchange(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("onchange", value);
				 return attribute;
			}
			public Attribute<String, String> type(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("type", value);
				 return attribute;
			}
			public Attribute<String, String> multiple(final String... value) {
				Attribute<String, String> attribute = null;
				if(value != null && value.length > 0){
					attribute = new ComponentAttribute<String, String>("multiple", value[0]);
				} else {
					attribute = new ComponentAttribute<String, String>("multiple", "multiple");
				}
				return attribute;
			}
			public Attribute<String, String> size(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("size", value);
				return attribute;
			}
			public Attribute<String, String> accesskey(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("accesskey", value);
				 return attribute;
			}
			public Attribute<String, String> forAttribute(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("for", value);
				 return attribute;
			}
			public Attribute<String, String> src(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("src", value);
				 return attribute;
			}
			public Attribute<String, String> align(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("align", value);
				 return attribute;
			}
			public Attribute<String, String> bgcolor(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("bgcolor", value);
				 return attribute;
			}
			public Attribute<String, String> border(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("border", value);
				 return attribute;
			}
			public Attribute<String, Integer> cellpadding(final Integer value) {
				Attribute<String, Integer> attribute = new ComponentAttribute<String, Integer>("cellpadding", value);
				 return attribute;
			}
			public Attribute<String, Integer> cellspacing(final Integer value) {
				Attribute<String, Integer> attribute = new ComponentAttribute<String, Integer>("cellspacing", value);
				 return attribute;
			}
			public Attribute<String, String> datafld(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("datafld", value);
				 return attribute;
			}
			public Attribute<String, String> datasrc(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("datasrc", value);
				 return attribute;
			}
			public Attribute<String, String> dataformatas(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("dataformatas", value);
				 return attribute;
			}
			public Attribute<String, String> frame(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("frame", value);
				 return attribute;
			}
			// custom YUI grids attribute
			public Attribute<String, String> role(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("role", value);
				 return attribute;
			}
			public Attribute<String, String> rules(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("rules", value);
				 return attribute;
			}
			public Attribute<String, String> summary(final String value) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("summary", value);
				 return attribute;
			}
			public Attribute<String, String> height(final String string) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("height", string);
				 return attribute;
			}
			public Attribute<String, String> width(final String string) {
				Attribute<String, String> attribute = new ComponentAttribute<String, String>("width", string);
				return attribute;
			}
			public Attribute<String, Class> typeConstraint(final Class klass) {
				Attribute<String, Class> attribute = new ComponentAttribute<String, Class>("typeConstraint", klass);
				return attribute;
			}
			
			/* *******************************************************************************
			 * DynamicFaces Ajax attributes
			 ******************************************************************************* */

			public Attribute<String, String> eventType(final String string) {
				Attribute<String, String> eventType = new ComponentAttribute<String, String>("eventType", string);
				 return eventType;
			}
			
			public Attribute<String, String> inspectElement(final String string) {
				Attribute<String, String> inspectElement = new ComponentAttribute<String, String>("inspectElement", string);
				 return inspectElement;
			}
			
			public Attribute<String, String> collectPostData(final String string) {
				Attribute<String, String> collectPostData = new ComponentAttribute<String, String>("collectPostData", string);
				 return collectPostData;
			}
			
			public Attribute<String, String> postReplace(final String string) {
				Attribute<String, String> postReplace = new ComponentAttribute<String, String>("postReplace", string);
				 return postReplace;
			}
			
			public Attribute<String, String> replaceElement(final String string) {
				Attribute<String, String> replaceElement = new ComponentAttribute<String, String>("replaceElement", string);
				 return replaceElement;
			}
			
			public Attribute<String, String> getCallbackData(final String string) {
				Attribute<String, String> getCallbackData = new ComponentAttribute<String, String>("getCallbackData", string);
				 return getCallbackData;
			}
			
			public Attribute<String, String> execute(final String string) {
				Attribute<String, String> execute = new ComponentAttribute<String, String>("execute", string);
				 return execute;
			}
			
			public Attribute<String, String> render(final String string) {
				Attribute<String, String> render = new ComponentAttribute<String, String>("render", string);
				 return render;
			}
			
			
			
			
			
			
			ClassClassMapEntry<Class<String>, Class<HasStringBody>> stringArgHasStringBodyComponentMapEntry =
				new ClassClassMapEntry<Class<String>, Class<HasStringBody>>();
			
			protected void populateArgTypeMap(){
				Map<ClassClassMapEntry, Closure> localArgTypeMap = new HashMap<ClassClassMapEntry, Closure>(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					{
						put(stringArgHasStringBodyComponentMapEntry, stringArgHasStringBodyComponentClosure);
					}
				};
				this.setArgTypeMap(localArgTypeMap);
			}
			
			Closure stringArgHasStringBodyComponentClosure = new Closure(){
				public Object execute(Object... args) {
					processStringArg((UIComponent) args[0], (String)args[1]);
					return null;
				}
			};

		

		protected void processArgs(UIComponent component, Object...args){
			Map<String, Object> attributes = component.getAttributes();
			for(Object arg : args){
				if(arg instanceof String  && component instanceof HasStringBody){
						processStringArg( component, (String) arg);
				}
				if(arg instanceof Attribute){
					Object value = ((Attribute) arg).getValue();
					if(value instanceof String && component instanceof UIInput){
						if(((String) value).startsWith("#{")){
							ValueExpression ve = FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
							.createValueExpression(FacesContext.getCurrentInstance().getELContext(), (String) value, String.class);
							attributes.put(((Attribute<String, String>) arg).getKey(),
									ve.getValue(FacesContext.getCurrentInstance().getELContext()));
						} else {
							attributes.put(((Attribute<String, String>) arg).getKey(),
								((Attribute<String, String>) arg).getValue());
						}
						continue;
					} else if(value instanceof Boolean){
						if(((Attribute<String, Boolean>) arg).getKey().equals("addToTree") &&
								((Attribute<String, Boolean>) arg).getValue()){
							component.getChildren().add((UIComponent)arg);
						} else {
						attributes.put(((Attribute<String, Boolean>) arg).getKey(),
								((Attribute<String, Boolean>) arg).getValue());
						if(!componentsToExclude.contains(component)) {
              componentsToExclude.add(component);
            }
						}
						continue;
					} else if(value instanceof Integer){
						attributes.put(((Attribute<String, Integer>) arg).getKey(),
								((Attribute<String, Integer>) arg).getValue());
						continue;
					} else if(value instanceof ValueClosure){
						((UIInput)component).getAttributes().put(((Attribute<String, ValueClosure>) arg).getKey(),
								(value)/*.execute(component)*/);
//							if(component instanceof Input && ((Input)component).getType().equals("text")){
//								((Input)component).setValue((ValueClosure) value);
//							}
						continue;
					} else if(value instanceof MethodClosure && component instanceof AbstractInputComponent){
						attributes.put(((Attribute<String, MethodClosure>) arg).getKey(),
								(value)/*.execute()*/);
						((AbstractInputComponent) component).setMethodClosure((MethodClosure) value);
						((AbstractInputComponent) component).setAction((MethodClosure) value);
						
						continue;
					} else if(value instanceof Validator && component instanceof UIInput){
						((UIInput) value).addValidator((Validator) value);
						continue;
					} else if(value instanceof Converter && component instanceof ValueHolder){
						((ValueHolder) component).setConverter((Converter) value);
						attributes.put(((Attribute<String, Converter>) arg).getKey(),
								((Attribute<String, Converter>) arg).getValue());
						continue;
					}  else if(value instanceof ActionListener && component instanceof AbstractInputComponent){
						attributes.put(((Attribute<String, ActionListener>) arg).getKey(),
								((Attribute<String, ActionListener>) arg).getValue());
						((AbstractInputComponent) component).addActionListener((ActionListener) value);
						continue;
					} else if(value instanceof ValueExpression){
						attributes.put(((Attribute<String, ValueExpression>) arg).getKey(),
								((Attribute<String, ValueExpression>) arg).getValue());
						continue;
					} else if(value instanceof MethodBinding){
						attributes.put(((Attribute<String, MethodBinding>) arg).getKey(),
								((Attribute<String, MethodBinding>) arg).getValue());
						continue;
					} else if(value instanceof MethodExpression){
						attributes.put(((Attribute<String, MethodExpression>) arg).getKey(),
								((Attribute<String, MethodExpression>) arg).getValue());
						continue;
					} else if(value instanceof Object){
						attributes.put(((Attribute<String, Object>) arg).getKey(),
								((Attribute<String, Object>) arg).getValue());
						continue;
					}
				} else if(arg instanceof UIComponent){
					if(!component.getChildren().contains(arg) && !componentsToExclude.contains(component)){
						component.getChildren().add((UIComponent)arg);
					}
					continue;
				} else {
//					attributes.put("id", ""+Calendar.getInstance().getTimeInMillis()+"generatedId");
//					generateIdIfNecessary(component);
				}
			}
			generateIdIfNecessary(component);
		}
		
//		private <T> void processAttribute(UIComponent component, Attribute<String, T> attribute){
//			component.getAttributes().put(((Attribute<String, T>) attribute).getKey(), ((Attribute<String, T>) attribute).getValue());
//		}
		

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		@Override
    public int getIndentCount() {
			return indentCount;
		}

		@Override
    public void setIndentCount(int indentCount) {
			this.indentCount = indentCount;
		}

		@Override
    public String getIndent() {
			return indent;
		}

		@Override
    public void setIndent(String indent) {
			this.indent = indent;
		}

		@Override
    public StringBuilder getCurrentIndent() {
			return currentIndent;
		}

		@Override
    public void setCurrentIndent(StringBuilder currentIndent) {
			this.currentIndent = currentIndent;
		}
		
		/**************************************************************************************
		 * custom component factory methods
		 ***************************************************************************************/
		
		
		public MethodExpression methodExpression(String methodExpressionString,
				Class<?> expectedType, Class<?>... parameterType){
			MethodExpression methodExpression = FacesContext.getCurrentInstance().getApplication()
			.getExpressionFactory()
			.createMethodExpression(FacesContext.getCurrentInstance().getELContext(),
					methodExpressionString, expectedType, parameterType);
			
			return methodExpression;
		}

		public ValueExpression valueExpression(String valueExpressionString,
				Class<?> expectedType, Class<?>... parameterType){
			ValueExpression valueExpression = FacesContext.getCurrentInstance().getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						valueExpressionString, expectedType
				);
				
			return valueExpression;
		}
		
		public UIComponent repeat(Collection<? extends UIComponent> components){
			UIComponent div = new Div();
			for(UIComponent component : components ) {
				div.getChildren().add(component);
			}
			return div;
		}
		
		public UIComponent repeat(UIComponent parent, Collection<? extends UIComponent> components){
			if(parent == null){
				throw new IllegalArgumentException("parent cannot be null");
			}
			for(UIComponent component : components ) {
				parent.getChildren().add(component);
			}
			return parent;
		}
		
		public <T extends UIComponent> UIComponent repeat(	UIComponent wrapper,
															Class<T> componentClass,
															int times){
			for(int i=0; i<times; i++ ){
				T component = null;
				try {
					component = componentClass.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(component != null){
//					component.setId("repeat"+i);
					component.getAttributes().put("id", "repeatId"+i);
					component.getAttributes().put("value", "repeat"+i+"Value");
					UIComponent text = new Text();
					((Text)text).setTextString("repeat"+Integer.toString(i)+"Text");

					component.getChildren().add(text);
					wrapper.getChildren().add(component);
				}
			}
			return wrapper;
		}
		
		public UIComponent include(JsfComponentTreeViewDirector subTree){
			return (subTree).writeView();
		}

		public UIComponent include(UIComponent subTree){
			return subTree;
		}
		
		public UIComponent include(String nameOrViewId){
			UIComponent view = null;
			String name = null;
			String viewId = null;
			if(nameOrViewId.startsWith("/")){
				viewId = nameOrViewId;
//				TODO: implement view with new way
//				view = (UIComponent) JBuiltViewHandler.getViewContainer().getViewByViewId(viewId);
			} else {
				name = nameOrViewId;
//				view = (UIComponent) JBuiltViewHandler.getViewContainer().getViewByName(name);
			}
			return view;
		}
		
		public <T> void navigateTo(Class<T> fqn){
			String packageName = fqn.getPackage().getName();
			String simpleName = fqn.getSimpleName();
			String responseViewId =
			    prepareViewIdPackage(packageName)
				.concat(simpleName);
			JsfUtil.navigateTo(responseViewId);
		}
		
        public void navigateTo(String responseViewId){
            JsfUtil.navigateTo(responseViewId);
        }
        
		private String prepareViewIdPackage(String packageName){
		    String preparedPackage = "/".concat(
                    packageName.replace(".", "/")
            )
            .concat("/");
		    return preparedPackage;
		}
		
		
        @SuppressWarnings("unchecked")
        public UIComponent canvasClock(Object... args) {
            UIComponent canvasClock = canvas();
            processArgs(canvasClock, args);
            UIComponent div = div(
                    canvasClock,
                    jawrScript(
                        src("/js/clock_canvas.js")
                    )
                    );
                    
            
            return div;
        }


		
		
		/* *******************************************************************************
		 * implicit servlet methods
		 * 
		 *  servlet		interface javax.servlet.ServletContext		The current servlet context
			faces		class javax.faces.context.FacesContext		The current faces context
			el			class javax.el.ELResolver					The current Expression Language resolver
			ef			class javax.el.ExpressionFactory			The current Expression Factory
			elCtx		class javax.el.ELContext					The current Expression Language context
			viewId		class java.lang.String						The view id of the current view
			path		class java.lang.String						The current file system path corresponding to the current view
			parentPath	class gracelets.util.FileWalker				An easy navigational object for getting relative files to the current view
			servletRoot	class gracelets.util.FileWalker				An easy navigational object for getting relative files to the servlet context
			actionURL	class java.lang.String						The url corresponding to the current view used for example with forms or links for post backs
			external	class javax.faces.context.ExternalContext	The current JSF external context
			request		class java.lang.Object						The current request object (an instanceof HttpServletRequest for http requests)
			response	class java.lang.Object						The current response object (an instanceof HttpServletResponse for http responses)
			param		interface java.util.Map						The current map that holds the posted variables for this request
			paramNames	interface java.util.Collection				A collection holding the names of the posted variables for this request
			varCtx		interface java.util.Map						Used to resolve context variables that have name clashes with local variables
			headers		interface java.util.Map						A header name -> collection map. Generally each collection will only contain one value.
			cookies		interface java.util.Map						A cookie name -> Cookie object map.
			useragent	class java.lang.String						A simple convenience method allowing one to be able to quickly evaluate the user agent.
			*Context
		 * 
		 ******************************************************************************* */
		
		
	public ServletContext servletContext() {
		return JsfUtil.getServletContext();
	}

	public String contextPath() {
		return servletContext().getContextPath();
	}

	public FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ELResolver el() {
		return facesContext().getApplication().getELResolver();
	}

	public ExpressionFactory ef() {
		return facesContext().getApplication().getExpressionFactory();
	}

	public ELContext elCtx() {
		return facesContext().getELContext();
	}

	public String viewId() {
		return facesContext().getViewRoot().getId();
	}

	public String path() {
		return servletContext().getRealPath(contextPath());
	}

	// public parentPath(){}
	// public servletRoot(){}
	public String actionURL() {
		return Util.getViewHandler(facesContext()).getActionURL(facesContext(),
				viewId());
	}

	public ExternalContext extContext() {
		return facesContext().getExternalContext();
	}

	public HttpServletRequest request() {
		return (HttpServletRequest) extContext().getRequest();
	}

	public HttpServletResponse response() {
		return (HttpServletResponse) extContext().getResponse();
	}

	public Map params() {
		return extContext().getRequestParameterMap();
	}

	public Collection paramNames() {
		return (Collection) extContext().getRequestParameterNames();
	}

	// public Map varCtx(){}
	public Map headers() {
		return extContext().getRequestHeaderMap();
	}

	public Map cookies() {
		return extContext().getRequestCookieMap();
	}

	public String userAgent() {
	    if(userAgent == null){
	    userAgent = (String) headers().get("User-Agent");
	    }
		return userAgent;
	}
	
	public Boolean isFirefox(){
	    String ua = userAgent();
	     isFirefox = ua != null && ua.indexOf( "Firefox/" ) != -1 ;
	     return isFirefox.booleanValue();
	}
	
    public Boolean isMSIE(){
        String ua = userAgent();
         isMSIE = ua != null && ua.indexOf( "MSIE" ) != -1 ;
         return isMSIE.booleanValue();
    }
    
//	String ua = ((HttpServletRequest) request()).getHeader( "User-Agent" );
//	response.setHeader( "Vary", "User-Agent" );
		
}


		

