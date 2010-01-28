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

package org.jbuilt.di.guice.modules;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._GuessView;
import org.jbuiltDemo.managed.annotations._ResponseView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._guess;
import org.jbuiltDemo.managed.annotations._response;
import org.jbuiltDemo.managed.model.NumberBean;
import org.jbuiltDemo.managed.view.GuessView;
import org.jbuiltDemo.managed.view.ResponseView;
import org.jbuiltDemo.managed.view.guess;
import org.jbuiltDemo.managed.view.response;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletScopes;
import com.google.inject.servlet.SessionScoped;
import com.sun.faces.context.FacesContextImpl;

//@Module
public class NumberGuessModule extends AbstractModule {

	@Override
	protected void configure() {

		bindScope(RequestScoped.class, ServletScopes.REQUEST);
		bindScope(SessionScoped.class, ServletScopes.SESSION);
		bind(NumberBean.class).in(SessionScoped.class);
		bind(UIComponent.class).annotatedWith(_UIViewRoot.class).to(UIViewRoot.class).in(RequestScoped.class);
		bind(FacesContext.class).to(FacesContextImpl.class).in(RequestScoped.class);
		bind(JsfViewDirector.class).annotatedWith(_guess.class).to(guess.class).in(RequestScoped.class);
		bind(JsfViewDirector.class).annotatedWith(_response.class).to(response.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_GuessView.class).to(GuessView.class).in(RequestScoped.class);
		bind(ViewClosure.class).annotatedWith(_ResponseView.class).to(ResponseView.class).in(RequestScoped.class);
//		bind(UIViewRoot.class).in(RequestScoped.class);
//		bind(new TypeLiteral<Provider<JsfViewDirector>>(){}).in(SessionScoped.class);
//		bind(GuessFactory.class).toProvider(
//			    FactoryProvider.newFactory(GuessFactory.class, guess.class));
	}

}


//interface GuessFactory {
//		  public guess create(UIComponent rootOrComponent);
//}
