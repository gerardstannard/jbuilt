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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.ActionSource;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.render.Renderer;
import javax.faces.webapp.UIComponentELTag;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbuilt.common.html.utils.ComponentUtils;
import org.jbuilt.context.Jbuilt;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;

import com.sun.faces.util.FacesLogger;
import com.sun.faces.util.MessageFactory;
import com.sun.faces.util.MessageUtils;
import com.sun.faces.util.RequestStateManager;
import com.sun.faces.util.Util;

/**
 * 
 * @JSFComponent
 *   name = "Tag"
 *   class = "org.jbuilt.components.html.raw.Tag"
 * @since
 * @author gstannard
 * @version
 */
public abstract class AbstractInputComponent extends UIInput implements ActionSource2, Jbuilt {
    /**
     * 
     */
    private static final long serialVersionUID = 4040461086391512843L;
    public static final String COMPONENT_TYPE = "org.jbuilt.Input";
    public static final String COMPONENT_FAMILY = "javax.faces.Input";
    
//    private List<Observer> observers;

    
    public AbstractInputComponent(){
    	setRendererType(null);
//    	observers = new ArrayList<Observer>();

    }
    


    private boolean hasStringConverter = false;

    private boolean hasStringConverterSet = false;

	private String enabledOnUserRole;
	private String visibleOnUserRole;
	private String forceId;
	private String forceIdIndex;
//	private Object value;
	private String label;
	protected MethodClosure action;
	protected String type;
	protected Class typeConstraint;

  
  
  
  /**
   * <p>The value binding expression (if any) used to wire up this component
   * to a {@link UIComponent} property of a JavaBean class.</p>
   */
  private ValueExpression binding = null;
  
  // Log instance for this class
  protected static final Logger logger = FacesLogger.RENDERKIT.getLogger();
  
  
  @Override
  public void decode(FacesContext context/*, UIComponent component*/)
  {
	  UIComponent component = this;
      rendererParamsNotNull(context, this);

      if (!(component instanceof UIInput)) {
          // decode needs to be invoked only for components that are
          // instances or subclasses of UIInput.
          if (logger.isLoggable(Level.FINE)) {
              logger.log(Level.FINE,
                         "No decoding necessary since the component {0} is not an instance or a sub class of UIInput",
                         component.getId());
          }
          return;
      }
      
      if ("submit".equals(getType()) && wasClicked(context, component) && !isReset(component)) {
          component.queueEvent(new ActionEvent(component));

          if (logger.isLoggable(Level.FINE)) {
              logger.fine("This command resulted in form submission " +
                          " ActionEvent queued.");
              logger.log(Level.FINE,
                         "End decoding component {0}",
                         component.getId());
          }
      }


      if (!shouldDecode(this)) {
          return;
      }
      
      
      String clientId = this.getClientId(context);
      assert clientId != null;
      Map<String, String> requestMap =
            context.getExternalContext().getRequestParameterMap();
      // Don't overwrite the value unless you have to!
      Object oldValue = null;
      ValueClosure oldValueClosure = null;
      if(getValue() instanceof ValueClosure){
    	 oldValueClosure = (ValueClosure) getValue();
      }
      String newValue = requestMap.get(clientId);
      if (newValue != null) {
    	  // if no renderer don't need component argument
          setSubmittedValue(newValue);
          if(oldValueClosure != null){
//          if(oldValueClosure.getPropertyType().isAssignableFrom(int.class)){
//          try {
//			PropertyUtils.setProperty(
//					oldValueClosure.getBean(), oldValueClosure.getProp(), Integer.parseInt(newValue)
//					  );
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//          }
          }
          if (logger.isLoggable(Level.FINE)) {
              logger.log(Level.FINE,
                         "new value after decoding {0}",
                         newValue);
          }
      }
  }
  
  
  
  
  protected void rendererParamsNotNull(FacesContext context,
          UIComponent component) {
	  component = this;
	  Util.notNull("context", context);
	  Util.notNull("component", component);

  }


  
  
