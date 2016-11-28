package com.templatemonster.demo.restTests;

import com.templatemonster.demo.util.HttpManager;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TM_011_OpenWordPressTemplates {
    private HttpManager httpManager = new HttpManager();
    private List<String> listOfTemplates = new ArrayList<>(Arrays.asList("59018", "59019", "59049", "53762"));

    @Test
    public void openWordPressTemplatesSuccess() {
        for (int i = 0; i < 1; i++) {
            String templateId = listOfTemplates.get(i);
            HttpUriRequest request = new HttpGet("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
            Assert.assertEquals(httpManager.getRequestStatusCode(request), 200, "Incorrect status code was received");
        }
    }

    @Test
    public void openWordPressTemplatesInvalid() {
        String templateId = listOfTemplates.get(listOfTemplates.size() - 1);
        HttpUriRequest request = new HttpGet("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
        Assert.assertEquals(httpManager.getRequestStatusCode(request), 404, "Incorrect status code was received");
    }

}


