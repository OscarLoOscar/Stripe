package com.example.shoppingcart.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.shoppingcart.model.TransactionData;
import com.example.shoppingcart.model.TransactionUpdateResponse;

public interface TransactionController {

        // 7. POST /transaction/prepare: Create a new transaction (Preparing state)
        @PostMapping("/prepare")
        @ResponseStatus(HttpStatus.CREATED)
        public TransactionData createTransaction(JwtAuthenticationToken jwt);

        // 8. GET    /transaction/{tid}: Get transaction details by ID
        @GetMapping("/{tid}")
        @ResponseStatus(HttpStatus.OK)
        public TransactionData getTransactionDetailByTransactionId(
                        @PathVariable(name = "tid") Long transactionId,
                        JwtAuthenticationToken jwt);

        // 9. PATCH  /transaction/{tid}/pay: Update the transaction status (PROCESSING)
        @PatchMapping("/{tid}/pay")
        @ResponseStatus(HttpStatus.PROCESSING)
        public ResponseEntity<TransactionUpdateResponse> updateTransactionToProcessing(
                        @PathVariable Long tid, JwtAuthenticationToken jwt);

        // 10. PATCH  /transaction/{tid}/finish
        @PatchMapping("/{tid}/finish")
        @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
        public ResponseEntity<TransactionData> finishTransaction(
                        @PathVariable Long tid, JwtAuthenticationToken jwt);

        // 11. GET    /transaction: Get ALL transaction details
        @GetMapping("/allTransaction")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<TransactionData>> getAllTransactionDetail(
                        JwtAuthenticationToken jwt);


}
