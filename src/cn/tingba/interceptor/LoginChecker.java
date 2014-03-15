package cn.tingba.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.tingba.common.Const;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

//检查是否经过登录的拦截器
public class LoginChecker implements Interceptor {
	//不需要检查的url
	List<String> pathsNotNeedCheck = new ArrayList<String>();
	
	//重写Interceptor接口的方法，初始化拦截器
	public void init() { 
		//转向登录和登录本身不需要检查
		pathsNotNeedCheck.add("/imageAction"); // 验证码
		pathsNotNeedCheck.add("/top10"); // 首页
		pathsNotNeedCheck.add("/search"); // 搜索
		pathsNotNeedCheck.add("/go_login"); // 跳转到登陆页
		pathsNotNeedCheck.add("/go_register"); // 跳转到注册页面
		pathsNotNeedCheck.add("/user_login"); // 提交登陆数据
		pathsNotNeedCheck.add("/user_register"); // 提交注册数据
		pathsNotNeedCheck.add("/song_playSong"); // 播放歌曲
		System.err.println("intercepter INIT..."); // 
	}

	//每次的地址栏请求，都会执行此方法
	public String intercept(ActionInvocation invocation) throws Exception {
		System.err.println("intercepter RUNNING...");
		String result; // 返回的逻辑视图名
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getServletPath(); //获得请求的url
		int pos = path.indexOf(".action"); 
		if (pos != -1) {
			path = path.substring(0, pos); 
		}
		if (pathsNotNeedCheck.contains(path)) {//若不需要拦截
			result = invocation.invoke();//放行
		} else { //否则，检查是否经过了登录
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute(Const.LOGINED_USER) != null) {
				result = invocation.invoke(); //经过了登录，则放行
			} else {
				result = "login"; //未经登录，转向登录页
			}
		}
		return result;
	}

	//重写Interceptor接口的方法，销毁拦截器
	public void destroy() {
	}
}