  protected boolean shouldDecode(UIComponent component) {
	  component = this;

      if (Util.componentIsDisabledOrReadonly(component)) {
          if (logger.isLoggable(Level.FINE)) {
              logger.log(Level.FINE,
                         "No decoding necessary since the component {0} is disabled or read-only",
                         component.getId());
          }
          return false;
      }
      return true;

  }


  
  @Override
  public boolean getRendersChildren(){
  	return false;
  }
  
  /**
   * @param context the <code>FacesContext</code> for the current request
   * @param imageURI the base URI of the image to use for the button
   * @return the encoded result for the base imageURI
   */
  private static String src(FacesContext context, String imageURI) {

      if (imageURI == null) {
          return "";
      }

      String u = context.getApplication().getViewHandler()
            .getResourceURL(context, imageURI);
      return context.getExternalContext().encodeResourceURL(u);

  }

  
  
  /**
   * <p>Determine if this component was activated on the client side.</p>
   *
   * @param context the <code>FacesContext</code> for the current request
   * @param component the component of interest
   * @return <code>true</code> if this component was in fact activated,
   *  otherwise <code>false</code>
   */
  private static boolean wasClicked(FacesContext context,
                                    UIComponent component) {
      
      // Was our command the one that caused this submission?
      // we don' have to worry about getting the value from request parameter
      // because we just need to know if this command caused the submission. We
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
          return requestParameterMap.get(xValue) != null
                  && requestParameterMap.get(yValue) != null;
      }
      return true;

  }

  /**
   * @param component the component of interest
   * @return <code>true</code> if the button represents a <code>reset</code>
   *  button, otherwise <code>false</code>
   */
  protected   boolean isReset(UIComponent component) {

      return "reset".equals(component.getAttributes().get("type"));

  }

  /**
   * <p>If the component's type attribute is null or not equal
   * to <code>reset</code> or <code>submit</code>, default to
   * <code>submit</code>.
   * @param component the component of interest
   * @return the type for this button
   */
  private static String getButtonType(UIComponent component) {

      String type = (String) component.getAttributes().get("type");
      if (type == null || !"reset".equals(type) && !"submit".equals(type)) {
          type = "submit";
          // This is needed in the decode method
          component.getAttributes().put("type", type);
      }
      return type;

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

//  public void encodeAll(FacesContext context){
//      // empty hook for subclasses to override as needed
//  }
  /**
   * Writes a script library resource at-most-once within a single
   * RenderResponse phase.
   * 
   * @param context       the Faces context
   * @param resourcePath  the script library resource path
   * 
   * @throws IOException  if an error occurs during rendering
   */
  protected void writeScriptResource(
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

  
  
  
  public Object getConvertedValue(FacesContext context, UIComponent component,
                                  Object submittedValue)
        throws ConverterException {
	  component = this;


      String newValue = (String) submittedValue;
      // if we have no local value, try to get the valueExpression.
      ValueExpression valueExpression = component.getValueExpression("value");
      Converter converter = null;

      // If there is a converter attribute, use it to to ask application
      // instance for a converter with this identifer.
      if (component instanceof ValueHolder) {
          converter = ((ValueHolder) component).getConverter();
      }

      if (null == converter && null != valueExpression) {
          Class converterType = valueExpression.getType(context.getELContext());
          // if converterType is null, assume the modelType is "String".
          if (converterType == null ||
              converterType == Object.class) {
              if (logger.isLoggable(Level.FINE)) {
                  logger.log(Level.FINE,
                             "No conversion necessary for value {0} of component {1}",
                             new Object[]{
                                   submittedValue,
                                   component.getId() });
              }
              return newValue;
          }

          // If the converterType is a String, and we don't have a
          // converter-for-class for java.lang.String, assume the type is
          // "String".
          if (converterType == String.class && !hasStringConverter(context)) {
//              if (logger.isLoggable(Level.FINE)) {
//                  logger.log(Level.FINE,
//                             "No conversion necessary for value {0} of component {1}",
//                             new Object[]{
//                                   submittedValue,
//                                   component.getId()});
//              }
              return newValue;
          }
          // if getType returns a type for which we support a default
          // conversion, acquire an appropriate converter instance.

          try {
              Application application = context.getApplication();
              converter = application.createConverter(converterType);
              if (logger.isLoggable(Level.FINE)) {
                  logger.log(Level.FINE,
                             "Created converter ({0}) for type {1} for component {2}.",
                             new Object[] {
                                   converter.getClass().getName(),
                                   converterType.getClass().getName(),
                                   component.getId() });
              }
          } catch (Exception e) {
//              if (logger.isLoggable(Level.SEVERE)) {
//                  logger.log(Level.SEVERE,
//                             "Could not instantiate converter for type {0}: {1}",
//                             new Object[] {
//                                   converterType,
//                                   e.toString() });
//              }
              return null;
          }
      } else if (converter == null) {
          // if there is no valueExpression and converter attribute set,
          // assume the modelType as "String" since we have no way of
          // figuring out the type. So for the selectOne and
          // selectMany, converter has to be set if there is no
          // valueExpression attribute set on the component.
          if (logger.isLoggable(Level.FINE)) {
              logger.log(Level.FINE,
                          "No conversion necessary for value {0} of component {1}",
                          new Object[] {
                                submittedValue,
                                component.getId() });
              logger.fine(" since there is no explicitly registered converter "
                          + "and the component value is not bound to a model property");
          }
          return newValue;
      }

      if (converter != null) {
          // If the conversion eventually falls to needing to use EL type coercion,
          // make sure our special ConverterPropertyEditor knows about this value.
          RequestStateManager.set(context,
                                  RequestStateManager.TARGET_COMPONENT_ATTRIBUTE_NAME,
                                  component);
          return converter.getAsObject(context, component, newValue);
      } else {
          // throw converter exception.
          Object[] params = {
                newValue,
                "null Converter"
          };

          throw new ConverterException(MessageFactory.getMessage(
                context, MessageUtils.CONVERSION_ERROR_MESSAGE_ID, params));
      }

  }


  public void setSubmittedValue(UIComponent component, Object value) {
	  component = this;

         ((UIInput) component).setSubmittedValue(value);
          if (logger.isLoggable(Level.FINE)) {
              logger.fine("Set submitted value " + value + " on component ");
          }

  }

  // ------------------------------------------------------- Protected Methods


  protected Object getValue(UIComponent component) {
	  component = this;

      if (component instanceof ValueHolder) {
          Object value = ((ValueHolder) component).getValue();
          if (logger.isLoggable(Level.FINE)) {
              logger.fine("component.getValue() returned " + value);
          }
          return value;
      }

      return null;

  }

  // --------------------------------------------------------- Private Methods


  private boolean hasStringConverter(FacesContext context) {

      if (!hasStringConverterSet) {
          hasStringConverter = null !=
                                context.getApplication()
                                      .createConverter(String.class);
          hasStringConverterSet = true;
      }
      return hasStringConverter;

  }

  
  
  
  
  
  
  
  
  
  


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
              comp.getAttributes().put("class", styleClass);
          }
      }
