//批量插入
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsert {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigudb?rewriteBatchedStatements=true","root","root");

        String sql = "insert into `order`(order_id,order_name) values (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        for(int i = 0; i < 1000; i++) {
            statement.setObject(1,5+i);
            statement.setObject(2,"trash" + i);
            statement.addBatch();
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }
}
