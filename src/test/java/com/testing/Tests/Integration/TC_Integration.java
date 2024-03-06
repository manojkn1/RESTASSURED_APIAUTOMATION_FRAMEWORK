package com.testing.Tests.Integration;


import com.google.gson.Gson;
import com.testing.Base.BaseTest;
import com.testing.Payloads.pojos.Booking;
import com.testing.endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TC_Integration extends BaseTest {

    String token;

    String bookingId;
   //public TC_Integration tcIntegration;

    //Get Token

    //Create a booking

    @Test(groups = "p0")
    public void testCreateBooking(){
        token=getToken();
        Assert.assertNotNull(token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_URL);
        response= RestAssured.given().spec(requestSpecification)
                .body(payloadManager.createPayload()).when().post();
        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(200);
       jsonPath= jsonPath.from(response.asString());
       bookingId= jsonPath.getString("bookingid");
        System.out.println("Booking id is--->"  +bookingId);
        System.out.println("Token is--->"+token);


    }

    //update booking by ID
    @Test(groups = "p0", dependsOnMethods = {"testCreateBooking"})
   public void testUpdateBooking(){

         requestSpecification.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingId);
         response= RestAssured.given().spec(requestSpecification).cookie("token",token)
                 .body(payloadManager.updatePayload()).when().put();
         validatableResponse =response.then().log().all();
         validatableResponse.statusCode(200);
        String jsonString= response.asString();

        // Validate Response ( JSON String -> Object)
        Gson gson=new Gson();

        Booking bookingResponseObject=gson.fromJson(jsonString,Booking.class);
        bookingResponseObject.getLastname().contentEquals("KN");

        assertThat(bookingResponseObject.getLastname()).isEqualTo("KN");
        assertThat(bookingResponseObject.getFirstname()).isNotEmpty();
        assertThat(bookingResponseObject.getTotalprice()).isNotNull();


        /*jsonPath= jsonPath.from(response.asString());
        String lastname= jsonPath.getString("lastname");
        System.out.println("Lastname is--->"  +lastname);
        Assert.assertEquals(lastname,"KN");*/


    }
    @Test(groups = "p0", dependsOnMethods = {"testUpdateBooking"})
    public void testGetBookingByID(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingId);
        response= RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(200);
        String response=validatableResponse.toString();
        System.out.println(response);
        validatableResponse.body("lastname", Matchers.is("KN"));
        System.out.println("Bokking ID is--->"+bookingId);




    }

    @Test(groups = "p0", dependsOnMethods = {"testGetBookingByID"})
    public void testDeleteBooking(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingId);
        response= RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().delete();
        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.equals("Created");


    }

    @Test(groups = "p0", dependsOnMethods = {"testDeleteBooking"})
    public void testGetBookingByIDAfterDelete(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingId);
        response= RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(404);




    }
}
