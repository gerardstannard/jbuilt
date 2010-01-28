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

package org.jbuiltDemo.di.guice;



import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._ClockCanvasView;
import org.jbuiltDemo.managed.annotations._GridView;
import org.jbuiltDemo.managed.annotations._GuessView;
import org.jbuiltDemo.managed.annotations._LayoutView;
import org.jbuiltDemo.managed.annotations._ModularLayoutView;
import org.jbuiltDemo.managed.annotations._PieChartView;
import org.jbuiltDemo.managed.annotations._ResponseView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._clockCanvas;
import org.jbuiltDemo.managed.annotations._grid;
import org.jbuiltDemo.managed.annotations._guess;
import org.jbuiltDemo.managed.annotations._layout;
import org.jbuiltDemo.managed.annotations._modularLayout;
import org.jbuiltDemo.managed.annotations._pieChart;
import org.jbuiltDemo.managed.annotations._response;
import org.jbuiltDemo.managed.annotations._sortableDataTable;
import org.jbuiltDemo.managed.annotations._sortableDataTableView;
import org.jbuiltDemo.managed.model.NumberBean;
import org.jbuiltDemo.managed.view.ClockCanvasView;
import org.jbuiltDemo.managed.view.GridView;
import org.jbuiltDemo.managed.view.GuessView;
import org.jbuiltDemo.managed.view.LayoutView;
import org.jbuiltDemo.managed.view.ModularLayoutView;
import org.jbuiltDemo.managed.view.PieChartView;
import org.jbuiltDemo.managed.view.ResponseView;
import org.jbuiltDemo.managed.view.SortableDataTableView;
import org.jbuiltDemo.managed.view.clockCanvas;
import org.jbuiltDemo.managed.view.grid;
import org.jbuiltDemo.managed.view.guess;
import org.jbuiltDemo.managed.view.layoutImpl;
import org.jbuiltDemo.managed.view.modularLayout;
import org.jbuiltDemo.managed.view.pieChart;
import org.jbuiltDemo.managed.view.response;
import org.jbuiltDemo.managed.view.sortableDataTable;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.RequestScoped;
import com.sun.faces.context.FacesContextImpl;
import com.wideplay.warp.servlet.FlashScoped;
import com.wideplay.warp.servlet.Servlets;
import com.wideplay.warp.servlet.SessionScoped;
import com.wideplay.warp.servlet.WarpServletContextListener;


public class JBuiltGuiceContextListener extends WarpServletContextListener {
	private static final Logger LOG = Logger.getLogger(JBuiltGuiceContextListener.class.getName());
	
	@Override
	protected Injector getInjector(){
		Injector injector = Guice.createInjector(Servlets.configure()
        .filters()
//                    .filter("/*").through(RequestPrintingFilter.class)

        .servlets()
//  doesn't work because FacesServlet doesn't extend HttpServlet		  .serve("/*").with(FacesServlet.class)
//                    .serve("/hello/*").with(Key.get(HttpServlet.class, named(HELLO_SERVLET)))
//                    .serve("/counter.html").with(ScopeDemoCountingServlet.class)
//                    .serve("/scoped.html").with(ScopedServletWrappingServlet.class)
//                    .serve("/params.html").with(RequestParameterServlet.class)
//                    .serve("/info/*").with(RequestInfoServlet.class)
//                    .serve("/forward").with(ForwardingServlet.class)
//                    .serve("*.info").with(RequestInfoServlet.class)
//                    .serve("/include/jsp").with(IncludingServlet.class)
//                    .serve("/include/managed").with(ManagedIncludingServlet.class)
//                    .serve("/context").with(ServletContextInjectServlet.class)

        .buildModule(),


        //bind other modules that our webapp needs
    new AbstractModule() {
        @Override
        protected void configure() {
        //example of binding scopes to scope annotations
		bindScope(RequestScoped.class, Servlets.REQUEST_SCOPE);
    	bindScope(SessionScoped.class, Servlets.SESSION_SCOPE);
        bindScope(FlashScoped.class, Servlets.FLASH_SCOPE);
        //etc...

//                        bind(HttpServlet.class).annotatedWith(named(HELLO_SERVLET)).to(HelloWorldServlet.class).in(Singleton.class);

        //Bug (?) in guice forces me to explicitly bind Counter to itself if I want to use annotatedWith + custom scopes =(
//                        bind(Counter.class).annotatedWith(named(REQUEST)).to(Counter.class).in(Servlets.REQUEST_SCOPE);
//                        bind(Counter.class).annotatedWith(named(SESSION)).to(Counter.class).in(SessionScoped.class);
//                        bind(Counter.class).annotatedWith(named(FLASH)).to(Counter.class).in(FlashScoped.class);

        // framework
		bind(UIComponent.class).annotatedWith(_UIViewRoot.class).to(UIViewRoot.class).in(RequestScoped.class);
		bind(FacesContext.class).to(FacesContextImpl.class).in(RequestScoped.class);
		
		// model
		bind(NumberBean.class).in(SessionScoped.class);
		
		
		// view
		bind(JsfViewDirector.class).annotatedWith(_guess.class).to(guess.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_GuessView.class).to(GuessView.class).in(RequestScoped.class);
		
		bind(JsfViewDirector.class).annotatedWith(_sortableDataTable.class).to(sortableDataTable.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_sortableDataTableView.class).to(SortableDataTableView.class).in(RequestScoped.class);

		bind(JsfViewDirector.class).annotatedWith(_response.class).to(response.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_ResponseView.class).to(ResponseView.class).in(RequestScoped.class);

		bind(JsfViewDirector.class).annotatedWith(_pieChart.class).to(pieChart.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_PieChartView.class).to(PieChartView.class).in(RequestScoped.class);
         
		bind(JsfViewDirector.class).annotatedWith(_layout.class).to(layoutImpl.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_LayoutView.class).to(LayoutView.class).in(RequestScoped.class);
		
        bind(JsfViewDirector.class).annotatedWith(_grid.class).to(grid.class).in(RequestScoped.class);
        bind(ViewClosure.class).annotatedWith(_GridView.class).to(GridView.class).in(RequestScoped.class);
        
        bind(JsfViewDirector.class).annotatedWith(_clockCanvas.class).to(clockCanvas.class).in(RequestScoped.class);
        bind(ViewClosure.class).annotatedWith(_ClockCanvasView.class).to(ClockCanvasView.class).in(RequestScoped.class);
        
        bind(JsfViewDirector.class).annotatedWith(_modularLayout.class).to(modularLayout.class).in(RequestScoped.class);
        bind(ViewClosure.class).annotatedWith(_ModularLayoutView.class).to(ModularLayoutView.class).in(RequestScoped.class);
        
		
		// controller
		
            }
        }
        );
		
	return injector;

    }
	
	@Override
	public void contextInitialized(ServletContextEvent contextEvent){
		super.contextInitialized(contextEvent);
		ServletContext context = contextEvent.getServletContext();
		context.setAttribute(Injector.class.getName(), getInjector());
		// TODO: this needs to be looked up from a web xml context param
		context.setAttribute("jbuilt.BASE_VIEW_PATH", "/org/jbuiltDemo/managed/view");
		LOG.log(Level.INFO, "JBuilt is Guiced!!");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		super.contextDestroyed(contextEvent);
		ServletContext context = contextEvent.getServletContext();
		context.removeAttribute(Injector.class.getName());
		LOG.log(Level.INFO, "JBuilt Guice destroyed");
}




}
