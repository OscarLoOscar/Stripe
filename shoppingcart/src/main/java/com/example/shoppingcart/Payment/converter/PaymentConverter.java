package com.example.shoppingcart.Payment.converter;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ShoppingCartItemDtoConverter.class , componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentConverter {

    @Mapping(target = "items", //
    source = "shoppingCartItems",//
     qualifiedByName = {"toShoppingCartItemDto"})
    ProcessedPaymentDetailsDto toDto(final Payment payment,
                                     final Set<ShoppingCartItem> shoppingCartItems);

}