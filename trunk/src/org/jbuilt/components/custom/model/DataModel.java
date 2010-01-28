package org.jbuilt.components.custom.model;

import java.util.List;

public interface DataModel<T,D> {
	public D getDataObject();
	public List<D> getRowData();
	public List<D> getRowLabels();
	public String getId();
}
