package servlet;

import bean.User;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/** @author yq */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
      System.out.println(basePath+"member-list.jsp");
      response.sendRedirect(basePath + "member-list.jsp");
    }
  }
}
