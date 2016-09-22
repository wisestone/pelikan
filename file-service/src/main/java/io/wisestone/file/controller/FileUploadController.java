/*
 * (#) io.wisestone.file.controller.FileUploadController.java
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
package io.wisestone.file.controller;

import io.wisestone.file.domain.File;
import io.wisestone.file.service.FileMetadata;
import io.wisestone.file.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@Controller
@RequestMapping(path = {"/files/upload"})
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<FileMetadata> upload(
            @RequestParam(name = "file", required = true) MultipartFile multipartFile,
            @RequestParam(name = "createdBy", required = true) String createdBy) {
        File file = convertToFile(multipartFile);
        file.setCreatedBy(createdBy);
        storageService.store(file);
        return new ResponseEntity<>(convertToFileMetadata(file), HttpStatus.ACCEPTED);
    }

    private FileMetadata convertToFileMetadata(File file) {
        return null;
    }

    private File convertToFile(MultipartFile multipartFile) {
        File f = new File();
        f.setName(multipartFile.getOriginalFilename());
        f.setType(multipartFile.getContentType());
        f.setSiez(multipartFile.getSize());
        return f;
    }

}
