// package com.example.shoppingcart.payment.converter;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.MappingConstants;

// import java.util.Set;

// @Mapper(uses = ShoppingCartItemDtoConverter.class , componentModel = MappingConstants.ComponentModel.SPRING)
// public interface PaymentConverter {

//     @Mapping(target = "items", source = "shoppingCartItems", qualifiedByName = {"toShoppingCartItemDto"})
//     ProcessedPaymentDetailsDto toDto(final Payment payment,
//                                      final Set<ShoppingCartItem> shoppingCartItems);

// }
