package dao;

import model.GroupModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupDBImplement extends OpenCloseConnectionWithDB implements GroupDB {

    @Override
    public void add(GroupModel groupModel) {
        String sql = "INSERT INTO group_names(group_name_id, signature, mentor_id) VALUES(?, ?, ?);";

        openConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, groupModel.getGroupId());
            ps.setString(2, groupModel.getName());
            ps.setInt(3, groupModel.getMentorId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
