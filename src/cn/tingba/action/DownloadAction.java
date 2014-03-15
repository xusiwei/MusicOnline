package cn.tingba.action;

import java.io.InputStream;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String path;
	
	private InputStream inputStream; 
	
	public String execute() throws Exception {
		inputStream =  ServletActionContext.getServletContext().getResourceAsStream(path);
		System.err.println("文件名：" + name);
		System.err.println("路径：" + path);
		return SUCCESS;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
