**Web UI code should be easy to define and refactor, while visually representing it's tree structure**

The Jbuilt project addresses the challenges of defining user interfaces and building custom components entirely with java code, for quickly building web applicaton user interfaces. Through the magic of var args, dependency injection, annotations, function objects, and a slick method call syntax, developers can write UI code in Java that is strikingly similar to html, combining the terseness of declarative programming, with the power of executable code. JBUILT IS NOT A WEB FRAMEWORK! It concerns itself only with building the UI and hooking it to model and controller layers via value "closures" and action "closures" ( the java code equivalents of EL expressions). Jbuilt code may not be suitable for production in many cases, but it shines in rapid prototyping and proof of concept stages for numerous reasons. For these cases, tools are provide to translate Jbuilt UIs into XML based UIs and vice versa. It is hoped that the community will see Jbuilt as a complement to their chosen web framework.


---

**What Jbuilt Is**

At it's core, Jbuilt consists of a set of UI components that mirror html tags and factory methods for creating those components. Each Jbuilt component has properties that, not surprisingly, reflect the attributes of html tags. Also, a bit surprisingly, each attribute has a factory method that that applies it to it's component. Using this approach, you can build any html based UI conceivable. This in itself would be helpful, but JBuilt's next feature is quite compelling. Jbuilt provides a fluent interface / builder syntax EDSL (embedded domain specific language) to go along with it's base components.
Jbuilt was partially inspired by the following...

