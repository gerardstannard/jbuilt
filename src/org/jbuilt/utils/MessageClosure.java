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
import javax.el.MethodExpression;
import javax.faces.component.StateHolder;
import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jbuiltDemo.managed.controller.PersonController;

public abstract class MessageClosure
				implements Closure, Serializable, StateHolder /*, ActionListener*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Arguments arguments;

	private FacesContext context = FacesContext.getCurrentInstance();
	private ELContext elContext = context.getELContext();

	/**
	 * 
	 */
	public MessageClosure(Object... args) {
		this.arguments = new Arguments(args);

	}

	public MessageClosure() {}

	// get value from decode method of component
	// set value after getting from decode method
	// if not value, don't set it
	// if value is calculated or retrieved, output it to component


	public void execute(ActionEvent event) {
		// no op for sub classes to implement
	}

	public Arguments getArguments() {
		return arguments;
	}

	public void setArguments(Arguments arguments) {
		this.arguments = arguments;
	}

	public boolean isLiteralText() {
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
	private PersonController personController = new PersonController();

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
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public abstract Object execute(Object... args);

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FacesContext getContext() {
		return context;
	}

	public ELContext getElContext() {
		return elContext;
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

	public Class getType(FacesContext context)
			throws javax.faces.el.MethodNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object invoke(FacesContext context, Object[] params)
			throws EvaluationException, javax.faces.el.MethodNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



}
