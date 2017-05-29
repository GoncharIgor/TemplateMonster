package com.templatemonster.demo.restTests;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class TM_024_JerseyTest extends JerseyTest {

    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder().build();
    }

    @Test
    public void testUserFetchesSuccess() throws JSONException,
            URISyntaxException {
        WebResource webResource = client().resource("https://api.myjson.com/");
        JSONObject json = webResource.path("/bins/13ft85")
                .get(JSONObject.class);
        assertEquals(423638, json.get("id"));
        assertEquals("ggobi", json.get("login"));
    }

    @Test(expected = UniformInterfaceException.class)
    public void testUserNotFound() {
        WebResource webResource = client().resource("https://api.myjson.com/");
        JSONObject json = webResource.path("bins/13ft852")
                .get(JSONObject.class);
    }
}
