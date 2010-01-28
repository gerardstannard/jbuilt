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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfViewDirector;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.annotations._modularLayout;
import org.jbuiltDemo.view.css.CSSBuilder;

import com.google.inject.Inject;

public class ModularLayoutView extends BaseViewClosure {
    @Inject
    public ModularLayoutView(@_UIViewRoot UIComponent tree, FacesContext facesContext,
            @_modularLayout JsfViewDirector owner) {
        super(tree, facesContext);
        this.owner = owner;
        this.tree = tree;
        this.facesContext = facesContext;
    }
    
    public ModularLayoutView(UIComponent tree, FacesContext facesContext) {
        super(tree, facesContext);
        this.tree = tree;
        this.facesContext = facesContext;
    }
    
    public UIComponent searchModlet(Object...args){
        ViewClosure searchModletContent = new SearchModletContent();
        Modlet searchModlet = new Modlet(searchModletContent, "moduleSearch", "module", "Search (fake)");
        return searchModlet.writeComponent();
    }

    @Override
    public Object doExecute(Object... args) {
        
        UIComponent html =

        html(
            xmlns("http://www.w3.org/1999/xhtml"),
                // xml:lang("en"),
            lang("en"),
            head(
                titleElement(
                    text("Custom Modular Layout")
                ),
                jawrStyle(
                      src("/css/modular.css")
                ),
                jawrScript(
                    src("/js/event_listeners.js")
                ),
                jawrScript(
                    src("/js/class_names.js")
                ),
                jawrScript(
                    src("/js/expand_collapse.js")
                ),
                jawrScript(
                    src("/js/modular.js")
                )//,
//                jawrScript(
//                    src("/js/CalendarPopup.js")
//                )
            ),
            body(
                ul(
                    id("top"),
                    li(
                        id("skipNav"),
                        a(
                            href("#menu"),
                            text("Skip to navigation")
                        )
                    ),
                    li(
                        a(
                            href("#content"),
                            text("Skip to content")
                        )
                    )
                ),
                div(
                    id("header"),
                    h1(
                        span(
                            styleClass("replacement")
                        ),
                        a(
                            href("#"),
                            span(
                                styleClass("replacement")
                            ),
                            text("The Modular Layout")
                        )
                    )
                ),

            // END #header
                
            // start composite modlet
                
            // end composite modlet
                
            div(
                id("content"),
                div(
                    id("modules1"),
                    
                    searchModlet(),
                    
                // END .moduleContent

                    // END .module
                    div(
                        id("moduleWeather"),
                        styleClass("module"),
                        h2(
                            text("Weather (fake)")),
                            div(
                                styleClass("moduleContent"),
                                p(
                                    img(
                                        src("/img/icon_sunny.gif"),
                                        width("39"),
                                        height("27"),
                                        alt("Sunny")
                                    ),
                                    strong(
                                        text("Today: ")
                                    ),
                                    text("Sunny with a bit of cloud, 25C")
                                ),
                                p(
                                    img(
                                        src("/img/icon_rain.gif"),
                                        width("39"),
                                        height("27"),
                                        alt("Rainy")
                                    ),
                                    strong(
                                        text("Tomorrow: ")
                                    ),
                                    text(" Scattered showers in the East, 19C")
                                )
                            )

                    // END .moduleContent
                    ),
                    
                    div(
                        id("moduleClock"),
                        styleClass("module"),
                        h2(text("Clock")),
                        div(
                            style("height:150px;"),
                            styleClass("moduleContent"),
                            canvasClock(
                                    id("clock_canvas"),
                                    width(300),
                                    height(300)
                            )
                        )

                    // END .moduleContent
                    )//,



                    // END .module

                    // END .moduleContent
//                    )

                // END .module
                ),
                
                
                

                // END #modules1
                div(
                    id("news"),
                    styleClass("module"),
                    div(
                        styleClass("moduleInner"),
                        h2(
                            text("j ( b ( u ( i ( l ( t ( ))))));")
                        ),
                        div(
                            styleClass("moduleContent"),
                            ul(
                                
                                li(
                                        styleClass("first"),
                                        div(
                                            styleClass("thumbnail"),
                                            img(
                                                src("/img/thumbnail1.jpg"),
                                                width("75"),
                                                height("75"),
                                                alt("Thumbnail"))
                                        ),
                                        // END
                                        // .thumbnail
                                        h3(text("A different approach to defining user interfaces and building custom web components")),
                                        //
                                        p(
                                            text(
                                                "The Jbuilt project attempts to address the challenges of ",
                                                "defining user interfaces and building custom components with java code, ",
                                                "for quickly building web " ,
                                                "applicatons.  JBUILT IS NOT A WEB FRAMEWORK! It concerns itself only with ",
                                                "building the UI and hooking it to model and ",
                                                "controller layers via value \"closures\" and method \"closures\" ( the java code ",
                                                "equivalents of EL expressions). ",
                                                "Jbuilt code may not be suitable for production in many cases, but it shines in rapid ",
                                                "prototyping and proof of concept stages for numerous reasons.  For these cases, tools are ",
                                                "provide or are ",
                                                "being developed to translate Jbuilt UIs into XML based UIs and vice versa.  It is hoped that ",
                                                "the community will see Jbuilt as a complement to their chosen web framework."
                                            ),
                                            br()
                                        )
                                    //
                                    ),

                                    li(
                                          div(
                                              styleClass("thumbnail"),
                                              img(
                                                  src("/img/thumbnail2.jpg"),
                                                  width("75"),
                                                  height("75"),
                                                  alt("Thumbnail"))
                                          ),
                                          // END
                                          // .thumbnail
                                          h3(text("What it is, what it's not.")),
                                          p(
                                              text(
                                                  "At it's core, Jbuilt consists of a set of ui components for creating html markup and ",
                                                  "a factory method for creating that component.  Each html ",
                                                  "tag has a corresponding Jbuilt factory method. ",
                                                  "Each Jbuilt component has properties that, not surprisingly, reflect the attributes of ",
                                                  "html tags. Also, a bit surprisingly, each attribute has a factory method that that applies ",
                                                  "it to it's component. ",
                                                  "Using this approach, you can build any html based UI conceivable. ",
                                                  "This in itself would be helpful, but JBuilt's next feature is quite compelling. ",
                                                  "Jbuilt provides a builder syntax EDSL (embedded domain specific language) to go along with it's ",
                                                  "base components.  Jbuilt's syntax was partially inspired by this article."
                                              ),
                                              br(),
                                              a(
                                                  href("http://www.jmock.org/oopsla2006.pdf"),
                                                  text("Evolving an Embedded Domain-Specific Language in Java")
                                              ),
                                              br()

                                              
                                          )
                                      ),

                                      li(
                                              div(
                                                  styleClass("thumbnail"),
                                                  img(
                                                          src("/img/thumbnail7.jpg"),
                                                      width("75"),
                                                      height("75"),
                                                      alt("Thumbnail"))
                                              ),
                                              // END
                                              // .thumbnail
                                              h3(text("Jbuilt Sample Components and Views")),
                                              p(
                                                  text(
                                                          "These humble samples are just glimpses of what can be done with Jbuilt.  They use fake data, ",
                                                          "and are not styled very well, but they begin to to show Jbuilt's cababilities with regard to ",
                                                          "defining user interfaces, updating model state via value closures, calling server side methods ",
                                                          "via method closures, programmatic navigation, and so on.  Each component is composed of smaller ",
                                                          "components that reflect their html counterparts.  Each one of these components took less than a ",
                                                          "day to compose and range in complexity from one method to several reusable classes.  Jbuilt does ",
                                                          "not intend to supply a library of components. It is meant to be easy enough to compose any ",
                                                          "component you need when you need it.  It will be up to the community to decide whether to share ",
                                                          "components or not."
                                                  ),
                                                  br()
                                              )
                                          ),

                                          li(
                                                  div(
                                                      styleClass("thumbnail"),
                                                      img(
                                                          src("/img/thumbnail4.jpg"),
                                                          width("75"),
                                                          height("75"),
                                                          alt("Thumbnail"))
                                                  ),
                                                  // END
                                                  // .thumbnail
                                                  h3(text("Flexibility, IDE Support, and Compiler Protection???!!!")),
                                                  p(
                                                      text(
                                                          "The examples below are JSF views and components, ",
                                                          "but they don't use EL expressions, there are no JSF managed beans, and there are no navigation rules. ",
                                                          "One of the nicest features of Jbuilt is that you can choose your own dependency injection mechanism. ",
                                                          "Here I've chosen Google Guice because of it's similar philosophy of easy java based syntax to ",
                                                          "accomplish complex tasks. Another nicety of Jbuilt is it's CSSBuilder.  You can look up any css ",
                                                          "style and the style's options if it has them, with IDE code completion. You will soon be using ",
                                                          "css styles and colors you didn't know existed."
                                                          ),
                                                      br()
                                                  )
                                              ),

                                              li(
                                                      div(
                                                          styleClass("thumbnail"),
                                                          img(
                                                              src("/img/thumbnail1.jpg"),
                                                              width("75"),
                                                              height("75"),
                                                              alt("Thumbnail"))
                                                      ),
                                                      // END
                                                      // .thumbnail
                                                      h3(text("Web Resources")),
                                                      p(
                                                          text(
                                                              "Jbuilt uses "
                                                          ),
                                                          a(
                                                              href("http://jawr.dev.java.net"),
                                                              text("JAWR")
                                                          ),
                                                          text(" for css, javascript, and image ",
                                                                  "handling because it is so easy.  Of course you're free to use and resource handling ",
                                                                  "mechanism you choose.  "
                                                          ),
                                                          br()
                                                      )
                                                  ),

                                
                                    li(
                                          div(
                                              styleClass("thumbnail"),
                                              img(
                                                  src("/img/thumbnail1.jpg"),
                                                  width("75"),
                                                  height("75"),
                                                  alt("Thumbnail"))
                                          ),
                                          // END
                                          // .thumbnail
                                          h3(text("The Standard Number Guess App to Demonstrate Your View Layer Capabilities")),
                                          //
                                          p(
                                              text(
                                                  "This little application works the exact same way as it's JSP, Facelets, Seam, etc. versions.",
                                                  "However, the UI is written in Java with Jbuilt method call syntax, it uses method closures ",
                                                  "for it's action methods, value closures for displaying model values, and programmatic ",
                                                  "navigation via navigateTo(\"/viewId.class\");"
                                              ),
                                              br(),
                                              a(
                                                  href(contextPath()+"/org/jbuiltDemo/managed/view/guess"),
                                                  text("Number Guess")
                                              )
                                          )
                                      //
                                      ),

                                
                                
                                
                                li(
                                    div(
                                        styleClass("thumbnail"),
                                        img(
                                    src("/img/thumbnail2.jpg"),
                                    width("75"),
                                    height("75"),
                                    alt("Thumbnail"))
                                ),
                                // END
                                    // .thumbnail
                                    h3(text("Pie Chart")),
                                    //
                                    p(
                                        text(
                                            "Reactive UI components are essential today.  The ability for them to easily maintain their state ",
                                            "is equally essential.  Here is a dynamic pie chart example modified from one of my favorite books, ",
                                            " \"The Art & Science of JavaScript\". The chart gets it's data from dynamically created ",
                                            "model data. Normally, such data would come from a database, however the important point here is that ",
                                            "after an http request, the slider controls and pie slices maintain their server side state. Currently ",
                                            "this example doesn't work in IE, although it will eventually."
                                        ),
                                        br(),
                                        a(
                                                href(contextPath()+"/org/jbuiltDemo/managed/view/pieChart"),
                                                text("Dynamic Pie Chart")
                                            )
                                    )
                                //
                                ),
                                li(
                                    div(
                                        styleClass("thumbnail"),
                                        img(
                                    src("/img/thumbnail7.jpg"),
                                    width("75"),
                                    height("75"),
                                    alt("Thumbnail"))
                                ),

                                // END
                                    // .thumbnail
                                h3(text("Sortable Data Table")),

                                //
                                p(
                                    text(
                                        "This simple data table uses the same model objects as the chart, however they don't share ",
                                        "the same data. This example takes advantage of Java's object oriented principles of composition, ",
                                        "encapsulation, polymorphism, generics, and type safety to build a table from simple table ",
                                        "building blocks.  This example is nowhere near a usable component that updates itself based on ",
                                        "database queries, but it's a good start."
                                    ),
                                    br(),
                                    a(
                                        href(contextPath()+"/org/jbuiltDemo/managed/view/sortableDataTable"),
                                        text("Sortable Data Table")
                                    )

                                )

                                //
                                ),
                                li(
                                    div(
                                        styleClass("thumbnail"),
                                        img(
                                            src("/img/thumbnail4.jpg"),
                                            width("75"),
                                            height("75"),
                                            alt("Thumbnail")
                                        )
                                    ),

                                // END
                                    // .thumbnail
                                    h3(text("YUI Grids and Layouts")),
                                    
                                    //
                                    p(
                                        text(
                                            "Yahoo has really outdone itself with it's grids and layouts.  They've taken all of ",
                                            "the tedious work out of defining page layouts. This example shows how easy it is ",
                                            "to define a resusable layout using YUI grids. "
                                        ),
                                        br(),
                                        a(
                                                href(contextPath()+"/org/jbuiltDemo/managed/view/grid"),
                                                text("YUI Grids Layout")
                                            )
                                    )
                                ),
                                li(
                                    div(
                                        styleClass("thumbnail"),
                                        img(
                                          src("/img/thumbnail3.jpg"),
                                          width("75"),
                                          height("75"),
                                          alt("Thumbnail")
                                        )
                                    ),

                                // END
                                    // .thumbnail
                                    h3(text("Modlets")),
                                    //
                                    p(
                                        text(
                                            "Jbuilt defines the concept of a Modlet.  Basically, a module, or highly reusable ",
                                            "wrapped container ",
                                            "of related information/data/content in a small, collapsible, draggable space. ",
                                            "The search box above is a modlet. If you're viewing this in Firefox you will see ",
                                            "a clock coded in an html canvas element. Althought it's a Jbuilt component, ",
                                            "it is not a modlet yet."
                                        )
                                    )
                                //
                                ),
                                li(div(
                                    styleClass("thumbnail"),
                                    img(
                                        src("/img/thumbnail6.jpg"),
                                        width("75"),
                                        height("75"),
                                        alt("Thumbnail"))
                                ),
                                // END
                                    // .thumbnail
                                h3(text("Todo")),
                                //
                                p(
                                    text(
                                        "Jbuilt has come a long way toward being a stable development tool, however ",
                                        "there is much to do in this regard. Below are some tasks to accomplish before ",
                                        "this project is releasable."
                                    ),
                                    br(),
                                    ul(
                                        style(CSSBuilder.listStyleType(CSSBuilder.ListStyleType.disc),
                                                marginLeft("150px")),
                                        li("General refactoring, design review"),
                                        li("code correctness, some components are missing some html attributes"),
                                        li("some lesser used html tags do not have Jbuilt counterparts yet"),
                                        li("the Jbuilt code base has some known inefficiencies with how it iterates through components and attributes while building the component tree"),
                                        li("there are some quirks with forms, ids, and actions. Certain combinations just don't work for currently unknown reasons"),
                                        li("converters and validators have not been implmented yet"),
                                        li("ajax is available but hasn't been used, tried, or tested"),
                                        li("there are no unit tests"),
                                        li("there is no logging"),
                                        li("for Jbuilt to be most beneficial during development it relies on dynamic code compiling and class reloading via JRebel, which is a $50 expense.  The Java 6 compiler api may alleviate this burden.")//,
//                                        li("")
                                        
                                    )
                                )

                                //
                                )
                            ), div(styleClass("clearer"))
                        )

                    // END .moduleContent
                    )

                // END .moduleInner
                ),
                
                

                // END .module
                div(
                    id("modules2"),
//                    div(
//                        id("moduleHoroscope"),
//                        styleClass("module"),
//                        h2(text("")),
//                        div(
//                            styleClass("moduleContent"),
//                            img(
//                                src("/img/icon_capricorn.gif"),
//                                width("42"),
//                                height("42"),
//                                alt("")
//                            ),
//                            h3(text("")),
//                            p(text("")))
//
//                    // END .moduleContent
//                    ),
                    
                    

                    // END .module
//                    div(
//                        id("moduleMovie"),
//                        styleClass("module"),
//                        h2(text("")),
//                        div(
//                            styleClass("moduleContent"),
//                            ul(
//                                li(
//                                    h3(text("")),
//                                    p(text(""))),
//                                li(h3(text("")),
//                                    p(text("")))))
//
//                    // END .moduleContent
//                    ),

                    // END .module
                    div(id("moduleBlogroll"), styleClass("module"),
                        h2(text("Blogroll")),
                        div(
                            styleClass("moduleContent"),
                            ul(
                                li(
                                    a(
                                        href("http://www.themaninblue.com/"),
                                        text("The Man in Blue")
                                    ),
                                    br(),
                                    text("This is the man who's creativity is responsible for the design and behavior of this layout.")
                                )
                            )
                        )
                    // END .moduleContent
                    )

                // END .module
                ),

                // END #modules2
                div(styleClass("clearer"))

            //
            )

        // END #content
        ))

    ;
    return html;
  }
}