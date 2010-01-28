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
import java.util.Map;
import java.util.logging.Level;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.render.Renderer;

import org.jbuilt.common.renderkit.html.HtmlRendererUtils;
import org.jbuilt.components.html.raw.base.AbstractInputComponent;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;

public class Select extends AbstractInputComponent {
	public Select(){
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
	private Object valueX;

	public Object getValueX() {

		if (this.valueX != null) {
			return this.valueX;
		}
		ValueExpression ve = getValueExpression("value");
		if (ve != null) {
			try {
				return ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}
		} else {
			return null;
		}

	}

	// public void setValue(Object value) {
	// this.value = value;
	// }

	public void setValueX(Object valueX) {
		super.setValue(valueX);
		// Mark the local value as set.
		setLocalValueSet(true);
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
    
     /* Property: border */
    private String border;
    
    public String getBorder() {

	if (this.border != null) {
	    return this.border;
	}
	ValueExpression ve = getValueExpression("border");
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


    public void setBorder(String border) {
        this.border = border;
    }
    
     /* Property: ondblclick */
    private String ondblclick;
    
    @Override
    public String getOndblclick() {

	if (this.ondblclick != null) {
	    return this.ondblclick;
	}
	ValueExpression ve = getValueExpression("ondblclick");
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
    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }
    
     /* Property: onmousedown */
    private String onmousedown;
    
    @Override
    public String getOnmousedown() {

	if (this.onmousedown != null) {
	    return this.onmousedown;
	}
	ValueExpression ve = getValueExpression("onmousedown");
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
    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }
    
     /* Property: onmouseup */
    private String onmouseup;
    
    @Override
    public String getOnmouseup() {

	if (this.onmouseup != null) {
	    return this.onmouseup;
	}
	ValueExpression ve = getValueExpression("onmouseup");
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
    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }
    
     /* Property: onmouseover */
    private String onmouseover;
    
    @Override
    public String getOnmouseover() {

	if (this.onmouseover != null) {
	    return this.onmouseover;
	}
	ValueExpression ve = getValueExpression("onmouseover");
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
    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }
    
     /* Property: onmousemove */
    private String onmousemove;
    
    @Override
    public String getOnmousemove() {

	if (this.onmousemove != null) {
	    return this.onmousemove;
	}
	ValueExpression ve = getValueExpression("onmousemove");
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
    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }
    
     /* Property: onmouseout */
    private String onmouseout;
    
    @Override
    public String getOnmouseout() {

	if (this.onmouseout != null) {
	    return this.onmouseout;
	}
	ValueExpression ve = getValueExpression("onmouseout");
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
    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }
    
     /* Property: onkeypress */
    private String onkeypress;
    
    @Override
    public String getOnkeypress() {

	if (this.onkeypress != null) {
	    return this.onkeypress;
	}
	ValueExpression ve = getValueExpression("onkeypress");
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
    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }
    
     /* Property: onkeydown */
    private String onkeydown;
    
    @Override
    public String getOnkeydown() {

	if (this.onkeydown != null) {
	    return this.onkeydown;
	}
	ValueExpression ve = getValueExpression("onkeydown");
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
    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }
    
     /* Property: onkeyup */
    private String onkeyup;
    
    @Override
    public String getOnkeyup() {

	if (this.onkeyup != null) {
	    return this.onkeyup;
	}
	ValueExpression ve = getValueExpression("onkeyup");
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
    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }
    
     /* Property: onclick */
    private String onclick;
    
    @Override
    public String getOnclick() {

	if (this.onclick != null) {
	    return this.onclick;
	}
	ValueExpression ve = getValueExpression("onclick");
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
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
    
     /* Property: datafld */
    private String datafld;
    
    public String getDatafld() {

	if (this.datafld != null) {
	    return this.datafld;
	}
	ValueExpression ve = getValueExpression("datafld");
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


    public void setDatafld(String datafld) {
        this.datafld = datafld;
    }
    
     /* Property: datasrc */
    private String datasrc;
    
    public String getDatasrc() {

	if (this.datasrc != null) {
	    return this.datasrc;
	}
	ValueExpression ve = getValueExpression("datasrc");
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


    public void setDatasrc(String datasrc) {
        this.datasrc = datasrc;
    }
    
     /* Property: dataformatas */
    private String dataformatas;
    
    public String getDataformatas() {

	if (this.dataformatas != null) {
	    return this.dataformatas;
	}
	ValueExpression ve = getValueExpression("dataformatas");
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


    public void setDataformatas(String dataformatas) {
        this.dataformatas = dataformatas;
    }
    
     /* Property: multiple */
    private String multiple;
    
    public String getMultiple() {

	if (this.multiple != null) {
	    return this.multiple;
	}
	ValueExpression ve = getValueExpression("multiple");
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


    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }
    
     /* Property: size */
    private Integer size;
    
    public Integer getSize() {

	if (this.size != null) {
	    return this.size;
	}
	ValueExpression ve = getValueExpression("size");
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


    public void setSize(Integer size) {
        this.size = size;
    }
    
     /* Property: disabled */
    private Boolean disabled;
    
    public Boolean getDisabled() {

	if (this.disabled != null) {
	    return this.disabled;
	}
	ValueExpression ve = getValueExpression("disabled");
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


    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

		@Override
		public Renderer getRenderer(FacesContext context){
			return null;
		}

		@Override
		public String getRendererType(){
			return null;
		}
		
		
		/* Property: action */
		private MethodClosure actionX;
		
		
		
		/**
		 * <p>
		 * Perform the following algorithm to update the model data associated with
		 * this {@link UIInput}, if any, as appropriate.
		 * </p>
		 * <ul>
		 * <li>If the <code>valid</code> property of this component is
		 * <code>false</code>, take no further action.</li>
		 * <li>If the <code>localValueSet</code> property of this component is
		 * <code>false</code>, take no further action.</li>
		 * <li>If no {@link ValueExpression} for <code>value</code> exists, take no
		 * further action.</li>
		 * <li>Call <code>setValue()</code> method of the {@link ValueExpression} to
		 * update the value that the {@link ValueExpression} points at.</li>
		 * <li>If the <code>setValue()</code> method returns successfully:
		 * <ul>
		 * <li>Clear the local value of this {@link UIInput}.</li>
		 * <li>Set the <code>localValueSet</code> property of this {@link UIInput}
		 * to false.</li>
		 * </ul>
		 * </li>
		 * <li>If the <code>setValue()</code> method call fails:
		 * <ul>
		 * <li>Enqueue an error message by calling <code>addMessage()</code> on the
		 * specified {@link FacesContext} instance.</li>
		 * <li>Set the <code>valid</code> property of this {@link UIInput} to
		 * <code>false</code>.</li>
		 * </ul>
		 * </li>
		 * </ul>
		 * 
		 * @param context
		 *            {@link FacesContext} for the request we are processing
		 * @throws NullPointerException
		 *             if <code>context</code> is <code>null</code>
		 */
		@Override
    public void updateModel(FacesContext context) {

			if (context == null) {
				throw new NullPointerException();
			}

			if (!isValid() || !isLocalValueSet()) {
				return;
			}

			// ValueClosure vc = (ValueClosure) this.getAttributes().get("value");
				Object value = this.getAttributes().get("value");
				if(value instanceof ValueClosure){
					((ValueClosure) value).execute(this);
				} else{
					
				}
				// if(value != null){
				// ((ValueClosure)value).execute(this);
				setLocalValueSet(true);
				// Object bean = value.getBean();
				// Object val = value.getValue();

				// }
			// if(this.getType().equals("submit")){
			// MethodClosure action = (MethodClosure)
			// this.getAttributes().get("action");
			// action.execute();
			// setLocalValueSet(true);
			// }

			ValueExpression ve = getValueExpression("value");
			if (ve != null) {
				try {
					ve.setValue(context.getELContext(), getLocalValue());
					setValue(null);
					setLocalValueSet(false);
				} catch (ELException e) {
					String messageStr = e.getMessage();
					Throwable result = e.getCause();
					while (null != result
							&& result.getClass()
									.isAssignableFrom(ELException.class)) {
						messageStr = result.getMessage();
						result = result.getCause();
					}
					FacesMessage message;
					if (null == messageStr) {
						// message =
						// MessageFactory.getMessage(context, UPDATE_MESSAGE_ID,
						// MessageFactory.getLabel(
						// context, this));
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								messageStr, messageStr);
					}
					// LOGGER.log(Level.SEVERE, message.getSummary(), result);
					// context.addMessage(getClientId(context), message);
					setValid(false);
				} catch (IllegalArgumentException e) {
					// FacesMessage message =
					// MessageFactory.getMessage(context, UPDATE_MESSAGE_ID,
					// MessageFactory.getLabel(
					// context, this));
					// LOGGER.log(Level.SEVERE, message.getSummary(), e);
					// context.addMessage(getClientId(context), message);
					setValid(false);
				} catch (Exception e) {
					// FacesMessage message =
					// MessageFactory.getMessage(context, UPDATE_MESSAGE_ID,
					// MessageFactory.getLabel(
					// context, this));
					// LOGGER.log(Level.SEVERE, message.getSummary(), e);
					// context.addMessage(getClientId(context), message);
					setValid(false);
				}
			}
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
	Select select = (Select) component;
	try {
		select.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		select.encodeEnd(context, component);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

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
//					 "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "border",  "ondblclick",  "onmousedown",  "onmouseup",  "onmouseover",  "onmousemove",  "onmouseout",  "onkeypress",  "onkeydown",  "onkeyup",  "onclick",  "datafld",  "datasrc",  "dataformatas",  "multiple",  "size",  "disabled" });
//
//
//
//
//    }
//}
  
	@Override
	public void decode(FacesContext context/* , UIComponent component */) {
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
		
			HtmlRendererUtils.renderHTMLAttributes(writer, component,
					new String[] { "value" });
		// if(getValue() == null ) {
		// writer.writeAttribute("value", "", "value");
		// }

		HtmlRendererUtils.renderHTMLAttributes(writer, component,
				new String[] { "id", "method", "name", /* "value", */"action",
						"colspan", "scope", "label", "selected", "dir",
						"lang", "title", "style", "styleClass", "onfocus",
						"onblur", "onselect", "onchange", "type",
						"onclick", "onmousedown", "multiple", "size", "disabled" });

	}
}

	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "select";
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

 HtmlRendererUtils.renderOptionalEndElement(writer, component, "select", new String[ ] { "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "border",  "ondblclick",  "onmousedown",  "onmouseup",  "onmouseover",  "onmousemove",  "onmouseout",  "onkeypress",  "onkeydown",  "onkeyup",  "onclick",  "datafld",  "datasrc",  "dataformatas",  "multiple",  "size",  "disabled" });
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[32 ];
        values[0 ] = super.saveState(facesContext);
	values[1 ] = id;
	values[2 ] = name;
	values[3 ] = valueX;
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
	values[15 ] = border;
	values[16 ] = ondblclick;
	values[17 ] = onmousedown;
	values[18 ] = onmouseup;
	values[19 ] = onmouseover;
	values[20 ] = onmousemove;
	values[21 ] = onmouseout;
	values[22 ] = onkeypress;
	values[23 ] = onkeydown;
	values[24 ] = onkeyup;
	values[25 ] = onclick;
	values[26 ] = datafld;
	values[27 ] = datasrc;
	values[28 ] = dataformatas;
	values[29 ] = multiple;
	values[30 ] = size;
	values[31 ] = disabled;
	return values;
	}

    @Override
    public void restoreState(FacesContext facesContext, Object state)
    {
    	Object[ ] values = (Object[ ])state;
		super.restoreState(facesContext, values[0 ]);

		id = (String) values[1 ];
		name = (String) values[2 ];
		valueX = values[3 ];
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
		border = (String) values[15 ];
		ondblclick = (String) values[16 ];
		onmousedown = (String) values[17 ];
		onmouseup = (String) values[18 ];
		onmouseover = (String) values[19 ];
		onmousemove = (String) values[20 ];
		onmouseout = (String) values[21 ];
		onkeypress = (String) values[22 ];
		onkeydown = (String) values[23 ];
		onkeyup = (String) values[24 ];
		onclick = (String) values[25 ];
		datafld = (String) values[26 ];
		datasrc = (String) values[27 ];
		dataformatas = (String) values[28 ];
		multiple = (String) values[29 ];
		size = (Integer) values[30 ];
		disabled = (Boolean) values[31 ];
    }


	@Override
  public void setAction(MethodClosure action) {
		this.action = action;
	}

	@Override
  public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	public void setAction(MethodBinding action) {
		setAction((MethodClosure) action);
	}

	public void setActionListener(MethodBinding actionListener) {
		setActionListener(actionListener);
	}


}

