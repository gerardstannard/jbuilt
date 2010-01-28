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

import static org.jbuiltDemo.view.css.CSSBuilder.*;
import static org.jbuiltDemo.view.css.CSSBuilder.FontWeight.*;
import static org.jbuiltDemo.view.css.CSSBuilder.TextTransform.*;

import org.jbuilt.utils.JsfUtil;

public class TableSortStyleSheet implements StyleSheet {
	
	public TableSortStyleSheet(){}
	
	public String writeStyles(){
		final String contextPath = JsfUtil.getServletContext().getContextPath();
		final String baseImagePath = /*"/" +*/ contextPath + "/img/";
		final String table = " table ";
		String[] rulesArray =
//			rules(
				new String[] { rule(
					selector(table),
					fontWeight(bold),
					fontSize("125%"),
					textTransform(uppercase)
					),
			
				rule(
					selector(table, "caption"),
					border("solid, 1px #fc0"),
					borderCollapse("collapse")
					),
					
				rule(
					selector(".footnote"),
					fontSize("75%"),
					color("#666")
					),
			
				rule(
					selector(table, " caption, " , table , " th, " , table , "td, .footnote"),
					fontFamily("Arial, Helvetica, sans-serif"),
					padding(".5em")
					),
			
				rule(
					selector(table , " td"),
					border("solid 1px #c96")
					),
			
				rule(
					selector(table , " th "),
						backgroundColor("#ec9"),
						color("#630"),
						borderBottom("solid 1px #c96")
					),
			
				rule(
					selector(table , " thead th "),
						background("#fea"),
						borderBottom("solid 3px #630")
					),
			
				rule(
					selector("table thead th a"),
						color("#630"),
						display("block"),
						padding("0 17px"),
						background("#fea url(" + baseImagePath + "arrow-up.gif) no-repeat right center")
						),
	
				rule(
					selector("table thead th.asc a"),
					background("#fea url(" + baseImagePath + "arrow-up-sel.gif) no-repeat right center")
					),
	
				rule(
					selector("table thead th.dsc a"),
						color("#630"),
						display("block"),
						padding("0 17px"),
						background("#fea url(" + baseImagePath + "arrow-dn-sel.gif) no-repeat right center")
					)
//			);
		};
		String rules = null;
		for(String rule : rulesArray){
		    rules += rule;
		}
		
		return rules;
		
	}

}
