package kisonar.poc.framework.spring.repository;

import kisonar.poc.framework.spring.domain.jpa.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}