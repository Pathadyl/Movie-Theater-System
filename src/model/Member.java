package model;

import java.sql.Date;

public class Member extends Customer{
    private Date dob;
    private int rewardPoint;

    public Member(String userName, String password, String name, String phone, String email, Date dob, int rewardPoint) {
<<<<<<< HEAD
        super(name, phone, email);
        this.userName = userName;
        this.password = password;
        this.dob = dob;
        this.rewardPoint = rewardPoint;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

=======
        super(userName, password, name, phone, email);
    setRole(Role.CUSTOMER);
        this.dob = dob;
        this.rewardPoint = rewardPoint;
}
>>>>>>> 141aa8214ecdc7a49ae0ecc27287cea91f40a6fb
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
