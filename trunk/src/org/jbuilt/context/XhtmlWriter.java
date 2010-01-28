/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jbuilt.context;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jbuilt.markup.AbstractXhtmlWriter;

import com.sun.faces.config.WebConfiguration;
import com.sun.faces.io.FastStringWriter;
import com.sun.faces.util.HtmlUtils;
import com.sun.faces.util.MessageUtils;

/**
 * @author Gerard Stananrd
 * 
 */

/**
 * this file has been renamed from HtmlResponseWrier and modified to
 * fit within the jbuilt architecture original authors are below
 * 
 * @author Manfred Geiler (latest modification by $Author: mmarinschek $)
 * @author Anton Koinov
 * @version $Revision: 511461 $ $Date: 2007-02-25 03:10:54 -0500 (Sun, 25 Feb 2007) $
 */
public class XhtmlWriter extends AbstractXhtmlWriter {

	private String contentType = "application/xhtml+xml";
	private String encoding;
	private Writer writer;
	private boolean closeStart;
	private WebConfiguration.DisableUnicodeEscaping disableUnicodeEscaping;
	private boolean escapeUnicode=false;
	private boolean escapeIso=false;
	private boolean dontEscape;
	private boolean writingCdata;
	private boolean isCdata;
	private boolean isScript;
	private boolean isStyle;
	private boolean scriptOrStyleSrc;
	private boolean isXhtml;
	private Writer origWriter;
	private FastStringWriter scriptBuffer;
	private FastStringWriter attributesBuffer;
	private Boolean isScriptHidingEnabled;
	private Boolean isScriptInAttributeValueEnabled;
	private char buffer[];
	private char textBuffer[];
	private char charHolder[];
	static final Pattern CDATA_START_SLASH_SLASH = Pattern
			.compile("^//\\s*\\Q<![CDATA[\\E");
	static final Pattern CDATA_END_SLASH_SLASH = Pattern
			.compile("//\\s*\\Q]]>\\E$");
	static final Pattern CDATA_START_SLASH_STAR = Pattern
			.compile("^/\\*\\s*\\Q<![CDATA[\\E\\s*\\*/");
	static final Pattern CDATA_END_SLASH_STAR = Pattern
			.compile("/\\*\\s*\\Q]]>\\E\\s*\\*/$");

	/**
	 * 
	 */
	public XhtmlWriter() {
		super();
	}

	public XhtmlWriter(Writer writer) throws FacesException {
		this(writer, null, null);
	}

	public XhtmlWriter(Writer writer, Boolean isScriptHidingEnabled,
			Boolean isScriptInAttributeValueEnabled) throws FacesException {
		this.writer = null;
		buffer = new char[1028];
		textBuffer = new char[128];
		charHolder = new char[1];
		this.writer = writer;
		if (isScriptHidingEnabled == null) {
			isScriptHidingEnabled = Boolean.FALSE;
		}
		if (isScriptInAttributeValueEnabled == null) {
			isScriptInAttributeValueEnabled = Boolean.FALSE;
		}
		this.isScriptHidingEnabled = isScriptHidingEnabled;
		this.isScriptInAttributeValueEnabled = isScriptInAttributeValueEnabled;
		attributesBuffer = new FastStringWriter(1024);
	}

