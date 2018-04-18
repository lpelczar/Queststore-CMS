package dao;

import data.DbHelper;
import model.Group;
import model.GroupModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteGroupDAO extends DbHelper implements GroupDAO {

    @Override
    public void add(GroupModel groupModel) {
        String sql = "INSERT INTO group_names(group_name_id, signature, mentor_id) VALUES(?, ?, ?);";

//        openConnection();
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, groupModel.getGroupId());
//            ps.setString(2, groupModel.getName());
//            ps.setInt(3, groupModel.getMentorId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public List<Group> getAllGroups() {
        String sqlStatement = "SELECT * FROM group_names; ";
        List<Group> groups = new ArrayList<>();

        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);

            while (resultSet.next()) {
                groups.add(
                        new Group(
                                resultSet.getInt("group_name_id"),
                                resultSet.getString("signature"),
                                resultSet.getInt("mentor_id")
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " --> " + e.getMessage());
        }
        return groups;
    }
}
