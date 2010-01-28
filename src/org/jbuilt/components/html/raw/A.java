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


public class A extends AbstractComponent {
	public A(){
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
    
     /* Property: accesskey */
    private String accesskey;
    
    public String getAccesskey() {

	if (this.accesskey != null) {
	    return this.accesskey;
	}
	ValueExpression ve = getValueExpression("accesskey");
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


    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }
    
     /* Property: charset */
    private String charset;
    
    public String getCharset() {

	if (this.charset != null) {
	    return this.charset;
	}
	ValueExpression ve = getValueExpression("charset");
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


    public void setCharset(String charset) {
        this.charset = charset;
    }
    
     /* Property: coords */
    private String coords;
    
    public String getCoords() {

	if (this.coords != null) {
	    return this.coords;
	}
	ValueExpression ve = getValueExpression("coords");
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


    public void setCoords(String coords) {
        this.coords = coords;
    }
    
     /* Property: hreflang */
    private String hreflang;
    
    public String getHreflang() {

	if (this.hreflang != null) {
	    return this.hreflang;
	}
	ValueExpression ve = getValueExpression("hreflang");
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


    public void setHreflang(String hreflang) {
        this.hreflang = hreflang;
    }
    
     /* Property: href */
    private String href;
    
    public String getHref() {

	if (this.href != null) {
	    return this.href;
	}
	ValueExpression ve = getValueExpression("href");
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


    public void setHref(String href) {
        this.href = href;
    }
    
     /* Property: rel */
    private String rel;
    
    public String getRel() {

	if (this.rel != null) {
	    return this.rel;
	}
	ValueExpression ve = getValueExpression("rel");
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


    public void setRel(String rel) {
        this.rel = rel;
    }
    
     /* Property: rev */
    private String rev;
    
    public String getRev() {

	if (this.rev != null) {
	    return this.rev;
	}
	ValueExpression ve = getValueExpression("rev");
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


    public void setRev(String rev) {
        this.rev = rev;
    }
    
     /* Property: shape */
    private String shape;
    
    public String getShape() {

	if (this.shape != null) {
	    return this.shape;
	}
	ValueExpression ve = getValueExpression("shape");
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


    public void setShape(String shape) {
        this.shape = shape;
    }
    
     /* Property: tabindex */
    private Integer tabindex;
    
    public Integer getTabindex() {

	if (this.tabindex != null) {
	    return this.tabindex;
	}
	ValueExpression ve = getValueExpression("tabindex");
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


    public void setTabindex(Integer tabindex) {
        this.tabindex = tabindex;
    }
    
     /* Property: target */
    private String target;
    
    @Override
    public String getTarget() {

	if (this.target != null) {
	    return this.target;
	}
	ValueExpression ve = getValueExpression("target");
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
    public void setTarget(String target) {
        this.target = target;
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
	A a = (A) component;
	try {
		a.encodeBegin(context, component);
		for(UIComponent child : getChildren()) {
				child.encodeAll(context);
		}
		a.encodeEnd(context, component);
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
					 "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "accesskey",  "charset",  "coords",  "hreflang",  "href",  "rel",  "rev",  "shape",  "tabindex",  "target",  "type",  "ondblclick",  "onmousedown",  "onmouseup",  "onmouseover",  "onmousemove",  "onmouseout",  "onkeypress",  "onkeydown",  "onkeyup",  "onclick" });


         
         
    }
}




	     /**
	      *
	      */
	     @Override
      public String getStringValue() {
	         return "a";
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

 HtmlRendererUtils.renderOptionalEndElement(writer, component, "a", new String[ ] { "id",  "name",  "value",  "method",  "action",  "colspan",  "scope",  "label",  "selected",  "dir",  "lang",  "title",  "style",  "styleClass",  "accesskey",  "charset",  "coords",  "hreflang",  "href",  "rel",  "rev",  "shape",  "tabindex",  "target",  "type",  "ondblclick",  "onmousedown",  "onmouseup",  "onmouseover",  "onmousemove",  "onmouseout",  "onkeypress",  "onkeydown",  "onkeyup",  "onclick" });
}

    @Override
    public Object saveState(FacesContext facesContext) {
        Object[ ] values = new Object[36 ];
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
	values[15 ] = accesskey;
	values[16 ] = charset;
	values[17 ] = coords;
	values[18 ] = hreflang;
	values[19 ] = href;
	values[20 ] = rel;
	values[21 ] = rev;
	values[22 ] = shape;
	values[23 ] = tabindex;
	values[24 ] = target;
	values[25 ] = type;
	values[26 ] = ondblclick;
	values[27 ] = onmousedown;
	values[28 ] = onmouseup;
	values[29 ] = onmouseover;
	values[30 ] = onmousemove;
	values[31 ] = onmouseout;
	values[32 ] = onkeypress;
	values[33 ] = onkeydown;
	values[34 ] = onkeyup;
	values[35 ] = onclick;
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
		accesskey = (String) values[15 ];
		charset = (String) values[16 ];
		coords = (String) values[17 ];
		hreflang = (String) values[18 ];
		href = (String) values[19 ];
		rel = (String) values[20 ];
		rev = (String) values[21 ];
		shape = (String) values[22 ];
		tabindex = (Integer) values[23 ];
		target = (String) values[24 ];
		type = (String) values[25 ];
		ondblclick = (String) values[26 ];
		onmousedown = (String) values[27 ];
		onmouseup = (String) values[28 ];
		onmouseover = (String) values[29 ];
		onmousemove = (String) values[30 ];
		onmouseout = (String) values[31 ];
		onkeypress = (String) values[32 ];
		onkeydown = (String) values[33 ];
		onkeyup = (String) values[34 ];
		onclick = (String) values[35 ];
    }

}

