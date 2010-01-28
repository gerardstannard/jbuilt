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
package org.jbuilt.components.html.raw;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;

import org.jbuilt.common.renderkit.html.HtmlRendererUtils;
import org.jbuilt.components.html.raw.base.AbstractInputComponent;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;

public class Option extends AbstractInputComponent {
	public Option(){
		setRendererType(null);
}

    
     /* Property: id */
    private String id;
    
    @Override
    public String getId() {

	if (this.id != null) {
	    return this.id;
	}
	ValueExpression ve = getValueExpression("id");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setId(String id) {
        this.id = id;
    }
    
     /* Property: name */
    private String name;
    
    public String getName() {

	if (this.name != null) {
	    return this.name;
	}
	ValueExpression ve = getValueExpression("name");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setName(String name) {
        this.name = name;
    }
    
     /* Property: value */
    private Object value;
    
    @Override
    public Object getValue() {

	if (this.value != null) {
	    return this.value;
	}
	ValueExpression ve = getValueExpression("value");
	if (ve != null) {
	    try {
		return ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setValue(Object value) {
        this.value = value;
    }
    
     /* Property: method */
    private String method;
    
    public String getMethod() {

	if (this.method != null) {
	    return this.method;
	}
	ValueExpression ve = getValueExpression("method");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setMethod(String method) {
        this.method = method;
    }
    
     /* Property: action */
    private MethodClosure actionX;
    
     /* Property: colspan */
    private Integer colspan;
    
    public Integer getColspan() {

	if (this.colspan != null) {
	    return this.colspan;
	}
	ValueExpression ve = getValueExpression("colspan");
	if (ve != null) {
	    try {
		return (Integer) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }
    
     /* Property: scope */
    private String scope;
    
    public String getScope() {

	if (this.scope != null) {
	    return this.scope;
	}
	ValueExpression ve = getValueExpression("scope");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }
    
    


    public void setScope(String scope) {
        this.scope = scope;
    }
    
     /* Property: labelAttribute */
    private String labelAttribute;
    
    public String getLabelAttribute() {

	if (this.labelAttribute != null) {
	    return this.labelAttribute;
	}
	ValueExpression ve = getValueExpression("labelAttribute");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setLabelAttribute(String labelAttribute) {
        this.labelAttribute = labelAttribute;
    }
    
     /* Property: selected */
    private Boolean selected;
    
    public Boolean getSelected() {

	if (this.selected != null) {
	    return this.selected;
	}
	ValueExpression ve = getValueExpression("selected");
	if (ve != null) {
	    try {
		return (Boolean) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
     /* Property: dir */
    private String dir;
    
    @Override
    public String getDir() {

	if (this.dir != null) {
	    return this.dir;
	}
	ValueExpression ve = getValueExpression("dir");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setDir(String dir) {
        this.dir = dir;
    }
    
     /* Property: lang */
    private String lang;
    
    @Override
    public String getLang() {

	if (this.lang != null) {
	    return this.lang;
	}
	ValueExpression ve = getValueExpression("lang");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setLang(String lang) {
        this.lang = lang;
    }
    
     /* Property: title */
    private String title;
    
    @Override
    public String getTitle() {

	if (this.title != null) {
	    return this.title;
	}
	ValueExpression ve = getValueExpression("title");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    
     /* Property: style */
    private String style;
    
    @Override
    public String getStyle() {

	if (this.style != null) {
	    return this.style;
	}
	ValueExpression ve = getValueExpression("style");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setStyle(String style) {
        this.style = style;
    }
    
     /* Property: styleClass */
    private String styleClass;
    
    @Override
    public String getStyleClass() {

	if (this.styleClass != null) {
	    return this.styleClass;
	}
	ValueExpression ve = getValueExpression("styleClass");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

		@Override
		public Renderer getRenderer(FacesContext context){
			return null;
		}

		@Override
		public String getRendererType(){
			return null;
		}

    /**
     * <p>Render nested child components by invoking the encode methods
     * on those components, but only when the <code>rendered</code>
     * property is <code>true</code>.</p>
     *
     * @param context FacesContext for the current request
     * @param component the component to recursively encode
     *
     * @throws IOException if an error occurrs during the encode process
     */


  @Override
  public void encodeAll(FacesContext context){
	if(context == null){
		throw new NullPointerException("FacesContext cannot be null");
	}
	UIComponent component = this;
	Option option = (Option) component;
	try {
		option.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		option.encodeEnd(context, component);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

  @Override
  public void decode(FacesContext context /*, UIComponent component*/ ) {
		Map<String, String> requestMap = context.getExternalContext()
		.getRequestParameterMap();
		String clientId = this.getClientId(context);

		// Don't overwrite the value unless you have to!
		Object oldValue = null;
		ValueClosure oldValueClosure = null;
		if (getValue() instanceof ValueClosure) {
			oldValueClosure = (ValueClosure) getValue();
		}
		String newValue = requestMap.get(clientId);
		
        Map<String, String[]> requestParameterValuesMap =
            context.getExternalContext().
                  getRequestParameterValuesMap();
      

		
		if (newValue != null) {
			decodeOne(context);
		} else if (requestParameterValuesMap.containsKey(clientId)) {
			decodeMany(context);
		} else {
			
		}


  
  }
  
  
  
  
  
	public void decodeOne(FacesContext context/* , UIComponent component */) {
		UIComponent component = this;
		rendererParamsNotNull(context, this);

		if (!(component instanceof UIInput)) {
			// decode needs to be invoked only for components that are
			// instances or subclasses of UIInput.
			if (logger.isLoggable(Level.FINE)) {
				logger
						.log(
								Level.FINE,
								"No decoding necessary since the component {0} is not an instance or a sub class of UIInput",
								component.getId());
			}
			return;
		}

			component.queueEvent(new ActionEvent(component));

			if (logger.isLoggable(Level.FINE)) {
				logger.fine("This command resulted in form submission "
						+ " ActionEvent queued.");
				logger.log(Level.FINE, "End decoding component {0}", component
						.getId());
			}

		if (!shouldDecode(this)) {
			return;
		}

		String clientId = this.getClientId(context);
		assert clientId != null;
		Map<String, String> requestMap = context.getExternalContext()
				.getRequestParameterMap();
		// Don't overwrite the value unless you have to!
		Object oldValue = null;
		ValueClosure oldValueClosure = null;
		if (getValue() instanceof ValueClosure) {
			oldValueClosure = (ValueClosure) getValue();
		}
		String newValue = requestMap.get(clientId);
		if (newValue != null) {
			// if no renderer don't need component argument
			if (oldValueClosure != null) {
				oldValueClosure.setValue(oldValueClosure.getConvertedValue(newValue, this));
				setSubmittedValue(oldValueClosure);
			} else {
//				setSubmittedValue(newValue);
				setSubmittedValue(getConvertedValue(context, newValue));
			}
			if (logger.isLoggable(Level.FINE)) {
				logger
						.log(Level.FINE, "new value after decoding {0}",
								newValue);
			}
		}
	}
	
	
    
    public void decodeMany(FacesContext context) {

        rendererParamsNotNull(context, this);

        if (!shouldDecode(this)) {
            return;
        }

        String clientId = this.getClientId(context);
        assert clientId != null;
        // currently we assume the model type to be of type string or
        // convertible to string and localized by the application.
//        if (component instanceof UISelectMany) {
            Map<String, String[]> requestParameterValuesMap =
                  context.getExternalContext().
                        getRequestParameterValuesMap();
            if (requestParameterValuesMap.containsKey(clientId)) {
                String newValues[] = requestParameterValuesMap.
                      get(clientId);
                setSubmittedValue(this, newValues);
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("submitted values for UISelectMany component "
                                +
                                this.getId()
                                + " after decoding "
                                + Arrays.toString(newValues));
                }
            } else {
                // Use the empty array, not null, to distinguish
                // between an deselected UISelectMany and a disabled one
                setSubmittedValue(this, new String[0]);
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("Set empty array for UISelectMany component " +
                                this.getId() + " after decoding ");
                }
            }
     /*   } else {
            // this is a UISelectOne
            Map<String, String> requestParameterMap =
                  context.getExternalContext().
                        getRequestParameterMap();
            if (requestParameterMap.containsKey(clientId)) {
                String newValue = requestParameterMap.get(clientId);
                setSubmittedValue(component, newValue);
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("submitted value for UISelectOne component "
                                +
                                component.getId()
                                + " after decoding "
                                + newValue);
                }

            } else {
                // there is no value, but this is different from a null
                // value.
                setSubmittedValue(component, RIConstants.NO_VALUE);
            }
        }*/

    }





						
//		public void encodeBegin(FacesContext context, UIComponent component)
//			            throws IOException {
//					component = this;
//			        if (context == null){
//			            throw new NullPointerException();
//			        }
//			        super.encodeBegin(context, component);
//            		String tag = this.getStringValue();
//            		if( tag.trim().length() == 0 ) // Don't render the tag, but render the children.
//					return;
//
//			        if (this.isRendered()) {
//			            ResponseWriter writer = context.getResponseWriter();
//
//
//            			writer.startElement(tag, component);
//            			HtmlRendererUtils.writeIdIfNecessary(writer, component, context);
//
//			            HtmlRendererUtils.renderHTMLAttributes(writer, component, new String[ ] {
//					 "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass" });
//
//
//
//
//    }
//}
		
		
		@Override
    public void encodeBegin(FacesContext context, UIComponent component)
		throws IOException {
	component = this;
	if (context == null) {
		throw new NullPointerException();
	}
	super.encodeBegin(context, component);
	String tag = this.getStringValue();
	if (tag.trim().length() == 0) {
    // children.
		return;
  }

	if (this.isRendered()) {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement(tag, component);
		HtmlRendererUtils.writeIdIfNecessary(writer, component, context);
		Map<String, String> requestMap = context.getExternalContext()
		.getRequestParameterMap();

		Object tempValue = null;
		Object finalValue = "";
		
		if (getValue() instanceof ValueClosure) {
			tempValue = ((ValueClosure) getValue()).execute(this);
			if(tempValue != null){
				finalValue = tempValue.equals(0) ? "" : tempValue;
			}
		} else if(getSubmittedValue() instanceof ValueClosure){
			tempValue = ((ValueClosure) getSubmittedValue()).execute(this);
			if(tempValue != null){
				finalValue = tempValue.equals(0) ? "" : tempValue;
			}
		} else {
			tempValue = getSubmittedValue();
			if(tempValue != null){
				finalValue = tempValue.equals(0) ? "" : tempValue;
			} else {
				tempValue = getAttributes().get("value");
				if(tempValue != null){
					finalValue = tempValue.equals(0) ? "" : tempValue;
				}
			}
		}
		
		String optionValue = null;
		optionValue = requestMap.get(((Select)this.getParent()).getName());
		if(optionValue != null){
			writer.writeAttribute("value", finalValue , "value");
			if(finalValue.toString().equals(optionValue)){
				writer.writeAttribute("selected", Boolean.TRUE, "selected");
//				writer.writeAttribute("value", finalValue , "value");
			}
		} else {
			writer.writeAttribute("value", finalValue , "value");
		}

		

		
//			HtmlRendererUtils.renderHTMLAttributes(writer, component,
//					new String[] { "value" });
		// if(getValue() == null ) {
		// writer.writeAttribute("value", "", "value");
		// }

            HtmlRendererUtils.renderHTMLAttributes(writer, component, new String[ ] {
			 "id",  "name",  /*"value",*/  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass" });

	}
}







	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "option";
	     }



   // @Override
    public void encodeChildren(FacesContext context, UIComponent component)
          throws IOException {
        // no-op
    }

  //  @Override
    public void encodeEnd(FacesContext context, UIComponent component)
          throws IOException {
		component = this;
		super.encodeEnd(context);
        ResponseWriter writer = context.getResponseWriter();

 HtmlRendererUtils.renderOptionalEndElement(writer, component, "option", new String[ ] { "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass" });
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[15 ];
        values[0 ] = super.saveState(facesContext);
	values[1 ] = id;
	values[2 ] = name;
	values[3 ] = value;
	values[4 ] = method;
	values[5 ] = actionX;
	values[6 ] = colspan;
	values[7 ] = scope;
	values[8 ] = labelAttribute;
	values[9 ] = selected;
	values[10 ] = dir;
	values[11 ] = lang;
	values[12 ] = title;
	values[13 ] = style;
	values[14 ] = styleClass;
	return values;
	}

    @Override
    public void restoreState(FacesContext facesContext, Object state)
    {
    	Object[ ] values = (Object[ ])state;
		super.restoreState(facesContext, values[0 ]);

		id = (String) values[1 ];
		name = (String) values[2 ];
		value = values[3 ];
		method = (String) values[4 ];
		actionX = (MethodClosure) values[5 ];
		colspan = (Integer) values[6 ];
		scope = (String) values[7 ];
		labelAttribute = (String) values[8 ];
		selected = (Boolean) values[9 ];
		dir = (String) values[10 ];
		lang = (String) values[11 ];
		title = (String) values[12 ];
		style = (String) values[13 ];
		styleClass = (String) values[14 ];
    }


	public void setAction(MethodBinding action) {
		// TODO Auto-generated method stub
		
	}


	public void setActionListener(MethodBinding actionListener) {
		// TODO Auto-generated method stub
		
	}

}

