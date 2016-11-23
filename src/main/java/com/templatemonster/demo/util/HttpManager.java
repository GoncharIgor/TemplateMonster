package com.templatemonster.demo.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by i.gonchar on 23.11.2016.
 */
public class HttpManager {

    private void manageTestMethod () throws IOException {
        HttpPost post = new HttpPost("https://wac.templatemonster.com/signin.html");

        List<BasicNameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("login", "IgorGoncharUA@gmail.com"));
        pairs.add(new BasicNameValuePair("password", "Test_Test"));

        try {
            post.setEntity(new UrlEncodedFormEntity(pairs));
        } catch (Exception e) {

        }
        HttpResponse response = HttpClientBuilder.create().build().execute(post);
        System.out.println(response.getStatusLine().getStatusCode());


        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

}
