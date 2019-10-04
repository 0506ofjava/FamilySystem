package servlet;

import bean.Page;
import service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/** @author yq */

@WebServlet(name = "MemberServlet", urlPatterns = "/member")
public class MemberServlet extends BaseServlet {
    public void getMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int currentCount = Integer.parseInt(request.getParameter("currentCount"));

        if (currentCount == 0) {
            System.out.println("设置默认页面大小为10");
            currentCount = 10;
        }
        if (currentPage == 0) {
            System.out.println("设置默认页码为1");
            currentPage = 1;
        }

        MemberService memberService = new MemberService();

        // service返回page对象
        Page page = null;
        try {
            page = memberService.findPageMember(currentPage, currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (page != null) {
            System.out.println("页面不为空");
            request.setAttribute("page", page);
                request.getRequestDispatcher("/member-list.jsp").forward(request, response);
        } else {
                request.getRequestDispatcher("/member-list.jsp").forward(request, response);
        }
    }
}
