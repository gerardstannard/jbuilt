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

package org.jbuilt.utils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.OperationNotSupportedException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jbuilt.components.html.raw.Input;

public class ValueClosure implements Closure, Serializable, StateHolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object bean;
	private String prop;
	private Object value;
	private Class propertyType;
	private Class expectedType;
	private Arguments arguments;
	private Converter converter;
	private UIComponent component;

	private String expressionString;
	private FacesContext context = FacesContext.getCurrentInstance();
	private ELContext elContext = context.getELContext();


	/**
	 * 
	 * @param bean
	 * @param prop
	 * @param value
	 *            - expecting 0 or 1 value
	 */
	public ValueClosure(Object bean, String prop, Object... value) {
		this.arguments = new Arguments(value);
		this.bean = bean;
		this.prop = prop;
//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(bean.getClass().getSimpleName()+"."+prop, bean);
//		this.expectedType = expectedType;
		try {
			this.propertyType = PropertyUtils.getPropertyType(bean, prop);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (value != null) {
			if (value.length > 0) {
				if (value[0] != null) {
					this.value = value[0];
				}
			}
		} else {
			this.value = null;
		}
		// if (value != null && value.length > 0 && value[0] != null) {
		// this.value = value[0];
		// }
	}

	public ValueClosure() {
	}

	// get value from decode method of component
	// set value after getting from decode method
	// if not value, don't set it
	// if value is calculated or retrieved, output it to component


	public static ValueClosure valueClosure(Object bean, String prop,
			Object... value) {
		return new ValueClosure(bean, prop, value);
	}

	public static ValueClosure v(Object bean, String prop,
			Object... value) {
		ValueClosure vc = new ValueClosure(bean, prop, value);
		
		return vc;
	}
	
	public Object execute(
			/* Object bean, String prop, Object value */UIComponent component) {
		context = FacesContext.getCurrentInstance();
		this.component = component;
		Object submittedValue;
		Object finalValue = null;
		if (context != null) {
			boolean renderResponse = context.getRenderResponse();
//			String no = "no";
//			String yes = "yes";
//			out.println("are we in render response phase? "
//					+ (renderResponse ? yes : no));
			boolean hasArg = true; // args.length > 0 && args[0] != null;
//			if (context != null) {
				if (hasArg) {
					Object tempValue = null;
					Object origProp = null;
					try {
						origProp = PropertyUtils.getProperty(bean, prop);
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String clientId = component.getClientId(context);
					assert clientId != null;
					Map<String, String> requestMap = context
							.getExternalContext().getRequestParameterMap();
					// FIXME: we should be getting the submitted value not the raw request value;
					
					Object newValue = requestMap.get(clientId);

					if (newValue != null) {

						newValue = getConvertedValue(newValue, component);

					} else {
						if (origProp != null) {
							newValue = origProp;
						} else {
							newValue = null;
						}
					}
					try {
						PropertyUtils.setProperty(bean, prop, newValue);
						finalValue = PropertyUtils.getProperty(bean, prop);
						Object a = null;
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				}
			}
		}
		return finalValue;
	}
	
	
	
	public Object getConvertedValue(Object newValue, UIComponent component) {
//		Closure converterClosure = converterClosureMap.get(propertyType);
//		 if(converterClosure != null){
//			 return converterClosure.execute(newValue);
//		}
//		 return newValue;
		
		
		// if(newValue == ""){
		// newValue = "0";
		// }
		if (propertyType.isAssignableFrom(Integer.class)) {
			if("".equals(newValue)){
				newValue = 0;
			} else {
			newValue = Integer.valueOf((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(Double.class)) {
			if("".equals(newValue)){
				newValue = 0.0;
			} else {
			newValue = Double.valueOf((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(int.class)) {
			if("".equals(newValue)){
				newValue = 0;
			} else {
			newValue = Integer.parseInt((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(double.class)) {
			if("".equals(newValue)){
				newValue = 0.0;
			} else {
			newValue = Double.parseDouble((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(Float.class)) {
			if("".equals(newValue)){
				newValue = 0.0;
			} else {
			newValue = Float.valueOf((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(Short.class)) {
			if("".equals(newValue)){
				newValue = 0;
			} else {
			newValue = Short.valueOf((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(Long.class)) {
			if("".equals(newValue)){
				newValue = 0;
			} else {
			newValue = Long.valueOf((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(Byte.class)) {
			if("".equals(newValue)){
				newValue = 0;
			} else {
			newValue = Byte.valueOf((String) newValue);
			}
			return newValue;
		} else if (propertyType.isAssignableFrom(Date.class)) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = df.parse((String) newValue);
				// System.out.println("Today = " + df.format(today));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			newValue = date;
			return newValue;
		} else {
			// if no renderer don't need component argument
		}
		return newValue;

	}
	
	private void setLocalSubmittedValue(Object value, UIComponent component){
		if(component instanceof Input){
			((Input) component).setSubmittedValue(value);
		}
	}

	public Arguments getArguments() {
		return arguments;
	}

	public void setArguments(Arguments arguments) {
		this.arguments = arguments;
	}

	public Object getBean() {
		return bean;
	}

	public String getProp() {
		return prop;
	}

	public Object getValue() {
		return getValue(this.elContext);
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public void setValue(Object value) {
		this.value = value;
		//setValue(this.elContext, value);
	}

	//
	// Methods from ValueExpression
	//

	public Object getValue(ELContext elContext) throws ELException {
		this.elContext = elContext;
		if (elContext == null) {
			throw new NullPointerException("ELContext -> null");
		}
		Object result = null;
		assert null != context;
		try {
			result = PropertyUtils.getProperty(bean, prop);
		} catch (Throwable e) {
			throw new ELException(e);
		}
		return result;
	}

	public void setValue(ELContext elContext, Object value) throws ELException {
		if (elContext == null) {
			throw new NullPointerException("ELContext -> null");
		}
		assert null != context;
		try {
			PropertyUtils.setProperty(bean, prop, ((ValueClosure) value)
					.getValue());
		} catch (Throwable e) {
			throw new ELException(e);
		}
	}

	public boolean isReadOnly(Object bean, String prop) {
		boolean result = false;
		result = !PropertyUtils.isWriteable(bean, prop);
		return result;
	}

	public boolean isReadOnly(ELContext elContext) throws ELException {
		if (elContext == null) {
			throw new NullPointerException("ELContext -> null");
		}
		boolean result = false;
		assert null != context;
		try {
			result = isReadOnly(bean, prop);
		} catch (Throwable e) {
			throw new ELException(e);
		}
		return result;
	}

	public Class<?> getType(ELContext elContext) throws ELException {
		if (elContext == null) {
			throw new NullPointerException("ELContext -> null");
		}
		Class result = null;
		assert null != context;
		try {
			result = getType(this.bean, this.prop);
		} catch (Throwable e) {
			throw new ELException(e);
		}
		return result;
	}

	public Class<?> getType(Object bean, String prop) throws ELException {
		Class result = null;
		try {
			result = PropertyUtils.getPropertyType(bean, prop);
		} catch (Throwable e) {
			throw new ELException(e);
		}
		return result;
	}

	/**
	 * <p>
	 * Always return <code>false</code> since we can't possibly know if this is
	 * a literal text binding or not.
	 * </p>
	 */

	public boolean isLiteralText() {
		return false;
	}

	public Class<?> getExpectedType(ELContext elContext) {
		Class result = null;
		try {
			Object value = getValue(elContext);
			result = value.getClass();
		} catch (Throwable e) {
			result = null;
		}
		return result;
	}

	public String getExpressionString() {
		return this.expressionString;
	}

	@Override
  public boolean equals(Object other) {

		if (other == this) {
			return true;
		}

		if (other instanceof ValueExpression) {
			ValueExpression otherVE = (ValueExpression) other;
			Class type = null;
			try {
				type = PropertyUtils.getPropertyType(this.bean,
						this.prop);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			if (type != null) {
				return type.equals(otherVE.getType(context.getELContext()));
			}
		}
		return false;
	}

	@Override
  public int hashCode() {

		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getDelimiterSyntax() {
		return "";
	}

	//
	// Methods from StateHolder
	//

	public Object saveState(FacesContext context) {
		Object result = null;
		if (!tranzient) {
			Object[] stateStruct = new Object[2];
			stateStruct[0] = ((StateHolder) this).saveState(context);
			stateStruct[1] = getClass().getName();
			result = stateStruct;
		}
		return result;
	}

	public void restoreState(FacesContext context, Object state) {
		// if we have state
		if (null == state) {
			return;
		}

		Object[] stateStruct = (Object[]) state;
		Object savedState = stateStruct[0];
		String className = stateStruct[1].toString();
		ValueExpression result = null;

		Class toRestoreClass = null;
		if (null != className) {
			try {
				toRestoreClass = loadClass(className, this);
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException(e.getMessage());
			}

			if (null != toRestoreClass) {
				try {
					result = (ValueExpression) toRestoreClass.newInstance();
				} catch (InstantiationException e) {
					throw new IllegalStateException(e.getMessage());
				} catch (IllegalAccessException a) {
					throw new IllegalStateException(a.getMessage());
				}
			}

			if (null != result && null != savedState) {
				// don't need to check transient, since that was
				// done on the saving side.
				((StateHolder) result).restoreState(context, savedState);
			}
		}
	}

	private boolean tranzient = false;

	public boolean isTransient() {
		return tranzient;
	}

	public void setTransient(boolean newTransientValue) {
		tranzient = newTransientValue;
	}

	//
	// Helper methods for StateHolder
	//

	private static Class loadClass(String name, Object fallbackClass)
			throws ClassNotFoundException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = fallbackClass.getClass().getClassLoader();
		}
		return Class.forName(name, true, loader);
	}

	public Class<?> getExpectedType() {
		return getExpectedType(this.elContext);
	}

	public Object execute(Object... args) {
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return null;
		return args;
	}

	public Class getPropertyType() {
		// TODO Auto-generated method stub
		return this.propertyType;
	}

}
