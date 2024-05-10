package model;

import java.sql.Date;

public class Member extends Customer{
    private Date dob;
    private int rewardPoint;

    public Member(int id, String userName, String password, String name, String phone, String email, Date dob, int rewardPoint) {
        super(id, userName, password, name, phone, email);
        setRole(Role.CUSTOMER);
        this.dob = dob;
        this.rewardPoint = rewardPoint;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }
}
