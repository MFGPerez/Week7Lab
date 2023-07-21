package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author mfgperez
 */
public class UserDB {


    private DBUtil dbUtility = new DBUtil();

    public UserDB() {

    }

    public ArrayList<User> getAllUsers() throws Exception {

        ArrayList<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user"; 

        try {
            ps = con.prepareStatement(sql);
            // ps.setString(1, owner); // set ? in query  
            rs = ps.executeQuery();

            while (rs.next()) {

                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                //String role = rs.getString(4);
                //int role = rs.getInt(4);

                User user = new User(email, firstName, lastName);
                users.add(user);
            }
        } finally {
            DBUtil.closePreparedStatement(ps); // equivalent to ps.close()
            DBUtil.closeResultSet(rs); // equivalent to rs.close()
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(int role) throws Exception {

        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE role_id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            rs = ps.executeQuery();
            if (rs.next()) {

                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                //String roleName = rs.getString(4);
                  int roleName = rs.getInt(4);

                user = new User(email, firstName, lastName, roleName);

            }
        } finally {

            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            cp.freeConnection(con);
        }

        return user;
    }

    public void insert(User user) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user (email, first_name, last_name , password, role) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRoleName());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET first_name=?, last_name=?, password=?, role_name=? WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE role_id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getRoleID());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
