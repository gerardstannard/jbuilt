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

package org.jbuilt.components.jsf;

import javax.faces.component.html.HtmlOutputText;

public class Facet extends HtmlOutputText {

	     /**
	      * <p>The name of this facet.  This will be used as the facet name for
	      * our <code>UIComponent</code> child in our <code>UIComponent</code>
	      * parent's facet list.</p>
	      */
	     private String name = null;
	     

	     /**
	      * <p>Return the name to be assigned to this facet.</p>
	      */
	     public String getName() {

	 	return name;

	     }
	     

	     /**
	      * <p>Set the name to be assigned to this facet.</p>
	      *
	      * @param name The new facet name
	      */
	     public void setName(String name) {

	 	this.name = name;

	     }




	
	
}

