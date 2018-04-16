package dao;

import model.WalletModel;
import model.ArtefactModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WalletDBImplement {

    private QueriesGenerator generator;
    private String artefactTableName;
    private String studentsTableName;
    private String artefactIdColumn;

    public WalletDBImplement() {
        this.generator = new QueriesGenerator();
        this.artefactTableName = "artefacts";
        this.studentsTableName = "students_artefacts";
        this.artefactIdColumn = "artefact_id";
    }

    public WalletModel loadWalletModel(int id) {

        PreparedStatement statement = generator.getUserItems(artefactTableName,
                artefactIdColumn, studentsTableName, id);
        PreparedStatement secondStatement = generator.getUserWallet(id);

        ResultSet resultSet = null;
        ResultSet secondResultSet = null;

        ArrayList<ArtefactModel> artefacts = new ArrayList<>();
        WalletModel wallet = null;

        int walletId;
        int balance;
        int totalEarned;

        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String artefactName = resultSet.getString("name");
                String artefactDescription = resultSet.getString("description");
                String artefactStatus = resultSet.getString("status_name");
                int artefactPrice = resultSet.getInt("price");
                int artefactId = resultSet.getInt("artefact_id");

                ArtefactModel artefact = new ArtefactModel(artefactName, artefactDescription,
                        artefactStatus, artefactPrice, artefactId);
                artefacts.add(artefact);
            }
            secondResultSet = secondStatement.executeQuery();
            while (secondResultSet.next()) {
                walletId = secondResultSet.getInt("student_id");
                balance = secondResultSet.getInt("balance");
                totalEarned = secondResultSet.getInt("total_earned");
                wallet = new WalletModel(walletId, balance, totalEarned, artefacts);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(100);
        }
        return wallet;
    }
}
