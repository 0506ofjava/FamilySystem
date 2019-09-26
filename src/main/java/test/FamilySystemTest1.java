package test;

import service.ServiceForDao;

import java.sql.SQLException;

/**
 * @author yq
 */
public class FamilySystemTest1 {
    public static void main(String[] args) throws SQLException {
        ServiceForDao serviceForDao=new ServiceForDao();
        serviceForDao.queryAll();
    }
}
