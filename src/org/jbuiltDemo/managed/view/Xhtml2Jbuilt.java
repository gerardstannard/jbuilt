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
 * this file borrows heavily from Xhtml2Gracelet from the Gracelets project.
 * this version applies logic specific to converting xhtml to
 * Jbuilt fluent syntax
 * original author is below
 */

/**
 * original author
 * @author ponder
 * @version $Revision$
 */


/**
 * 
 * @author Gerard Stannard
 *
 */


package org.jbuiltDemo.managed.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.collections.CollectionUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A tool that can be used to convert normal xhtml files to jbuilt java source.
 * does not quite convert the entire file properly because of how Jbuilt works
 * a clever work around is yet to come
 * 
 * @author Gerard Stannard
 * @version
 */
public class Xhtml2Jbuilt extends DefaultHandler implements LexicalHandler, Locator {
	
	static Map<String, String> defaultMappings = new HashMap<String, String>();
//	static {
//		Map<String, String> db = GraceletBuilder.defaultLibraries;
//		for (String alias : db.keySet()) defaultMappings.put(db.get(alias), alias);
//	}
	
	static Pattern attribute = Pattern.compile("[^a-zA-Z0-9]");
	static String fileName = "ModularLayoutView";
	static String ownerName = "modularLayout";
    static String fileAnnotation = "_modularLayout";
    static String viewAnnotation = "_ModularLayoutView";


	public static void main (String[] args) throws Exception {
		
//		File file = args.length == 0 ? new File(".") : new File(args[0]);
//		if (file.isDirectory()) {
//			List<File> files = new ArrayList<File>();
//			getAllFiles(file, files);
//			convert(files.toArray(new File[files.size()]));
//		} else convert(file);
		File file = new File("j:/galileo/workspace/jbuiltTest/src/org/jbuiltTest/managed/view/"+fileName+".xhtml");
		convert(file);
		
	}
	
	SAXParserFactory factory;
	SAXParser parser;
	
	public Xhtml2Jbuilt () throws Exception {
		factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
		factory.setFeature("http://xml.org/sax/features/validation", false);
		factory.setValidating(false);

		parser = factory.newSAXParser();
    XMLReader reader = parser.getXMLReader();
    reader.setDTDHandler(this);
    reader.setContentHandler(this);
    reader.setProperty("http://xml.org/sax/properties/lexical-handler", this);
    reader.setErrorHandler(this);
    reader.setEntityResolver(this);
	}
	
	public static void convert (File... files) throws Exception {
		Xhtml2Jbuilt x2j = new Xhtml2Jbuilt();
		for (File src : files) {
			System.out.print("Attempting to convert: " + src + " ... ");
			try {
				x2j.convertToJbuilt(src);
				System.out.println("Done.");
			} catch  (Exception e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public static void getAllFiles (File dir, List<File> files) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
        getAllFiles(file, files);
      } else if (file.getName().endsWith(".xhtml")) {
        files.add(file);
      } else {
        System.out.println("Ignoring: " + file);
      }
		}
	}
	
	ParsingInfo info;
	
	public void convertToJbuilt (File src) throws Exception {
		this.convertToJbuilt(src, new File(src.getAbsolutePath().replaceAll("\\.xhtml", ".java")));
	}
	
	public void convertToJbuilt (File src, File dst) throws Exception {
		
		String code = this.convertToJbuilt(new FileInputStream(src));
		code = code.replaceAll("class\\(", "styleClass(").replaceAll("title", "titleElement");

		if (dst.exists()) {
      dst.delete();
    }
		FileOutputStream out = new FileOutputStream(dst);
		out.write(code.getBytes());
		out.close();
		
	}
	
	public String convertToJbuilt (String xhtmlSource) throws Exception {
		return this.convertToJbuilt(new ByteArrayInputStream(xhtmlSource.getBytes()));
	}
	
