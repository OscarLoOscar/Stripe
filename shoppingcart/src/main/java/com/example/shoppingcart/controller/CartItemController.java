package com.example.shoppingcart.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.shoppingcart.exception.CartItemNotFoundException;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.model.CartItemData;
import com.example.shoppingcart.model.TransactionUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface CartItemController {
        // 3.Add New Item To Cart. If Cart Item existed, add quantity.
        // Add quantity only, do not reduce stock
        @PutMapping("/{pid}/{quantity}")
        @ResponseStatus(HttpStatus.CREATED)
        public TransactionUpdateResponse addCartItem(JwtAuthenticationToken jwt, //
                        @PathVariable(name = "pid") String inputPid, //
                        @PathVariable(name = "quantity") String inputQuantity)
                        throws ProductNotExistException, UserNotExistException;

        // 4.Get User Cart Items
        @Operation(summary = "Get all the Cart Item by user JWT")
        @ApiResponses({//
                        @ApiResponse(responseCode = "200", //
                                        content = {@Content(schema = @Schema(
                                                        implementation = CartItemData.class))})})
        @GetMapping
        @ResponseStatus(HttpStatus.ACCEPTED)
        public List<CartItemData> getUserCartItems(JwtAuthenticationToken jwt);

        // 5. PATCH  /cart/{pid}/{quantity}: Update cart quantity
        @Operation(summary = "Update cart quantity")
        @PatchMapping("/{pid}/{quantity}")
        public CartItemData updateCartQuantity(
                        @PathVariable(name = "pid") String inputPid, //
                        @PathVariable(name = "quantity") String inputQuantity, //
                        JwtAuthenticationToken jwt)
                        throws ProductNotExistException, UserNotExistException;

        // 6.DELETE /cart/{cid}: Remove cart item
        @DeleteMapping("/{cid}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        TransactionUpdateResponse removeCartItem(
                        @PathVariable(name = "cid") String inputPid, //
                        JwtAuthenticationToken jwt)
                        throws NumberFormatException, UserNotExistException,
                        CartItemNotFoundException;


}