//      if (value != null)
//      {
//          if (isValueReference((String) value))
//          {
//              ValueExpression vb = getValueExpression((String) value);
//              comp.setValueExpression("value", vb);
//          }
//          else
//          {
//              comp.getAttributes().put("value", value);
//          }
//      }
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
//	public Object getValue() {
//      if (value != null)
//      {
//          return value;
//      }
//      ValueExpression vb = getValueExpression("value");
//      if (vb != null)
//      {
//          return (String) vb.getValue(getFacesContext().getELContext());
//      }
//      return null;
//	}

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
//	public void setValue(ValueClosure value) {
//		this.value = value;
//        ((ValueClosure) value).execute(((ValueClosure) value).getArguments());
//
//	}
	
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
//  		LOG.warn("Remember to over-ride equals for " + ClassUtils.getShortClassName(getClass()) + "!");
  	}
      return EqualsBuilder.reflectionEquals(this, obj);
  }

  /**
   * Generic reflection-based hashCode
   * @see java.lang.Object#hashCode()
   */
//  public int hashCode() {
//  	if (LOG.isWarnEnabled()) {
////  		LOG.warn("Remember to over-ride hashcode " + ClassUtils.getShortClassName(getClass()) + "!");
//  	}
//      return HashCodeBuilder.reflectionHashCode(this);
//  }

  /**
   * Generic reflection-based toString.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
  	// TODO: change to not use reflection toString
  	if (LOG.isWarnEnabled()) {
//  		LOG.warn("Remember to over-ride toString " + ClassUtils.getShortClassName(getClass()) + "!");
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
	
	
    /**
     * {@inheritDoc}
     * @deprecated This has been replaced by {@link #getActionExpression}.
     */
