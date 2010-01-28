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

package org.jbuilt.context;

import index.ErrorView;
import index.error;
import index.index;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.JsfUtil;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.view.ModularLayoutView;

import com.google.inject.Injector;


public class ViewProvider implements Provider<JsfViewDirector> {

	FacesContext context;
	UIViewRoot viewRoot;
	
	public ViewProvider(UIViewRoot viewRoot){
		this.viewRoot = viewRoot;
	}
	
	public JsfViewDirector get(Object... viewToRender) {
		
//		UIViewRoot viewRoot = null;
//		if(viewToRender != null && viewToRender.length > 0 && viewToRender[0] instanceof UIViewRoot) {
//			this.viewToRender = (UIViewRoot) viewToRender[0];
//			viewRoot = this.viewToRender;
//
//		}

		
    	String viewId = viewRoot.getViewId();
        viewId = (String) JsfUtil.getServletContext().getAttribute("jbuilt.BASE_VIEW_PATH") + viewId;
        String viewClassName = resolveViewClassName(viewId);
        
        JsfViewDirector view = null;
        Class viewClass = null;
  
      try {
        
        // get String Class
          if(viewClassName.endsWith("index")){
              viewClass = Class.forName("index.index");
          } else {
              viewClass = Class.forName(viewClassName);
          }
        
        Injector injector = (Injector) JsfUtil.getServletContext().getAttribute(Injector.class.getName());

//        Injector injector =
//        	(Injector) ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false))
//		.getServletContext().getAttribute(Injector.class.getName());
//        Injector injector = Guice.createInjector(new NumberGuessModule());

        /*
         * Now that we've got the injector, we can build objects.
         */
        if(viewClass.equals(index.class)){
            ModularLayoutView home = new ModularLayoutView(viewRoot,context);
            view = new index(home, context, viewRoot);
        } else {
            view = (JsfViewDirector) injector.getInstance(viewClass);
        }
        
      }
      catch (Exception e) {
          try {
            viewClass = Class.forName("index.error");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
          e.printStackTrace();
      }
	
              if(view != null) {
            	  view.setTree(viewRoot);
            	  view.createView(context);
              } else {
                  ErrorView errorView = new ErrorView(viewRoot, context);
                  view = new error(errorView, context, viewRoot);
                  view.setTree(viewRoot);
                  view.createView(context);
            	  // send a pretty error page
              }
              
              return view;
	
	}
	
    private String resolveViewClassName(String viewId){
    	String viewClassName = null;
    	if(!viewId.contains(".")){
    	viewClassName = viewId.substring(1).replaceAll("/", ".");
    	} else{
    		if(viewId.endsWith(".jsf")){
    			viewClassName = viewId.substring(1, (viewId.length() - 4)).replaceAll("/", ".");
    		}
    	}
    	
    	return viewClassName;
    }

	

}
