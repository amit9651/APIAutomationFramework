package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LoggingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        logRequest(requestSpec);
        Response response = filterContext.next(requestSpec,responseSpec);
        logResponse(response);
        return response;
    }

    public void logRequest(FilterableRequestSpecification requestSpec){
        logger.info("BASE URI: "+requestSpec.getBaseUri());
        logger.info("Request Header: "+requestSpec.getHeaders());
        logger.info("Request Payload: "+requestSpec.getBody());
        logger.info("BASE URI: "+requestSpec.getBaseUri());
        logger.info("Method-Type: "+requestSpec.getMethod());

    }

    public void logResponse(Response response){
        logger.info("Time taken in mill sec: "+response.getTimeIn(TimeUnit.MILLISECONDS));
        logger.info("Response Header: "+response.headers());
        logger.info("Response Payload: "+response.getBody().prettyPrint());
        logger.info("Status Code: "+response.getStatusCode());

    }
}
