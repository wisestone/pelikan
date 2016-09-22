package io.wisestone.file.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ms29.seo on 2016-09-22.
 */
public class FileSystemStorageServiceTest {

    private StorageProperties properties;
    private FileSystemStoragePolicy policy;
    private FileSystemStorageService service;

    private Path targetPath;

    private String inputText = "This is a test text.";
    private String originalFilename = "fsss-test.txt";

    @Before
    public void setup() {
        properties = new StorageProperties();
        properties.setLocation(System.getProperty("java.io.tmpdir"));
        policy = new SimpleFileSystemStoragePolicy();

        targetPath = Paths.get(properties.getLocation());
        System.out.println("target path: " + targetPath.toAbsolutePath());

        service = new FileSystemStorageService(properties, policy);
    }

    @After
    public void teardown() {
    }


    @Test
    public void test_store_inputstream_to_expected_dir_filename() throws IOException {
        // convert string into inputstream
        InputStream inputStream = new ByteArrayInputStream(inputText.getBytes());

        FileMetadata metadata = service.store(inputStream, originalFilename);

        Path rp = targetPath.resolve(metadata.getRelativePath());
        boolean result = Files.exists(rp.resolve(metadata.getMaskedFilename()));
        assertThat(result, is(Boolean.TRUE));

        Files.delete(rp.resolve(metadata.getMaskedFilename()));
        Files.deleteIfExists(rp);
    }

}