	public String convertToJbuilt (InputStream xhtmlSource) throws Exception {
		this.info = new ParsingInfo();
		
		SAXParser parser = this.createParser();
		parser.parse(xhtmlSource, this);
		xhtmlSource.close();
		
		StringBuffer code = new StringBuffer();
		code.append("package org.jbuiltDemo.managed.view;\n")
	    .append("import javax.faces.component.UIComponent;\n")
	    .append("import javax.faces.context.FacesContext;\n")
		.append("import org.jbuilt.view.componentTree.JsfViewDirector;\n")
		.append("import org.jbuiltDemo.managed.annotations._UIViewRoot;\n")
		.append("import org.jbuiltDemo.managed.annotations._grid;\n")
		.append("import com.google.inject.Inject;\n")
		.append("public class " + fileName + " extends BaseViewClosure {\n")
		.append("@Inject\n")
		.append(fileName + "(@_UIViewRoot UIComponent tree, FacesContext facesContext, \n")
		.append("@_" + ownerName + " JsfViewDirector owner){\n")
		.append("super(tree, facesContext);\n")
		.append("this.owner = owner;\n")
		.append("this.tree = tree;\n")
		.append("this.facesContext = facesContext;\n")
		.append("}\n")
		.append("@Override\n")
		.append("public Object doExecute(Object... args) {\n")
		.append("UIComponent html =\n");
		
		
		for (String mapping : info.mappings.keySet()) {
			if (defaultMappings.containsKey(mapping)) {
        continue;
      }
			code.append(info.mappings.get(mapping))
				.append(" = ns.\"")
				.append(mapping)
				.append("\"\n");
		}
		
		code.append("\n");
		
//		if (info.dtd != null) code.append("println \"").append(info.dtd.replaceAll("\"", "\\\\\""))
//			.append("\"").append("\n");
		this.convertNode(info.root, code, "");
		code.append("\n").append("\t").append(";\n return html;\n}\n}");
		String codeAsString = code.toString().trim();
//		codeAsString = codeAsString.replaceAll("/\\*P\\*/\r\t\t\\(/\\*Z\\*/", ",");
        codeAsString = codeAsString.replaceAll("\\(/\\*Z\\*/", ",");
        codeAsString = codeAsString.replaceAll("([a-z0-9]+)\\s,", "$1(");
        codeAsString = codeAsString.replaceAll("/\\*L no CH\\*/", ",");
        codeAsString = codeAsString.replaceAll("/\\*[A-Z0-9a-z]*\\*/", "");
        
//        \)/\*L no CH\*/
//        /\*[A-Z0-9a-z]*\*/
		return codeAsString;
    
	}
	
	public void convertNode(ElementNode node, StringBuffer code, String tabs) {
		if (node.textOnly) {
			if (node.text != null && !"".equals(node.text.trim())) {
				String[] lines = node.text.trim().split("\n");
				if (lines.length == 1) {
					code.append(tabs).append("text( \"").append(node.text.trim())
						.append("\")/*TM*/\n").append(tabs);//.append(")/*TN*/,\n");//.append(tabs);
				} else {
					code.append("\n").append(tabs).append("text( \"\n");
					for (String line : lines) {
						code.append(tabs).append("\t\"").append(line).append("\",\n");
					}
					code.append(tabs).append("\")/*TO*/\n").append(tabs).append(")/*TP*/,\n").append(tabs);
				}
			} else if (node.comments != null) {
				code.append("\n");
				for (String line : node.comments.split("\n")) {
					code.append(tabs).append("//").append(line).append("\n");
				}
			} else if (node.text.contains("\n")) {
        code.append("\n");
      }
			return;
		}
		
		String ns = defaultMappings.containsKey(node.mapping) ?
				defaultMappings.get(node.mapping) :
				info.mappings.get(node.mapping);
				
		if (ns == null || "".equals(ns)) {
      ns = "xh";
    }
		
		boolean t = false; //node.areChildrenOnlyText();
		String txt = node.getChildrenAsText();
		if (txt.length() > 40 || txt.length() == 0) {
      t = false;
    }
		code.append(tabs)./*append(ns).append(".").*/append(node.name);
		if (node.attributes.size() > 0 || t || node.children.size() == 0){
		    code.append(" (/*T*/\n").append(tabs).append("\t");
		}
		if (t) {
      code.append("\"").append(txt.replaceAll("\"", "\\\\\"")).append("\"");
    }
		int atts = 0;
		Set<String> nodeAttributeSet = node.attributes.keySet();
		for (String an : nodeAttributeSet) {
			if ("".equals(an)) {
        continue;
      }
			if (atts > 0 || t) {
        code.append(""); // was ,/*C*/
      }
//			if (an.matches(".*[^a-zA-Z0-9]+.*")) code.append("\"").append(an).append("\"");
			/*else*/ code.append(an);
			code.append("(/*S*/").append("\"");
			// if this is the last one, don't add a comma
			if(CollectionUtils.get(nodeAttributeSet, nodeAttributeSet.size()-1) == an){
		         code.append(node.attributes.get(an)).append("\")/*P*/\n\t\t");
			} else {
				// if this is last node wrap an extra comma and parenthese
	              code.append(node.attributes.get(an)).append("\"),/*P2*/\n\t").append(tabs);
			}
			atts++;
		}
		if (node.children.size() > 0 && !t) {
			String ntabs = tabs + "\t";
			if (node.attributes.size() > 0 || node.children.size() == 0) {
        code.append("");// code.append("(),\n"); // was )/*Y*/
      }
			    code.append(" (/*Z*/\n");
			this.compactTextChildren(node);
			for (ElementNode child : node.children) {
        this.convertNode(child, code, ntabs);
      }
			if(node.attributes.size() > 0 || node.children.size() == 0){
			    code.append(tabs).append(")/*L no CH*/\n");
			} else {
                code.append(tabs).append("),/*L*/\n");
			}
			
		} else if (node.attributes.size() > 0 || t || node.children.size() == 0){
		    code.append(")/*Atts no Ch and text*/\n").append("\t");//.append("),/*N*/\n");
		}
	}
	
