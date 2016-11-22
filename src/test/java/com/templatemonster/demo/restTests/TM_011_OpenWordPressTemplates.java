package com.templatemonster.demo.restTests;

import com.templatemonster.demo.baseTests.BaseTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by i.gonchar on 16.11.2016.
 */
public class TM_011_OpenWordPressTemplates {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    private List<String> listOfTemplates = new ArrayList<>(Arrays.asList("59018", "59019", "59049", "53762"));

    @Test
    public void tm_011_OpenWordPressTemplatesSuccess() {
        for (int i = 0; i < 1; i++) {
            String templateId = listOfTemplates.get(i);
            HttpUriRequest request = new HttpGet("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
            Assert.assertEquals(getRequestStatusCode(request), 200, "Incorrect status code was received");
        }
    }

    @Test
    public void tm_011_OpenWordPressTemplatesInvalid() {
        String templateId = listOfTemplates.get(listOfTemplates.size() - 1);
        HttpUriRequest request = new HttpGet("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
        Assert.assertEquals(getRequestStatusCode(request), 404, "Incorrect status code was received");
    }


    private int getRequestStatusCode(HttpUriRequest request) {
        int statusCode = 0;
        try {
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            statusCode = response.getStatusLine().getStatusCode();


        } catch (IOException e) {
            LOGGER.error("Could not execute Http reauest");
        }
        return statusCode;
    }
}


