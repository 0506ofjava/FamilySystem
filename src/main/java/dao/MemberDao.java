package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;

/** @author yq */
public class MemberDao {

  /**
   *
   * @return 返回成员信息总条目
   * @throws SQLException sql语句异常
   */
  public int queryCount() throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "select count(*) from family";
    Long query = queryRunner.query(sql, new ScalarHandler<>());
    System.out.println("总条目已查询完毕");
    return query.intValue();
  }


  /**
   * @return 返回当前页面成员信息集合
   */
  public List<Member> queryPageMemberList(int startPosition, int pageSize) throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "select * from family limit ?,?";
    List<Member> memberList =
        queryRunner.query(
            sql, new BeanListHandler<Member>(Member.class), startPosition, pageSize);
    System.out.println("单页成员链表查询 运行");
    return memberList;
  }
}
