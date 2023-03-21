package one.panpiper.sample.resiliency.controller;

import one.panpiper.sample.resiliency.representation.Customer;
import one.panpiper.sample.resiliency.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<Optional<List<Customer>>> retrieveCustomers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.retrieveCustomers());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Optional<Customer>> retrieveCustomerDetails(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.retrieveCustomerByCustomerId(customerId));
    }
}
