package servlet;

import bean.User;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author yq
 */

@WebServlet(name = "RegisterServlet" ,urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+
                request.getContextPath()+"/";

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);


        UserService userService=new UserService();
        try {
            boolean register=userService.register(user);
            if(register){
                System.out.println(basePath+"login.jsp");
                response.sendRedirect(basePath+"login.jsp");
            }else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("注册失败");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){

    }
}
