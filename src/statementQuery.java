//基于statement查询
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class statementQuery {
    public static void main(String[] args) throws SQLException {
        //导入驱动
        DriverManager.registerDriver(new Driver());//Class.forName("Driver");
        //建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigudb","root","root");
        //建立statement
        Statement statement = connection.createStatement();
        //发送查询并用resultset接收
        String query1 = "select * from countries";
        ResultSet resultSet = statement.executeQuery(query1);
        //循环显示
        while(resultSet.next()){
            String country_id = resultSet.getString("country_id");
            System.out.println(country_id);
        }
        //释放
        resultSet.close();
        statement.close();
        connection.close();

    }
}
