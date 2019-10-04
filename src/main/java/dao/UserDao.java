package dao;

import bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/** @author yq */
public class UserDao {
  public boolean checkUser(String username) {

    // 连接池数据源ComboPoolDataSource,c3p0连接池
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    // QueryRunner是Dbutil的运行对象
    QueryRunner queryRunner = new QueryRunner(dataSource);
    String sql = "select username from user where username=?";
    try {
      /*
       *QueryRunner下的query方法，里面需要提供ResultHandler接口的实现类
       * ResultHandler实现结果集的标准化
       * preparedStatement封装了创建sql处理对象preparedStatement,并且引入了sql参数
       * fillStatement就写入了占位符所需的参数
       */
      User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
      if (user == null) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public Boolean register(User user) throws SQLException {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    // QueryRunner是Dbutil的运行对象
    QueryRunner queryRunner = new QueryRunner(dataSource);
    String sql = "insert into user values(null,?,?,?)";
    int row = queryRunner.update(sql, user.getName(), user.getPassword(), user.getEmail());
    if (row > 0) {
      return true;
    } else {
      return false;
    }
  }

  public User login(String username,String password) throws SQLException {
    System.out.println("UserDao执行login方法");
    User user=null;
    ComboPooledDataSource dataSource=new ComboPooledDataSource();
    QueryRunner queryRunner=new QueryRunner(dataSource);
    String sql="select * from user where username=? and password=?";
    user=queryRunner.query(sql,new BeanHandler<User>(User.class),username,password);
    return user;
  }
}
