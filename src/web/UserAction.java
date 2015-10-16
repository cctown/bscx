package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.UserService;

import domain.UserVo;

@Controller
public class UserAction {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/userMgr.htm")
	public ModelAndView userMgr(HttpServletRequest request) {
		List<UserVo> userList=userService.allUsers();
		request.getSession().setAttribute("userList", userList);
		return new ModelAndView("user");
	}
	@RequestMapping(value = "/delUser.htm")
	public ModelAndView delUser(HttpServletRequest request) {
		userService.delUser(request.getParameter("userId"));
		List<UserVo> userList=userService.allUsers();
		request.getSession().setAttribute("userList", userList);
		return new ModelAndView("user");
	}
	@RequestMapping(value = "/toUser.htm")
	public ModelAndView toUser(HttpServletRequest request) {
		userService.toUser(request.getParameter("userId"));
		List<UserVo> userList=userService.allUsers();
		request.getSession().setAttribute("userList", userList);
		return new ModelAndView("user");
	}
	@RequestMapping(value = "/toAdmin.htm")
	public ModelAndView toAdmin(HttpServletRequest request) {
		userService.toAdmin(request.getParameter("userId"));
		List<UserVo> userList=userService.allUsers();
		request.getSession().setAttribute("userList", userList);
		return new ModelAndView("user");
	}
}