	public void compactTextChildren (ElementNode node) {
		List<ElementNode> children = new ArrayList<ElementNode>();
		
		StringBuffer text = new StringBuffer();
		StringBuffer comment = new StringBuffer();
		for (ElementNode child : node.children) {
			if (child.textOnly) {
				if (child.text != null) {
					if (comment.length() > 0) {
            children.add(this.createTextNode(node, true, comment));
          }
					text.append(child.text);
				} else if (child.comments != null) {
					if (text.length() > 0) {
            children.add(this.createTextNode(node, false, text));
          }
					comment.append(child.comments);
				}
			} else {
				if (text.length() > 0) {
          children.add(this.createTextNode(node, false, text));
        } else if (comment.length() > 0) {
          children.add(this.createTextNode(node, true, comment));
        }
				children.add(child);
			}
		}
		if (text.length() > 0) {
      children.add(this.createTextNode(node, false, text));
    } else if (comment.length() > 0) {
      children.add(this.createTextNode(node, true, comment));
    }
		
		node.children = children;
	}
	
	public ElementNode createTextNode (ElementNode parent, boolean comment, StringBuffer data) {
		ElementNode text = new ElementNode();
		text.parent = parent;
		text.textOnly = true;
		if (comment) {
      text.comments = data.toString();
    } else {
      text.text = data.toString();
    }
		data.delete(0, data.length()-1);
		return text;
	}
	
	public SAXParser createParser () throws Exception {
    return parser;
	}
	
	Locator original;
	
	public int getColumnNumber() {
	  return original.getColumnNumber();
  }

	public int getLineNumber() {
	  return original.getLineNumber();
  }

	public String getPublicId() {
	  return original.getPublicId();
  }

	public String getSystemId() {
	  return original.getSystemId();
  }

	public void comment(char[] ch, int start, int length) throws SAXException {
		info.startNode(new ElementNode(), true, new String(ch, start, length), null);
	}

	public void endCDATA() throws SAXException {}
	public void endDTD() throws SAXException {}
	public void endEntity(String name) throws SAXException {}
	public void startCDATA() throws SAXException {}
	public void startDTD(String name, String publicId, String systemId) throws SAXException {
		info.dtd = "<!DOCTYPE " + name + " PUBLIC \"" + publicId + "\" \"" + systemId + "\">";
	}
	public void startEntity(String name) throws SAXException {}

	@Override public void characters(char[] ch, int start, int length) throws SAXException {
		info.startNode(new ElementNode(), true, null, new String(ch, start, length));
	  super.characters(ch, start, length);
  }

