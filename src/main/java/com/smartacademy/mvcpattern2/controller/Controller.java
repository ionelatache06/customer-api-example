package com.smartacademy.mvcpattern2.controller;

import com.smartacademy.mvcpattern2.model.getcustomer.GetCustomerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface Controller {

    @GetMapping("/getCustomer")
    List<GetCustomerResponse> getCustomers(@RequestParam Optional<Integer> customerId,
                                           @RequestParam Optional<String> phoneNumber,
                                           HttpServletResponse httpServletResponse);

}
