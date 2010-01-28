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

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.jbuilt.common.renderkit.html.HtmlRendererUtils;
import org.jbuilt.componentTree.HasStringBody;
import org.jbuilt.components.html.raw.base.AbstractComponent;
import org.jbuilt.utils.ValueClosure;

public class Script extends AbstractComponent implements HasStringBody {

    static public final String COMPONENT_TYPE =
        "org.jbuilt.Script";
    
    public Script(){
    	setRendererType(null);
    }

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

    
     /* Property: src */
    private String src;
    
    public String getSrc() {

	if (this.src != null) {
	    return this.src;
	}
	ValueExpression ve = getValueExpression("src");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setSrc(String src) {
        this.src = src;
    }
    
     /* Property: id */
    private String id;
    
    public StringBuilder getStringBuilder() {
		return stringBuilder;
	}
	public void setStringBuilder(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}
	@Override
  public String getId() {

	if (this.id != null) {
	    return this.id;
	}
	ValueExpression ve = getValueExpression("id");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setId(String id) {
        this.id = id;
    }
    
     /* Property: dir */
    private String dir;
    
    @Override
    public String getDir() {

	if (this.dir != null) {
	    return this.dir;
	}
	ValueExpression ve = getValueExpression("dir");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setDir(String dir) {
        this.dir = dir;
    }
    
     /* Property: lang */
    private String lang;
    
    @Override
    public String getLang() {

	if (this.lang != null) {
	    return this.lang;
	}
	ValueExpression ve = getValueExpression("lang");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setLang(String lang) {
        this.lang = lang;
    }
    
     /* Property: title */
    private String title;
    
    @Override
    public String getTitle() {

	if (this.title != null) {
	    return this.title;
	}
	ValueExpression ve = getValueExpression("title");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    
     /* Property: style */
    private String style;
    
    @Override
    public String getStyle() {

	if (this.style != null) {
	    return this.style;
	}
	ValueExpression ve = getValueExpression("style");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setStyle(String style) {
        this.style = style;
    }
    
     /* Property: styleClass */
    private String styleClass;
    
    @Override
    public String getStyleClass() {

	if (this.styleClass != null) {
	    return this.styleClass;
	}
	ValueExpression ve = getValueExpression("styleClass");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
    
     /* Property: type */
    private String type;
    
    public String getType() {

	if (this.type != null) {
	    return this.type;
	}
	ValueExpression ve = getValueExpression("type");
	if (ve != null) {
	    try {
		return (String) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setType(String type) {
        this.type = type;
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
	Script script = (Script) component;
	try {
		script.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		script.encodeEnd(context, component);
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
				

            			writer.startElement(tag, component);
            			HtmlRendererUtils.writeIdIfNecessary(writer, component, context);

			            HtmlRendererUtils.renderHTMLAttributes(writer, component, new String[ ] {
					 "src",  "id",  "dir",  "lang",  "title",  "style",  "styleClass",  "type" });
			            this.stringBuilder.append(this.textString);
			            writer.write(this.stringBuilder.toString());


         
         
    }
}




	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "script";
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

 HtmlRendererUtils.renderOptionalEndElement(writer, component, "script", new String[ ] { "src",  "id",  "dir",  "lang",  "title",  "style",  "styleClass",  "type" });
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[9 ];
        values[0 ] = super.saveState(facesContext);
	values[1 ] = src;
	values[2 ] = id;
	values[3 ] = dir;
	values[4 ] = lang;
	values[5 ] = title;
	values[6 ] = style;
	values[7 ] = styleClass;
	values[8 ] = type;
	return values;
	}

    @Override
    public void restoreState(FacesContext facesContext, Object state)
    {
    	Object[ ] values = (Object[ ])state;
		super.restoreState(facesContext, values[0 ]);

		src = (String) values[1 ];
		id = (String) values[2 ];
		dir = (String) values[3 ];
		lang = (String) values[4 ];
		title = (String) values[5 ];
		style = (String) values[6 ];
		styleClass = (String) values[7 ];
		type = (String) values[8 ];
    }

}

