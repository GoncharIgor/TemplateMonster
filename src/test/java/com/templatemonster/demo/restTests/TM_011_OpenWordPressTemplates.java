package com.templatemonster.demo.restTests;

import com.templatemonster.demo.baseTests.BaseTest;
import com.templatemonster.demo.util.HttpManager;
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
    private HttpManager httpManager = new HttpManager();
    private List<String> listOfTemplates = new ArrayList<>(Arrays.asList("59018", "59019", "59049", "53762"));

    @Test
    public void tm_011_OpenWordPressTemplatesSuccess() {
        for (int i = 0; i < 1; i++) {
            String templateId = listOfTemplates.get(i);
            HttpUriRequest request = new HttpGet("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
            Assert.assertEquals(httpManager.getRequestStatusCode(request), 200, "Incorrect status code was received");
        }
    }

    @Test
    public void tm_011_OpenWordPressTemplatesInvalid() {
        String templateId = listOfTemplates.get(listOfTemplates.size() - 1);
        HttpUriRequest request = new HttpGet("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
        Assert.assertEquals(httpManager.getRequestStatusCode(request), 404, "Incorrect status code was received");
    }

}


