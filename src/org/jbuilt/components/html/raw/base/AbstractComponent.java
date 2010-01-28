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

package org.jbuilt.components.html.raw.base;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.render.Renderer;
import javax.faces.webapp.UIComponentELTag;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbuilt.common.html.utils.ComponentUtils;

/**
 * 
 * @JSFComponent
 *   name = "Tag"
 *   class = "org.jbuilt.components.html.raw.Tag"
 * @since
 * @author gstannard
 * @version
 */
public abstract class AbstractComponent extends UIOutput implements ComponentOrAttribute
    
{
    /**
     * 
     */
    private static final long serialVersionUID = -434952077451972922L;

    public static final String COMPONENT_TYPE = "javax.faces.Output";
//    private static final String DEFAULT_RENDERER_TYPE = "org.jbuilt.TagRenderer";

    private String enabledOnUserRole;
    private String  visibleOnUserRole;
    private String  forceId;
    private String  forceIdIndex;
    private Object  value;
    private String label;
    private boolean addToTree;
    
    
    /**
     * <p>The value binding expression (if any) used to wire up this component
     * to a {@link UIComponent} property of a JavaBean class.</p>
     */
    private ValueExpression binding = null;
    
    @Override
    public boolean getRendersChildren(){
    	return false;
    }
    
    
    /**
     * Begins the encoded output for this component.
     * 
     * @param context    the Faces context
     * @param component  the Faces component
     *
     * @throws IOException  if an I/O error occurs during rendering
     */
    public void encodeBegin(
      FacesContext context,
      UIComponent  component) throws IOException
    {
    	component = this;
      // write out resources
    	super.encodeBegin(context);
      encodeResources(context, component);
    }
    
    /**
     * Override hook for subclasses to write out their resources.
     * 
     * @param context    the Faces context
     * @param component  the Faces component
     */
    protected void encodeResources(
      FacesContext context,
      UIComponent  component) throws IOException
    {
      // empty hook for subclasses to override as needed
    }

//    public void encodeAll(FacesContext context){
//        // empty hook for subclasses to override as needed
//    }
    /**
     * Writes a script library resource at-most-once within a single
     * RenderResponse phase.
     * 
     * @param context       the Faces context
     * @param resourcePath  the script library resource path
     * 
     * @throws IOException  if an error occurs during rendering
     */
    public void writeScriptResource(
      FacesContext context,
      String       resourcePath) throws IOException
    {
      Set scriptResources = _getScriptResourcesAlreadyWritten(context);

      // Set.add() returns true only if item was added to the set
      // and returns false if item was already present in the set
      if (scriptResources.add(resourcePath))
      {
        ViewHandler handler = context.getApplication().getViewHandler();
        String resourceURL = handler.getResourceURL(context, resourcePath);
        ResponseWriter out = context.getResponseWriter();
        out.startElement("script", null);
        out.writeAttribute("type", "text/javascript", null);
        out.writeAttribute("src", resourceURL, null);
        out.endElement("script");
      }
    }
    
    /**
     * Writes an inline script at-most-once within a single
     * RenderResponse phase.
     * 
     * @param context       the Faces context
     * @param inlineScript  the inline script code
     * 
     * @throws IOException  if an error occurs during rendering
     */
    protected void writeScriptInline(
      FacesContext context,
      String       inlineScript) throws IOException
    {
      Set scriptResources = _getScriptResourcesAlreadyWritten(context);

      // Set.add() returns true only if item was added to the set
      // and returns false if item was already present in the set
      if (scriptResources.add(inlineScript))
      {
        ResponseWriter out = context.getResponseWriter();
        out.startElement("script", null);
        out.writeAttribute("type", "text/javascript", null);
        out.writeText(inlineScript, null);
        out.endElement("script");
      }
    }
    
    /**
     * Writes a stylesheet resource at-most-once within a single
     * RenderResponse phase.
     * 
     * @param context       the Faces context
     * @param resourcePath  the stylesheet resource path
     * 
     * @throws IOException  if an error occurs during rendering
     */
    protected void writeStyleResource(
      FacesContext context,
      String       resourcePath) throws IOException
    {
      Set styleResources = _getStyleResourcesAlreadyWritten(context);

      // Set.add() returns true only if item was added to the set
      // and returns false if item was already present in the set
      if (styleResources.add(resourcePath))
      {
        ViewHandler handler = context.getApplication().getViewHandler();
        String resourceURL = handler.getResourceURL(context, resourcePath);
        ResponseWriter out = context.getResponseWriter();
        out.startElement("style", null);
        out.writeAttribute("type", "text/css", null);
        out.writeText("@import url(" + resourceURL + ");", null);
        out.endElement("style");
      }
    }
    
