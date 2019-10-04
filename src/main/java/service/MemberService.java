package service;

import bean.Page;
import dao.MemberDao;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;

/** @author yq */
public class MemberService {

  /**
   * 传递page对象
   *
   * @param currentPage 当前页码
   * @param pageSize 页面大小
   * @return 当前页面page对象，包含pageSize、currentPage、totalPage、totalCount和成员信息集合
   * @throws SQLException 抛出sql异常
   */
  public Page<Member> findPageMember(int currentPage, int pageSize) throws SQLException {
    Page<Member> page = new Page<>();
    MemberDao memberDao = new MemberDao();

    // 利用Dao层查询总数目
    int totalCount = memberDao.queryCount();
    // 利用总数目与页面大小的关系，求出总页码
    int totalPage = (int) Math.ceil(1.0 * totalCount / pageSize);

    // 当前页面起始位置
    int startPosition = (currentPage - 1) * pageSize;
    // 获取当前页面成员信息集合
    List<Member> memberList = memberDao.queryPageMemberList(startPosition, pageSize);


    page.setList(memberList);
    // 当前页码和页面大小为传递值
    page.setPageSize(pageSize);
    page.setCurrentPage(currentPage);
    page.setTotalPage(totalPage);
    page.setTotalCount(totalCount);
    System.out.println("service：page属性封装完毕");

    return page;
  }
}
