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

import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._clockCanvas;

import com.google.inject.Inject;
public class ClockCanvasView extends BaseViewClosure {
@Inject
ClockCanvasView(@_UIViewRoot UIComponent tree, FacesContext facesContext,
@_clockCanvas JsfViewDirector owner){
super(tree, facesContext);
this.owner = owner;
this.tree = tree;
this.facesContext = facesContext;
}
@Override
public Object doExecute(Object... args) {
UIComponent html =

html(
	head(
    		titleElement("Mozilla Canvas Clock")
//    		,
//    		jawrScript(
//    		        src("/js/clock_canvas.js")
//            )
		),
	body(
        canvasClock(
                id("clock_canvas"),
                width(300),
                height(300)
        )
	)
);
 return html;
}
}