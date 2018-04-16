package model;

import java.util.ArrayList;

public class WalletModel {

    private int studentId;
    private int balance;
    private int totalEarned;
    private ArrayList<ArtefactModel> artefacts;

    public WalletModel(int studentId, int balance, int totalEarned,
                       ArrayList<ArtefactModel> artefacts) {
        this.studentId = studentId;
        this.balance = balance;
        this.totalEarned = totalEarned;
        this.artefacts = artefacts;
    }

//    public WalletModel(int lastStudentId) {
//        this.studentId = ++lastStudentId;
//        this.balance = 0;
//        this.totalEarned = 0;
//        this.artefacts = new ArrayList<>();
//    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(int totalEarned) {
        this.totalEarned = totalEarned;
    }

    public ArrayList<ArtefactModel> getArtefacts() {
        return artefacts;
    }

    @Override
    public String toString() {
        return String.format("Student wallet\nbalance: %d\ntotal earned coolcoins: %d", balance, totalEarned);
    }
}
