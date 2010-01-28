package org.jbuilt.components.custom.datatables.simple;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Table;
import org.jbuilt.components.html.raw.Td;
import org.jbuilt.components.html.raw.Th;
import org.jbuilt.components.html.raw.Thead;
import org.jbuilt.components.html.raw.Tr;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

import static org.jbuilt.utils.JbuiltUtils.*;

public class DataTableHead<T extends DataModel<T,?>>  {
	
	public T dataModel;
	public Thead thead;
	public DataTableHeaderRow<T> dataTableHeaderRow;
	public String id;
	public Table parent;
	
	public DataTableHead(T dataModel){
		this.dataModel = dataModel;
		this.dataTableHeaderRow = new DataTableHeaderRow<T>(dataModel);
	}
	
	public DataTableHeaderRow<T> getDataTableHeaderRow(){
		return dataTableHeaderRow;		
	}
	
	public UIComponent getComponent(){
		Thead thead = new Thead();
//		thead.setId(dataModel.get)
		Tr tr = (Tr) dataTableHeaderRow.getComponent();
		thead.getChildren().add(tr);
		this.thead = thead;
		return thead;
	}

	public T getDataModel() {
		return dataModel;
	}
	
}
