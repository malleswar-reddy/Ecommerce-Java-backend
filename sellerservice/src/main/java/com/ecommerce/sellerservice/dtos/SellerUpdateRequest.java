package com.ecommerce.sellerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerUpdateRequest {
    private Long id;
    private String name;
    private String email;
    private String contactNumber;
}
