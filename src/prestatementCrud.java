//prestatement的crud
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class prestatementCrud {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigudb?user=root&password=root");

        String sql = "insert into `order`(order_id,order_name) values (?,?);";//注意加着重号

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,5);
        statement.setObject(2,"trash");

        int rows = statement.executeUpdate();

        if(rows > 0){
            System.out.println(rows);
        }
        else{
            System.out.println("insert failed");
        }

        statement.close();
        connection.close();
    }
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigudb?user=root&password=root");

        String sql = "update `order` set order_name = ? where order_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setObject(1,"update1");
        statement.setObject(2,4);

        int rows = statement.executeUpdate();

        if(rows > 0){
            System.out.println(rows);
        }
        else{
            System.out.println("update failed");
        }
        statement.close();
        connection.close();

    }
    @Test
    public void testDelete(){

    }
    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigudb?user=root&password=root");

        String sql = "select * from `order`";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();//获取ResultSet和metadata
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<Map> list = new ArrayList<Map>();

        while(resultSet.next()){
            Map map = new HashMap();
            for(int i = 1; i <= columnCount; i++){
                map.put(metaData.getColumnLabel(i),resultSet.getObject(i));//最好是getColumnLabel，可以获取别名
            }
            list.add(map);

        }

        System.out.println("list=" + list);

        statement.close();
        connection.close();
    }
}
