package service;

import dao.BillDao;
import dao.MemberDao;
import model.Bill;
import model.Member;
import model.User;

import java.util.List;

public class MemberService extends CustomerService{
    private BillDao billDao;
    private MemberDao memberDao;

    public MemberService(Member member) {
        super(member);
        billDao = new BillDao();
        memberDao = new MemberDao();
    }

    public List<Bill> getBillList() {
        return billDao.getMemberBillList(getUser().getId());
    }

    @Override
    public User logIn(String userName, String password) {
        return memberDao.getAuthenticated(userName, password);
    }
}
