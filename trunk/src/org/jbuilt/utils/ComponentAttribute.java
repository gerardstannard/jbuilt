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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ComponentAttribute<K, V extends Object> implements Attribute<String, V> {
	private String key;
	private V value;
	
	public ComponentAttribute(String key, V value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	private static final Log LOG = LogFactory.getLog(ComponentAttribute.class);

	
    /**
     * Generic reflection-based equals
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	if (LOG.isWarnEnabled()) {
    	//	LOG.warn("Remember to over-ride equals for " + ClassUtils.getShortClassName(getClass()) + "!");
    	}
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * Generic reflection-based hashCode
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	if (LOG.isWarnEnabled()) {
    	//	LOG.warn("Remember to over-ride hashcode " + ClassUtils.getShortClassName(getClass()) + "!");
    	}
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Generic reflection-based toString.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	// TODO: change to not use reflection toString
    	if (LOG.isWarnEnabled()) {
    		//LOG.warn("Remember to over-ride toString " + ClassUtils.getShortClassName(getClass()) + "!");
    	}
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

}