    /**
     * Writes an inline style at-most-once within a single
     * RenderResponse phase.
     * 
     * @param context      the Faces context
     * @param inlineStyle  the inline style classes
     * 
     * @throws IOException  if an error occurs during rendering
     */
    protected void writeStyleInline(
      FacesContext context,
      String       inlineStyle) throws IOException
    {
      Set styleResources = _getStyleResourcesAlreadyWritten(context);

      // Set.add() returns true only if item was added to the set
      // and returns false if item was already present in the set
      if (styleResources.add(inlineStyle))
      {
        ResponseWriter out = context.getResponseWriter();
        out.startElement("style", null);
        out.writeAttribute("type", "text/css", null);
        out.writeText(inlineStyle, null);
        out.endElement("style");
      }
    }
    
    // Implements at-most-once semantics for each script resource on
    // the currently rendering page
    private Set _getScriptResourcesAlreadyWritten(
      FacesContext context)
    {
      ExternalContext external = context.getExternalContext();
      Map requestScope = external.getRequestMap();
      Set written = (Set)requestScope.get(_SCRIPT_RESOURCES_KEY);
      
      if (written == null)
      {
        written = new HashSet();
        requestScope.put(_SCRIPT_RESOURCES_KEY, written);
      }
      
      return written;
    }
    
    // Implements at-most-once semantics for each style resource on
    // the currently rendering page
    private Set _getStyleResourcesAlreadyWritten(
      FacesContext context)
    {
      ExternalContext external = context.getExternalContext();
      Map requestScope = external.getRequestMap();
      Set written = (Set)requestScope.get(_STYLE_RESOURCES_KEY);
      
      if (written == null)
      {
        written = new HashSet();
        requestScope.put(_STYLE_RESOURCES_KEY, written);
      }
      
      return written;
    }
    
    static private final String _STYLE_RESOURCES_KEY =
                           AbstractComponent.class.getName() + ".STYLES_WRITTEN";
    static private final String _SCRIPT_RESOURCES_KEY =
                           AbstractComponent.class.getName() + ".SCRIPTS_WRITTEN";

    
    
    
    
    
    
    
    
    
    
    
    
    
    


    /**
     * <p>Set the value expression for our component.</p>
     *
     * @param binding The new value expression
     *
     * @throws JspException if an error occurs
     */
    public void setBinding(ValueExpression binding) {
	this.binding = binding;
    }

    protected boolean hasBinding() {
	return null != binding;
    }
    
    protected abstract String getStringValue();


    /**
     * <p>An override for the rendered attribute associated with our
     * {@link UIComponent}.</p>
     */
    private ValueExpression rendered = null;


    /**
     * <p>Set an override for the rendered attribute.</p>
     *
     * @param rendered The new value for rendered attribute
     */
    public void setRendered(ValueExpression rendered) {
        this.rendered = rendered;
    }

    /**
     * <p>Return the {@link ELContext} for the {@link FacesContext} for
     * this request.</p>
     *
     * <p>This is a convenience for
     * <code>getFacesContext().getELContext()</code>.</p>
     */

    protected ELContext getELContext() {
	FacesContext fc = getFacesContext();
	ELContext result = null;
	if (null != fc) {
	    result = (ELContext) fc.getExternalContext().getContext();
	}
	return result;
    }

    
    /**
     * <p>Flag indicating whether or not rendering should occur.</p>
     */
    private boolean suppressed = false;


    protected boolean isSuppressed() {

        return suppressed;

    }