	@Override public void endDocument() throws SAXException { super.endDocument(); }

	@Override public void endElement(String uri, String localName, String name) throws SAXException {
		info.endNode();
	  super.endElement(uri, localName, name);
  }

	@Override public void endPrefixMapping(String prefix) throws SAXException {
	  super.endPrefixMapping(prefix);
  }

	@Override public void error(SAXParseException e) throws SAXException {
	  super.error(e);
  }

	@Override public void fatalError(SAXParseException e) throws SAXException {
	  super.fatalError(e);
  }

	@Override public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { }

	@Override public void notationDecl(String name, String publicId, String systemId) throws SAXException {
	  System.out.println(name + "/" + publicId + "/" + systemId);
  }

	@Override public void processingInstruction(String target, String data) throws SAXException {
	  System.out.println("Processing: " + target + "/" + data);
  }

	@Override public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
		return new X2GInputSource();
  }

	@Override public void setDocumentLocator(Locator locator) {
		this.original = locator;
	  super.setDocumentLocator(this);
  }

	@Override public void skippedEntity(String name) throws SAXException {}

	@Override public void startDocument() throws SAXException { super.startDocument(); }

	@Override public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		ElementNode node = new ElementNode();
		node.name = localName;
		node.mapping = uri;
		for (int i=0; i<attributes.getLength(); i++) {
			String aln = attributes.getQName(i);
			String ava = attributes.getValue(i);
			node.attributes.put(aln, ava);
		}
		info.startNode(node, false, null, null);
	  super.startElement(uri, localName, name, attributes);
  }

	@Override public void startPrefixMapping(String prefix, String uri) throws SAXException {
		info.mappings.put(uri, prefix);
	  super.startPrefixMapping(prefix, uri);
  }

	@Override public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
		if (notationName.endsWith("ent")) {
      super.unparsedEntityDecl(name, publicId, systemId, notationName);
    } else {
      System.out.println(name + "/" + publicId + "/" + systemId + "/" + notationName);
    }
  }

	@Override public void warning(SAXParseException e) throws SAXException {
	  e.printStackTrace();
  }
	
	public class ParsingInfo {
		
		Map<String, String> mappings = new LinkedHashMap<String, String>();
		ElementNode root;
		ElementNode currentNode;
		String dtd = null;
		
		public void startNode (ElementNode node, boolean textOnly, String comment, String text) {
			if (textOnly && (comment != null && comment.trim().length() == 0 ||
											 text != null && text.trim().length() == 0)) {
        return;
      }
			if (this.currentNode == null) {
        this.root = this.currentNode = node;
      } else {
				node.parent = this.currentNode;
				node.level = this.currentNode.level + 1;
				this.currentNode.children.add(node);
				this.currentNode = node;
			}
			
			if (textOnly) {
				node.textOnly = true;
				if (comment != null) {
          node.comments = comment;
        } else {
          node.text = text;
        }
				this.endNode();
			}
		}
		
		public void endNode () {
			this.currentNode = this.currentNode.parent;
		}
		
	}
	
	public class X2GInputSource extends InputSource {

		@Override public InputStream getByteStream() {
	    return new ByteArrayInputStream(new byte[0]);
    }

		@Override public Reader getCharacterStream() {
	    return new InputStreamReader(this.getByteStream());
    }
		
	}
	
	public class ElementNode {
		
		boolean textOnly = false;
		int level = 0;
		ElementNode parent;
		String name;
		String mapping;
		String text;
		String comments;
		Map<String, String> attributes = new LinkedHashMap<String, String>();
		List<ElementNode> children = new ArrayList<ElementNode>();
		
		public boolean areChildrenOnlyText () {
			boolean ot = children.size() > 0;
			for (ElementNode child : children) {
				if (!child.textOnly) { ot = false; break; }
			}
			return ot;
		}
		
		public String getChildrenAsText () {
			StringBuffer sb = new StringBuffer();
			for (ElementNode child : children) {
				if (child.textOnly) {
					if (child.text != null) {
            sb.append(child.text);
          }
					if (child.comments != null) {
            sb.append(child.comments);
          }
				}
			}
			return sb.toString();
		}
		
	}
	
}