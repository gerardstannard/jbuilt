package org.jbuilt.components.custom.datatables.simple;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;
import org.jbuilt.components.html.raw.Td; 
import org.jbuilt.components.html.raw.Th;
import org.jbuilt.components.html.raw.Thead;
import org.jbuilt.components.html.raw.Tr;
import org.jbuilt.utils.Closure;
import org.jbuilt.utils.MethodClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

import static org.jbuilt.utils.JbuiltUtils.*;

public class DataTableHeaderRow<T extends DataModel<T,?>> extends DataTableRow {
	public T dataModel;
	public Tr tr;
	public DataTableHeaderRowHead dataTableHeaderRowHead;
	public List<Th> thList;
	public String rowId;
	public Thead parent;
	public DataTable simpleDataTable;
	
	public DataTableHeaderRow(T dataModel){
		super(dataModel);
		this.dataModel = dataModel;
		this.dataTableHeaderRowHead = 
			new DataTableHeaderRowHead<T>(dataModel);
		this.thList = populateThs();
	}
	
	Closure sortCol = new MethodClosure(){
		private static final long serialVersionUID = 1L;
		public Object execute(Object... args){
			System.out.println("sort columns");
			return null;
		}
	};
	
	public List<Th> populateThs(){
		List<Th> thListLocal = newArrayList();
		Th thHead = (Th) dataTableHeaderRowHead.getComponent();
//		thHead.setId(dataTableHeaderRowHead.id);
		thListLocal.add(thHead);
		List<String> rowLabels = (List<String>) dataModel.getRowLabels();
		thHead.getChildren().add(/*a(href("#"),*/text(rowLabels.get(0))/*)*/);
		for(int i=1; i< rowLabels.size(); i++){
			String label = rowLabels.get(i);
			thListLocal.add(
				(Th) th(
						id(rowId+i+"_"+label),
//						a(
//							href(""),
//							onclick("document.getElementById('guessForm:hiddenButton').click();"),
							text(label)//,
//							jawrImg(
//								style(border("none")),
//								src("/img/arrow-up.gif")
//							)
//						)//,
//						input(
//							id("hiddenButton"+i),
//							action(sortCol),
//							type("submit"),
//							style("display:none")
//						)
					)
				);
		}
		return thListLocal;
	}
	
	@Override
	public UIComponent getComponent(){
		Tr tr = null;
		if(this.tr == null){
			tr = (Tr) tr(
					id(rowId)
					);
			for(Th th : thList){
				tr.getChildren().add(th);
			}
			this.tr = tr;
		} else {
			for(Th th : thList){
				this.tr.getChildren().add(th);
			}
		}
		
		return this.tr;
	}
	
}