    /**
     * <p>Release any resources allocated during the execution of this
     * tag handler.</p>
     */
    public void release() {
	
	this.suppressed = false;
	this.binding = null;
        this.rendered = null;
    }




    
    @Override
    public String getClientId(FacesContext context)
    {
        String clientId =  ComponentUtils.getClientId(this,
                getRenderer(context), context);
        if (clientId == null)
        {
            clientId = super.getClientId(context);
        }

        return clientId;
    }
    
    
    /**
     * <p>Return <code>true</code> if the specified value conforms to the
     * syntax requirements of a value binding expression.  Such expressions
`    * may be used on most component tag attributes to signal a desire for
     * deferred evaluation of the attribute or property value to be set on
     * the underlying {@link UIComponent}.</p>
     *
     * @param value The value to evaluate
     *
     * @throws NullPointerException if <code>value</code> is
     *  <code>null</code>
     */
    public static boolean isValueReference(String value) {

	if (value == null) {
	    throw new NullPointerException();
	}
	int start = value.indexOf("#{");
	if (start != -1 && start < value.indexOf('}', start)) {
            return true;
        }
        return false;

    }

    
    
    /**
     * <p>Override properties and attributes of the specified component,
     * if the corresponding properties of this tag handler instance were
     * explicitly set.  This method must be called <strong>ONLY</strong>
     * if the specified {@link UIComponent} was in fact created during
     * the execution of this tag handler instance, and this call will occur
     * <strong>BEFORE</strong> the {@link UIComponent} is added to
     * the view.</p>
     *
     * <p>Tag subclasses that want to support additional set properties
     * must ensure that the base class <code>setProperties()</code>
     * method is still called.  A typical implementation that supports
     * extra properties <code>foo</code> and <code>bar</code> would look
     * something like this:</p>
     * <pre>
     * protected void setProperties(UIComponent component) {
     *   super.setProperties(component);
     *   if (foo != null) {
     *     component.setAttribute("foo", foo);
     *   }
     *   if (bar != null) {
     *     component.setAttribute("bar", bar);
     *   }
     * }
     * </pre>
     *
     * <p>The default implementation overrides the following properties:</p>
     * <ul>
     * <li><code>rendered</code> - Set if a value for the
     *     <code>rendered</code> property is specified for
     *     this tag handler instance.</li>
     * <li><code>rendererType</code> - Set if the <code>getRendererType()</code>
     *     method returns a non-null value.</li>
     * </ul>
     *
     * @param component {@link UIComponent} whose properties are to be
     *  overridden
     */
    protected void setProperties(UIComponent comp) {
        // The "id" property is explicitly set when components are created
        // so it does not need to be set here
        if (rendered != null) {
            if (rendered.isLiteralText()) {
                try {
                    comp.setRendered(Boolean.valueOf(rendered.getExpressionString())
                            .booleanValue());
                } catch (ELException e) {
                    throw new FacesException(e);
                }
            } else {
                comp.setValueExpression("rendered", rendered);
            }
        }
        if (getRendererType() != null) {
            comp.setRendererType(getRendererType());
        }
        
        
        FacesContext context = getFacesContext();

        if (enabledOnUserRole != null)
        {
            if (isValueReference(enabledOnUserRole))
            {
                ValueExpression vb = getValueExpression(enabledOnUserRole);
                comp.setValueExpression("enabledOnUserRole", vb);
            }
            else
            {
                comp.getAttributes().put("enabledOnUserRole", enabledOnUserRole);
            }
        }
        if (visibleOnUserRole != null)
        {
            if (isValueReference(visibleOnUserRole))
            {
                ValueExpression vb = getValueExpression(visibleOnUserRole);
                comp.setValueExpression("visibleOnUserRole", vb);
            }
            else
            {
                comp.getAttributes().put("visibleOnUserRole", visibleOnUserRole);
            }
        }
        if (forceId != null)
        {
            comp.getAttributes().put("forceId", Boolean.valueOf(forceId));
        }
        if (forceIdIndex != null)
        {
            comp.getAttributes().put("forceIdIndex", Boolean.valueOf(forceIdIndex));
        }
        if (style != null)
        {
            if (isValueReference(style))
            {
                ValueExpression vb = getValueExpression(style);
                comp.setValueExpression("style", vb);
            }
            else
            {
                comp.getAttributes().put("style", style);
            }
        }
        if (styleClass != null)
        {
            if (isValueReference(styleClass))
            {
                ValueExpression vb = getValueExpression(styleClass);
                comp.setValueExpression("styleClass", vb);
            }
            else
            {
                comp.getAttributes().put("styleClass", styleClass);
            }
        }
        if (value != null)
        {
            if (isValueReference((String) value))
            {
                ValueExpression vb = getValueExpression((String) value);
                comp.setValueExpression("value", vb);
            }
            else
            {
                comp.getAttributes().put("value", value);
            }
        }
    }

