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

import static org.jbuilt.utils.JbuiltUtils.*;
import static org.jbuiltDemo.managed.model.ObjectMother.*;
import static org.jbuiltDemo.view.css.CSSBuilder.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.utils.Closure;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.utils.ValueClosure;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.model.Company;
import org.jbuiltDemo.managed.model.CompanyModel;

import com.google.inject.Inject;

public class PieChartView extends BaseViewClosure  {

	private List<DataModel<Company, String>> modelList;
	private Map<String, ValueClosure> chartData;
	private Closure populateFieldSet1;
	
	@Inject
	PieChartView(@_UIViewRoot UIComponent tree, FacesContext facesContext){
		super(tree, facesContext);
		this.tree = tree;
		this.facesContext = facesContext;
		this.modelList = createCompanyModelData();
        this.chartData = newHashMap();
        // populate chart with data from Q1
        chartData.put("data1", ((CompanyModel) modelList.get(0)).getVDollars1());
        chartData.put("data2", ((CompanyModel) modelList.get(1)).getVDollars1());
        chartData.put("data3", ((CompanyModel) modelList.get(2)).getVDollars1());
        chartData.put("data4", ((CompanyModel) modelList.get(3)).getVDollars1());
        chartData.put("data5", ((CompanyModel) modelList.get(4)).getVDollars1());
        chartData.put("data6", ((CompanyModel) modelList.get(5)).getVDollars1());

	}
	
    protected MethodClosure defaultAction = new MethodClosure(){
        private static final long serialVersionUID = 2L;
        private String value = "search term";
        @Override
        public Object execute(Object... args) {
//          System.out.println("new value is " + getValue());
          this.value="";
          facesContext.addMessage(null, new FacesMessage("model has been updated, sliders should have maintained their state"));
          return args;
        }
    };

//	@Override
//	public void beforeExecute(){
//		proceed = true;
//	}
//	@Override
//	public void afterExecute(){
//	}
    
    UIComponent conditionalScript(Boolean isBrowserType, String sourcePath){
        UIComponent exCanvasScript;
        if(isBrowserType){
            exCanvasScript = jawrScript( src(sourcePath));
        } else {
            exCanvasScript = script("// stub");
        }
        return exCanvasScript;
    }



	@Override
  public Object doExecute(Object... args) {
        
        /* ************************************************
         * 
         * Value Closure pie chart
         */
        
        final UIComponent fieldSetWithInputs1 = fieldset(
                id("chartDataFieldSet1")
            );
        
    
        Closure populateFieldSet1 = new Closure(){
            public Object execute(Object... args) {
                Map.Entry dataPoint = (Entry) args[0];
                fieldSetWithInputs1.getChildren().add(
                    input(
                        id(dataPoint.getKey()),
                        styleClass("slider"),
                        type("text"),
                        value(dataPoint.getValue())
                    )
                );
                return null;
            }
        };

      forEach(this.chartData, populateFieldSet1 );
		
		UIComponent html =
		html(
			id("pieChart"),
			head(
				titleElement("jbuilt: Pie Chart"),
				styleElement(
					rule(
						selector("body"),
						cssBackgroundImage("/img/bg_blue_2.gif"),
//						backgroundColor(white),
//						color(aqua),
						fontFamily("Verdana, Arial, Helvetica, sans-serif"),
						fontSize("small")
					)
				),
				jawrStyle( src("/css/main.css")),
				jawrStyle( src("/css/pie_chart_dynamic.css")),
				jawrScript( src("/js/slider_control.js")),
				conditionalScript(isFirefox(), "/js/pie_chart_dynamic.js" ),
                conditionalScript(isMSIE(), "/js/excanvas.js" ),
                conditionalScript(isMSIE(), "/js/pie_chart_excanvas.js" )//,
//				jawrScript( src("/js/pie_chart_dynamic.js"))//,
//                jawrScript( src("/js/excanvas.js")),
//                jawrScript( src("/js/pie_chart_excanvas.js")),
//                jawrScript( src("/js/excanvas.js"))

			),
			body(
				div(
					div(
				        id("formContainer"),
    					form(
    						id("pieChartForm"),
//							prependId(false),
    						br(),
			               div(
			                   h1("Q1 Company Sales Figures"),
			                   div(
			                       id("chartContainer1"),
			                       div(
			                           id("chartForm1"),
			                           style("width:305px;"),
			                           fieldSetWithInputs1//,
			                       ),
			                       canvas(
			                           id("chart1"),
			                           width(400),
			                           height(400)
			                       )
			                   )//,
			               ),
			               br(),
                           input(
                                   id("default"),
//                                 style(displayNone()),
                                   type("submit"),
                                   value("Update Model"),
                                   action(defaultAction),
                                   title("notice that sliders and pie slices retain their values after updating the model on the server. ")
                           )//,
//	                       messages()
						)
					)
				),
				br()//,
			)
		);
	return html;
	}
}
