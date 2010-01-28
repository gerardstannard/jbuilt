package org.jbuilt.components.custom.datatables.simple;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Td;
import org.jbuilt.components.html.raw.Tr;
import org.jbuilt.utils.ValueClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;
import org.jbuiltDemo.managed.model.CompanyModel;
import org.jbuiltDemo.view.css.CSSBuilder;
import org.jbuiltDemo.view.css.CSSBuilder.Color;

import static org.jbuilt.utils.JbuiltUtils.*;

public class DataTableRow<T extends DataModel<T, ?>> extends JsfComponentTreeViewDirector {
	
	public T dataModel;
	public Tr tr;
	public List<Td> tds;
	public String rowId;
	public List<DataTableCell> cells;
	
	public DataTableRow(T dataModel){
		this.dataModel = dataModel;
		this.tds =  populateTds();
		this.rowId = dataModel.getId();
	}
	
	public List<DataTableCell> populateCells(){
		
		return cells;
	}
	
	public List<Td> populateTds(){
		List<Td> tdsLocal = newArrayList();
		for(Object data : dataModel.getRowData()){
			tdsLocal.add(
				(Td) td(
    					id(rowId+"_"+data),
    					text(data),
    					input(
					        id(rowId+"_"+data+"Input"),
					        type("text"),
					        style("display:none"),
					        value(((CompanyModel) dataModel).dollars1())
    			        )
					)
				);
		}
		return tdsLocal;
	}

	public UIComponent getComponent() {
		Tr tr = (Tr) tr(/*styleClass(CSSBuilder.background(Color.white))*/);
		for(Td td : tds){
			tr.getChildren().add(td);
		}
		return tr;
	}

	public T getDataModel() {
		return dataModel;
	}
	
}