    /**
     * <p>Create and return a new child component of the type returned by
     * calling <code>getComponentType()</code>.  If this {@link UIComponentELTag}
     * has a non-null <code>binding</code> attribute, this is done by
     * call {@link Application#createComponent} with the {@link ValueExpression}
     * created for the <code>binding</code> attribute, and the
     * {@link ValueExpression} will be stored on the component.  Otherwise,
     * {@link Application#createComponent} is called with only
     * the component type.  Finally, initialize the components id
     * and other properties.
     * </p>
     * @param context {@link FacesContext} for the current request
     * @param newId id of the component
     */
    protected UIComponent createComponent(FacesContext context, String newId) {
        UIComponent component;
        Application application = context.getApplication();
        if (binding != null) {
            component = application.createComponent(binding, context,
                                                    getComponentType());
	    component.setValueExpression("binding", binding);
        } else {
            component = application.createComponent(getComponentType());
        }

        component.setId(newId);
        setProperties(component);

        return component;
    }
    
	
    @SuppressWarnings("unchecked")
	public <T> T getExpressionValue(UIComponent component, String attribute, T overrideValue, T defaultValue)
    {
        if (overrideValue != null)
        {
            return overrideValue;
        }
        ValueExpression ve = component.getValueExpression(attribute);
        if (ve != null)
        {
            return (T) ve.getValue(getFacesContext().getELContext());
        }
        return defaultValue;
    }

    
    


    

	/**
	 * @return the COMPONENT_TYPE
	 */
	public static String getComponentType() {
		return COMPONENT_TYPE;
	}
	
	@Override
  protected Renderer getRenderer(FacesContext context){
		return null;
	}
	
	/**
     * This component converts submitted values to String, so
     * converter is not needed, no custom conversion necessary.
     * 
     * @JSFProperty
     *   tagExcluded = "${tagExcluded}"
     */
    @Override
    public Converter getConverter()
    {
        return null;
    }
    
    @Override
    public void setConverter(Converter converter)
    {
        throw new UnsupportedOperationException();
    }

	/**
	 * @return the enabledOnUserRole
	 */
	public String getEnabledOnUserRole() {
		return enabledOnUserRole;
	}

	/**
	 * @return the visibleOnUserRole
	 */
	public String getVisibleOnUserRole() {
		return visibleOnUserRole;
	}

	/**
	 * @return the forceId
	 */
	public String getForceId() {
		return forceId;
	}

	/**
	 * @return the forceIdIndex
	 */
	public String getForceIdIndex() {
		return forceIdIndex;
	}


	/**
	 * @return the value
	 */
	@Override
  public Object getValue() {
        if (value != null)
        {
            return value;
        }
        ValueExpression vb = getValueExpression("value");
        if (vb != null)
        {
            return vb.getValue(getFacesContext().getELContext());
        }
        return null;
	}

	/**
	 * @param enabledOnUserRole the enabledOnUserRole to set
	 */
	public void setEnabledOnUserRole(String enabledOnUserRole) {
		this.enabledOnUserRole = enabledOnUserRole;
	}

	/**
	 * @param visibleOnUserRole the visibleOnUserRole to set
	 */
	public void setVisibleOnUserRole(String visibleOnUserRole) {
		this.visibleOnUserRole = visibleOnUserRole;
	}

	/**
	 * @param forceId the forceId to set
	 */
	public void setForceId(String forceId) {
		this.forceId = forceId;
	}

