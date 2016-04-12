package com.elk.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.elk.cache.RedisService;


/**
 * Servlet implementation class UploadTemplate
 */
@WebServlet("/UploadTemplate")
public class UploadTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadTemplate() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String templatesource = "";
		String indexName = "";
		try{
			response.setCharacterEncoding("UTF-8");
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == true) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						
						if (fieldName.equals("indexName")){
							indexName = item.getString();
						}
						
					} else {

						templatesource = item.getName();
						File remoteFile = new File(item.getName());
        				
        				File destFile = new File(Thread.currentThread().getContextClassLoader().getResource("resource/template/es/").getPath(),remoteFile.getName());
        				destFile.getParentFile().mkdirs();
        				destFile.createNewFile();
                        
                        InputStream ins = item.getInputStream();
                        OutputStream ous = new FileOutputStream(destFile);
                        try{
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while((len = ins.read(buffer)) > -1)
                                ous.write(buffer,0,len);
                        }finally{
                            ous.close();
                            ins.close();
                        }
					}
				}
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
		
		@SuppressWarnings("resource")
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:resource/applicationContext.xml");  
		RedisService redisService =(RedisService) ac.getBean("redisService");
		redisService.putEsTemplate(indexName, templatesource);
		response.sendRedirect("front/initScriptConfig");
	}

}
