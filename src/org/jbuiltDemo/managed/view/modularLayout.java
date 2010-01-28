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

package org.jbuiltDemo.managed.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.ViewClosure;
import org.jbuiltDemo.managed.annotations._ModularLayoutView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;

import com.google.inject.Inject;


public class modularLayout extends BaseView {
	

	@Inject
	public modularLayout(@_ModularLayoutView ViewClosure viewClosure,
					FacesContext facesContext,
					@_UIViewRoot UIComponent rootOrComponent) {
		super(viewClosure, facesContext, rootOrComponent);
	}
		

	@Override
	// optional
	public void beforeWriteView(){
		super.beforeWriteView();
		log("calling super on " + this.getClass().getSimpleName() + ", then overriding, before write view");

	}
	
	@Override
	public void afterWriteView(){
		// optional
		super.afterWriteView();
		log("calling super on " + this.getClass().getSimpleName() + ", then overriding, after write view");
	}

	
	
}
