/*
 * (#) io.wisestone.file.service.FileSystemStorageService.java
 * Created on 2016-09-22
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.wisestone.file.service;

import io.wisestone.file.domain.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ms29.seo
 */
@Slf4j
@Service
public class FileSystemStorageService implements StorageService {

    private final Path location;

    private final FileSystemStoragePolicy fileSystemStoragePolicy;

    @Autowired
    public FileSystemStorageService(StorageProperties props, FileSystemStoragePolicy fileSystemStoragePolicy) {
        this.location = Paths.get(props.getLocation());
        log.info("location: {}", this.location.toAbsolutePath());
        this.fileSystemStoragePolicy = fileSystemStoragePolicy;
    }

    @Override
    public FileMetadata store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
        }

        try {
            return store(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public void store(File file) {

    }

    public FileMetadata store(InputStream inputStream, String originalFilename) throws IOException {
        FileMetadata meta = fileSystemStoragePolicy.applyPolicy(originalFilename);
        store(inputStream, meta);
        return meta;
    }


    public void store(InputStream inputStream, FileMetadata meta) throws IOException {
        Path targetPath = this.location.resolve(meta.getRelativePath());
        if (Files.notExists(targetPath)) {
            Files.createDirectories(targetPath);
        }
        Files.copy(inputStream, targetPath.resolve(meta.getMaskedFilename()));
    }

    public static void main(String... args) throws IOException {
        StorageProperties props = new StorageProperties();
        props.setLocation("d:/tmp");

        FileSystemStoragePolicy policy = new SimpleFileSystemStoragePolicy();

        FileSystemStorageService service = new FileSystemStorageService(props, policy);
        FileMetadata metadata = service.store(new FileInputStream(Paths.get("d:/yonadb-20160321.sql").toFile()), "yonadb-20160321.sql");
        System.out.println(metadata.toString());
    }

}
