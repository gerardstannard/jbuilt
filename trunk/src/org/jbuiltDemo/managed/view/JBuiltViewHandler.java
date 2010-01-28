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

package org.jbuiltDemo.managed.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.StateManager;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.jbuilt.StateWriter;
import org.jbuilt.context.ViewProvider;
import org.jbuilt.utils.JsfUtil;
import org.jbuilt.view.componentTree.JsfViewDirector;

import com.sun.facelets.FaceletFactory;
import com.sun.facelets.FaceletViewHandler;
import com.sun.facelets.tag.ui.UIDebug;
import com.sun.facelets.util.DevTools;


/**
 * ViewHandler implementation for Jbuilt
 *
 */
public class JBuiltViewHandler extends FaceletViewHandler {

    protected final static Logger log = Logger
            .getLogger("facelets.viewhandler");


    /**
     * Context initialization parameter for defining what viewIds should be
     * handled by Jbuilt, and what should not. When left unset, all URLs will
     * be handled by Jbuilt. When set, it must be a semicolon separated list
     * of either extension mappings or prefix mappings. For example:
     *
     * would use Jbuilt for processing all viewIds in the "/demos" directory
     * or that end in .xhtml, and use the standard JSP engine for all other
     * viewIds.
     * <p>
     * <strong>NOTE</strong>: when using this parameter, you need to use
     * prefix-mapping for the <code>FacesServlet</code> (that is,
     * <code>/faces/*</code>, not <code>*.jsf</code>).
     * </p>
     */
    public final static String PARAM_RESOURCE_RESOLVER = "facelets.RESOURCE_RESOLVER";

    public final static String PARAM_BUFFER_SIZE = "facelets.BUFFER_SIZE";

    private final static String STATE_KEY = "~facelets.VIEW_STATE~";

    private final static int STATE_KEY_LEN = STATE_KEY.length();

    private final static Object STATE_NULL = new Object();

    private final ViewHandler parent;

    private int bufferSize;

    private String defaultSuffix;

    private FaceletFactory faceletFactory;

    private String[] extensionsArray;

    private String[] prefixesArray;


	private Map<String, Object> requestMap= FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
    
    
    private ViewProvider viewProvider;


	/**
     *
     */
    public JBuiltViewHandler(ViewHandler parent) {
    	super(parent);
        this.parent = parent;
    }

    /**
     * Initialize the ViewHandler during its first request.
     */
    @Override
    protected void initialize(FacesContext context) {
        synchronized (this) {
            	if(this.viewProvider == null){
//                log.fine("Initializing");

//                this.initializeMappings(context);
//                this.initializeMode(context);
                this.initializeBuffer(context);
                
//                this.viewProvider = new ViewProvider();

                log.fine("Initialization Successful");
                
            }
            
        }
    }
    


    private void initializeBuffer(FacesContext context) {
        ExternalContext external = context.getExternalContext();
        String param = external.getInitParameter(PARAM_BUFFER_SIZE);
        this.bufferSize = param != null && !"".equals(param) ? Integer
                .parseInt(param) : -1;
    }

    /**
     * Initialize mappings, during the first request.
     */
//    private void initializeMappings(FacesContext context) {
//        ExternalContext external = context.getExternalContext();
//        String viewMappings = external.getInitParameter(PARAM_VIEW_MAPPINGS);
//        if ((viewMappings != null) && (viewMappings.length() > 0)) {
//            String[] mappingsArray = viewMappings.split(";");
//
//            List extensionsList = new ArrayList(mappingsArray.length);
//            List prefixesList = new ArrayList(mappingsArray.length);
//
//            for (int i = 0; i < mappingsArray.length; i++) {
//                String mapping = mappingsArray[i].trim();
//                int mappingLength = mapping.length();
//                if (mappingLength <= 1) {
//                    continue;
//                }
//
//                if (mapping.charAt(0) == '*') {
//                    extensionsList.add(mapping.substring(1));
//                } else if (mapping.charAt(mappingLength - 1) == '*') {
//                    prefixesList.add(mapping.substring(0, mappingLength - 1));
//                }
//            }
//
//            extensionsArray = new String[extensionsList.size()];
//            extensionsList.toArray(extensionsArray);
//
//            prefixesArray = new String[prefixesList.size()];
//            prefixesList.toArray(prefixesArray);
//        }
//    }

