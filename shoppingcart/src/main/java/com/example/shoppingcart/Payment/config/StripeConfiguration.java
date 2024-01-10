// package com.example.shoppingcart.payment.config;

// import com.stripe.Stripe;
// import jakarta.annotation.PostConstruct;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.stereotype.Component;

// /**
//  * This record responsible for displaying the Stripe Api configuration in Spring as a bean. Configuration stores special keys with which Stripe controls data security.
//  *
//  * @param secretKey used for security work with the Stripe Api from the backend side.
//  * @param publishableKey used for security work with the Stripe Api from the frontend side.
//  * @param webHookSecretKey used for security work with the Stripe Api from the webhooks side.
//  * @param currency used for provide currency for Stripe Api.
//  */
// @Component
// @ConfigurationProperties(prefix = "stripe.api")
// public record StripeConfiguration(String secretKey, //
//         String publicKey, //
//         String webHookSecretKey, //
//         String currency
//         ) {

//     @PostConstruct
//     private void init() {
//         setStripeKey(secretKey);
//     }

//     public static synchronized void setStripeKey(String stripeKey) {
//         Stripe.apiKey = stripeKey;
//     }
// }
