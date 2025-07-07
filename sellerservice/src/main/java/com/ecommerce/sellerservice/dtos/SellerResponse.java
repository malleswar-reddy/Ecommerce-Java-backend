package com.ecommerce.sellerservice.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellerResponse {
    private Long id;
    private String name;
    private String email;
    private String contactNumber;
    private boolean verified;
}
