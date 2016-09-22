package io.wisestone.file.service;

import io.wisestone.file.service.FileMetadata;
import io.wisestone.file.service.SimpleFileSystemStoragePolicy;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ms29.seo on 2016-09-22.
 */
public class SimpleFileSystemStoragePolicyTest {

    SimpleFileSystemStoragePolicy policy;

    @Before
    public void setup() {
        policy = new SimpleFileSystemStoragePolicy();
    }

    @After
    public void tearDown() {}

    @Test
    public void test_filemeat_policy() {
        FileMetadata metadata = policy.applyPolicy("abcdef.txt");
        System.out.println(metadata);
        LocalDate today = LocalDate.now();
        String expectedRelativePath = today.getYear() + "/" + today.getMonthOfYear() + "/" + today.getDayOfMonth();
        assertThat(metadata.getRelativePath(), is(equalTo(expectedRelativePath)));
    }

}
