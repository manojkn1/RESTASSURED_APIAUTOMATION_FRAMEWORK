package com.testing.Modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.testing.Payloads.pojos.*;


public class PayloadManager {
    String expFirstname;




    public String createPayload(){

          Faker faker= new Faker();

        Booking booking= new Booking();
       expFirstname =faker.name().firstName();
        booking.setFirstname(expFirstname);
        booking.setLastname("brown");
        booking.setTotalprice(100);
        booking.setAdditionalneeds("breakfast");
        booking.setDepositpaid(true);

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingdates);
        // Object -> JSON String (GSON)
        Gson gson=new Gson();
        String jsonBookingPayload=gson.toJson(booking);
        System.out.println(jsonBookingPayload);
        return  jsonBookingPayload;



    }

    public String creatTokenPayload(){
        CreateAuth createAuth=new CreateAuth();
        createAuth.setUsername("admin");
        createAuth.setPassword("password123");

        Gson gson=new Gson();
        String jsonCreateTokenPayload=gson.toJson(createAuth);

        return jsonCreateTokenPayload;
    }

    public String updatePayload() {
        Faker faker= new Faker();

        Booking booking= new Booking();
        booking.setFirstname(expFirstname);
        booking.setLastname("KN");
        booking.setTotalprice(100);
        booking.setAdditionalneeds("breakfast");
        booking.setDepositpaid(true);

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingdates);
        // Object -> JSON String (GSON)
        Gson gson=new Gson();
        String jsonUpdateBookingPayload=gson.toJson(booking);
        System.out.println(jsonUpdateBookingPayload);
        return  jsonUpdateBookingPayload;

    }
}
