package service;

import dao.BillDao;
import model.Bill;
import model.Member;
import model.User;

import java.util.List;

public class MemberService extends CustomerService{
    private BillDao billDao;

    public MemberService(Member member) {
        super(member);
        billDao = new BillDao();
    }

    public List<Bill> getBillList() {
        return billDao.getMemberBillList(getUser().getId());
    }

    @Override
    public User logIn(String userName, String password) {
        return null;
    }
}