//    public MethodBinding getAction() {
//	MethodBinding result = null;
//	MethodExpression me;
//
//	if (null != (me = getActionExpression())) {
//	    // if the MethodExpression is an instance of our private
//	    // wrapper class.
//	    if (me.getClass().equals(MethodExpressionMethodBindingAdapter.class)) {
//		result = ((MethodExpressionMethodBindingAdapter)me).getWrapped();
//	    }
//	    else {
//		// otherwise, this is a real MethodExpression.  Wrap it
//		// in a MethodBinding.
//		result = new MethodBindingMethodExpressionAdapter(me);
//	    }
//	}
//	return result;
//
//    }

    /**
     * {@inheritDoc}
     * @deprecated This has been replaced by {@link #setActionExpression(javax.el.MethodExpression)}.
     */
//    public void setAction(MethodBinding action) {
//	MethodExpressionMethodBindingAdapter adapter;
//	if (null != action) {
//	    adapter = new MethodExpressionMethodBindingAdapter(action);
//	    setActionExpression(adapter);
//	}
//	else {
//	    setActionExpression(null);
//	}
//    }
    
    /**
     * {@inheritDoc}
     * @deprecated Use {@link #getActionListeners} instead.
     */
//    public MethodBinding getActionListener() {
//        return this.methodBindingActionListener;
//    }

    /**
     * {@inheritDoc}
     * @deprecated This has been replaced by {@link #addActionListener(javax.faces.event.ActionListener)}.
     */
