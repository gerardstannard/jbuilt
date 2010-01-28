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
import javax.faces.context.FacesContext;

import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._calctron;

import com.google.inject.Inject;
public class CalctronView extends BaseViewClosure {
@Inject
CalctronView(@_UIViewRoot UIComponent tree, FacesContext facesContext,
@_calctron JsfViewDirector owner){
super(tree, facesContext);
this.owner = owner;
this.tree = tree;
this.facesContext = facesContext;
}
@Override
public Object doExecute(Object... args) {
UIComponent html =

html(
//	head(
//		link (
//			rel("StyleSheet"),
//			href("css/styles.css"),
//			type("text/css")
//		),/*Atts no Ch and text*/
//			script (
//			src("js/prototype.js"),
//			type("text/javascript")
//		),/*Atts no Ch and text*/
//			script (
//			src("js/rico.js"),
//			type("text/javascript")
//		),/*Atts no Ch and text*/
//			script (
//			src("js/jscript.math.js"),
//			type("text/javascript")
//		),/*Atts no Ch and text*/
//			script (
//			src("js/Mode.js"),
//			type("text/javascript")
//		),/*Atts no Ch and text*/
//			script (
//			src("js/Classloader.js"),
//			type("text/javascript")
//		),/*Atts no Ch and text*/
//			script (
//			src("js/CalcTron.js"),
//			type("text/javascript")
//		),/*Atts no Ch and text*/
//			script(
//			text( "var calcTron = new CalcTron();")
//					)
//	),
//	body (
//		onLoad("calcTron.init();")
//		 ,
//		div (
//			id("divMode"),
//			styleClass("cssDivMode")
//		 ,
//			br (
//				)/*Atts no Ch and text*/
//				center(
//				text( "Select mode here")
//								br (
//					)/*Atts no Ch and text*/
//					text( "e")
//								br (
//					)/*Atts no Ch and text*/
//					text( "e")
//								input (
//					type("button"),
//					value("Standard"),
//					onclick("calcTron.setMode('Standard');")
//		)/*Atts no Ch and text*/
//					text( "e")
//								br (
//					)/*Atts no Ch and text*/
//					text( "e")
//								input (
//					type("button"),
//					value("BaseCalc"),
//					onclick("calcTron.setMode('BaseCalc');")
//		)/*Atts no Ch and text*/
//					text( "e")
//							),
//		),
//		center(
//			img (
//				src("img/CalcTron.gif"),
//				vspace("5")
//		)/*Atts no Ch and text*/
//				br (
//				)/*Atts no Ch and text*/
//				img (
//				src("img/tagline.gif"),
//				vspace("5")
//		)/*Atts no Ch and text*/
//				div (
//				id("mainContainer"),
//				styleClass("cssCalculatorOuter")
//		 ,
//				table (
//					cellpadding("2"),
//					cellspacing("2"),
//					border("1"),
//					align("center")
//		 ,
//					tr(
//						td (
//							nowrap("nowrap"),
//							colspan("10"),
//							align("right"),
//							valign("middle")
//		 ,
//							div (
//								style("height:16px;"),
//								id("divResults")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton0"),
//								onclick("calcTron.currentMode.commandButton0();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton1"),
//								onclick("calcTron.currentMode.commandButton1();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton2"),
//								onclick("calcTron.currentMode.commandButton2();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton3"),
//								onclick("calcTron.currentMode.commandButton3();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton4"),
//								onclick("calcTron.currentMode.commandButton4();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_0"),
//								onclick("calcTron.currentMode.button0_0();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_1"),
//								onclick("calcTron.currentMode.button0_1();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_2"),
//								onclick("calcTron.currentMode.button0_2();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_3"),
//								onclick("calcTron.currentMode.button0_3();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_4"),
//								onclick("calcTron.currentMode.button0_4();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_5"),
//								onclick("calcTron.currentMode.button0_5();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_6"),
//								onclick("calcTron.currentMode.button0_6();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_7"),
//								onclick("calcTron.currentMode.button0_7();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_8"),
//								onclick("calcTron.currentMode.button0_8();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button0_9"),
//								onclick("calcTron.currentMode.button0_9();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_0"),
//								onclick("calcTron.currentMode.button1_0();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_1"),
//								onclick("calcTron.currentMode.button1_1();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_2"),
//								onclick("calcTron.currentMode.button1_2();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_3"),
//								onclick("calcTron.currentMode.button1_3();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_4"),
//								onclick("calcTron.currentMode.button1_4();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_5"),
//								onclick("calcTron.currentMode.button1_5();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_6"),
//								onclick("calcTron.currentMode.button1_6();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_7"),
//								onclick("calcTron.currentMode.button1_7();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_8"),
//								onclick("calcTron.currentMode.button1_8();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button1_9"),
//								onclick("calcTron.currentMode.button1_9();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_0"),
//								onclick("calcTron.currentMode.button2_0();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_1"),
//								onclick("calcTron.currentMode.button2_1();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_2"),
//								onclick("calcTron.currentMode.button2_2();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_3"),
//								onclick("calcTron.currentMode.button2_3();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_4"),
//								onclick("calcTron.currentMode.button2_4();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_5"),
//								onclick("calcTron.currentMode.button2_5();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_6"),
//								onclick("calcTron.currentMode.button2_6();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_7"),
//								onclick("calcTron.currentMode.button2_7();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_8"),
//								onclick("calcTron.currentMode.button2_8();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button2_9"),
//								onclick("calcTron.currentMode.button2_9();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_0"),
//								onclick("calcTron.currentMode.button3_0();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_1"),
//								onclick("calcTron.currentMode.button3_1();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_2"),
//								onclick("calcTron.currentMode.button3_2();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_3"),
//								onclick("calcTron.currentMode.button3_3();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_4"),
//								onclick("calcTron.currentMode.button3_4();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_5"),
//								onclick("calcTron.currentMode.button3_5();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_6"),
//								onclick("calcTron.currentMode.button3_6();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_7"),
//								onclick("calcTron.currentMode.button3_7();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_8"),
//								onclick("calcTron.currentMode.button3_8();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button3_9"),
//								onclick("calcTron.currentMode.button3_9();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_0"),
//								onclick("calcTron.currentMode.button4_0();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_1"),
//								onclick("calcTron.currentMode.button4_1();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_2"),
//								onclick("calcTron.currentMode.button4_2();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_3"),
//								onclick("calcTron.currentMode.button4_3();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_4"),
//								onclick("calcTron.currentMode.button4_4();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_5"),
//								onclick("calcTron.currentMode.button4_5();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_6"),
//								onclick("calcTron.currentMode.button4_6();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_7"),
//								onclick("calcTron.currentMode.button4_7();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_8"),
//								onclick("calcTron.currentMode.button4_8();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputButton"),
//								id("button4_9"),
//								onclick("calcTron.currentMode.button4_9();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton5"),
//								onclick("calcTron.currentMode.commandButton5();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton6"),
//								onclick("calcTron.currentMode.commandButton6();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton7"),
//								onclick("calcTron.currentMode.commandButton7();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								id("commandButton8"),
//								onclick("calcTron.currentMode.commandButton8();")
//		)/*Atts no Ch and text*/
//							),
//						td (
//							nowrap("nowrap"),
//							colspan("2"),
//							align("center"),
//							valign("middle")
//		 ,
//							input (
//								type("button"),
//								styleClass("cssInputCommandButton"),
//								style("display:block;"),
//								value("Mode"),
//								onclick("calcTron.changeModePopup();")
//		)/*Atts no Ch and text*/
//							),
//					),
//					tr(
//						td (
//							nowrap("nowrap"),
//							colspan("10"),
//							align("center"),
//							valign("middle")
//		 ,
//							div (
//								style("height:16px;"),
//								id("divInfo")
//		)/*Atts no Ch and text*/
//							),
//					),
//				),
//			),
//		),
//	),
)

	;
 return html;
}
}