    @Override
    public UIViewRoot restoreView(FacesContext context, String viewId) {
        if (UIDebug.debugRequest(context)) {
            return new UIViewRoot();
        }

    		if(this.viewProvider == null){
    			this.initialize(context);
    		}

        ViewHandler outerViewHandler = context.getApplication()
                .getViewHandler();
        String renderKitId = outerViewHandler.calculateRenderKitId(context);
        viewId = (String) JsfUtil.getServletContext().getAttribute("jbuilt.BASE_VIEW_PATH") + viewId;
        UIViewRoot viewRoot = createView(context, viewId);
        context.setViewRoot(viewRoot);
        try {
            this.buildView(context, viewRoot);
        } catch (IOException ioe) {
            log.log(Level.SEVERE, "Error Building View", ioe);
        }
            
        context.getApplication().getStateManager().restoreView(context, viewId,
                renderKitId);
        
//        UIViewRoot viewRoot = context.getViewRoot();
        
        return viewRoot;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.faces.application.ViewHandlerWrapper#getWrapped()
     */
    @Override
    protected ViewHandler getWrapped() {
        return this.parent;
    }
    
    @Override
    protected ResponseWriter createResponseWriter(FacesContext context)
            throws IOException, FacesException {
        ExternalContext extContext = context.getExternalContext();
        RenderKit renderKit = context.getRenderKit();
        // Avoid a cryptic NullPointerException when the renderkit ID
        // is incorrectly set
        if (renderKit == null) {
            String id = context.getViewRoot().getRenderKitId();
            throw new IllegalStateException(
                    "No render kit was available for id \"" + id + "\"");
        }

        ServletResponse response = (ServletResponse) extContext.getResponse();

        // set the buffer for content
        if (this.bufferSize != -1) {
            response.setBufferSize(this.bufferSize);
        }

        // get our content type
        String contentType = (String)extContext.getRequestMap().get("facelets.ContentType");

        // get the encoding
        String encoding = (String) extContext.getRequestMap().get("facelets.Encoding");

        ResponseWriter writer;
        //append */* to the contentType so createResponseWriter will succeed no matter
        //the requested contentType.
        if(contentType != null && !contentType.equals("*/*")) {
        	contentType += ",*/*";
        }
        // Create a dummy ResponseWriter with a bogus writer,
        // so we can figure out what content type the ReponseWriter
        // is really going to ask for
        try {
	        writer = renderKit.createResponseWriter(
	                NullWriter.Instance, contentType, encoding);
        } catch(IllegalArgumentException e) {
        	//Added because of an RI bug prior to 1.2_05-b3.  Might as well leave it in case other
        	//impls have the same problem.  https://javaserverfaces.dev.java.net/issues/show_bug.cgi?id=613
        	log.fine("The impl didn't correctly handled '*/*' in the content type list.  Trying '*/*' directly.");
	        writer = renderKit.createResponseWriter(
	                NullWriter.Instance, "*/*", encoding);
        }

        //Override the JSF provided content type if necessary
        contentType = getResponseContentType(context, writer.getContentType());
        encoding = getResponseEncoding(context, writer.getCharacterEncoding());

        // apply them to the response
        response.setContentType(contentType + "; charset=" + encoding);

        // removed 2005.8.23 to comply with J2EE 1.3
        // response.setCharacterEncoding(encoding);

        // Now, clone with the real writer
        writer = writer.cloneWithWriter(response.getWriter());

        return writer;
    }

    /**
     * Generate the encoding
     *
     * @param context
     * @param orig
     * @return
     */
    @Override
    protected String getResponseEncoding(FacesContext context, String orig) {
        String encoding = orig;

        // see if we need to override the encoding
        Map m = context.getExternalContext().getRequestMap();
        Map sm = context.getExternalContext().getSessionMap();

        // 1. check the request attribute
        if (m.containsKey("facelets.Encoding")) {
            encoding = (String) m.get("facelets.Encoding");
            if (log.isLoggable(Level.FINEST)) {
                log.finest("Jbuilt specified alternate encoding '" + encoding
                        + "'");
            }
            sm.put(CHARACTER_ENCODING_KEY, encoding);
        }

        // 2. get it from request
        Object request = context.getExternalContext().getRequest();
        if (encoding == null && request instanceof ServletRequest) {
            encoding = ((ServletRequest) request).getCharacterEncoding();
        }

        // 3. get it from the session
        if (encoding == null) {
            encoding = (String) sm.get(CHARACTER_ENCODING_KEY);
            if (log.isLoggable(Level.FINEST)) {
                log.finest("Session specified alternate encoding '" + encoding
                        + "'");
            }
        }

        // 4. default it
        if (encoding == null) {
            encoding = "UTF-8";
            if (log.isLoggable(Level.FINEST)) {
                log
                        .finest("ResponseWriter created had a null CharacterEncoding, defaulting to UTF-8");
            }
        }

        return encoding;
    }

    /**
     * Generate the content type
     *
     * @param context
     * @param orig
     * @return
     */
    @Override
    protected String getResponseContentType(FacesContext context, String orig) {
        String contentType = orig;

        // see if we need to override the contentType
        Map m = context.getExternalContext().getRequestMap();
        if (m.containsKey("facelets.ContentType")) {
            contentType = (String) m.get("facelets.ContentType");
            if (log.isLoggable(Level.FINEST)) {
                log.finest("Jbuilt specified alternate contentType '"
                        + contentType + "'");
            }
        }

        // safety check
        if (contentType == null) {
            contentType = "text/html";
            if (log.isLoggable(Level.FINEST)) {
                log
                        .finest("ResponseWriter created had a null ContentType, defaulting to text/html");
            }
        }

        return contentType;
    }

    @Override
    public void buildView(FacesContext context, UIViewRoot viewToRender)
            throws IOException, FacesException {

//        if (log.isLoggable(Level.FINE)) {
//            log.fine("Building View: " + renderedViewId);
//        }

        long time = System.currentTimeMillis();
        ResponseWriter out;
        
//        String viewClassName = resolveViewClassName(viewId);
        
        // Delegate view lookup to Provider
        
        JsfViewDirector view = null;
        
        viewProvider = new ViewProvider(viewToRender);
        
        view = viewProvider.get();
        
        
//      this works but is very slow, DI Rocks! for obtaining views to render
//
//            try {
//
//                // get String Class
//                Class viewClass = Class.forName(viewClassName);
//
//                // get the constructor with one parameter
//                java.lang.reflect.Constructor viewConstructor =
//                   viewClass.getConstructor(new Class[] {UIComponent.class});
//
//                // create an instance of view
//                view = (JsfComponentTreeViewDirector) viewConstructor.newInstance(new Object[]{viewToRender});
//
//              }
//              catch (Exception e) {
//                  e.printStackTrace();
//              }
        
        
        // populate UIViewRoot
//        f.apply(context, viewToRender);
//        time = System.currentTimeMillis() - time;
//        if (log.isLoggable(Level.FINE)) {
//            log.fine("Took " + (time/1000) + "seconds to build view: "
//                    + viewId);
//        }
    }
    
    private String resolveViewClassName(String viewId){
    	String viewClassName = null;
    	if(!viewId.contains(".")){
    	viewClassName = viewId.substring(1).replaceAll("/", ".");
    	} else{
    		if(viewId.endsWith(".jsf")){
    			viewClassName = viewId.substring(1, (viewId.length() - 4)).replaceAll("/", ".");
    		}
    	}
    	
    	return viewClassName;
    }
    @Override
    public void renderView(FacesContext context, UIViewRoot viewToRender)
            throws IOException, FacesException {

        // lazy initialize so we have a FacesContext to use
//        if (this.faceletFactory == null) {
            this.initialize(context);
//        }

        // exit if the view is not to be rendered
        if (!viewToRender.isRendered()) {
            return;
        }

        // if not supposed to handle this request
        if (!handledByJbuilt(viewToRender.getViewId())) {
            this.parent.renderView(context, viewToRender);
            return;
        }

        // log request
        if (log.isLoggable(Level.FINE)) {
            log.fine("Rendering View: " + viewToRender.getViewId());
        }

        StateWriter stateWriter = null;
        try {
        	List viewChildren = viewToRender.getChildren();
        	
            if (viewChildren.isEmpty()) {
                this.buildView(context, viewToRender);
            }

            // setup writer and assign it to the context
            ResponseWriter origWriter = null;
        		origWriter = this.createResponseWriter(context);

            
            // QUESTION: should we use bufferSize? Or, since the
            // StateWriter usually only needs a small bit at the end,
            // should we always use a much smaller size?
            stateWriter = new StateWriter(origWriter,
                    this.bufferSize != -1 ? this.bufferSize : 1024);

            ResponseWriter writer = origWriter.cloneWithWriter(stateWriter);
            context.setResponseWriter(writer);

            // force creation of session if saving state there
            StateManager stateMgr = context.getApplication().getStateManager();
            if (!stateMgr.isSavingStateInClient(context)) {
                context.getExternalContext().getSession(true);
            }

            long time = System.currentTimeMillis();

            // render the view to the response
            writer.startDocument();
    		// TODO: get the doctype out of the requestMap and if present, write it to respone,
            // otherwise provide default

            writer.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
                viewToRender.encodeAll(context);
//            	viewToRender.encodeBegin(context);
//            	for(UIComponent child : viewToRender.getChildren()){
//            		child.encodeAll(context);
//            	}
//            	viewToRender.encodeEnd(context);
            writer.endDocument();

            // finish writing
            writer.close();


            boolean writtenState = stateWriter.isStateWritten();
            // flush to origWriter
            if (writtenState) {
                String content = stateWriter.getAndResetBuffer();
                int end = content.indexOf(STATE_KEY);
                // See if we can find any trace of the saved state.
                // If so, we need to perform token replacement
                if (end >= 0) {
                    // save state
                    Object stateObj = stateMgr.saveSerializedView(context);
                    String stateStr;
                    if (stateObj == null) {
                        stateStr = null;
                    } else {
                        stateMgr.writeState(context,
                                       (StateManager.SerializedView) stateObj);
                        stateStr = stateWriter.getAndResetBuffer();
                    }

                    int start = 0;

                    while (end != -1) {
                        origWriter.write(content, start, end - start);
                        if (stateStr != null) {
                            origWriter.write(stateStr);
                        }
                        start = end + STATE_KEY_LEN;
                        end = content.indexOf(STATE_KEY, start);
                    }
                    origWriter.write(content, start, content.length() - start);
                // No trace of any saved state, so we just need to flush
                // the buffer
                } else {
                    origWriter.write(content);
                    // But for JSF 1.1 (actually, just 1.1_01 RI), if we didn't
                    // detect any saved state, force a call to
                    // saveSerializedView() in case we're using the old
                    // pure-server-side state saving
//                    if ((FacesAPI.getVersion() < 12)
//                        && !stateMgr.isSavingStateInClient(context)) {
//                        stateMgr.saveSerializedView(context);
//                    }
                }
            }

            time = System.currentTimeMillis() - time;
            if (log.isLoggable(Level.FINE)) {
                log.fine("Took " + time + "ms to render view: "
                        + viewToRender.getViewId());
            }

        } catch (FileNotFoundException fnfe) {
            this.handleJbuiltNotFound(context, viewToRender.getViewId());
        } catch (Exception e) {
            this.handleRenderException(context, e);
        } finally {
            if (stateWriter != null) {
              stateWriter.release();
            }
        }
    }

    @Override
    protected void handleRenderException(FacesContext context, Exception e)
            throws IOException, ELException, FacesException {
        Object resp = context.getExternalContext().getResponse();

        // always log
        if (log.isLoggable(Level.SEVERE)) {
            UIViewRoot root = context.getViewRoot();
            StringBuffer sb = new StringBuffer(64);
            sb.append("Error Rendering View");
            if (root != null) {
                sb.append('[');
                sb.append(root.getViewId());
                sb.append(']');
            }
            log.log(Level.SEVERE, sb.toString(), e);
        }

        // handle dev response
        if (!context.getResponseComplete()
                && resp instanceof HttpServletResponse) {
            HttpServletResponse httpResp = (HttpServletResponse) resp;
            if (!httpResp.isCommitted()) {
	            httpResp.reset();
	            httpResp.setContentType("text/html; charset=UTF-8");
	            Writer w = httpResp.getWriter();
	            DevTools.debugHtml(w, context, e);
	            w.flush();
	            context.responseComplete();
            }
        } else if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        } else if (e instanceof IOException) {
            throw (IOException) e;
        } else {
            throw new FacesException(e.getMessage(), e);
        }
    }

