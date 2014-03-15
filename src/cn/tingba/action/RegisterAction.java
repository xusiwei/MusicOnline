package cn.tingba.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.tingba.entity.User;
import cn.tingba.service.UserLogService;
import cn.tingba.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String password2;
	private String sex;
	private String email;
	private boolean agreeLicense;
	private String validateNum;
	
	private UserService userService = new UserService();
	private UserLogService userLogService = new UserLogService();

	public String execute() {
		// 校验复选框
		if(!agreeLicense) {
			addFieldError("agreeLicense", "必须同意用户条款才能注册！");
			return INPUT;
		}		
		
		// 校验验证码
		Map<String, Object> session = ActionContext.getContext().getSession();
		String randstr = (String) session.get(ImageAction.IMAGE_STR_KEY);
		if (!randstr.equals(validateNum)) {
			addFieldError("validateNum", "验证码输入错误！");
			return INPUT;
		}
		
		// 检查用户是否已经注册
		if (userService.exist(username)) { // 在数据库中找
			addFieldError("username", "用户名已注册！");
			return INPUT;
		}
		
		User user = userService.register(username, password, sex, email); // 放入数据库
		userLogService.logUserAction(user, "REGISTER");
		return SUCCESS;
	}

	// getter and setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAgreeLicense() {
		return agreeLicense;
	}

	public void setAgreeLicense(boolean agreeLicense) {
		this.agreeLicense = agreeLicense;
	}

	public String getValidateNum() {
		return validateNum;
	}

	public void setValidateNum(String validateNum) {
		this.validateNum = validateNum;
	}

}
