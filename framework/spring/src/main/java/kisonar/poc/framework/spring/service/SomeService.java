package kisonar.poc.framework.spring.service;

import kisonar.poc.framework.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeService {

    CustomerRepository customerRepository;

    @Autowired
    public SomeService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}