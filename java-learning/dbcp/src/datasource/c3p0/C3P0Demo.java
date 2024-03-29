package datasource.c3p0;

/**
 * @author meihewang
 * @date 2019/5/31 00310:55
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0的演示
 */
public class C3P0Demo {
    public static void main(String[] args) throws SQLException {
        //1.创建数据库连接池对象
        DataSource ds  = new ComboPooledDataSource();
        //2. 获取连接对象
        Connection conn = ds.getConnection();
        //3. 打印
        System.out.println(conn);

    }
}
