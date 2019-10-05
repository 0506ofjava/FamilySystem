package servlet;

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/** @author yq */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {

  public void login(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String basePath =
        request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    final String REMEMBER = "yes";
    String username = request.getParameter("name");
    String password = request.getParameter("password");
    String remember = request.getParameter("remember");

    UserService userService = new UserService();
    User user = null;
    try {
      user = userService.login(username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // 检查是否已经注册：查询数据库中是否存在相关数据
    if (user == null) {
      response.setContentType("text/html;charset=utf-8");
      response.getWriter().write("登录失败，请检查账号或密码是否正确");
    }
    // 用户已注册：点选了记住密码，则加入cookie中，否则就只是转发页面即可
    else {
      if (REMEMBER.equals(remember)) {
        System.out.println("用户勾选了记住密码，故将信息存入Cookie");
        Cookie passwordCookie = new Cookie("password", password);
        Cookie usernameCookie = new Cookie("username", username);
        passwordCookie.setMaxAge(60 * 240);
        usernameCookie.setMaxAge(60 * 240);
        response.addCookie(passwordCookie);
        response.addCookie(usernameCookie);
      }

      System.out.println(basePath + "member-list.jsp");
      response.sendRedirect(basePath + "/member?method=getMemberList");
    }
  }

  public void register(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String basePath =
        request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    User user = new User();
    Map<String, String[]> parameterMap = request.getParameterMap();
    try {
      BeanUtils.populate(user, parameterMap);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    UserService userService = new UserService();
    try {
      boolean register = userService.register(user);
      if (register) {
        System.out.println(basePath + "login.jsp");
        response.sendRedirect(basePath + "login.jsp");
      } else {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("注册失败");
      }
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }


/*  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      String method=req.getParameter("method");
      if("login".equals(method)){
          login(req,resp);
      }else if("register".equals(method)){
          register(req,resp);
      }
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        doGet(req,resp);
  }*/
}
