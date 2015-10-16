package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.MobanService;
import service.UserService;

import domain.MobanVo;
import domain.UserVo;

@Controller
public class LoginAction {

	@Autowired
	private UserService userService;
	@Autowired
	private MobanService mobanService;
	
	@RequestMapping(value = "/logoff.htm")
	public ModelAndView logoff(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/loginCheck.htm")
	public ModelAndView loginCheck(HttpServletRequest request,
			UserVo userVo) throws Exception{
		boolean isValidUser = userService.hasMatchUser(userVo.getId(),
				userVo.getPassword());
		if (!isValidUser) {
			return new ModelAndView("index","error","用户名或密码错误");
		} else {
			UserVo user = userService.findUserByUserName(userVo.getId());
			request.getSession().setAttribute("user", user);
			List<MobanVo> list= mobanService.findMoban();
			request.getSession().setAttribute("mobanList",list);
			return new ModelAndView("main");
		}
	}

	@RequestMapping(value = "/registry.htm")
	public ModelAndView registry(HttpServletRequest request, UserVo userVo) {
		userVo.setRole("N");
		boolean isSuccess = userService.registerUser(userVo);
		if(isSuccess){
			return new ModelAndView("index", "msg", "用户注册成功！");
		}
		else{
			return new ModelAndView("index", "error", "用户注册失败！");
		}
	}
}
