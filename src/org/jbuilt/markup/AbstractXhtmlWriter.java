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
package org.jbuilt.markup;

import java.io.IOException;
import java.io.Writer;

import javax.faces.context.ResponseWriter;

public abstract class AbstractXhtmlWriter extends ResponseWriter {
	
	private Writer writer;
	private Boolean isScriptHidingEnabled;
	private Boolean isScriptInAttributeValueEnabled;
	
	public AbstractXhtmlWriter() {
	}
	
	public AbstractXhtmlWriter(Writer writer){
		this.writer = writer;
	}

	public AbstractXhtmlWriter(Writer writer, Boolean isScriptHidingEnabled,
			Boolean isScriptInAttributeValueEnabled){
		this.writer = writer;
		this.isScriptHidingEnabled = isScriptHidingEnabled;
		this.isScriptInAttributeValueEnabled = isScriptInAttributeValueEnabled;
		
	}
	
	@Override
  public abstract String getContentType();

	@Override
  public abstract String getCharacterEncoding();

	@Override
  public abstract void flush() throws IOException;

	@Override
  public abstract void startDocument() throws IOException;

	@Override
  public abstract void endDocument() throws IOException;

	public abstract void startElement(String name) throws IOException;

	@Override
  public abstract void endElement(String name) throws IOException;

	public abstract void writeAttribute(String name, Object value)
			throws IOException;

	public abstract void writeURIAttribute(String name, Object value)
			throws IOException;

	@Override
  public abstract void writeComment(Object comment) throws IOException;

	public abstract void writeText(Object text) throws IOException;

	@Override
  public void writeText(Object text, String element) throws IOException {
		writeText(text);
	}

	@Override
  public abstract void writeText(char ac[], int i, int j) throws IOException;

	@Override
  public abstract AbstractXhtmlWriter cloneWithWriter(Writer writer);
}