	/**
	 * @param forceIdIndex the forceIdIndex to set
	 */
	public void setForceIdIndex(String forceIdIndex) {
		this.forceIdIndex = forceIdIndex;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
    // Property: accept
    private String accept;
    
    public String getAccept()
    {
        if (accept != null)
        {
            return accept;
        }
        ValueExpression vb = getValueExpression("accept");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setAccept(String accept)
    {
        this.accept = accept;
    }
    // Property: acceptcharset
    private String acceptcharset;
    
    public String getAcceptcharset()
    {
        if (acceptcharset != null)
        {
            return acceptcharset;
        }
        ValueExpression vb = getValueExpression("acceptcharset");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setAcceptcharset(String acceptcharset)
    {
        this.acceptcharset = acceptcharset;
    }
    // Property: enctype
    private String enctype;
    
    public String getEnctype()
    {
        if (enctype != null)
        {
            return enctype;
        }
        ValueExpression vb = getValueExpression("enctype");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return "application/x-www-form-urlencoded";
    }

    public void setEnctype(String enctype)
    {
        this.enctype = enctype;
    }
    // Property: onreset
    private String onreset;
    
    public String getOnreset()
    {
        if (onreset != null)
        {
            return onreset;
        }
        ValueExpression vb = getValueExpression("onreset");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnreset(String onreset)
    {
        this.onreset = onreset;
    }
    // Property: onsubmit
    private String onsubmit;
    
    public String getOnsubmit()
    {
        if (onsubmit != null)
        {
            return onsubmit;
        }
        ValueExpression vb = getValueExpression("onsubmit");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnsubmit(String onsubmit)
    {
        this.onsubmit = onsubmit;
    }
    // Property: target
    private String target;
    
    public String getTarget()
    {
        if (target != null)
        {
            return target;
        }
        ValueExpression vb = getValueExpression("target");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }
    // Property: style
    private String style;
    
    public String getStyle()
    {
        if (style != null)
        {
            return style;
        }
        ValueExpression vb = getValueExpression("style");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setStyle(String style)
    {
        this.style = style;
    }
    // Property: styleClass
    private String styleClass;
    
    public String getStyleClass()
    {
        if (styleClass != null)
        {
            return styleClass;
        }
        ValueExpression vb = getValueExpression("styleClass");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setStyleClass(String styleClass)
    {
        this.styleClass = styleClass;
    }
    // Property: dir
    private String dir;
    
    public String getDir()
    {
        if (dir != null)
        {
            return dir;
        }
        ValueExpression vb = getValueExpression("dir");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setDir(String dir)
    {
        this.dir = dir;
    }
    // Property: lang
    private String lang;
    
    public String getLang()
    {
        if (lang != null)
        {
            return lang;
        }
        ValueExpression vb = getValueExpression("lang");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }
    // Property: title
    private String title;
    
    public String getTitle()
    {
        if (title != null)
        {
            return title;
        }
        ValueExpression vb = getValueExpression("title");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    // Property: onclick
    private String onclick;
    
    public String getOnclick()
    {
        if (onclick != null)
        {
            return onclick;
        }
        ValueExpression vb = getValueExpression("onclick");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnclick(String onclick)
    {
        this.onclick = onclick;
    }
    // Property: ondblclick
    private String ondblclick;
    
    public String getOndblclick()
    {
        if (ondblclick != null)
        {
            return ondblclick;
        }
        ValueExpression vb = getValueExpression("ondblclick");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOndblclick(String ondblclick)
    {
        this.ondblclick = ondblclick;
    }
    // Property: onkeydown
    private String onkeydown;
    
    public String getOnkeydown()
    {
        if (onkeydown != null)
        {
            return onkeydown;
        }
        ValueExpression vb = getValueExpression("onkeydown");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnkeydown(String onkeydown)
    {
        this.onkeydown = onkeydown;
    }
    // Property: onkeypress
    private String onkeypress;
    
    public String getOnkeypress()
    {
        if (onkeypress != null)
        {
            return onkeypress;
        }
        ValueExpression vb = getValueExpression("onkeypress");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnkeypress(String onkeypress)
    {
        this.onkeypress = onkeypress;
    }
    // Property: onkeyup
    private String onkeyup;
    
    public String getOnkeyup()
    {
        if (onkeyup != null)
        {
            return onkeyup;
        }
        ValueExpression vb = getValueExpression("onkeyup");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnkeyup(String onkeyup)
    {
        this.onkeyup = onkeyup;
    }
    // Property: onmousedown
    private String onmousedown;
    
    public String getOnmousedown()
    {
        if (onmousedown != null)
        {
            return onmousedown;
        }
        ValueExpression vb = getValueExpression("onmousedown");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnmousedown(String onmousedown)
    {
        this.onmousedown = onmousedown;
    }
    // Property: onmousemove
    private String onmousemove;
    
    public String getOnmousemove()
    {
        if (onmousemove != null)
        {
            return onmousemove;
        }
        ValueExpression vb = getValueExpression("onmousemove");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnmousemove(String onmousemove)
    {
        this.onmousemove = onmousemove;
    }
    // Property: onmouseout
    private String onmouseout;
    
    public String getOnmouseout()
    {
        if (onmouseout != null)
        {
            return onmouseout;
        }
        ValueExpression vb = getValueExpression("onmouseout");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnmouseout(String onmouseout)
    {
        this.onmouseout = onmouseout;
    }
    // Property: onmouseover
    private String onmouseover;
    
    public String getOnmouseover()
    {
        if (onmouseover != null)
        {
            return onmouseover;
        }
        ValueExpression vb = getValueExpression("onmouseover");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnmouseover(String onmouseover)
    {
        this.onmouseover = onmouseover;
    }
    // Property: onmouseup
    private String onmouseup;
    
    public String getOnmouseup()
    {
        if (onmouseup != null)
        {
            return onmouseup;
        }
        ValueExpression vb = getValueExpression("onmouseup");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext().getELContext());
        }
        return null;
    }

    public void setOnmouseup(String onmouseup)
    {
        this.onmouseup = onmouseup;
    }
    
    
	private static final Log LOG = LogFactory.getLog(AbstractComponent.class);
	
    /**
     * Generic reflection-based equals
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	if (LOG.isWarnEnabled()) {
//    		LOG.warn("Remember to over-ride equals for " + ClassUtils.getShortClassName(getClass()) + "!");
    	}
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * Generic reflection-based hashCode
     * @see java.lang.Object#hashCode()
     */
//    public int hashCode() {
//    	if (LOG.isWarnEnabled()) {
////    		LOG.warn("Remember to over-ride hashcode " + ClassUtils.getShortClassName(getClass()) + "!");
//    	}
//        return HashCodeBuilder.reflectionHashCode(this);
//    }

    /**
     * Generic reflection-based toString.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	// TODO: change to not use reflection toString
    	if (LOG.isWarnEnabled()) {
//    		LOG.warn("Remember to over-ride toString " + ClassUtils.getShortClassName(getClass()) + "!");
    	}
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
    
	public void printToConsole(){
		for(UIComponent child : getChildren()){
			
			System.out.println("child is " + child.getClass().getSimpleName());
//			for(Map.Entry attr: child.getAttributes().entrySet()){
//				System.out.println("attribute of " +  child.getClass().getSimpleName() + " is " + attr.getKey() + " = " + attr.getValue());
//			}
//			((AbstractTag)child).printToConsole();
		}
	}




    @Override
    public Object saveState(FacesContext facesContext)
    {
        Object[] values = new Object[23];
        values[0] = super.saveState(facesContext);
        values[1] = accept;
        values[2] = acceptcharset;
        values[3] = enctype;
        values[4] = onreset;
        values[5] = onsubmit;
        values[6] = target;
        values[7] = style;
        values[8] = styleClass;
        values[9] = dir;
        values[10] = lang;
        values[11] = title;
        values[12] = onclick;
        values[13] = ondblclick;
        values[14] = onkeydown;
        values[15] = onkeypress;
        values[16] = onkeyup;
        values[17] = onmousedown;
        values[18] = onmousemove;
        values[19] = onmouseout;
        values[20] = onmouseover;
        values[21] = onmouseup;
        values[22] = addToTree;
        return values;
    }

    @Override
    public void restoreState(FacesContext facesContext, Object state)
    {
        Object[] values = (Object[])state;
        super.restoreState(facesContext,values[0]);
        accept = (java.lang.String) values[1];
        acceptcharset = (java.lang.String) values[2];
        enctype = (java.lang.String) values[3];
        onreset = (java.lang.String) values[4];
        onsubmit = (java.lang.String) values[5];
        target = (java.lang.String) values[6];
        style = (java.lang.String) values[7];
        styleClass = (java.lang.String) values[8];
        dir = (java.lang.String) values[9];
        lang = (java.lang.String) values[10];
        title = (java.lang.String) values[11];
        onclick = (java.lang.String) values[12];
        ondblclick = (java.lang.String) values[13];
        onkeydown = (java.lang.String) values[14];
        onkeypress = (java.lang.String) values[15];
        onkeyup = (java.lang.String) values[16];
        onmousedown = (java.lang.String) values[17];
        onmousemove = (java.lang.String) values[18];
        onmouseout = (java.lang.String) values[19];
        onmouseover = (java.lang.String) values[20];
        onmouseup = (java.lang.String) values[21];
        addToTree = (Boolean) values[22];
    }


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public Boolean isAddToTree() {
		return addToTree;
	}


	public void setAddToTree(Boolean addToTree) {
		this.addToTree = addToTree;
	}
	
    
}