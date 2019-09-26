package dao;

import util.db.DbForMysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author yq
 */
public class FamilyDao {
    /**
     * 获取数据库myFirstDb连接对象
     */
    private Connection connection=new DbForMysql().getConnection();
    /**
     * 遍历全表，对全体对象进行查询
     */
    public void queryAll() throws SQLException {
        String sql="select * from family";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id")+" "+resultSet.getString("name")+" "
                            +resultSet.getString("sex")+" "+resultSet.getInt("age")+" "
                            +resultSet.getString("relationship"));
        }
    }
}
