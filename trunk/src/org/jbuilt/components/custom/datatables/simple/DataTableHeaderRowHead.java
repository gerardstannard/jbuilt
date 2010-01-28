package org.jbuilt.components.custom.datatables.simple;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Th;

public class DataTableHeaderRowHead<T extends DataModel<T,?>>   {
	
	public T dataModel;
	public Th th;
	public String label;
	public String id = "headerRowHead";
	
	public DataTableHeaderRowHead(T dataModel){
		this.dataModel = dataModel;
		this.label = (String) dataModel.getRowLabels().get(0);
	}
	
	public UIComponent getComponent() {
		Th th = null;
		if(this.th == null){
			th = new Th();
			th.setId(id);		
			this.th = th;
		}
		return th;
	}

	public T getDataModel() {
		return dataModel;
	}
	
}
