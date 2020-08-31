package com.smartacademy.mvcpattern2.controller;

import com.smartacademy.mvcpattern2.model.customermodel.Customer;
import com.smartacademy.mvcpattern2.model.getcustomer.GetCustomerResponse;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerImpl implements Controller {

    private static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(1, "Jack", LocalDate.now(), "NY", "jack@g.com", "1234"));
        customers.add(new Customer(2, "Marry", LocalDate.now(), "Alaska", "marry@g.com", "8087"));
        customers.add(new Customer(3, "John", LocalDate.now(), "CA", "john@g.com", "6565"));
        customers.add(new Customer(4, "Mike", LocalDate.now(), "NY", "mike@g.com", "12444"));
    }



    @Override
    public List<GetCustomerResponse> getCustomers(Optional<Integer> customerId,
                                                  Optional<String> phoneNumber,
                                                  HttpServletResponse httpServletResponse) {
        // customerId case
        if(customerId.isPresent() && phoneNumber.isEmpty()){
            //processing
            // create response list
            List<GetCustomerResponse> responseList = new ArrayList<>();
            //customerId is found in the list
            for(Customer customer: customers){

                //customer found, send back response
                if(customer.getCustomerId().equals(customerId.get())){
                    GetCustomerResponse response = new GetCustomerResponse();
                    //preparing response
                    response.setCustomerId(customerId.get());
                    response.setCustomerName(customer.getCustomerName());
                    response.setDateOfBirth(customer.getDateOfBirth());
                    response.setAddress(customer.getAddress());
                    response.setEmail(customer.getEmail());
                    response.setPhoneNo(customer.getPhoneNo());
                    //add to list
                    responseList.add(response);
                }
            }
            //check if there any customers
            if(responseList.isEmpty()){
                //no customers found
                httpServletResponse.setStatus(404);
            }
            //if there are customers, send 200
            else {
                httpServletResponse.setStatus(200);
                return responseList;
            }
        }
        // phoneNumber case
        else if(customerId.isEmpty() && phoneNumber.isPresent()){

        }
        //error case
        else{
            //processing
        }
        return null;
    }
}
