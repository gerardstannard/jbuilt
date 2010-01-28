package org.jbuilt.common;

public interface Style {

    /**
     * HTML: CSS styling instructions.
     * 
     * @JSFProperty
     */
    public abstract String getStyle();

    /**
     * The CSS class for this element.  Corresponds to the HTML 'class' attribute.
     * 
     * @JSFProperty
     */
    public abstract String getStyleClass();

	
}
