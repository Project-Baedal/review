package com.baedal.review.adapter.web.in.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAttachment;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Store;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T19:57:26+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ReviewDetailMapperImpl implements ReviewDetailMapper {

    @Override
    public ReviewDetail.ReviewerInfo toReviewerInfo(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long reviewerId = null;
        String nickname = null;

        reviewerId = customer.id();
        nickname = customer.nickname();

        ReviewDetail.ReviewerInfo reviewerInfo = new ReviewDetail.ReviewerInfo( reviewerId, nickname );

        return reviewerInfo;
    }

    @Override
    public ReviewDetail.StoreInfo toStoreInfo(Store store) {
        if ( store == null ) {
            return null;
        }

        Long storeId = null;
        String name = null;

        storeId = store.id();
        name = store.name();

        ReviewDetail.StoreInfo storeInfo = new ReviewDetail.StoreInfo( storeId, name );

        return storeInfo;
    }

    @Override
    public ReviewDetail.AttachmentInfo toAttachmentInfo(ReviewAttachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        Long attachmentId = null;
        String url = null;

        attachmentId = attachment.getId();
        url = attachment.getUrl();

        ReviewDetail.AttachmentInfo attachmentInfo = new ReviewDetail.AttachmentInfo( attachmentId, url );

        return attachmentInfo;
    }

    @Override
    public List<ReviewDetail.AttachmentInfo> toAttachmentInfoList(List<ReviewAttachment> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewDetail.AttachmentInfo> list1 = new ArrayList<ReviewDetail.AttachmentInfo>( list.size() );
        for ( ReviewAttachment reviewAttachment : list ) {
            list1.add( toAttachmentInfo( reviewAttachment ) );
        }

        return list1;
    }
}
