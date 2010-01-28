package org.jbuilt.components.custom.datatables.simple;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Td;
import org.jbuilt.components.html.raw.Th;

public class DataTableCell<T extends DataModel<T,?>>   {
	
	public T dataModel;
	public Td td;
	public String label;
	public String id = "";
	public UIComponent content;
	
	public DataTableCell(T dataModel){
		this.dataModel = dataModel;
		this.label = (String) dataModel.getRowLabels().get(0);
	}
	
	public UIComponent getComponent() {
		Td td = null;
		if(this.td == null){
			td = new Td();
			td.setId(id);		
			this.td = td;
		}
		return td;
	}

	public T getDataModel() {
		return dataModel;
	}
	
	public UIComponent getContent(){
		return content;
	}
	
	public void setContent(UIComponent content){
		this.content = content;
	}
	
}
