package one.panpiper.sample.resiliency.representation;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Customer {
    private Long customerId;
    private String customerName;
    private LocalDate dateOfBirth;
    private LocalDate fromDate;
    private LocalDate lastLogin;
}
