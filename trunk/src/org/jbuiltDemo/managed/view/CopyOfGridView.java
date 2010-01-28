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
import org.jbuiltDemo.managed.annotations._grid;

import com.google.inject.Inject;

public class CopyOfGridView extends BaseViewClosure {
    
        @Inject
        CopyOfGridView(@_UIViewRoot UIComponent tree, FacesContext facesContext,
                @_grid JsfViewDirector owner){
            super(tree, facesContext);
            this.owner = owner;
            this.tree = tree;
            this.facesContext = facesContext;
        }
    @Override
    public Object doExecute(Object... args) {
        UIComponent html =

html (/*Z*/
	head (/*Z*/
		titleElement (/*Z*/
			text( "YUI Base Page")/*TM*/
					),/*L*/
		link (/*T*/
			rel(/*S*/"stylesheet"),/*P2*/
			href(/*S*/"http://yui.yahooapis.com/2.7.0/build/reset-fonts-grids/reset-fonts-grids.css"),/*P2*/
			type(/*S*/"text/css")/*P*/
		)
	),/*L*/
	body (/*Z*/
		div (/*T*/
			id(/*S*/"doc2"),/*P2*/
			styleClass(/*S*/"yui-t7")/*P*/,
			div (/*T*/
				id(/*S*/"hd"),/*P2*/
				role(/*S*/"application")/*P*/,
				h1 (/*Z*/
					text( "Header")/*TM*/
				)//,/*L*/
			),/*L*/
			div (/*T*/
				id(/*S*/"bd"),/*P2*/
				role(/*S*/"main")/*P*/,
				div (/*T*/
					styleClass(/*S*/"yui-g")/*P*/

					// YOUR DATA GOES HERE
				),/*L*/
				div (/*T*/
					styleClass(/*S*/"yui-gc")/*P*/,
					div (/*T*/
						styleClass(/*S*/"yui-u first")/*P*/

						// YOUR DATA GOES HERE
					),/*L*/
					div (/*T*/
						styleClass(/*S*/"yui-u")/*P*/

						// YOUR DATA GOES HERE
					)//,/*L*/
				),/*L*/
				div (/*T*/
					styleClass(/*S*/"yui-g")/*P*/

					// YOUR DATA GOES HERE
				),/*L*/
				div (/*T*/
					styleClass(/*S*/"yui-g")/*P*/,
					div (/*T*/
						styleClass(/*S*/"yui-u first")/*P*/

						// YOUR DATA GOES HERE
					),/*L*/
					div (/*T*/
						styleClass(/*S*/"yui-u")/*P*/

						// YOUR DATA GOES HERE
					)//,/*L*/
				)//,/*L*/
			),/*L*/
			div (/*T*/
				id(/*S*/"ft"),/*P2*/
				role(/*S*/"contentinfo")/*P*/,
				p (/*Z*/
					text( "Footer")/*TM*/
				)//,/*L*/
			)//,/*L*/
		)//,/*L*/
	)//,/*L*/
);//,/*L*/

	
//			)
//	);

return html;
}
}