package org.jbuilt.components.custom;

import java.util.Map;

import org.jbuilt.utils.Closure;
import org.jbuilt.utils.ValueClosure;
import org.jbuilt.view.componentTree.JsfComponentTreeViewDirector;

import javax.faces.component.UIComponent;


public class PieChart<T extends Map<String, ValueClosure>>
					extends JsfComponentTreeViewDirector implements Chart {
	
	T chartData;
	Closure populateFieldSet;
	UIComponent fieldSetWithInputs;
	String containerId;
	String chartId;
	String title;
	String formId;
	Integer width;
	Integer height;
	
	/**
	 * TODO: make package private to encourage dependency injection
	 */
	public PieChart(T chartData){
		this.chartData = chartData;
	}

	
	
	/**
	 * @return the chartData
	 */
	public T getChartData() {
		return chartData;
	}

	/**
	 * @return the populateFieldSet
	 */
	public Closure getPopulateFieldSet() {
		return populateFieldSet;
	}

	/**
	 * @return the fieldSetWithInputs
	 */
	public UIComponent getFieldSetWithInputs() {
		return fieldSetWithInputs;
	}

	/**
	 * @return the chartId
	 */
	public String getChartId() {
		return chartId;
	}

	/**
	 * @param chartData the chartData to set
	 */
	public void setChartData(T chartData) {
		this.chartData = chartData;
	}

	/**
	 * @param populateFieldSet the populateFieldSet to set
	 */
	public void setPopulateFieldSet(Closure populateFieldSet) {
		this.populateFieldSet = populateFieldSet;
	}

	/**
	 * @param fieldSetWithInputs the fieldSetWithInputs to set
	 */
	public void setFieldSetWithInputs(UIComponent fieldSetWithInputs) {
		this.fieldSetWithInputs = fieldSetWithInputs;
	}

	/**
	 * @param chartId the chartId to set
	 */
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getFormId() {
		return this.formId;
	}

	/**
	 * @return the containerId
	 */
	public String getContainerId() {
		return containerId;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param containerId the containerId to set
	 */
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	
}