    protected void handleJbuiltNotFound(FacesContext context, String viewId)
            throws FacesException, IOException {
        String actualId = this.getActionURL(context, viewId);
        Object respObj = context.getExternalContext().getResponse();
        if (respObj instanceof HttpServletResponse) {
            HttpServletResponse respHttp = (HttpServletResponse) respObj;
            respHttp.sendError(HttpServletResponse.SC_NOT_FOUND, actualId);
            context.responseComplete();
        }
    }

    /**
     * Determine if Jbuilt needs to handle this request.
     */
    private boolean handledByJbuilt(String viewId) {
        // If there's no extensions array or prefixes array, then
        // just make Jbuilt handle everything
        if (extensionsArray == null && prefixesArray == null) {
            return true;
        }

        if (extensionsArray != null) {
            for (int i = 0; i < extensionsArray.length; i++) {
                String extension = extensionsArray[i];
                if (viewId.endsWith(extension)) {
                    return true;
                }
            }
        }

        if (prefixesArray != null) {
            for (int i = 0; i < prefixesArray.length; i++) {
                String prefix = prefixesArray[i];
                if (viewId.startsWith(prefix)) {
                    return true;
                }
            }
        }

//        return false;
        return true;
    }

    @Override
    public String getDefaultSuffix(FacesContext context) throws FacesException {
        if (this.defaultSuffix == null) {
            ExternalContext extCtx = context.getExternalContext();
            String viewSuffix = extCtx
                    .getInitParameter(ViewHandler.DEFAULT_SUFFIX_PARAM_NAME);
            this.defaultSuffix = viewSuffix != null ? viewSuffix
                    : ViewHandler.DEFAULT_SUFFIX;
        }
        return this.defaultSuffix;
    }

