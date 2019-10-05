package servlet;

import bean.Member;
import bean.Page;
import org.apache.commons.beanutils.BeanUtils;
import service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/** @author yq */
@WebServlet(name = "MemberServlet", urlPatterns = "/member")
public class MemberServlet extends BaseServlet {

  public void addMember(HttpServletRequest request, HttpServletResponse response)
      throws InvocationTargetException, IllegalAccessException, ServletException, IOException,
          SQLException {
    // 获取参数集合等属性
    Map<String, String[]> parameterMap = request.getParameterMap();
    Member member = new Member();
    BeanUtils.populate(member, parameterMap);

    MemberService memberService = new MemberService();
    boolean isAdd = memberService.addMember(member);
    if (isAdd) {
      response.setStatus(201);
      request.getRequestDispatcher("/member?method=getMemberList&currentPage=1").forward(request, response);
    } else {
      response.setStatus(600);
      request.getRequestDispatcher("/member-list.jsp").forward(request, response);
    }
  }

  public void getMemberList(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("MemberServlet运行了");
    System.out.println("页面获取的currentPage为：" + request.getParameter("currentPage"));
    System.out.println("页面获取的pageSize为：" + request.getParameter("pageSize"));

    String curPage = request.getParameter("currentPage");
    if (curPage == null) {
      curPage = "1";
    }
    int currentPage = Integer.parseInt(curPage);
    int pageSize = 10;

    MemberService memberService = new MemberService();

    // service返回page对象
    Page page = null;
    try {
      page = memberService.findPageMember(currentPage, pageSize);
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

  public void updateMember(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, InvocationTargetException, IllegalAccessException,
          SQLException {

    Map<String, String[]> parameterMap = request.getParameterMap();
    Member member = new Member();
    BeanUtils.populate(member, parameterMap);
    MemberService memberService = new MemberService();
    System.out.println("updateMember方法执行了");
    boolean isUpdate = memberService.updateMember(member);
    System.out.println("updateMember方法执行了，isUpdate");
    if (isUpdate) {
      response.sendRedirect(request.getContextPath() + "/member?method=getMemberList");
    } else {
      response.setContentType("text/html;charset=utf-8");
      response.getWriter().write("修改失败");
    }
  }

  public void deleteMember(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, InvocationTargetException, IllegalAccessException,
          SQLException {
    Map<String, String[]> parameterMap = request.getParameterMap();
    Member member = new Member();
    BeanUtils.populate(member, parameterMap);
    MemberService memberService = new MemberService();
    System.out.println("updateMember方法执行了");
    boolean isDelete = memberService.deleteMember(member);
    System.out.println("updateMember方法执行了，isDelete");
    if (isDelete) {
      response.sendRedirect(request.getContextPath() + "/member?method=getMemberList");
    } else {
      response.setContentType("text/html;charset=utf-8");
      response.getWriter().write("删除失败");
    }
  }

  /*    public void getMemberList(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException, SQLException{
      MemberService memberService=new MemberService();
      List<Member> memberList=memberService.findMember();
      if(memberList!=null&&memberList.size()>0){
          request.setAttribute("list",memberList);
          request.getRequestDispatcher("/member-list.jsp").forward(request,response);
      }else {
          request.getRequestDispatcher("/member-list.jsp").forward(request,response);
      }
  }*/

}
