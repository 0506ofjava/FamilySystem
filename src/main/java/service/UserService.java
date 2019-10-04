package service;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;

/** @author yq */
public class UserService {
  private UserDao userDao = new UserDao();

  public Boolean register(User user) throws SQLException {
    Boolean regiser = false;
    boolean checkUser = userDao.checkUser(user.getName());
    // 如果用户不存在，则将其加入数据库
    if (checkUser) {
      regiser = userDao.register(user);
    }
    return regiser;
  }

  public User login(String username, String password) throws SQLException {
    User user = null;
    System.out.println("UserService执行login方法");
    user = userDao.login(username, password);
    return user;
  }
}
