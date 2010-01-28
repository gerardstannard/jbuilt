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

import static org.jbuilt.utils.ValueClosure.*;
import static org.jbuiltDemo.view.css.CSSBuilder.*;

import javax.faces.component.UIComponent;

import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;
import org.jbuiltDemo.view.css.CSSBuilder.ListStyleType;

public class SearchModletContent extends BaseModletContent  {

	private String title;
	private Integer size;
	private MethodClosure searchAction;
	private ValueClosure searchValueClosure = v(this, "searchTerm");
	private UIComponent image;
	private String searchTerm;
	
	public SearchModletContent(){
		if(this.styleClass == null){
		    this.styleClass = "moduleContent";
		}
		if(this.title == null){
		    this.title = "Search";
		}
		if(this.size == null){
		    this.size = 13;
		}
		if(this.image == null){
		    this.image =  img(
                    id("searchImage"),
                    style(
                        position("relative"),
                        top("5px"),
                        left("5px"),
                        cursor("pointer")
                    ),
                    styleClass("image"),
                    src("/img/button_search.gif"),
                    onclick("document.getElementById('searchForm:searchButton').click();")
                );
		}
		if(this.searchAction == null){
            this.searchAction = new MethodClosure(){
                private static final long serialVersionUID = 2L;
                private String value = "";// = (String) searchValueClosure.execute();
                @Override
                public Object execute(Object... args) {
                  System.out.println("searching for " + getValue() + " from SearchModletContent");
                  this.value="";
                  return args;
                }
                
              /**
               * @return the value
               */
              public String getValue() {
                return value;
              }
              /**
               * @param value the value to set
               */
              public void setValue(String value) {
                this.value = value;
              }
                
              };
		}

	}

	@Override
  public void beforeExecute(){

	}
	
	@Override
  public void afterExecute(){
		
	}
	
	@Override
  protected Object doExecute(Object... args) {
        UIComponent contentContainer =
            div(
                styleClass(styleClass),
                form(
                    id("searchForm"),
//                          prependId(false),
                    fieldset(
                        labelElement(
                          forAttribute("searchField"),
                          span(
                              styleClass("labelText"),
                              text(title)
                          ),
                          input(
                            id("searchField"),
                            styleClass("text"),
                            type("text"),
                            value(
                                searchValueClosure
                            ),
                            size(size)
                          )
                        ),
                        image,
                        input(
                            id("searchButton"),
                            style("display:none;"),
                            type("submit"),
                            action(searchAction)
                        ),
                        div(
                            ul(
                                style(
                                    listStyleType(ListStyleType.decimal),
                                    display(this.searchTerm == null ? "none" : "block")
                                ),
                                li("Search results for " + v(this, "searchTerm")),
                                li("Image results for " + v(this, "searchTerm")),
                                li("Interesting facts about " + v(this, "searchTerm"))
                            )
                            
                        )
                    )
                )
             );
        return contentContainer;

	}
	
	@Override
  public void aroundExecute() {
		// no op for sub classes
		
	}

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the searchTerm
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * @param searchTerm the searchTerm to set
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }



}
