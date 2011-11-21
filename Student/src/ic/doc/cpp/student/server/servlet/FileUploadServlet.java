package ic.doc.cpp.student.server.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.xml.DOMConfigurator;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Singleton;

@Singleton
@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check that we have a file upload request
		if (ServletFileUpload.isMultipartContent(request)) {
			processFiles(request, response);
		}
	}
	
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files/upload";
	private File destinationDir;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	    DOMConfigurator.configure("log4j.xml");

	    Log.debug("FileUpload Servlet");

	    tmpDir = new File(((File) getServletContext().getAttribute("javax.servlet.context.tempdir")).toString());

	    if (! tmpDir.isDirectory()) {
	      throw new ServletException(tmpDir.toString() + " is not a directory");
	    }

	    Log.debug("tmpDir: " + tmpDir.toString());

	    String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
	    destinationDir = new File(realPath);

	    if (! destinationDir.isDirectory()) {
	      throw new ServletException(DESTINATION_DIR_PATH + " is not a directory");
	    }

	    Log.debug("destinationDir: " + destinationDir.toString());
	}
	
	private void processFiles(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// set the size threshold, above which content will be stored on disk
		factory.setSizeThreshold(1 * 1024 * 1024); // 1 MB

		// set the temporary directory (this is where files that exceed the threshold will be stored)
		factory.setRepository(tmpDir);

	    // create a new file upload handler
	    ServletFileUpload upload = new ServletFileUpload(factory);

	    try {
	    	// parse the request
	    	List<?> items = upload.parseRequest(request);

	    	// process the uploaded items
	    	Iterator<?> itr = items.iterator();

	    	while (itr.hasNext()) {
	    		FileItem item = (FileItem) itr.next();

	    		Log.debug("Field Name: " + item.getFieldName() + ", Value: " + item.getName() +
	    				", Content Type: " + item.getContentType() + ", In Memory: " + item.isInMemory() +
	    				", File Size: " + item.getSize());
	
	    		// write the uploaded file to the application's file staging area
	    		File file = new File(destinationDir, item.getName());
	    		item.write(file);
	    	}
	    } catch (FileUploadException e) {
	    	Log.error("Error encountered while parsing the request", e);
    	} catch (Exception e) {
    		Log.error("Error encountered while uploading file", e);
	    }
	}
	
}
