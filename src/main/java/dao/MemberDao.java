package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.Member;
import java.sql.SQLException;
import java.util.List;

/** @author yq */
public class MemberDao {

  /**
   * @return 返回成员信息总条目
   * @throws SQLException sql语句异常
   */
  public int queryCount() throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "select count(*) from family";
    Long query = queryRunner.query(sql, new ScalarHandler<>());
    System.out.println("总条目已查询完毕,结果为："+query);
    return query.intValue();
  }

  /** @return 返回当前页面成员信息集合 */
  public List<Member> queryPageMemberList(int startPosition, int pageSize) throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "select * from family limit ?,?";
    List<Member> memberList =
        queryRunner.query(sql, new BeanListHandler<>(Member.class), startPosition, pageSize);
    System.out.println("单页成员链表查询 运行");
    return memberList;
  }

  public boolean addMember(Member member) throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "insert into family(id,name,sex,age,type) values(null,?,?,?,?)";
    int row=queryRunner.update(sql,member.getName(),member.getSex(),member.getAge(),member.getType());
    if(row>0){
      return true;
    }else{
      return false;
    }
  }

  public List<Member> queryMemberList() throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "select * from family";
    List<Member> memberList=queryRunner.query(sql,new BeanListHandler<Member>(Member.class));
    return memberList;
  }

  public boolean updateMember(Member member) throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "update family set name=?,sex=?,age=?,type=? where id=?";
    int row=queryRunner.update(sql,member.getName(),member.getSex(),member.getAge(),member.getType(),member.getId());
    if(row>0){
      return true;
    }else{
      return false;
    }
  }

  public boolean deleteMember(Member member) throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
    String sql = "delete from family where id=?";
    int row=queryRunner.update(sql,member.getId());
    if(row>0){
      return true;
    }else{
      return false;
    }
  }
}
