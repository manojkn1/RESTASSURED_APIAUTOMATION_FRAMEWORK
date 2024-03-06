package com.testing.Base;

import com.testing.Modules.PayloadManager;
import com.testing.Payloads.pojos.CreateAuth;
import com.testing.endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {

    public RequestSpecification requestSpecification;

    public PayloadManager payloadManager;
    public Response response;

    public JsonPath jsonPath;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setConfig(){
    payloadManager = new PayloadManager();

    requestSpecification= new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();


    }
    @Test
    public String getToken(){
        payloadManager = new PayloadManager();
        requestSpecification= RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL);
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payloadManager.creatTokenPayload());


        Response response= requestSpecification.when().post();
        ValidatableResponse vResponse= response.then();

        vResponse.statusCode(200);
        JsonPath jsonPath1=new JsonPath(response.asString());
       return jsonPath1.getString("token");



    }


}
