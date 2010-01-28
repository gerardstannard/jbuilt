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

package org.jbuilt.view;

import java.io.IOException;
import java.io.Writer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.context.XhtmlWriter;
import org.jbuilt.view.componentTree.JsfViewDirector;


public abstract class AbstractViewBuilder implements JsfViewDirector  {

	public Writer xw;
	protected int indentCount;
	protected String indent = "....";
	protected StringBuilder currentIndent = new StringBuilder();
	
	public AbstractViewBuilder(Writer... xw) {
			if(xw.length > 1) {
        throw new IllegalArgumentException("AbstractViewBuilder takes either 0 or 1 arguments");
      }
			if(xw[0] != null) {
        this.xw = new XhtmlWriter();
      }
		}

	public AbstractViewBuilder() {}

	public abstract UIComponent buildView(FacesContext context);

	
	public String buildViewId(String extension){
		String viewId = getClass().getSimpleName();
		String firstLetter = viewId.substring(0,1).toLowerCase();
		String theRest = viewId.substring(1);
		viewId = firstLetter + theRest + extension;
		System.out.println("viewId is " + viewId);
		return viewId;
	}
	
	
	public <T>  T writeXhtml() {
		return null;
	}
	
	

	
	public void incrementIndent() {
		indentCount++;
		getCurrentIndent().append(getIndent());
		try {
			xw.write(getCurrentIndent().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decrementIndent() {
		indentCount--;
		getCurrentIndent().setLength(getCurrentIndent().length() - indent.length());
		try {
			//System.out.printf("getIndentCount() is now %d \n", getIndentCount());
			xw.write(getCurrentIndent().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the indent
	 */
	public String getIndent() {
		return indent;
	}

	/**
	 * @param indent the indent to set
	 */
	public void setIndent(String indent) {
		this.indent = indent;
	}

	/**
	 * @return the currentIndent
	 */
	public StringBuilder getCurrentIndent() {
		return currentIndent;
	}

	/**
	 * @param currentIndent the currentIndent to set
	 */
	public void setCurrentIndent(StringBuilder currentIndent) {
		this.currentIndent = currentIndent;
	}

	/**
	 * @return the indentCount
	 */
	public int getIndentCount() {
		return indentCount;
	}

	/**
	 * @param indentCount the indentCount to set
	 */
	public void setIndentCount(int indentCount) {
		this.indentCount = indentCount;
	}


	
}

