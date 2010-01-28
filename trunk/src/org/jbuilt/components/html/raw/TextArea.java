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

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.jbuilt.common.renderkit.html.HtmlRendererUtils;
import org.jbuilt.components.html.raw.base.AbstractComponent;

public class TextArea extends AbstractComponent {
	public TextArea(){
		setRendererType(null);
}

    
     /* Property: id */
    private String id;
    
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
    
     /* Property: name */
    private String name;
    
    public String getName() {

	if (this.name != null) {
	    return this.name;
	}
	ValueExpression ve = getValueExpression("name");
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


    public void setName(String name) {
        this.name = name;
    }
    
     /* Property: value */
    private Object value;
    
    @Override
    public Object getValue() {

	if (this.value != null) {
	    return this.value;
	}
	ValueExpression ve = getValueExpression("value");
	if (ve != null) {
	    try {
		return ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    @Override
    public void setValue(Object value) {
        this.value = value;
    }
    
     /* Property: method */
    private String method;
    
    public String getMethod() {

	if (this.method != null) {
	    return this.method;
	}
	ValueExpression ve = getValueExpression("method");
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


    public void setMethod(String method) {
        this.method = method;
    }
    
     /* Property: action */
    private Class action;
    
    public Class getAction() {

	if (this.action != null) {
	    return this.action;
	}
	ValueExpression ve = getValueExpression("action");
	if (ve != null) {
	    try {
		return (Class) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setAction(Class action) {
        this.action = action;
    }
    
     /* Property: colspan */
    private Integer colspan;
    
    public Integer getColspan() {

	if (this.colspan != null) {
	    return this.colspan;
	}
	ValueExpression ve = getValueExpression("colspan");
	if (ve != null) {
	    try {
		return (Integer) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }
    
     /* Property: scope */
    private String scope;
    
    public String getScope() {

	if (this.scope != null) {
	    return this.scope;
	}
	ValueExpression ve = getValueExpression("scope");
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


    public void setScope(String scope) {
        this.scope = scope;
    }
    
     /* Property: labelAttribute */
    private String labelAttribute;
    
    public String getLabelAttribute() {

	if (this.labelAttribute != null) {
	    return this.labelAttribute;
	}
	ValueExpression ve = getValueExpression("labelAttribute");
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


    public void setLabelAttribute(String labelAttribute) {
        this.labelAttribute = labelAttribute;
    }
    
     /* Property: selected */
    private Boolean selected;
    
    public Boolean getSelected() {

	if (this.selected != null) {
	    return this.selected;
	}
	ValueExpression ve = getValueExpression("selected");
	if (ve != null) {
	    try {
		return (Boolean) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setSelected(Boolean selected) {
        this.selected = selected;
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
    
     /* Property: cols */
    private Integer cols;
    
    public Integer getCols() {

	if (this.cols != null) {
	    return this.cols;
	}
	ValueExpression ve = getValueExpression("cols");
	if (ve != null) {
	    try {
		return (Integer) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setCols(Integer cols) {
        this.cols = cols;
    }
    
     /* Property: datafld */
    private String datafld;
    
    public String getDatafld() {

	if (this.datafld != null) {
	    return this.datafld;
	}
	ValueExpression ve = getValueExpression("datafld");
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


    public void setDatafld(String datafld) {
        this.datafld = datafld;
    }
    
     /* Property: datasrc */
    private String datasrc;
    
    public String getDatasrc() {

	if (this.datasrc != null) {
	    return this.datasrc;
	}
	ValueExpression ve = getValueExpression("datasrc");
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


    public void setDatasrc(String datasrc) {
        this.datasrc = datasrc;
    }
    
     /* Property: dataformatas */
    private String dataformatas;
    
    public String getDataformatas() {

	if (this.dataformatas != null) {
	    return this.dataformatas;
	}
	ValueExpression ve = getValueExpression("dataformatas");
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


    public void setDataformatas(String dataformatas) {
        this.dataformatas = dataformatas;
    }
    
     /* Property: readonly */
    private Boolean readonly;
    
    public Boolean getReadonly() {

	if (this.readonly != null) {
	    return this.readonly;
	}
	ValueExpression ve = getValueExpression("readonly");
	if (ve != null) {
	    try {
		return (Boolean) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }
    
     /* Property: rows */
    private Integer rows;
    
    public Integer getRows() {

	if (this.rows != null) {
	    return this.rows;
	}
	ValueExpression ve = getValueExpression("rows");
	if (ve != null) {
	    try {
		return (Integer) ve.getValue(getFacesContext().getELContext());
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return null;
	}

    }


    public void setRows(Integer rows) {
        this.rows = rows;
    }
    
     /* Property: wrap */
    private String wrap;
    
    public String getWrap() {

	if (this.wrap != null) {
	    return this.wrap;
	}
	ValueExpression ve = getValueExpression("wrap");
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


    public void setWrap(String wrap) {
        this.wrap = wrap;
    }
    
     /* Property: ondblclick */
    private String ondblclick;
    
    @Override
    public String getOndblclick() {

	if (this.ondblclick != null) {
	    return this.ondblclick;
	}
	ValueExpression ve = getValueExpression("ondblclick");
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
    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }
    
     /* Property: onmousedown */
    private String onmousedown;
    
    @Override
    public String getOnmousedown() {

	if (this.onmousedown != null) {
	    return this.onmousedown;
	}
	ValueExpression ve = getValueExpression("onmousedown");
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
    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }
    
     /* Property: onmouseup */
    private String onmouseup;
    
    @Override
    public String getOnmouseup() {

	if (this.onmouseup != null) {
	    return this.onmouseup;
	}
	ValueExpression ve = getValueExpression("onmouseup");
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
    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }
    
     /* Property: onmouseover */
    private String onmouseover;
    
    @Override
    public String getOnmouseover() {

	if (this.onmouseover != null) {
	    return this.onmouseover;
	}
	ValueExpression ve = getValueExpression("onmouseover");
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
    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }
    
     /* Property: onmousemove */
    private String onmousemove;
    
    @Override
    public String getOnmousemove() {

	if (this.onmousemove != null) {
	    return this.onmousemove;
	}
	ValueExpression ve = getValueExpression("onmousemove");
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
    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }
    
     /* Property: onmouseout */
    private String onmouseout;
    
    @Override
    public String getOnmouseout() {

	if (this.onmouseout != null) {
	    return this.onmouseout;
	}
	ValueExpression ve = getValueExpression("onmouseout");
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
    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }
    
     /* Property: onkeypress */
    private String onkeypress;
    
    @Override
    public String getOnkeypress() {

	if (this.onkeypress != null) {
	    return this.onkeypress;
	}
	ValueExpression ve = getValueExpression("onkeypress");
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
    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }
    
     /* Property: onkeydown */
    private String onkeydown;
    
    @Override
    public String getOnkeydown() {

	if (this.onkeydown != null) {
	    return this.onkeydown;
	}
	ValueExpression ve = getValueExpression("onkeydown");
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
    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }
    
     /* Property: onkeyup */
    private String onkeyup;
    
    @Override
    public String getOnkeyup() {

	if (this.onkeyup != null) {
	    return this.onkeyup;
	}
	ValueExpression ve = getValueExpression("onkeyup");
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
    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }
    
     /* Property: onclick */
    private String onclick;
    
    @Override
    public String getOnclick() {

	if (this.onclick != null) {
	    return this.onclick;
	}
	ValueExpression ve = getValueExpression("onclick");
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
    public void setOnclick(String onclick) {
        this.onclick = onclick;
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
	TextArea textarea = (TextArea) component;
	try {
		textarea.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		textarea.encodeEnd(context, component);
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
					 "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "cols",  "datafld",  "datasrc",  "dataformatas",  "readonly",  "rows",  "wrap",  "ondblclick",  "onmousedown",  "onmouseup",  "onmouseover",  "onmousemove",  "onmouseout",  "onkeypress",  "onkeydown",  "onkeyup",  "onclick" });


         
         
    }
}




	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "textarea";
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

 HtmlRendererUtils.renderOptionalEndElement(writer, component, "textarea", new String[ ] { "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "cols",  "datafld",  "datasrc",  "dataformatas",  "readonly",  "rows",  "wrap",  "ondblclick",  "onmousedown",  "onmouseup",  "onmouseover",  "onmousemove",  "onmouseout",  "onkeypress",  "onkeydown",  "onkeyup",  "onclick" });
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[32 ];
        values[0 ] = super.saveState(facesContext);
	values[1 ] = id;
	values[2 ] = name;
	values[3 ] = value;
	values[4 ] = method;
	values[5 ] = action;
	values[6 ] = colspan;
	values[7 ] = scope;
	values[8 ] = labelAttribute;
	values[9 ] = selected;
	values[10 ] = dir;
	values[11 ] = lang;
	values[12 ] = title;
	values[13 ] = style;
	values[14 ] = styleClass;
	values[15 ] = cols;
	values[16 ] = datafld;
	values[17 ] = datasrc;
	values[18 ] = dataformatas;
	values[19 ] = readonly;
	values[20 ] = rows;
	values[21 ] = wrap;
	values[22 ] = ondblclick;
	values[23 ] = onmousedown;
	values[24 ] = onmouseup;
	values[25 ] = onmouseover;
	values[26 ] = onmousemove;
	values[27 ] = onmouseout;
	values[28 ] = onkeypress;
	values[29 ] = onkeydown;
	values[30 ] = onkeyup;
	values[31 ] = onclick;
	return values;
	}

    @Override
    public void restoreState(FacesContext facesContext, Object state)
    {
    	Object[ ] values = (Object[ ])state;
		super.restoreState(facesContext, values[0 ]);

		id = (String) values[1 ];
		name = (String) values[2 ];
		value = values[3 ];
		method = (String) values[4 ];
		action = (Class) values[5 ];
		colspan = (Integer) values[6 ];
		scope = (String) values[7 ];
		labelAttribute = (String) values[8 ];
		selected = (Boolean) values[9 ];
		dir = (String) values[10 ];
		lang = (String) values[11 ];
		title = (String) values[12 ];
		style = (String) values[13 ];
		styleClass = (String) values[14 ];
		cols = (Integer) values[15 ];
		datafld = (String) values[16 ];
		datasrc = (String) values[17 ];
		dataformatas = (String) values[18 ];
		readonly = (Boolean) values[19 ];
		rows = (Integer) values[20 ];
		wrap = (String) values[21 ];
		ondblclick = (String) values[22 ];
		onmousedown = (String) values[23 ];
		onmouseup = (String) values[24 ];
		onmouseover = (String) values[25 ];
		onmousemove = (String) values[26 ];
		onmouseout = (String) values[27 ];
		onkeypress = (String) values[28 ];
		onkeydown = (String) values[29 ];
		onkeyup = (String) values[30 ];
		onclick = (String) values[31 ];
    }

}

