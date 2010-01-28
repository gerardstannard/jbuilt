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

package org.jbuilt.view.xhtml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.jbuilt.view.AbstractView;

public class XhtmlView extends AbstractView {
	
	public String viewId;
	public String xhtmlString;
	
	public XhtmlView(String viewId, String xhtmlString){
		this.viewId = viewId;
		this.xhtmlString = xhtmlString;
	}
	
	public void writeFile(){
		String filePath = "l:/galileo/workspace/jbuiltGen/";
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filePath+viewId);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			IOUtils.write(xhtmlString, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createView(FacesContext context) {
		// TODO Auto-generated method stub
		
	}

	public void setTree(UIViewRoot viewRoot) {
		// TODO Auto-generated method stub
		
	}

	public UIComponent writeView() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
