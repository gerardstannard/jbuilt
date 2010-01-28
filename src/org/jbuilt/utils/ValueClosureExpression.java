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

import static java.lang.System.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.component.StateHolder;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ValueClosureExpression extends ValueExpression implements Closure, Serializable, StateHolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object bean;
	private String prop;
	private Object value;
	private Arguments arguments;

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
	public ValueClosureExpression(Object bean, String prop, Object... value) {
		if(value != null && (value.length > 3 || value.length == 2 || value.length == 1)){
			throw new IllegalArgumentException("execute method of ValueClosure can only take 0 or 3 arguments");
		}
		this.arguments = new Arguments(value);
		this.bean = bean;
		this.prop = prop;
		if (value != null && value.length > 0 && value[0] != null) {
			this.value = value[0];
		}
	}

	public ValueClosureExpression() {}

	// get value from decode method of component
	// set value after getting from decode method
	// if not value, don't set it
	// if value is calculated or retrieved, output it to component

	public static ValueClosureExpression valueClosure(Object bean, String prop,
			Object... value) {
		return new ValueClosureExpression(bean, prop, value);
	}

	public Object execute(Object... args) {
		if(context != null){
			boolean renderResponse = context.getRenderResponse();
			String no = "no";
			String yes = "yes";
			out.println("are we in render response phase? " + (renderResponse ? yes : no));
			boolean hasArg = args.length > 0 && args[0] != null;
			if(context != null){
				if (hasArg) {
					if (renderResponse) {
						return this.getValue();
					} else {
						this.setValue(args[0]);
//						setValue(args[0]);
//						return null;
					}
				}
			}
		}
		return null;
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
		setValue(this.elContext, value);
	}

	//
	// Methods from ValueExpression
	//

	@Override
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

	@Override
  public void setValue(ELContext elContext, Object value) throws ELException {
		if (elContext == null) {
			throw new NullPointerException("ELContext -> null");
		}
		assert null != context;
		try {
			PropertyUtils.setProperty(bean, prop, ((ValueClosureExpression) value).getValue());
		} catch (Throwable e) {
			throw new ELException(e);
		}
	}

	public boolean isReadOnly(Object bean, String prop) {
		boolean result = false;
		result = !PropertyUtils.isWriteable(bean, prop);
		return result;
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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
				type = PropertyUtils.getPropertyType(this.bean, this.prop);
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

	@Override
	public Class<?> getExpectedType() {
		return getExpectedType(this.elContext);
	}

}
