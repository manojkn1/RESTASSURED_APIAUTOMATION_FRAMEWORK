package com.testing.Tests.Crud;

import com.testing.Base.BaseTest;
import com.testing.Modules.PayloadManager;
import com.testing.endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;

public class CreateBooking extends BaseTest {

    @Owner("Manoj")
    @Description("TC#1 - Verify that the Booking can be Created")
    @Test
    public void TC1CreateBookingPositive(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_URL);
        response= RestAssured.given().spec(requestSpecification)
                .body(payloadManager.createPayload()).when().post();
                validatableResponse =response.then().log().all();
                validatableResponse.statusCode(200);


    }



}