    @Override
    protected String getRenderedViewId(FacesContext context, String actionId) {
        ExternalContext extCtx = context.getExternalContext();
        String viewId = actionId;
        if (extCtx.getRequestPathInfo() == null) {
            String viewSuffix = this.getDefaultSuffix(context);
            viewId = new StringBuffer(viewId).replace(viewId.lastIndexOf('.'),
                                                      viewId.length(),
                                                      viewSuffix).toString();
        }
        if (log.isLoggable(Level.FINE)) {
            log.fine("ActionId -> ViewId: " + actionId + " -> " + viewId);
        }
        System.out.println("ActionId -> ViewId: " + actionId + " -> " + viewId);

        return viewId;
    }

    @Override
    public void writeState(FacesContext context) throws IOException {
        if (handledByJbuilt(context.getViewRoot().getViewId())) {
            // Tell the StateWriter that we're about to write state
            StateWriter.getCurrentInstance().writingState();
            // Write the STATE_KEY out.  Unfortunately, this will
            // be wasteful for pure server-side state managers where nothing
            // is actually written into the output, but this cannot
            // programatically be discovered
            context.getResponseWriter().write(STATE_KEY);
        } else {
            this.parent.writeState(context);
        }
    }

    @Override
    public Locale calculateLocale(FacesContext context) {
        return this.parent.calculateLocale(context);
    }

    @Override
    public String calculateRenderKitId(FacesContext context) {
        return this.parent.calculateRenderKitId(context);
    }

    @Override
    public UIViewRoot createView(FacesContext context, String viewId) {
        if (UIDebug.debugRequest(context)) {
            return new UIViewRoot();
        }
        return this.parent.createView(context, viewId);
    }

    @Override
    public String getActionURL(FacesContext context, String viewId) {
        return this.parent.getActionURL(context, viewId);
    }

    @Override
    public String getResourceURL(FacesContext context, String path) {
        ExternalContext extContext = context.getExternalContext();
        if (path.startsWith("/")) {
            return extContext.getRequestContextPath() + path;
        } else {
            return path;
        }

    }


    protected static class NullWriter extends Writer {

        static final NullWriter Instance = new NullWriter();

        @Override
        public void write(char[] buffer) {
        }

        @Override
        public void write(char[] buffer, int off, int len) {
        }

        @Override
        public void write(String str) {
        }

        @Override
        public void write(int c) {
        }

        @Override
        public void write(String str, int off, int len) {
        }

        @Override
        public void close() {
        }

        @Override
        public void flush() {
        }
    }
}
