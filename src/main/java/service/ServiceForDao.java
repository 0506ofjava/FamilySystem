package service;

import dao.FamilyDao;

import java.sql.SQLException;

/**
 * @author yq
 */
public class ServiceForDao {
    private FamilyDao familyDao;
    public ServiceForDao(){
        this.familyDao=new FamilyDao();
    }

    public void queryAll() throws SQLException {
        familyDao.queryAll();
    }
}
