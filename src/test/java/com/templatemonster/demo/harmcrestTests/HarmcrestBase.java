package com.templatemonster.demo.harmcrestTests;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class HarmcrestBase {

    @Test
    public void testSimpleHarmcrest() {
        String a = "foo";
        String b = "FOO";
        assertThat(a, equalToIgnoringCase(b));
    }

    @Test
    public void testNumber() {
        List<Integer> list = Arrays.asList(5);
        Assert.assertThat(list, Matchers.hasItem(5));
        int a = 12;
        assertThat(a, Matchers.lessThanOrEqualTo(12));
    }
}
