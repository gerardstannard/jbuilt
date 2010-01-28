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

public class Table extends AbstractComponent {

    static public final String COMPONENT_TYPE =
        "org.jbuilt.Table";
    
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
    
     /* Property: align */
    private String align;
    
    public String getAlign() {

	if (this.align != null) {
	    return this.align;
	}
	ValueExpression ve = getValueExpression("align");
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


    public void setAlign(String align) {
        this.align = align;
    }
    
     /* Property: bgcolor */
    private String bgcolor;
    
    public String getBgcolor() {

	if (this.bgcolor != null) {
	    return this.bgcolor;
	}
	ValueExpression ve = getValueExpression("bgcolor");
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


    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }
    
     /* Property: border */
    private String border;
    
    public String getBorder() {

	if (this.border != null) {
	    return this.border;
	}
	ValueExpression ve = getValueExpression("border");
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


    public void setBorder(String border) {
        this.border = border;
    }
    
     /* Property: cellpadding */
    private Integer cellpadding;
    
    public Integer getCellpadding() {

	if (this.cellpadding != null) {
	    return this.cellpadding;
	}
	ValueExpression ve = getValueExpression("cellpadding");
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


    public void setCellpadding(Integer cellpadding) {
        this.cellpadding = cellpadding;
    }
    
     /* Property: cellspacing */
    private Integer cellspacing;
    
    public Integer getCellspacing() {

	if (this.cellspacing != null) {
	    return this.cellspacing;
	}
	ValueExpression ve = getValueExpression("cellspacing");
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


    public void setCellspacing(Integer cellspacing) {
        this.cellspacing = cellspacing;
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
    
     /* Property: frame */
    private String frame;
    
    public String getFrame() {

	if (this.frame != null) {
	    return this.frame;
	}
	ValueExpression ve = getValueExpression("frame");
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


    public void setFrame(String frame) {
        this.frame = frame;
    }
    
     /* Property: rules */
    private String rules;
    
    public String getRules() {

	if (this.rules != null) {
	    return this.rules;
	}
	ValueExpression ve = getValueExpression("rules");
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


    public void setRules(String rules) {
        this.rules = rules;
    }
    
     /* Property: summary */
    private String summary;
    
    public String getSummary() {

	if (this.summary != null) {
	    return this.summary;
	}
	ValueExpression ve = getValueExpression("summary");
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


    public void setSummary(String summary) {
        this.summary = summary;
    }
    
     /* Property: width */
    private Integer width;
    
    public Integer getWidth() {

	if (this.width != null) {
	    return this.width;
	}
	ValueExpression ve = getValueExpression("width");
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


    public void setWidth(Integer width) {
        this.width = width;
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
	Table table = (Table) component;
	try {
		table.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		table.encodeEnd(context, component);
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
					 "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "align",  "bgcolor",  "border",  "cellpadding",  "cellspacing",  "datafld",  "datasrc",  "dataformatas",  "frame",  "rules",  "summary",  "width" });


         
         
    }
}




	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "table";
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

 HtmlRendererUtils.renderOptionalEndElement(writer, component, "table", new String[ ] { "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "align",  "bgcolor",  "border",  "cellpadding",  "cellspacing",  "datafld",  "datasrc",  "dataformatas",  "frame",  "rules",  "summary",  "width" });
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[27 ];
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
	values[15 ] = align;
	values[16 ] = bgcolor;
	values[17 ] = border;
	values[18 ] = cellpadding;
	values[19 ] = cellspacing;
	values[20 ] = datafld;
	values[21 ] = datasrc;
	values[22 ] = dataformatas;
	values[23 ] = frame;
	values[24 ] = rules;
	values[25 ] = summary;
	values[26 ] = width;
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
		align = (String) values[15 ];
		bgcolor = (String) values[16 ];
		border = (String) values[17 ];
		cellpadding = (Integer) values[18 ];
		cellspacing = (Integer) values[19 ];
		datafld = (String) values[20 ];
		datasrc = (String) values[21 ];
		dataformatas = (String) values[22 ];
		frame = (String) values[23 ];
		rules = (String) values[24 ];
		summary = (String) values[25 ];
		width = (Integer) values[26 ];
    }

}

