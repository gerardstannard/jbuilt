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
package org.jbuilt.components.html.raw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.jbuilt.componentTree.HasStringBody;
import org.jbuilt.components.html.raw.base.AbstractComponent;
import org.jbuilt.utils.ValueClosure;

public class Text extends AbstractComponent implements HasStringBody {

    static public final String COMPONENT_TYPE =
        "org.jbuilt.Text";
    

    
	private String textString;
	private StringBuilder stringBuilder = new StringBuilder();
	
	
	
	private List<ValueClosure> valueClosures = new ArrayList<ValueClosure>();
	
	public void setTextString(String str){
		this.textString = str;
}
	public String getTextString(){
		return textString;
}
	
	public List<ValueClosure> getValueClosures() {
		return valueClosures;
	}
	public void setValueClosures(List<ValueClosure> valueClosures) {
		this.valueClosures = valueClosures;
	}


	public StringBuilder getStringBuilder() {
		return stringBuilder;
	}
	public void setStringBuilder(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}
	public Text(Object... args) {
			setRendererType(null);
	for(Object arg : args){
		if(arg instanceof String){
			stringBuilder.append(arg);
		} else if(arg instanceof ValueClosure) {
			valueClosures.add((ValueClosure) arg);
			stringBuilder.append(" (valueClosure) ");
		} else {
			throw new IllegalArgumentException("can only accept strings and value closures");

		}
	}

}
	
	public Text(){
		setRendererType(null);
}

		@Override
		public Renderer getRenderer(FacesContext context){
			return null;
		}

		@Override
		public String getRendererType(){
			return null;
		}

    /**
     * <p>Render nested child components by invoking the encode methods
     * on those components, but only when the <code>rendered</code>
     * property is <code>true</code>.</p>
     *
     * @param context FacesContext for the current request
     * @param component the component to recursively encode
     *
     * @throws IOException if an error occurrs during the encode process
     */


  @Override
  public void encodeAll(FacesContext context){
	if(context == null){
		throw new NullPointerException("FacesContext cannot be null");
	}
	UIComponent component = this;
	Text text = (Text) component;
	try {
		text.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		text.encodeEnd(context, component);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
						
		@Override
    public void encodeBegin(FacesContext context, UIComponent component)
			            throws IOException {
			component = this;
	        if (context == null){
	            throw new NullPointerException();
	        }
	        super.encodeBegin(context, component);
    		String tag = this.getStringValue();
    		if( tag.trim().length() == 0 ) {
          return;
        }

	        if (this.isRendered()) {
	            ResponseWriter writer = context.getResponseWriter();
	            String str = this.getTextString();
	            if(str == null || "".equals(str)){
	            	writer.write("");
	            } else {
		            for(ValueClosure vc : getValueClosures()){
	            		Object closureValue = vc.execute(this);
		            	if(str != null){
		            		if(closureValue != null){
		            			if(str.contains("valueClosure")){
		            				str = str.replaceFirst("valueClosure", closureValue.toString());
		            			} else {
		            				str = closureValue.toString();
		            			}
		            		}
		            	}
		            }
			        //TODO: figure out why we need to replace "valueClosure" again
		            str = str.replace("null", "").replace("valueClosure", "");
		            this.textString = str;
		            writer.write(this.textString);
	            }
	        }
		}




	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "text";
	     }



   // @Override
    public void encodeChildren(FacesContext context, UIComponent component)
          throws IOException {
        // no-op
    }

  //  @Override
    public void encodeEnd(FacesContext context, UIComponent component)
          throws IOException {
		component = this;
		super.encodeEnd(context);
        ResponseWriter writer = context.getResponseWriter();
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[1 ];
        values[0 ] = super.saveState(facesContext);
	return values;
	}

    @Override
    public void restoreState(FacesContext facesContext, Object state)
    {
    	Object[ ] values = (Object[ ])state;
		super.restoreState(facesContext, values[0 ]);

    }

}

