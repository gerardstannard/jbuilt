package index;


import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;


import org.jbuilt.utils.ViewClosure;
import org.jbuiltDemo.managed.annotations._ModularLayoutView;
import org.jbuiltDemo.managed.annotations._UIViewRoot;
import org.jbuiltDemo.managed.view.BaseView;
import org.jbuiltDemo.managed.view.ModularLayoutView;


public class index extends BaseView {
	

	public index(ModularLayoutView viewClosure, 
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
