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

import static org.jbuiltDemo.managed.model.ObjectMother.*;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.components.custom.datatables.simple.SimpleDataTable;
import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ViewClosure;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._sortableDataTableView;
import org.jbuiltDemo.managed.model.Company;

import com.google.inject.Inject;

public class SortableDataTableView extends BaseViewClosure  {

	private ViewClosure sortableDataTableView;
	private List<DataModel<Company, String>> companyModelDataList;
	
	@Inject
	SortableDataTableView(@_UIViewRoot UIComponent tree, FacesContext facesContext,
			@_sortableDataTableView ViewClosure sortableDataTableView){
		super(tree, facesContext);
		this.tree = tree;
		this.facesContext = facesContext;
		this.sortableDataTableView = sortableDataTableView;
		this.companyModelDataList = createCompanyModelData();
	}
	
	public UIComponent listDataTable(
	        List<DataModel<Company, String>> dataModelList){
		return 	new SimpleDataTable(dataModelList).writeView();
	}

	public MethodClosure defaultAction = new MethodClosure(){
		private static final long serialVersionUID = 2L;
		@Override
		public Object execute(Object... args) {
			System.out.println("printing default action");
			return args;
		}
	};
	

	@Override
  public Object doExecute(Object... args) {
		
//		StyleSheet tableStyleSheet = new TableSortStyleSheet();
		
		UIComponent html =
		html(
			id("sortableDataTable"),
			head(
				titleElement("jbuilt: Sortable Data Table"),
				jawrScript(
					src("/js/tablesort.js")
				),
				script(
					"window.onload = function () {",
						"var sales = new TableSort('sortableDataTableForm:sales');",
		    		"};"
				),
				jawrStyle(
					src("/css/tablesort.css")
				)//,
//				styleElement(
//					rule(
//						selector("body"),
//    						cssBackgroundImage("/img/bg_blue_2.gif"),
    //						backgroundColor(white),
    //						color(aqua),
//    						fontFamily("Verdana, Arial, Helvetica, sans-serif"),
//    						fontSize("small")
//					)
//				)//,
//				styleElement(
//					((TableSortStyleSheet) tableStyleSheet).writeStyles()
//				)
//				jawrStyle( src("/css/main.css"))
			),
			body(
				div(
//						style(marginTop("100px")),
					form(
						id("sortableDataTableForm"),
						// build the data table passing in a datamodel
						listDataTable(companyModelDataList),
						p(
							styleClass("footnote"),
							text("*Stated in millions of dollars")
						)
					)
				)
			)
		);
			
	return html;
	}

	
}
