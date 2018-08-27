package baseinfo;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 * created by chenminqing
 */
public class DBconn {
    public static Connection getegCon() {
        Connection connect=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
//            System.out.println("Success loading Mysql Driver!");
            connect = DriverManager.getConnection("jdbc:mysql://47.97.179.224:3506/eaglehorn_engine", "root", "1017~Fulin");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return connect;
    }
}
