package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.domain.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-22T12:20:05+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toReviewer(GetCustomerResponse response) {
        if ( response == null ) {
            return null;
        }

        String name = null;

        name = response.name();

        Long id = null;

        Customer customer = new Customer( id, name );

        return customer;
    }
}
