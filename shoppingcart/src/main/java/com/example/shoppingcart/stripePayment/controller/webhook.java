package com.example.shoppingcart.stripePayment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nimbusds.jose.shaded.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/public")
public class webhook {
@PostMapping("/stripe/webhook")
public ResponseEntity<String> webhook(@RequestBody String payload,
@RequestHeader("Stripe-Signature") String stripeSignatureHeader) {
Event event;
// Stripe CLI secret
final String END_POINT_SECRET = "<<end_point_secret_provided>>";
 // Step 1
try {
event = Webhook.constructEvent(payload, stripeSignatureHeader,
END_POINT_SECRET);
} catch (SignatureVerificationException e) {
System.out.println("Failed signature verification");
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
} catch (JsonSyntaxException e) {
System.out.println("Json syntax error");
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
}
 // Step 2
EventDataObjectDeserializer dataObjectDeserializer =
event.getDataObjectDeserializer();
StripeObject stripeObject = null;
if(dataObjectDeserializer.getObject().isPresent()) {
stripeObject = dataObjectDeserializer.getObject().get();
} else {
System.out.println("EventDataObjectDeserializer failed");
}
// Step 3
if ("checkout.session.completed".equals(event.getType())) {
// complete checkout session
// Put your session completed handling here
} else {
// log other unhandled event
log.error("Unhandled event type: " + event.getType());
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
}
return new ResponseEntity<>("Success", HttpStatus.OK);
}}
