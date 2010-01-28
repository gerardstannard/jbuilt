package org.jbuilt.components.custom.datatables.simple;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Table;
import org.jbuilt.components.html.raw.Tbody;
import org.jbuilt.components.html.raw.Td;
import org.jbuilt.components.html.raw.Th;
import org.jbuilt.components.html.raw.Thead;
import org.jbuilt.components.html.raw.Tr;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

import static org.jbuilt.utils.JbuiltUtils.*;

public class DataTableBody<T extends DataModel<T, ?>>  {
	
	public T dataModel;
	public List<DataTableRow<T>> dataTableRows;
	public Tbody tbody;
	public String id;
	public Table parent;
	
	public DataTableBody(List<DataTableRow<T>> dataTableRows){
		this.dataTableRows = dataTableRows;
	}
	
	public UIComponent getComponent(){
		Tbody tbody = new Tbody();
		for(DataTableRow<T> row : dataTableRows){
			tbody.getChildren().add(row.getComponent());
		}
		this.tbody = tbody;
		return tbody;
	}

	public T getDataModel() {
		return dataModel;
	}
	
}
