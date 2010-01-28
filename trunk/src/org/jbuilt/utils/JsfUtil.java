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


import java.io.IOException;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jbuiltDemo.managed.view.JBuiltViewHandler;

import com.sun.faces.util.Util;

/**
 * Util class for JSF.
 */
public class JsfUtil {

	public static FacesContext getCurrentInstance() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getCurrentInstance().getExternalContext();
	}

	public static ELContext getELContext() {
		return getCurrentInstance().getELContext();
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * Useful for downloading
	 * 
	 * @return
	 */
	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 * Get servlet context.
	 * 
	 * @return the servlet context
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) getExternalContext().getContext();
	}

	/**
	 * Get managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name
	 * @return the managed bean associated with the bean name
	 */
	public static Object getManagedBean(String beanName) {
		ELContext elContext = getELContext();
		ELResolver resolver = elContext.getELResolver();
		return resolver.getValue(elContext, null, beanName);
	}


	/**
	 * Store the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static void setManagedBeanInSession(String beanName, Object managedBean) {
		getExternalContext().getSessionMap().put(beanName, managedBean);
	}

	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name
	 *            the name of the parameter
	 * @return the parameter value
	 */
	public static String getRequestParameter(String name) {
		return getExternalContext().getRequestParameterMap().get(name);
	}

	public static HttpSession getHttpSession(boolean create) {
		return (HttpSession) getExternalContext().getSession(create);
	}

	/**
	 * Add information message.
	 * 
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Add information message
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Add error message.
	 * 
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	/**
	 * Add error message
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String clientId, String msg) {
		getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}

	public static String getMessageByKey(String key) {
		String messageBundleName = getCurrentInstance().getApplication().getMessageBundle();
		ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundleName);
		try {
			return resourceBundle.getString(key);
		} catch (Exception e) {
			return key;
		}
	}

	public static String getViewRootId(PhaseEvent event) {
		FacesContext fc = null;
		if (event == null) {
			fc = JsfUtil.getCurrentInstance();
		} else {
			fc = event.getFacesContext();
		}
		UIViewRoot root = fc.getViewRoot();
		if (root != null) {
      return root.getViewId();
    }
		return null;
	}

	public static void setNavigation(String anOutcome) {
		FacesContext context = getCurrentInstance();
		Application app = context.getApplication();
		NavigationHandler navHandler = app.getNavigationHandler();
		navHandler.handleNavigation(context, null, anOutcome);
	}

	public static void navigateTo(String viewId, boolean... redirect) {
		FacesContext context = getCurrentInstance();
		ExternalContext extContext = context.getExternalContext();
		
		ViewHandler viewHandler = Util.getViewHandler(context);
		UIViewRoot newRoot = viewHandler.createView(context,
                viewId);
		
		if(redirect != null && redirect.length > 0 && redirect[0]){
			String newPath =
                viewHandler.getActionURL(context, viewId);
            try {
//                if (logger.isLoggable(Level.FINE)) {
//                    logger.fine("Redirecting to path " + newPath
//                                + " for outcome " + outcome +
//                                "and viewId " + caseStruct.viewId);
//                }
                // encode the redirect to ensure session state
                // is maintained
                extContext.redirect(extContext.encodeActionURL(newPath));
            } catch (java.io.IOException ioe) {
//                if (logger.isLoggable(Level.SEVERE)) {
//                    logger.log(Level.SEVERE,"jsf.redirect_failed_error",
//                               newPath);
//                }
            	System.out.println("jsf.redirect_failed_error");
                throw new FacesException(ioe.getMessage(), ioe);
            }
            context.responseComplete();
//           if (logger.isLoggable(Level.FINE)) {
//               logger.fine("Response complete for " + caseStruct.viewId);
//           }
            System.out.println("respone complete for" + viewId);
		} else {
			try {
				((JBuiltViewHandler) viewHandler).buildView(context, newRoot);
			} catch (FacesException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			context.setViewRoot(newRoot);
		}
	}


	

}