//    public void setActionListener(MethodBinding actionListener) {
//        this.methodBindingActionListener = actionListener;
//    }

	
	
	
	
	
	
	
	/**
	 * {@inheritDoc}
	 */
    public MethodClosure getAction() {
    	return this.action;
    }
	
    public void setAction(MethodClosure action) {
    	this.action = action;
	}
	
    protected ActionListener actionListener;
	/**
	 * {@inheritDoc}
	 */
    public MethodBinding getActionListener() {
        return (MethodBinding) this.actionListener;
    }
	
    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
	
    /**
     * <p>The immediate flag.</p>
     */
    private Boolean immediate;


    @Override
    public boolean isImmediate() {

	if (this.immediate != null) {
	    return this.immediate;
	}
	ValueExpression ve = getValueExpression("immediate");
	if (ve != null) {
	    try {
		return Boolean.TRUE.equals(ve.getValue(getFacesContext().getELContext()));
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return false;
	}

    }


    @Override
    public void setImmediate(boolean immediate) {

        this.immediate = immediate;

    }



    /**
     * <p>Returns the <code>value</code> property of the
     * <code>UICommand</code>. This is most often rendered as a label.</p>
     */
//    public Object getValue() {
//
//	if (this.value != null) {
//	    return (this.value);
//	}
//	ValueExpression ve = getValueExpression("value");
//	if (ve != null) {
//	    try {
//		return (ve.getValue(getFacesContext().getELContext()));
//	    }
//	    catch (ELException e) {
//		throw new FacesException(e);
//	    }
//
//	} else {
//	    return (null);
//	}
//
//    }


    /**
     * <p>Sets the <code>value</code> property of the <code>UICommand</code>.
     * This is most often rendered as a label.</p>
     *
     * @param value the new value
     */
//    public void setValue(Object value) {
//
//        this.value = value;
//
//    }
    
    private MethodBinding methodBindingActionListener = null;


    // ---------------------------------------------------- ActionSource / ActionSource2 Methods

    
    /**
     * <p>The {@link MethodExpression} that, when invoked, yields the
     * literal outcome value.</p>
     */
    private MethodExpression actionExpression = null;
    
    public MethodExpression getActionExpression() {
        return actionExpression;
    }
    
    public void setActionExpression(MethodExpression actionExpression) {
        this.actionExpression = actionExpression;
    }
    
    /**
     * <p>The {@link MethodExpression} that, when invoked, yields the
     * literal outcome value.</p>
     */
    private MethodClosure methodClosure = null;
    
    public MethodClosure getMethodClosure() {
    	return methodClosure;
    }
    
    public void setMethodClosure(MethodClosure methodClosure) {
    	this.methodClosure = methodClosure;
    }
    
    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public void addActionListener(ActionListener listener) {

        addFacesListener(listener);

    }
    
    public ActionListener[] getActionListeners() {

        ActionListener al[] = (ActionListener [])
	    getFacesListeners(ActionListener.class);
        return al;

    }



    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public void removeActionListener(ActionListener listener) {

        removeFacesListener(listener);

    }


    // ----------------------------------------------------- StateHolder Methods


//    private Object[] values;
//
//    public Object saveState(FacesContext context) {
//
//        if (values == null) {
//             values = new Object[5];
//        }
//
//        values[0] = super.saveState(context);
//        values[1] = saveAttachedState(context, methodBindingActionListener);
//        values[2] = saveAttachedState(context, actionExpression);
//        values[3] = immediate;
//        values[4] = value;
//
//        return (values);
//
//    }
//
//
//    public void restoreState(FacesContext context, Object state) {
//        values = (Object[]) state;
//        super.restoreState(context, values[0]);
//        methodBindingActionListener = (MethodBinding)
//            restoreAttachedState(context, values[1]);
//        actionExpression =
//	    (MethodExpression) restoreAttachedState(context, values[2]);
//        immediate = (Boolean) values[3];
//        value = values[4];
//
//    }


    // ----------------------------------------------------- UIComponent Methods


    /**
     * <p>In addition to to the default {@link UIComponent#broadcast}
     * processing, pass the {@link ActionEvent} being broadcast to the
     * method referenced by <code>actionListener</code> (if any),
     * and to the default {@link ActionListener} registered on the
     * {@link javax.faces.application.Application}.</p>
     *
     * @param event {@link FacesEvent} to be broadcast
     *
     * @throws AbortProcessingException Signal the JavaServer Faces
     *  implementation that no further processing on the current event
     *  should be performed
     * @throws IllegalArgumentException if the implementation class
     *  of this {@link FacesEvent} is not supported by this component
     * @throws NullPointerException if <code>event</code> is
     * <code>null</code>
     */
    @Override
    public void broadcast(FacesEvent event) throws AbortProcessingException {

        // Perform standard superclass processing (including calling our
        // ActionListeners)
        super.broadcast(event);

        if (event instanceof ActionEvent) {
            FacesContext context = getFacesContext();
            
            // Notify the specified action listener method (if any)
            MethodClosure mc = getAction();
//            MethodClosure mc = getMethodClosure();
//            MethodBinding mb = getActionListener();
            if (mc != null) {
                mc.execute(event);
            }

            // Invoke the default ActionListener
            ActionListener listener =
              context.getApplication().getActionListener();
            if (listener != null) {
                listener.processAction((ActionEvent) event);
            }
        }
    }

    /**
     * <p>Intercept <code>queueEvent</code> and, for {@link ActionEvent}s,
     * mark the phaseId for the event to be
     * <code>PhaseId.APPLY_REQUEST_VALUES</code> if the
     * <code>immediate</code> flag is true,
     * <code>PhaseId.INVOKE_APPLICATION</code> otherwise.</p>
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





  @Override
  public Object saveState(FacesContext facesContext)
  {
      Object[] values = new Object[26];
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
      values[22] = immediate;
  	  values[23 ] = actionListener;
  	  values[24] = action;
  	  values[25] = type;
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
      immediate = (java.lang.Boolean) values[22];
      actionListener = (ActionListener) values[23 ];
      action = (MethodClosure) values[24];
      type = (String) values[25];
  }


public String getLabel() {
	return label;
}


public void setLabel(String label) {
	this.label = label;
}




public String getType() {
	return type;
}




public void setType(String type) {
	this.type = type;
}




public Class getTypeConstraint() {
	return typeConstraint;
}




public void setTypeConstraint(Class typeConstraint) {
	this.typeConstraint = typeConstraint;
}
	


    
}