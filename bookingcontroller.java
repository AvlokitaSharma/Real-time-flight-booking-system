package com.flightbooking.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.flightbooking.producer.FlightBookingProducer;

@RestController
public class BookingController {
    
    @Autowired
    private FlightBookingProducer producer;
    
    @PostMapping("/bookFlight")
    public String bookFlight(@RequestBody String bookingDetails) {
        // Assuming 'flight-booking-requests' is your Kafka topic
        producer.sendBookingRequest("flight-booking-requests", bookingDetails);
        return "Booking request sent";
    }
}
