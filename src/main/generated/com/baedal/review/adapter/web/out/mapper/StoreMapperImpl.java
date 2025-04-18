package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetStoreResponse;
import com.baedal.review.domain.model.Store;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T20:05:41+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public Store toStore(GetStoreResponse response) {
        if ( response == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = response.id();
        name = response.name();

        Store store = new Store( id, name );

        return store;
    }
}