	@SuppressWarnings("unused")
	private WebConfiguration getWebConfiguration(WebConfiguration webConfig) {
		if (webConfig != null) {
      return webConfig;
    }
		FacesContext context = FacesContext.getCurrentInstance();
		if (null != context) {
			javax.faces.context.ExternalContext extContext = context
					.getExternalContext();
			if (null != extContext) {
        webConfig = WebConfiguration.getInstance(extContext);
      }
		}
		return webConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbuilt.context.AbstractXhtmlWriter#cloneWithWriter(java.io.Writer)
	 */
	@Override
	public AbstractXhtmlWriter cloneWithWriter(Writer writer) {
		try {
			return new XhtmlWriter(writer, isScriptHidingEnabled,
					isScriptInAttributeValueEnabled);
		} catch (FacesException e) {
			throw new IllegalStateException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#endDocument()
	 */
	@Override
	public void endDocument() throws IOException {
		writer.flush();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#endElement(java.lang.String)
	 */
	@Override
	public void endElement(String name) throws IOException {
		if (name == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "name" }));
    }
		if (!writingCdata) {
      dontEscape = false;
    }
		isXhtml = getContentType().equals("application/xhtml+xml");
		if (isScriptOrStyle(name) && !scriptOrStyleSrc
				&& writer instanceof FastStringWriter) {
			String result = ((FastStringWriter) writer).getBuffer().toString();
			writer = origWriter;
			if (result != null) {
				String trim = result.trim();
				if (isXhtml) {
					if (isScript) {
						Matcher cdataStartSlashSlash = CDATA_START_SLASH_SLASH
								.matcher(trim);
						Matcher cdataEndSlashSlash = CDATA_END_SLASH_SLASH
								.matcher(trim);
						Matcher cdataStartSlashStar = CDATA_START_SLASH_STAR
								.matcher(trim);
						Matcher cdataEndSlashStar = CDATA_END_SLASH_STAR
								.matcher(trim);
						int trimLen = trim.length();
						if (cdataStartSlashSlash.find()
								&& cdataEndSlashSlash.find()) {
							int start = cdataStartSlashSlash.end()
									- cdataStartSlashSlash.start();
							int end = trimLen
									- (cdataEndSlashSlash.end() - cdataEndSlashSlash
											.start());
							writer.write(trim.substring(start, end));
						} else if (null != cdataStartSlashSlash.reset()
								&& cdataStartSlashSlash.find()
								&& cdataEndSlashStar.find()) {
							int start = cdataStartSlashSlash.end()
									- cdataStartSlashSlash.start();
							int end = trimLen
									- (cdataEndSlashStar.end() - cdataEndSlashStar
											.start());
							writer.write(trim.substring(start, end));
						} else if (cdataStartSlashStar.find()
								&& null != cdataEndSlashStar.reset()
								&& cdataEndSlashStar.find()) {
							int start = cdataStartSlashStar.end()
									- cdataStartSlashStar.start();
							int end = trimLen
									- (cdataEndSlashStar.end() - cdataEndSlashStar
											.start());
							writer.write(trim.substring(start, end));
						} else if (null != cdataStartSlashStar.reset()
								&& cdataStartSlashStar.find()
								&& null != cdataEndSlashStar.reset()
								&& cdataEndSlashSlash.find()) {
							int start = cdataStartSlashStar.end()
									- cdataStartSlashStar.start();
							int end = trimLen
									- (cdataEndSlashSlash.end() - cdataEndSlashSlash
											.start());
							writer.write(trim.substring(start, end));
						} else {
							writer.write(result);
						}
					} else if (trim.startsWith("<![CDATA[")
							&& trim.endsWith("]]>")) {
            writer.write(trim.substring(9, trim.length() - 3));
          } else {
            writer.write(result);
          }
				} else if (trim.startsWith("<!--") && trim.endsWith("//-->")) {
          writer.write(trim.substring(4, trim.length() - 5));
        } else {
          writer.write(result);
        }
			}
			if (isXhtml) {
				if (!writingCdata) {
          if (isScript) {
            writer.write("\n//]]>\n");
          } else {
            writer.write("\n]]>\n");
          }
        }
			} else if (isScriptHidingEnabled.booleanValue()) {
        writer.write("\n//-->\n");
      }
		}
		isScript = false;
		isStyle = false;
		if ("cdata".equalsIgnoreCase(name)) {
			writer.write("]]>");
			writingCdata = false;
			isCdata = false;
			dontEscape = false;
			return;
		}
		if (closeStart) {
			boolean isEmptyElement = HtmlUtils.isEmptyElement(name);
			if (isEmptyElement) {
				flushAttributes();
				writer.write(" />");
				closeStart = false;
				return;
			}
			flushAttributes();
			writer.write(62);
			closeStart = false;
		}
		writer.write("</");
		writer.write(name);
		writer.write(62);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#flush()
	 */
	@Override
	public void flush() throws IOException {
		closeStartIfNecessary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		return encoding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#getContentType()
	 */
	@Override
	public String getContentType() {
		return contentType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#startDocument()
	 */
	@Override
	public void startDocument() throws IOException {
		// do nothing

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbuilt.context.AbstractXhtmlWriter#startElement(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void startElement(String name) throws IOException {
		if (name == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "name" }));
    }
		closeStartIfNecessary();
		isScriptOrStyle(name);
		scriptOrStyleSrc = false;
		if ("cdata".equalsIgnoreCase(name)) {
			isCdata = true;
			writingCdata = true;
			dontEscape = true;
			writer.write("<![CDATA[");
			closeStart = false;
			return;
		}
		if (writingCdata) {
			isCdata = false;
			writingCdata = true;
			dontEscape = true;
		}
		writer.write(60);
		writer.write(name);
		closeStart = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbuilt.context.AbstractXhtmlWriter#writeAttribute(java.lang.String,
	 * java.lang.Object, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void writeAttribute(String name, Object value) throws IOException {
		if (name == null) {
      throw new NullPointerException(" "+name+"is null");
    }
		if (value == null){
			throw new NullPointerException(" value is null");

		}
		if (isCdata) {
      return;
    }
		if (name.equalsIgnoreCase("src") && isScriptOrStyle()) {
      scriptOrStyleSrc = true;
    }
		Class valueClass = value.getClass();
		if (valueClass == Boolean.class) {
			if (Boolean.TRUE.equals(value)) {
				attributesBuffer.write(32);
				attributesBuffer.write(name);
				attributesBuffer.write("=\"");
				attributesBuffer.write(name);
				attributesBuffer.write(34);
			}
		} else {
			attributesBuffer.write(32);
			attributesBuffer.write(name);
			attributesBuffer.write("=\"");
			String val = value.toString();
			ensureTextBufferCapacity(val);
			HtmlUtils.writeAttribute(attributesBuffer, escapeUnicode,
					escapeIso, buffer, val, textBuffer,
					isScriptInAttributeValueEnabled.booleanValue());
			attributesBuffer.write(34);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbuilt.context.AbstractXhtmlWriter#writeComment(java.lang.Object)
	 */
	@Override
	public void writeComment(Object comment) throws IOException {
		if (comment == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[0]));
    }
		if (writingCdata) {
			return;
		} else {
			closeStartIfNecessary();
			writer.write("<!--");
			writer.write(comment.toString());
			writer.write("-->");
			return;
		}
	}

	public void writeText(char text) throws IOException {
		closeStartIfNecessary();
		if (dontEscape) {
			writer.write(text);
		} else {
			charHolder[0] = text;
			HtmlUtils.writeText(writer, escapeUnicode, escapeIso, buffer,
					charHolder);
		}
	}

	public void writeText(char text[]) throws IOException {
		if (text == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "text" }));
    }
		closeStartIfNecessary();
		if (dontEscape) {
      writer.write(text);
    } else {
      HtmlUtils.writeText(writer, escapeUnicode, escapeIso, buffer, text);
    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#writeText(java.lang.Object,
	 * java.lang.String)
	 */
	@Override
	public void writeText(Object text, String propertyName) throws IOException {
		if (text == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "text" }));
    }
		closeStartIfNecessary();
		if (dontEscape) {
			writer.write(text.toString());
		} else {
			String val = text.toString();
			ensureTextBufferCapacity(val);
			HtmlUtils.writeText(writer, escapeUnicode, escapeIso, buffer, val,
					textBuffer);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbuilt.context.AbstractXhtmlWriter#writeText(char[], int, int)
	 */
	@Override
	public void writeText(char[] text, int off, int len) throws IOException {
		if (text == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "text" }));
    }
		if (off < 0 || off > text.length || len < 0 || len > text.length) {
      throw new IndexOutOfBoundsException();
    }
		closeStartIfNecessary();
		if (dontEscape) {
      writer.write(text, off, len);
    } else {
      HtmlUtils.writeText(writer, escapeUnicode, escapeIso, buffer, text,
					off, len);
    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbuilt.context.AbstractXhtmlWriter#writeURIAttribute(java.lang.String
	 * , java.lang.Object, java.lang.String)
	 */
	@Override
	public void writeURIAttribute(String name, Object value)
			throws IOException {
		if (name == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "name" }));
    }
		if (value == null) {
      throw new NullPointerException(MessageUtils
					.getExceptionMessageString(
							"com.sun.faces.NULL_PARAMETERS_ERROR",
							new Object[] { "value" }));
    }
		if (isCdata) {
      return;
    }
		if (name.equalsIgnoreCase("src") && isScriptOrStyle()) {
      scriptOrStyleSrc = true;
    }
		attributesBuffer.write(32);
		attributesBuffer.write(name);
		attributesBuffer.write("=\"");
		String stringValue = value.toString();
		ensureTextBufferCapacity(stringValue);
		if (stringValue.startsWith("javascript:")) {
      HtmlUtils.writeAttribute(attributesBuffer, escapeUnicode,
					escapeIso, buffer, stringValue, textBuffer,
					isScriptInAttributeValueEnabled.booleanValue());
    } else {
      HtmlUtils.writeURL(attributesBuffer, stringValue, textBuffer,
					encoding, getContentType());
    }
		attributesBuffer.write(34);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Writer#close()
	 */
	@Override
	public void close() throws IOException {
		closeStartIfNecessary();
		writer.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Writer#write(char[], int, int)
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
        closeStartIfNecessary();
        writer.write(cbuf, off, len);

	}

	@Override
  public void write(char cbuf[]) throws IOException {
		closeStartIfNecessary();
		writer.write(cbuf);
	}

	@Override
  public void write(int c) throws IOException {
		closeStartIfNecessary();
		writer.write(c);
	}

	@Override
  public void write(String str) throws IOException {
		closeStartIfNecessary();
		writer.write(str);
	}
	
	@Override
  public void write(String str, int off, int len) throws IOException {
		closeStartIfNecessary();
		writer.write(str, off, len);
	}
	
	@Override
	public void writeText(Object text) throws IOException {
		closeStartIfNecessary();
		writer.write((String)text);

	}


	private void ensureTextBufferCapacity(String source) {
		int len = source.length();
		if (textBuffer.length < len) {
      textBuffer = new char[len * 2];
    }
	}

	public void closeStartIfNecessary() throws IOException {
		if (closeStart) {
			flushAttributes();
			writer.write(62);
			closeStart = false;
			if (isScriptOrStyle() && !scriptOrStyleSrc) {
				isXhtml = getContentType().equals("application/xhtml+xml");
				if (isXhtml) {
					if (!writingCdata) {
            if (isScript) {
              writer.write("\n//<![CDATA[\n");
            } else {
              writer.write("\n<![CDATA[\n");
            }
          }
				} else if (isScriptHidingEnabled.booleanValue()) {
          writer.write("\n<!--\n");
        }
				origWriter = writer;
				if (scriptBuffer == null) {
          scriptBuffer = new FastStringWriter(1024);
        }
				scriptBuffer.reset();
				writer = scriptBuffer;
				isScript = false;
				isStyle = false;
			}
		}
	}

	public void flushAttributes() throws IOException {
		StringBuilder b = attributesBuffer.getBuffer();
		int totalLength = b.length();
		if (totalLength != 0) {
			for (int curIdx = 0; curIdx < totalLength;) {
        if (totalLength - curIdx > buffer.length) {
					int end = curIdx + buffer.length;
					b.getChars(curIdx, end, buffer, 0);
					writer.write(buffer);
					curIdx += buffer.length;
				} else {
					int len = totalLength - curIdx;
					b.getChars(curIdx, curIdx + len, buffer, 0);
					writer.write(buffer, 0, len);
					curIdx += len;
				}
      }
			attributesBuffer.reset();
		}
	}

	private boolean isScriptOrStyle(String name) {
		if ("script".equalsIgnoreCase(name)) {
			isScript = true;
			dontEscape = true;
		} else if ("style".equalsIgnoreCase(name)) {
			isStyle = true;
			dontEscape = true;
		} else {
			isScript = false;
			isStyle = false;
			dontEscape = false;
		}
		return isScript || isStyle;
	}

	private boolean isScriptOrStyle() {
		return isScript || isStyle;
	}
	
	@Override
  public String toString(){
		return writer.toString();
	}

	public StringBuilder getAttributesBuffer() {
		return attributesBuffer.getBuffer();
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the writer
	 */
	public Writer getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * @return the closeStart
	 */
	public boolean isCloseStart() {
		return closeStart;
	}

	/**
	 * @param closeStart the closeStart to set
	 */
	public void setCloseStart(boolean closeStart) {
		this.closeStart = closeStart;
	}

	/**
	 * @return the disableUnicodeEscaping
	 */
	public WebConfiguration.DisableUnicodeEscaping getDisableUnicodeEscaping() {
		return disableUnicodeEscaping;
	}

	/**
	 * @param disableUnicodeEscaping the disableUnicodeEscaping to set
	 */
	public void setDisableUnicodeEscaping(
			WebConfiguration.DisableUnicodeEscaping disableUnicodeEscaping) {
		this.disableUnicodeEscaping = disableUnicodeEscaping;
	}

	/**
	 * @return the escapeUnicode
	 */
	public boolean isEscapeUnicode() {
		return escapeUnicode;
	}

	/**
	 * @param escapeUnicode the escapeUnicode to set
	 */
	public void setEscapeUnicode(boolean escapeUnicode) {
		this.escapeUnicode = escapeUnicode;
	}

	/**
	 * @return the escapeIso
	 */
	public boolean isEscapeIso() {
		return escapeIso;
	}

	/**
	 * @param escapeIso the escapeIso to set
	 */
	public void setEscapeIso(boolean escapeIso) {
		this.escapeIso = escapeIso;
	}

	/**
	 * @return the dontEscape
	 */
	public boolean isDontEscape() {
		return dontEscape;
	}

	/**
	 * @param dontEscape the dontEscape to set
	 */
	public void setDontEscape(boolean dontEscape) {
		this.dontEscape = dontEscape;
	}

	/**
	 * @return the writingCdata
	 */
	public boolean isWritingCdata() {
		return writingCdata;
	}

	/**
	 * @param writingCdata the writingCdata to set
	 */
	public void setWritingCdata(boolean writingCdata) {
		this.writingCdata = writingCdata;
	}

	/**
	 * @return the isCdata
	 */
	public boolean isCdata() {
		return isCdata;
	}

	/**
	 * @param isCdata the isCdata to set
	 */
	public void setCdata(boolean isCdata) {
		this.isCdata = isCdata;
	}

	/**
	 * @return the isScript
	 */
	public boolean isScript() {
		return isScript;
	}

	/**
	 * @param isScript the isScript to set
	 */
	public void setScript(boolean isScript) {
		this.isScript = isScript;
	}

	/**
	 * @return the isStyle
	 */
	public boolean isStyle() {
		return isStyle;
	}

	/**
	 * @param isStyle the isStyle to set
	 */
	public void setStyle(boolean isStyle) {
		this.isStyle = isStyle;
	}

	/**
	 * @return the scriptOrStyleSrc
	 */
	public boolean isScriptOrStyleSrc() {
		return scriptOrStyleSrc;
	}

	/**
	 * @param scriptOrStyleSrc the scriptOrStyleSrc to set
	 */
	public void setScriptOrStyleSrc(boolean scriptOrStyleSrc) {
		this.scriptOrStyleSrc = scriptOrStyleSrc;
	}

	/**
	 * @return the isXhtml
	 */
	public boolean isXhtml() {
		return isXhtml;
	}

	/**
	 * @param isXhtml the isXhtml to set
	 */
	public void setXhtml(boolean isXhtml) {
		this.isXhtml = isXhtml;
	}

	/**
	 * @return the origWriter
	 */
	public Writer getOrigWriter() {
		return origWriter;
	}

	/**
	 * @param origWriter the origWriter to set
	 */
	public void setOrigWriter(Writer origWriter) {
		this.origWriter = origWriter;
	}

	/**
	 * @return the scriptBuffer
	 */
	public FastStringWriter getScriptBuffer() {
		return scriptBuffer;
	}

	/**
	 * @param scriptBuffer the scriptBuffer to set
	 */
	public void setScriptBuffer(FastStringWriter scriptBuffer) {
		this.scriptBuffer = scriptBuffer;
	}

	/**
	 * @return the isScriptHidingEnabled
	 */
	public Boolean getIsScriptHidingEnabled() {
		return isScriptHidingEnabled;
	}

	/**
	 * @param isScriptHidingEnabled the isScriptHidingEnabled to set
	 */
	public void setIsScriptHidingEnabled(Boolean isScriptHidingEnabled) {
		this.isScriptHidingEnabled = isScriptHidingEnabled;
	}

	/**
	 * @return the isScriptInAttributeValueEnabled
	 */
	public Boolean getIsScriptInAttributeValueEnabled() {
		return isScriptInAttributeValueEnabled;
	}

	/**
	 * @param isScriptInAttributeValueEnabled the isScriptInAttributeValueEnabled to set
	 */
	public void setIsScriptInAttributeValueEnabled(
			Boolean isScriptInAttributeValueEnabled) {
		this.isScriptInAttributeValueEnabled = isScriptInAttributeValueEnabled;
	}

	/**
	 * @return the buffer
	 */
	public char[] getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(char[] buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return the textBuffer
	 */
	public char[] getTextBuffer() {
		return textBuffer;
	}

	/**
	 * @param textBuffer the textBuffer to set
	 */
	public void setTextBuffer(char[] textBuffer) {
		this.textBuffer = textBuffer;
	}

	/**
	 * @return the charHolder
	 */
	public char[] getCharHolder() {
		return charHolder;
	}

	/**
	 * @param charHolder the charHolder to set
	 */
	public void setCharHolder(char[] charHolder) {
		this.charHolder = charHolder;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @param attributesBuffer the attributesBuffer to set
	 */
	public void setAttributesBuffer(FastStringWriter attributesBuffer) {
		this.attributesBuffer = attributesBuffer;
	}

  @Override
  public void startElement(String arg0, UIComponent arg1) throws IOException {
    // Noop to satisfy inheritance
  }

  @Override
  public void writeAttribute(String arg0, Object arg1, String arg2) throws IOException {
    // Noop to satisfy inheritance
  }

  @Override
  public void writeURIAttribute(String arg0, Object arg1, String arg2) throws IOException {
    // Noop to satisfy inheritance
  }


}
