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

package org.jbuilt.utils;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class RenderUtil {
    public final static void encodeRecursive(FacesContext context,
            UIComponent viewToRender) throws IOException, FacesException {
        if (viewToRender.isRendered()) {
            viewToRender.encodeBegin(context);
            if (viewToRender.getRendersChildren()) {
                viewToRender.encodeChildren(context);
            } else if (viewToRender.getChildCount() > 0) {
                Iterator kids = viewToRender.getChildren().iterator();
                while (kids.hasNext()) {
                    UIComponent kid = (UIComponent) kids.next();
                    encodeRecursive(context, kid);
                }
            }
            viewToRender.encodeEnd(context);
        }
    }
    
//	public Object getConvertedValue(Object newValue, UIComponent component) {
//		// if(newValue == ""){
//		// newValue = "0";
//		// }
//		if (propertyType.isAssignableFrom(Integer.class)) {
//			newValue = Integer.valueOf((String) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(Double.class)) {
//			newValue = Double.valueOf((String) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(int.class)) {
//			newValue = Integer.parseInt((String) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(double.class)) {
//			newValue = Double.parseDouble((String) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(Float.class)) {
//			newValue = Float.valueOf((Float) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(Short.class)) {
//			newValue = Short.valueOf((Short) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(Long.class)) {
//			newValue = Long.valueOf((Long) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(Byte.class)) {
//			newValue = Byte.valueOf((Byte) newValue);
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else if (propertyType.isAssignableFrom(Date.class)) {
//			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			Date date = null;
//			try {
//				date = df.parse((String) newValue);
//				// System.out.println("Today = " + df.format(today));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			newValue = date;
////			setLocalSubmittedValue(newValue, component);
//			return newValue;
//		} else {
//			// if no renderer don't need component argument
////			setLocalSubmittedValue(newValue, component);
//			// ((UIInput) component).setSubmittedValue(newValue, component);
//		}
//		return newValue;
//
//	}


}