[Evolving an Embedded Domain-Specific Language in Java](http://www.jmock.org/oopsla2006.pdf)

[Martin Fowler's Fluent Interface Article](http://www.martinfowler.com/bliki/FluentInterface.html)

[Martin Fowler's Internal DSL Article](http://martinfowler.com/dslwip/InternalOverview.html)


---


**Example Jbuilt "Page"**

Here is a sample of Jbuilt user interface code based on the familiar number guess game sample application.

```

public class GuessViewClient extends BaseViewClosure  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6447768434701746703L;
	private NumberBean numberBean;
	private ViewClosure guessView = this;
	private Layout layout;
	private boolean listenerInvoked = false;
	private final JsFunctionParts formId = guessForm;
	private final ValueClosure guessValueClosure; 
	private final ConverterClosure guessConverter = new GuessConverter();
	private final FaceletsComponentLibrary ui = new FaceletsComponentLibrary();
	private final JsfComponentLibrary jsf = new JsfComponentLibrary();
	

	
	@Inject
	GuessViewClient(@_UIViewRoot UIComponent tree, FacesContext facesContext, NumberBean numberBean, 
			@_guess JsfViewDirector owner, @Named("_LayoutView") Layout layout){
		super(tree, facesContext);
		this.owner = owner;
		this.tree = tree;
		this.facesContext = facesContext;
		this.numberBean = numberBean;
		this.layout = layout;
		this.guessValueClosure = v(numberBean, "guess");
	}

	public ActionClosure guessAction = new ActionClosure(){
		private static final long serialVersionUID = 1L;
		@Override
		public Object execute(Object... args) {
			numberBean.setDisplayGuess(false);
			return args;
		}
	};
	
	public ValueChangeClosure changeListener = new ValueChangeClosure(){
		private static final long serialVersionUID = 1L;
		public void execute(ValueChangeEvent event) {
			if(!listenerInvoked){
					UIComponent component = event.getComponent();
					log("value changed for " + component.getClientId(facesContext) + 
							" old value was " + event.getOldValue() + " new value is " +
							event.getNewValue());
				}
			listenerInvoked = true;
		}
	};
	
	public AttributeValueClosure<Boolean> immediate = new AttributeValueClosure<Boolean>(){
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public Boolean execute(Object... args) {
			log("this was an immediate request"); 
			return  Boolean.TRUE;
		}

		public Object[] getArgs() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String toString(){
			return "AttributeValueClosure<Boolean> immediate is Boolean.TRUE";
		}
		
	};
	
	public ActionClosure backAction = new ActionClosure(){
		private static final long serialVersionUID = 54345435L;
		@Override
		public Object execute(Object... args) {
			numberBean.setDisplayGuess(true);
			return args;
		}
	};
	
	public ActionClosure startOverAction = new ActionClosure(){
		private static final long serialVersionUID = 567677567567L;
		@Override
		public Object execute(Object... args) {
			numberBean.setDisplayGuess(true);
            numberBean.resetActual();
			numberBean.setGuess(new Guess(0));
			return args;
		}
	};

	@Override
	public void beforeExecute(){
		proceed = true;
	}
	@Override
	public void afterExecute(){
	}
	
	 <Id extends Enum<Id>> String cid(Id id){
		return buildId(formId, id);
	}
	 
		public static String clear(){
			return "clear()";
		}
		public static String focus(){
			return "focus()";
		}
		


	 
	public Object doExecute(Object... args) {
		
		layout.headerText("Number Guess");
		
		JsFunctionBody clearGuessBody = new JsFunctionBody(){
			StringBuilder sb = newStringBuilder();
			public String execute(Object... args) {
				sb.append($(userNo)).append(".").append(NoArgMethods.clear.name()).append("().")
					.append(NoArgMethods.focus.name()).append("();");
				return sb.toString(); 
			}
		};
		
		
		
				
		this.layout.mainContentInner(
				script(
					jsFunction(
						functionName(clearGuess),
						functionBody($(userNo),dot,clear(),dot,focus())
					),
					"var activateGuess = function(){$('userNo').activate();};",
					"var replaceElement = function(id, content, closure, xjson){closure();};",
					"var focusBack = function(){$('back').focus();};",
					"var focusAfterGuess = function(){if($('guessMessage').innerHTML",
					"==' Sweet, you got it right! '){$('startOver').focus();} else {$('back').focus();};};",
					"Event.observe(window, 'load', function(){",
						"$('userNo').clear().focus();",
					"});"
					),
					form(   
						id(formId),
						div(id(formInner),
							div(id(guess),
								h1(    
									style(display(numberBean.isDisplayGuess() == true)),
									text("I'm thinking of a number from", 
											numberBean.min(),
											" to ", 
											v(numberBean, "max"), 
											".  Can you guess it?")
								),
								h1(    
									style(display(numberBean.isDisplayGuess() == false)),
									span(
											text(id("guessOutput"),
												"Your guess was", numberBean.guessValueClosure, "."
											,
 											converterClosure(guessConverter)
											)
									),
									br(),
									span(
										id(guessMessage),
										text(v(numberBean, "message"))
									)
								),
									input(  
										style(display(numberBean.isDisplayGuess()==true)),
										type(text),
										id(userNo),
										value(numberBean.guessValueClosure)
										,
										validatorClosure(numberBean.guessValidator),
										valueChangeClosure(changeListener),
										converterClosure(guessConverter)
									),
									br(),
									input( 
										id(submitGuess),
										style(display(numberBean.isDisplayGuess()==true)),
										type(submit),
										onclick(
											fireAjax(
												execute(cid(userNo),cid(submitGuess)),
												render(formId),
												postReplace(replaceElement),
												closure(focusAfterGuess)
											)
										),
										action(guessAction),
										value("Submit")
									),
									br(),
									input( 
										id(back),
										style(displayInline(numberBean.isDisplayGuess()==false)),
										type(submit),
										onclick(
											fireAjax(
												execute(cid(back)),
												render(formId.name()),
												postReplace(replaceElement),
												closure(activateGuess)
											)
										),
										immediate(immediate),
										action(backAction),
										value("Back")
									),
									input( 
										style(displayInline(numberBean.isDisplayGuess()==false)),
										id(startOver),
										type(submit),
										onclick(
											fireAjax(
												execute(cid(startOver)),
												render(formId),
												closure(clearGuess),
												postReplace(replaceElement)
											)
										),
										immediate(numberBean.immediate),
										action(startOverAction),
										value("Start Over")
									),
									br()
									,jsf.message(
										forClientId(cid(userNo))
									)
								)
							)
						),
					codeModlet(),
					ui.debug(hotkey("P"))
				);
		
		 return this.layout.execute();
	}
			
}


```
**_Of course, you may or may not want to embed action code into your view code, but the point here is that it's possible, which may be highly desirable when banging out a quick prototype._**


---


**Flexibility, IDE Support, and Compiler Protection???!!!**

The example above does not use EL expressions, there are no JSF managed beans, and there are no navigation rules. While Jbuilt does provide factory methods for each of the JSF standard components, this example does not use any of them.  It uses Jbuilt components that mirror raw html tags.  One of the nicest features of Jbuilt is that you can choose your dependency injection mechanism. Here I've chosen Google Guice because of it's similar philosophy of easy java based syntax to accomplish complex tasks.  Jbuilt UIs are fail fast.  The IDE will catch most problems before you even build your app, and if you use Guice, it will not let the app start if your dependencies are not correct.  Another nicety of Jbuilt is it's CSSBuilder. You can look up any css style and the style's options if it has them, with IDE code completion. You will soon be using css styles and colors you didn't know existed.


---


**Fast and Easy Custom Components**

Aside from the provided raw html custom components, you can quickly and easily build your own components using only Java methods and/or classes.  There is no need for tags, namespaces, tag libraries, or tag classes.  There is also no need to implement render methods like, encodeBegin(), encodeEnd(), encodeAll(), decode(), etc.  This is because the base components render themselves.  Jbuilt takes a slightly different approach to rendering flexibility.  There are very few ways and in most cases only one way to render an html element.  Therefore, you can easily switch out different rendering scenarios simply by changing the way the raw base components are assembled. See the [demo](http://www.jbuilt.org/org/jbuilt/jbuiltDemoMaven/managed/view/modularLayout) for examples.


---


**Example Custom Component**

```

public class Modlet extends JsfComponentTreeViewDirector  {

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
                            alt("Expand/Collapse")
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
}

```

That's it.  Now you have a collapsible, draggable, panel that you can stuff any content you want into.  You could inject a login component, search box, advertisement, just about anything you want into it.  Just instantiate it, or inject it, or provide a factory method for it, and slide it right in with the rest of your view code.  Factory methods for custom components are easy.  Just return UIComponent and provide parameters that reflect the component's properties.  Instantiate or inject the component inside the factory method, process the arguments, and return the component or one of its children that are of type UIComponent.  See the demo for some examples of this component in action.



---

**Demo**

A demo project is provided that applies Jbuilt to a JSF project, replacing the default view handler with a Jbuilt view handler.  The JSF managed bean facility is replaced with Google Guice for first class injection of controller and model objects into view code.


---

**Road Map**

Although functional, Jbuilt will not be released in its current form.  Anyone who likes the approach can, with relative ease, write an implementation for their chosen Java web framework, ie. Spring MVC, Wicket, etc based on this work.  Code has been uploaded for both the project and the demo. Comments are welcome. gerardstannard 'at' gmail 'dot' com