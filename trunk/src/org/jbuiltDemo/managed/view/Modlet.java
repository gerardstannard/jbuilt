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

package org.jbuiltDemo.managed.view;

import javax.faces.component.UIComponent;

import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

public class Modlet extends JsfComponentTreeViewDirector  {

  /**
   * 
   */
  private static final long serialVersionUID = 497294279431258122L;

  private String id;
  private String styleClass;
  private String title;
  private ViewClosure modletContent;
  
      Modlet(ViewClosure modletContent, String id, String styleClass, String title){
          this.modletContent = modletContent;
          this.id = id;
          this.styleClass = styleClass;
          this.title = title;
          if(styleClass == null) {
              styleClass = "module";
          }
      }
  
    public UIComponent writeComponent(Object...args ) {
        modletContent.beforeExecute();
        UIComponent contentContainer =
            div(
                id(id),
                styleClass(styleClass),
                h2(
                    text(title),
                    a(
                        id("expandCollapse"),
                        href("#"),
                        title("Expand/Collapse"),
                        img(
                            src("/img/expand_collapse.gif"),
                            alt("Expand/Collapse")//,
//                            onmousedown("mousedownExpandCollapse"),
//                            onclick("clickExpandCollapse")
                        )
                    )
                ),
                script("attachEventListener(document.getElementById('expandCollapse'),",
                        "'mousedown', mousedownExpandCollapse, false);"),
                script("attachEventListener(document.getElementById('expandCollapse'),",
                "'click', clickExpandCollapse, false);"),
                
                modletContent.execute(args)
            );
        modletContent.afterExecute();
        return contentContainer;
    }
  
  /**
   * @return the id
   */
  public String getId() {
    return id;
  }
  /**
   * @return the styleClass
   */
  public String getStyleClass() {
    return styleClass;
  }
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }
  /**
   * @param styleClass the styleClass to set
   */
  public void setStyleClass(String styleClass) {
    this.styleClass = styleClass;
  }
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

/**
 * @return the modletContent
 */
public ViewClosure getModletContent() {
    return modletContent;
}

/**
 * @param modletContent the modletContent to set
 */
public void setModletContent(ViewClosure modletContent) {
    this.modletContent = modletContent;
}
  
  
}
