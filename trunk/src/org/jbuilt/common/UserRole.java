package org.jbuilt.common;

public interface UserRole {
    static final String ENABLED_ON_USER_ROLE_ATTR = "enabledOnUserRole";
    static final String VISIBLE_ON_USER_ROLE_ATTR = "visibleOnUserRole";

    /**
     * If user is in given role, this component will be rendered 
     * normally. If not, no hyperlink is rendered but all nested 
     * tags (=body) are rendered.
     * 
     * @JSFProperty
     * @return
     */
    String getEnabledOnUserRole();
    
    void setEnabledOnUserRole(String userRole);

    /**
     *  If user is in given role, this component will be rendered 
     *  normally. If not, nothing is rendered and the body of this 
     *  tag will be skipped.
     * 
     * @JSFProperty
     * @return
     */
    String getVisibleOnUserRole();
    
    void setVisibleOnUserRole(String userRole);

}
