package org.lrd.customerapi.controller;


import org.lrd.customerapi.model.Customer;
import org.lrd.customerapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class CustomerController {


    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(
            value = "/customer/get",
            params = { "firstName", "lastName" },
            method = GET)
    public String getCustomer(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        String[] customer = customerRepository.fetchCustomer(firstName, lastName).split(",");
        return String.format("{\"firstName\":\"%s\",\"lastName\":\"%s\"}", customer[0], customer[1]);
    }

    @PostMapping("/customer/add")
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Successfully added entry\"}");
    }

    @DeleteMapping("/customer/delete")
    public ResponseEntity deleteCustomer(@Valid @RequestBody Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        customerRepository.deleteCustomer(firstName, lastName);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Successfully deleted customer\"}");
    }

}