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

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.MethodInfo;
import javax.el.MethodNotFoundException;
import javax.el.PropertyNotFoundException;
import javax.faces.component.StateHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class MethodClosureExpression extends MethodExpression
				implements Closure, Serializable, StateHolder, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Arguments arguments;

	private String expressionString;
	private FacesContext context = FacesContext.getCurrentInstance();
	private ELContext elContext = context.getELContext();

	/**
	 * 
	 */
	public MethodClosureExpression(Object... args) {
		this.arguments = new Arguments(args);
	}

	public MethodClosureExpression() {}

	// get value from decode method of component
	// set value after getting from decode method
	// if not value, don't set it
	// if value is calculated or retrieved, output it to component

	public static MethodClosureExpression methodClosure(
			Object... args) {
		return new MethodClosureExpression(args);
	}

	public void execute(ActionEvent event) {
		// no op for sub classes to implement
	}

	public Arguments getArguments() {
		return arguments;
	}

	public void setArguments(Arguments arguments) {
		this.arguments = arguments;
	}

	@Override
  public boolean isLiteralText() {
		return false;
	}


	@Override
  public String getExpressionString() {
		return this.expressionString;
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
		MethodExpression result = null;

		Class toRestoreClass = null;
		if (null != className) {
			try {
				toRestoreClass = loadClass(className, this);
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException(e.getMessage());
			}

			if (null != toRestoreClass) {
				try {
					result = (MethodExpression) toRestoreClass.newInstance();
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
	public MethodInfo getMethodInfo(ELContext arg0)
			throws NullPointerException, PropertyNotFoundException,
			MethodNotFoundException, ELException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object invoke(ELContext arg0, Object[] arg1)
			throws NullPointerException, PropertyNotFoundException,
			MethodNotFoundException, ELException {
		return execute(arg1);
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object execute(Object... args) {
		// TODO Auto-generated method stub
		return execute(args[0]);
		}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FacesContext getContext() {
		return context;
	}

	public ELContext getElContext() {
		return elContext;
	}

	public void setExpressionString(String expressionString) {
		this.expressionString = expressionString;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public void setElContext(ELContext elContext) {
		this.elContext = elContext;
	}

	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
	}


}
