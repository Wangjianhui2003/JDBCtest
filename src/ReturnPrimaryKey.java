//主键返回显示，必须是auto_increment primary key
import java.sql.*;

public class ReturnPrimaryKey {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigudb?user=root&password=root");

        String sql = "insert into region(region_id,region_name) values (?,?);";

        PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1,2);
        statement.setObject(2,"Africa");

        int i = statement.executeUpdate();

        if(i>0){
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            System.out.println(generatedKeys.getInt(1));
        }
        else{
            System.out.println("failed to insert");
        }

        statement.close();
        connection.close();
    }
}
