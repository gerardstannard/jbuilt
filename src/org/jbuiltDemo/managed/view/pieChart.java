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
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.ViewClosure;
import org.jbuiltDemo.managed.annotations._PieChartView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;

import com.google.inject.Inject;

public class pieChart extends BaseView {

	@Inject
	public pieChart(@_PieChartView ViewClosure viewClosure,
			FacesContext facesContext, @_UIViewRoot UIComponent rootOrComponent) {
        super(viewClosure, facesContext, rootOrComponent);

		if(rootOrComponent instanceof UIViewRoot){
		this.tree = rootOrComponent;
		} else {
			this.tree =  rootOrComponent;
		}
		this.viewClosure = viewClosure;
		this.facesContext = facesContext;

	}
	
//	@Override
//	public void beforeWriteView(){
//
//	}
//
//	@Override
//	public void afterWriteView(){
//
//	}

	
	
}
