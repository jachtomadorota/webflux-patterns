package com.vinsguru.webfluxpatterns.sec01.service;

import com.vinsguru.webfluxpatterns.sec01.client.ProductClient;
import com.vinsguru.webfluxpatterns.sec01.client.PromotionClient;
import com.vinsguru.webfluxpatterns.sec01.client.ReviewClient;
import com.vinsguru.webfluxpatterns.sec01.dto.Price;
import com.vinsguru.webfluxpatterns.sec01.dto.ProductAggregator;
import com.vinsguru.webfluxpatterns.sec01.dto.ReviewsWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductAggregatorService {

    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;


    public Mono<ProductAggregator> aggregateById(Integer id) {
        return Mono.zip(productClient.getProductById(id), promotionClient.getPromotionById(id),
                        reviewClient.getReviewById(id))
                .map(tuple -> {
                    var price = tuple.getT1().price();
                    var discount = tuple.getT2().discount().doubleValue();
                    var amountSaved = (double) (price * discount / 100);
                    return ProductAggregator.builder()
                            .id(id)
                            .category(tuple.getT1().category())
                            .description(tuple.getT1().description())
                            .endDate(tuple.getT2().endDate())
                            .price(Price.builder()
                                    .price(price)
                                    .discount(discount)
                                    .amountSaved(amountSaved)
                                    .discountedPrice(price - amountSaved)
                                    .build())
                            .reviews(ReviewsWrapper.builder()
                                    .reviews(tuple.getT3().reviews())
                                    .build())
                            .build();
                });
    }
}
