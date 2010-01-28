package org.jbuilt.components.custom.datatables.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Table;
import org.jbuilt.components.html.raw.Tbody;
import org.jbuilt.components.html.raw.Td;
import org.jbuilt.components.html.raw.Tfoot;
import org.jbuilt.components.html.raw.Th;
import org.jbuilt.components.html.raw.Thead;
import org.jbuilt.components.html.raw.Tr;
import org.jbuilt.utils.ValueClosure;
import org.jbuilt.utils.ViewClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;
import org.jbuiltDemo.managed.model.CompanyModel;

import static org.jbuilt.utils.JbuiltUtils.*;

public class SimpleDataTable<T extends List<DataModel<T,?>>> extends JsfComponentTreeViewDirector 
						implements DataTable {
	
	// factory method arguments
	T dataModelList;
	Table table;
	Thead thead;
	Tbody tbody;
	String tableId = "sales";
	String summaryText = "Quarterly Sales (stated in millions of dollars)";
	String captionText = "Quarterly Sales*";
	ValueClosure titleProperty;
	String thStyleClass = "asc";
	String headerRowTitle = "Companies";
	
	// structural elements
	DataTableHead dataTableHead;
	DataTableBody dataTableBody;
	List<DataTableRow> dataTableRows;
	List<? extends UIComponent> headers;
	
	// sorting
	Th lastSortedTh;
	
	
//	public DataTable(List<?> list, String summary, String caption){
	
		public SimpleDataTable(T dataModelList){
//		this.list = list;
//		this.summary = summary;
//		this.caption = caption;
		this.dataModelList = dataModelList;
		this.dataTableHead = new DataTableHead((DataModel) dataModelList.get(0));
		this.thead = (Thead) dataTableHead.getComponent();
		this.dataTableRows = (List<DataTableRow>) populateTableRows();
		this.dataTableBody = new DataTableBody(dataTableRows);
		this.tbody = (Tbody) dataTableBody.getComponent();
		Pattern sortDirection = Pattern.compile("asc|dsc");
		Tr headerRow = (Tr) thead.getChildren().get(0);
		headers = (List<Th>)((List<?>)headerRow.getChildren());
//		for(UIComponent th  : headers){
//			//TODO: some iteration here causes NPE
//			String thStyle = ((Th) th).getStyleClass();
//			if(thStyle != null){
//				if(sortDirection.matcher(thStyle) != null){
//					this.lastSortedTh = (Th) th;
//				}
//			}
//		}
	}
		
	private void makeSortable(){
	}
		
	private List<DataTableRow> populateTableRows(){
		dataTableRows = newArrayList();
		for(DataModel<T, ?> dataModel : dataModelList) {
			dataTableRows.add(new DataTableRow(dataModel));
		}
		return (List<DataTableRow>) dataTableRows;
	}
	
	public UIComponent writeView(){
		
		UIComponent table = 
		    table(
    			id(tableId), 
    			summary(summaryText),
    			caption(   
    				text(captionText)
    			),
    			thead,
    			tbody
			);
			this.table = (Table) table;
			return  table;
	}
	


	public UIComponent getComponent() {
		return table;
	}

	public List<T> getDataModelList() {
		return (List<T>) dataModelList;
	}


}
