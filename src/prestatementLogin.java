//利用prestatement编写一个mysql登录程序

import java.sql.*;
import java.util.Scanner;

public class prestatementLogin {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //输入账号密码
        Scanner scanner = new Scanner(System.in);
        String account = scanner.nextLine();
        String password = scanner.nextLine();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigudb?user=root&password=root");

        String sql = "select * from t_user where account = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,account);
        preparedStatement.setObject(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        //检查是否存在该用户
        if(resultSet.next()) {
            System.out.println("login successfully");
        }
        else{
            System.out.println("login failed");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}