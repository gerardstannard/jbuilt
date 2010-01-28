package org.jbuilt.components.custom.datatables.simple;

import java.util.List;

import javax.faces.component.UIComponent;

import org.jbuilt.components.custom.model.DataModel;

public interface DataTable<T extends List<DataModel<T, ?>>> {
	UIComponent getComponent();
	T getDataModelList();
}
