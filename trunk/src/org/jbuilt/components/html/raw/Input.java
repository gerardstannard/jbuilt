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
 */package org.jbuilt.components.html.raw;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.render.Renderer;

import org.jbuilt.common.renderkit.html.HtmlRendererUtils;
import org.jbuilt.components.html.raw.base.AbstractInputComponent;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;

public class Input extends AbstractInputComponent {

	static public final String COMPONENT_TYPE = "org.jbuilt.Input";
	static public final String RENDERER_TYPE = "org.jbuilt.InputRenderer";

	public Input() {
		// setRendererType(RENDERER_TYPE);
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
	//
	// public MethodClosure getAction() {
	//
	// if (this.action != null) {
	// return (this.action);
	// }
	// ValueExpression ve = getValueExpression("action");
	// if (ve != null) {
	// try {
	// return (MethodClosure) (ve.getValue(getFacesContext().getELContext()));
	// }
	// catch (ELException e) {
	// throw new FacesException(e);
	// }
	// } else {
	// return (null);
	// }
	//
	// }

	// public void setAction(MethodClosure action) {
	// this.action = action;
	// }
	//
	// /* Property: actionListener */
	// private ActionListener actionListener;
	//
	// public ActionListener getActionListener() {
	//
	// if (this.actionListener != null) {
	// return (this.actionListener);
	// }
	// ValueExpression ve = getValueExpression("actionListener");
	// if (ve != null) {
	// try {
	// return (ActionListener) (ve.getValue(getFacesContext().getELContext()));
	// }
	// catch (ELException e) {
	// throw new FacesException(e);
	// }
	// } else {
	// return (null);
	// }
	//
	// }
	//
	//
	// public void setActionListener(ActionListener actionListener) {
	// this.actionListener = actionListener;
	// }

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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
			} catch (ELException e) {
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
	
	
	
	
	
	

  /* Property: src */
  private String src;

  public String getSrc() {

    if (this.src != null) {
      return this.src;
    }
    ValueExpression ve = getValueExpression("src");
    if (ve != null) {
      try {
        return (String) ve.getValue(getFacesContext().getELContext());
      } catch (ELException e) {
        throw new FacesException(e);
      }
    } else {
      return null;
    }

  }

  public void setSrc(String src) {
    this.src = src;
  }


  
  
  
  
  /* Property: src */
  private Integer size;

  public Integer getSize() {

    if (this.size != null) {
      return this.size;
    }
    ValueExpression ve = getValueExpression("size");
    if (ve != null) {
      try {
        return (Integer) ve.getValue(getFacesContext().getELContext());
      } catch (ELException e) {
        throw new FacesException(e);
      }
    } else {
      return null;
    }

  }

  public void setSize(Integer size) {
    this.size = size;
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
			} catch (ELException e) {
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

	/* Property: onfocus */
	private String onfocus;

	public String getOnfocus() {

		if (this.onfocus != null) {
			return this.onfocus;
		}
		ValueExpression ve = getValueExpression("onfocus");
		if (ve != null) {
			try {
				return (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}
		} else {
			return null;
		}

	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	/* Property: onblur */
	private String onblur;

	public String getOnblur() {

		if (this.onblur != null) {
			return this.onblur;
		}
		ValueExpression ve = getValueExpression("onblur");
		if (ve != null) {
			try {
				return (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}
		} else {
			return null;
		}

	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	/* Property: onselect */
	private String onselect;

	public String getOnselect() {

		if (this.onselect != null) {
			return this.onselect;
		}
		ValueExpression ve = getValueExpression("onselect");
		if (ve != null) {
			try {
				return (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}
		} else {
			return null;
		}

	}

	public void setOnselect(String onselect) {
		this.onselect = onselect;
	}

	/* Property: onchange */
	private String onchange;

	public String getOnchange() {

		if (this.onchange != null) {
			return this.onchange;
		}
		ValueExpression ve = getValueExpression("onchange");
		if (ve != null) {
			try {
				return (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}
		} else {
			return null;
		}

	}

	/* Property: onchange */
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
			} catch (ELException e) {
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

	/* Property: type */
	private String typeX;
	private Renderer renderer/* = new InputRenderer() */;

	public String getTypeX() {

		if (this.typeX != null) {
			return this.typeX;
		}
		ValueExpression ve = getValueExpression("typeX");
		if (ve != null) {
			try {
				return (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}
		} else {
			return null;
		}

	}

	public void setTypeX(String typeX) {
		this.typeX = typeX;
	}

	@Override
	public Renderer getRenderer(FacesContext context) {
		// return renderer;
		return null;
	}

	@Override
	public String getRendererType() {
		return null;
	}


	/**
	 * <p>
	 * Render nested child components by invoking the encode methods on those
	 * components, but only when the <code>rendered</code> property is
	 * <code>true</code>.
	 * </p>
	 * 
	 * @param context
	 *            FacesContext for the current request
	 * @param component
	 *            the component to recursively encode
	 * 
	 * @throws IOException
	 *             if an error occurrs during the encode process
	 */

	@Override
  public void encodeAll(FacesContext context) {
		if (context == null) {
			throw new NullPointerException("FacesContext cannot be null");
		}
		UIComponent component = this;
		Input input = (Input) component;
		try {
			input.encodeBegin(context, component);
			for (UIComponent child : getChildren()) {
				child.encodeAll(context);
			}
			input.encodeEnd(context, component);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * @param component
	 *            the component of interest
	 * @return <code>true</code> if the button represents a <code>reset</code>
	 *         button, otherwise <code>false</code>
	 */
	private boolean isReset() {

		return "reset".equals(this.getAttributes().get("type"));

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
			if(!"radio".equals(getType()) && !"checkbox".equals(getType())) {
			writer.writeAttribute("name", component.getClientId(context),
					"clientId");
			}
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
			
			if("submit".equals(getType())){
				writer.writeAttribute("value", finalValue , "value");
			} else if("text".equals(getType())){
				writer.writeAttribute("value", finalValue , "value");
			} else if("radio".equals(getType()) ){
				String radioValue = null;
				radioValue = requestMap.get(this.getName());
				if(radioValue != null){
					writer.writeAttribute("value", finalValue , "value");
					if(finalValue.toString().equals(radioValue)){
						writer.writeAttribute("checked", Boolean.TRUE, "checked");
						writer.writeAttribute("value", finalValue , "value");
					}
				} else {
					writer.writeAttribute("value", finalValue , "value");
				}
      } else if("image".equals(getType()) ){
        String imageValue = null;
        imageValue = requestMap.get(this.getName());
        if(imageValue != null){
          writer.writeAttribute("value", finalValue , "value");
        }
			} else if("checkbox".equals(getType())){
				String checkboxValue = null;
				checkboxValue = requestMap.get(this.getName());
				if(checkboxValue != null){
					writer.writeAttribute("value", finalValue , "value");
					if(finalValue.toString().equals(checkboxValue)){
						writer.writeAttribute("checked", Boolean.TRUE, "checked");
						writer.writeAttribute("value", finalValue , "value");
					}
				} else {
					writer.writeAttribute("value", finalValue , "value");
				}
			} else {
				HtmlRendererUtils.renderHTMLAttributes(writer, component,
						new String[] { "value" });
			}
			// if(getValue() == null ) {
			// writer.writeAttribute("value", "", "value");
			// }

			HtmlRendererUtils.renderHTMLAttributes(writer, component,
					new String[] { "id", "method", "name", /* "value", */"action",
							"colspan", "scope", "label", "selected", "dir",
							"lang", "title", "style", "styleClass", "onfocus",
							"onblur", "onselect", "onchange", "type",
							"onclick", "onmousedown", "src", "size" });

		}
	}

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
		if (!"submit".equals(getType())) {
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
		}
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

		if ("submit".equals(getType()) && wasClicked(context, component)
				&& !isReset(component)) {
			component.queueEvent(new ActionEvent(component));

			if (logger.isLoggable(Level.FINE)) {
				logger.fine("This command resulted in form submission "
						+ " ActionEvent queued.");
				logger.log(Level.FINE, "End decoding component {0}", component
						.getId());
			}
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

	/**
	      *
	      */
	@Override
  public String getStringValue() {
		return "input";
	}

	public boolean wasClicked(FacesContext context, UIComponent component) {
		component = this;
		// Was our command the one that caused this submission?
		// we don' have to worry about getting the value from request parameter
		// because we just need to know if this command caused the submission.
		// We
		// can get the command name by calling currentValue. This way we can
		// get around the IE bug.
		String clientId = component.getClientId(context);
		Map<String, String> requestParameterMap = context.getExternalContext()
				.getRequestParameterMap();
		if (requestParameterMap.get(clientId) == null) {
			StringBuilder builder = new StringBuilder(clientId);
			String xValue = builder.append(".x").toString();
			builder.setLength(clientId.length());
			String yValue = builder.append(".y").toString();
			return requestParameterMap.get(xValue) != null && requestParameterMap
					.get(yValue) != null;
		}
		return true;
	}

	/**
	 * <p>
	 * Intercept <code>queueEvent</code> and, for {@link ActionEvent}s, mark the
	 * phaseId for the event to be <code>PhaseId.APPLY_REQUEST_VALUES</code> if
	 * the <code>immediate</code> flag is true,
	 * <code>PhaseId.INVOKE_APPLICATION</code> otherwise.
	 * </p>
	 */

	@Override
  public void queueEvent(FacesEvent e) {
		UIComponent c = e.getComponent();
		if (e instanceof ActionEvent && c instanceof ActionSource) {
			if (((ActionSource) c).isImmediate()) {
				e.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
			} else {
				e.setPhaseId(PhaseId.INVOKE_APPLICATION);
			}
		}
		super.queueEvent(e);
	}

	// @Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		// no-op
	}

	// @Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		component = this;
		super.encodeEnd(context);
		ResponseWriter writer = context.getResponseWriter();

		HtmlRendererUtils.renderOptionalEndElement(writer, component, "input",
				new String[] { "id", "name", "value", "method", "action",
						"colspan", "scope", "label", "selected", "dir", "lang",
						"title", "style", "styleClass", "onfocus", "onblur",
						"onselect", "onchange", "type", "src", "size" });
	}

	@Override
  public Object saveState(FacesContext facesContext) {
		Object[] values = new Object[22];
		values[0] = super.saveState(facesContext);
		values[1] = id;
		values[2] = name;
		values[3] = valueX;
		values[4] = method;
		values[5] = actionX;
		values[6] = colspan;
		values[7] = scope;
		values[8] = labelAttribute;
		values[9] = selected;
		values[10] = dir;
		values[11] = lang;
		values[12] = title;
		values[13] = style;
		values[14] = styleClass;
		values[15] = onfocus;
		values[16] = onblur;
		values[17] = onselect;
		values[18] = onchange;
		values[19] = typeX;
		values[20] = src;
		values[21] = size;
		
		return values;
	}

	@Override
  public void restoreState(FacesContext facesContext, Object state) {
		Object[] values = (Object[]) state;
		super.restoreState(facesContext, values[0]);

		id = (String) values[1];
		name = (String) values[2];
		valueX = values[3];
		method = (String) values[4];
		actionX = (MethodClosure) values[5];
		colspan = (Integer) values[6];
		scope = (String) values[7];
		labelAttribute = (String) values[8];
		selected = (Boolean) values[9];
		dir = (String) values[10];
		lang = (String) values[11];
		title = (String) values[12];
		style = (String) values[13];
		styleClass = (String) values[14];
		onfocus = (String) values[15];
		onblur = (String) values[16];
		onselect = (String) values[17];
		onchange = (String) values[18];
		typeX = (String) values[19];
		src = (String) values[20];
		size = (Integer) values[21];
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
