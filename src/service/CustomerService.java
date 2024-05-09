package service;

import model.Customer;

public abstract class CustomerService extends UserService {

    public CustomerService(Customer customer) {
        super(customer);
    }
}
