/*
 *   Copyright 2010 Gerard Stannard
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 */

/**
 * 
 * @author Gerard Stannard
 *
 */

package org.jbuiltDemo.managed.model;

import static org.jbuilt.utils.JbuiltUtils.*;

import java.util.List;

import org.jbuilt.components.custom.model.DataModel;

public class ObjectMother {
	
	private static final String Q1 = "Q1";
	private static final String Q2 = "Q2";
	private static final String Q3 = "Q3";
	private static final String Q4 = "Q4";

	public static Company createCompany(){
		Company company = new Company();
		return company;

	}
	
	public static <T>SalesFigures<T> createSalesFigures(){
		SalesFigures<T> salesFigures = new SalesFigures<T>();
		return salesFigures;

	}
	
	
	public static CompanyModel createCompanyModel(){
		
		CompanyModel companyModel = new CompanyModel();
		
		return companyModel;
	}
	
	
	public static List<DataModel<Company, String>> createCompanyModelData(){
		
		List<DataModel<Company, String>> companyModelData = newArrayList();
		
		CompanyModel aModel = createCompanyModel();
		Company<String> a = createCompany();
		SalesFigures asf1 = createSalesFigures();
		SalesFigures asf2 = createSalesFigures();
		SalesFigures asf3 = createSalesFigures();
		SalesFigures asf4 = createSalesFigures();
		
		
		
		aModel.id = a.id = "1";
		aModel.name = a.name = "Company A";
		aModel.quarter1 = asf1.quarter = Q1;
		aModel.dollars1 = asf1.dollars = "100";
		aModel.quarter2 = asf2.quarter = Q2;
		aModel.dollars2 = asf2.dollars = "942";
		aModel.quarter3 = asf3.quarter = Q3;
		aModel.dollars3 = asf3.dollars = "224";
		aModel.quarter4 = asf4.quarter = Q4;
		aModel.dollars4 = asf4.dollars = "486";
		a.salesFiguresListGeneric = newArrayList();
		a.salesFiguresListGeneric.add(asf1);
		a.salesFiguresListGeneric.add(asf2);
		a.salesFiguresListGeneric.add(asf3);
		a.salesFiguresListGeneric.add(asf4);
		
		
		companyModelData.add(aModel);
		
		
	
		// company b

		CompanyModel bModel = createCompanyModel();
		Company b = createCompany();
		SalesFigures bsf1 = createSalesFigures();
		SalesFigures bsf2 = createSalesFigures();
		SalesFigures bsf3 = createSalesFigures();
		SalesFigures bsf4 = createSalesFigures();
		
		
		
		bModel.id = b.id = "2";
		bModel.name = b.name = "Company B";
		bModel.quarter1 = bsf1.quarter = Q1;
		bModel.dollars1 = bsf1.dollars = "56";
		bModel.quarter2 = bsf2.quarter = Q2;
		bModel.dollars2 = bsf2.dollars = "437";
		bModel.quarter3 = bsf3.quarter = Q3;
		bModel.dollars3 = bsf3.dollars = "235";
		bModel.quarter4 = bsf4.quarter = Q4;
		bModel.dollars4 = bsf4.dollars = "867";
		b.salesFiguresListGeneric = newArrayList();
		b.salesFiguresListGeneric.add(bsf1);
		b.salesFiguresListGeneric.add(bsf2);
		b.salesFiguresListGeneric.add(bsf3);
		b.salesFiguresListGeneric.add(bsf4);
		
		
		companyModelData.add(bModel);
		
		
		

		// Company c
		
		CompanyModel cModel = createCompanyModel();
		Company c = createCompany();
		SalesFigures csf1 = createSalesFigures();
		SalesFigures csf2 = createSalesFigures();
		SalesFigures csf3 = createSalesFigures();
		SalesFigures csf4 = createSalesFigures();
		
		
		
		cModel.id = c.id = "3";
		cModel.name = c.name = "Company C";
		cModel.quarter1 = csf1.quarter = Q1;
		cModel.dollars1 = csf1.dollars = "23";
		cModel.quarter2 = csf2.quarter = Q2;
		cModel.dollars2 = csf2.dollars = "347";
		cModel.quarter3 = csf3.quarter = Q3;
		cModel.dollars3 = csf3.dollars = "956";
		cModel.quarter4 = csf4.quarter = Q4;
		cModel.dollars4 = csf4.dollars = "567";
		c.salesFiguresListGeneric = newArrayList();
		c.salesFiguresListGeneric.add(csf1);
		c.salesFiguresListGeneric.add(csf2);
		c.salesFiguresListGeneric.add(csf3);
		c.salesFiguresListGeneric.add(csf4);
		
		
		companyModelData.add(cModel);

		
		// Company d

		CompanyModel dModel = createCompanyModel();
		Company d = createCompany();
		SalesFigures dsf1 = createSalesFigures();
		SalesFigures dsf2 = createSalesFigures();
		SalesFigures dsf3 = createSalesFigures();
		SalesFigures dsf4 = createSalesFigures();
		
		
		
		dModel.id = d.id = "4";
		dModel.name = d.name = "Company D";
		dModel.quarter1 = dsf1.quarter = Q1;
		dModel.dollars1 = dsf1.dollars = "75";
		dModel.quarter2 = dsf2.quarter = Q2;
		dModel.dollars2 = dsf2.dollars = "635";
		dModel.quarter3 = dsf3.quarter = Q3;
		dModel.dollars3 = dsf3.dollars = "574";
		dModel.quarter4 = dsf4.quarter = Q4;
		dModel.dollars4 = dsf4.dollars = "345";
		d.salesFiguresListGeneric = newArrayList();
		d.salesFiguresListGeneric.add(dsf1);
		d.salesFiguresListGeneric.add(dsf2);
		d.salesFiguresListGeneric.add(dsf3);
		d.salesFiguresListGeneric.add(dsf4);
		
		companyModelData.add(dModel);
		
		
		// Company e

		CompanyModel eModel = createCompanyModel();
		Company e = createCompany();
		SalesFigures esf1 = createSalesFigures();
		SalesFigures esf2 = createSalesFigures();
		SalesFigures esf3 = createSalesFigures();
		SalesFigures esf4 = createSalesFigures();
		
		
		eModel.id = e.id = "5";
		eModel.name = e.name = "Company E";
		eModel.quarter1 = esf1.quarter = Q1;
		eModel.dollars1 = esf1.dollars = "115";
		eModel.quarter2 = esf2.quarter = Q2;
		eModel.dollars2 = esf2.dollars = "864";
		eModel.quarter3 = esf3.quarter = Q3;
		eModel.dollars3 = esf3.dollars = "546";
		eModel.quarter4 = esf4.quarter = Q4;
		eModel.dollars4 = esf4.dollars = "421";
		e.salesFiguresListGeneric = newArrayList();
		e.salesFiguresListGeneric.add(esf1);
		e.salesFiguresListGeneric.add(esf2);
		e.salesFiguresListGeneric.add(esf3);
		e.salesFiguresListGeneric.add(esf4);
		
		
		companyModelData.add(eModel);
		
		// Company e

		CompanyModel fModel = createCompanyModel();
		Company f = createCompany();
		SalesFigures fsf1 = createSalesFigures();
		SalesFigures fsf2 = createSalesFigures();
		SalesFigures fsf3 = createSalesFigures();
		SalesFigures fsf4 = createSalesFigures();
		
		
		fModel.id = f.id = "6";
		fModel.name = f.name = "Company F";
		fModel.quarter1 = fsf1.quarter = Q1;
		fModel.dollars1 = fsf1.dollars = "30";
		fModel.quarter2 = fsf2.quarter = Q2;
		fModel.dollars2 = fsf2.dollars = "147";
		fModel.quarter3 = fsf3.quarter = Q3;
		fModel.dollars3 = fsf3.dollars = "698";
		fModel.quarter4 = fsf4.quarter = Q4;
		fModel.dollars4 = fsf4.dollars = "654";
		f.salesFiguresListGeneric = newArrayList();
		f.salesFiguresListGeneric.add(fsf1);
		f.salesFiguresListGeneric.add(fsf2);
		f.salesFiguresListGeneric.add(fsf3);
		f.salesFiguresListGeneric.add(fsf4);
		
		
		companyModelData.add(fModel);
		
		return  companyModelData;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
