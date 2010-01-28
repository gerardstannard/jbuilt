package index;

import static org.jbuiltDemo.view.css.CSSBuilder.*;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuilt.utils.MethodClosure;
import org.jbuiltDemo.managed.view.BaseViewClosure;

public class ErrorView extends BaseViewClosure  {

	
	public ErrorView(UIViewRoot tree, FacesContext facesContext){
		super(tree, facesContext);
		this.tree = tree;
		this.facesContext = facesContext;
	}


	public MethodClosure defaultAction = new MethodClosure(){
		private static final long serialVersionUID = 2L;
		@Override
		public Object execute(Object... args) {
			System.out.println("page does not exist");
            navigateTo("/org/jbuiltDemo/managed/view/index/index");
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

	public Object doExecute(Object... args) {
		
		UIComponent html = 
		html(
			id("error"),	
			head(
				titleElement("Error: page does not exist"),
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
				jawrStyle( src("/css/main.css"))

			),
			body(  
				div(
					h1(    
						text("I'm sorry, the page requested does not exist.")
					),
						form(   
							id("errorForm"),
							br(),
							input( 
								id("submit"),
								type("submit"),
								action(defaultAction),
								value("home")
							),
	
							br()
						)
					)
			)
		);
		
			
	return html;
	}
	
}
