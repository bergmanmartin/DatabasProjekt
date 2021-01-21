package LoginView;

import javax.swing.table.DefaultTableModel;
import java.net.UnknownHostException;
import java.sql.*;

public class DBConnection {

    AdminView view;
    private static final String user = "marre";
    private static final String pswrd = "970321";
    private static final String url = "com.microsoft.sqlserver.jdbc.SQLServerDriver";



    public Connection createConnecttion() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String user = "Erik";
        String pw = "redred34";

        String url = "jdbc:sqlserver://localhost";

        Connection conn = DriverManager.getConnection(url, user, pw);

        if (conn != null) {
            System.out.println("Connected");
            return conn;
        }
        return conn;

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pswrd);
    }


    public void testQuery() {
        try {
            Connection connection = createConnecttion();
            Statement statement = connection.createStatement();
            String selectQuery = "Select * from Hospital.dbo.DOCTOR_REGISTER";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel retrieveData() {
        DefaultTableModel docs = view.getDoctorsCol();
        try {
            Connection con = createConnecttion();
            Statement statement = con.createStatement();

            String sql = "Select * from Hospital.dbo.DOCTOR_REGISTER";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                String d = rs.getString("dr_id");
                String e = rs.getString("dr_skill");
                String f = rs.getString("dr_price");
                String g = rs.getString("dr_phoneNumber");
                String h = rs.getString("dr_name");
                docs.addRow(new Object[] {d, e, f, g, h } );
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        return docs;
    }

    public static void main(String[] args) {
        DBConnection sql = new DBConnection();
        try {
            sql.testQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
