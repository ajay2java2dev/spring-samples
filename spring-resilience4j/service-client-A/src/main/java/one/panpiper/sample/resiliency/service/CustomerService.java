package one.panpiper.sample.resiliency.service;

import one.panpiper.sample.resiliency.representation.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CustomerService {

    private List<Customer> dummyCustomers () {

        var customer1 = Customer.builder()
                .customerId(123l)
                .customerName("Kalathil Ajay")
                .dateOfBirth(LocalDate.of(1988,1,1))
                .fromDate(LocalDate.now()).lastLogin(LocalDate.now())
                .build();

        var customer2 = Customer.builder()
                .customerId(421l)
                .customerName("Pradeep Nair")
                .dateOfBirth(LocalDate.of(1988,1,1))
                .fromDate(LocalDate.now()).lastLogin(LocalDate.now())
                .build();

        return Stream.of(customer1, customer2).toList();
    }

    public Optional<List<Customer>> retrieveCustomers() {
        return Optional.ofNullable(dummyCustomers());
    }

    public Optional<Customer> retrieveCustomerByCustomerId (Long customerId) {
        if (customerId != null) {
            var customers = retrieveCustomers();
            if (customers.isPresent()) {
                return customers.get().stream()
                        .filter(customer -> customerId.equals(customer.getCustomerId()))
                        .findFirst();
            }
        }
        return Optional.empty();
    }
}
