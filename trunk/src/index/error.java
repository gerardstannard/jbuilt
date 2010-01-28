package index;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.jbuiltDemo.managed.view.BaseView;

public class error extends BaseView {

	public error(ErrorView viewClosure, 
					FacesContext facesContext, 
					UIViewRoot rootOrComponent) {
		super(viewClosure, facesContext, rootOrComponent);
	}
		
	@Override
	// optional
	public void beforeWriteView(){
		super.beforeWriteView();
		log("calling super on " + this.getClass().getSimpleName() + ", then overriding, before write view");
		log("using ModularLayoutView as the home page");

	}
	
	@Override
	public void afterWriteView(){
		// optional
		super.afterWriteView();
		log("calling super on " + this.getClass().getSimpleName() + ", then overriding, after write view");
	}

	
	
}
