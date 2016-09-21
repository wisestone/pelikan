/*
 * (#) io.wisestone.file.controller.FileRestController.java
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
import io.wisestone.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@RestController
@RequestMapping(path = {"/files"})
public class FileRestController {

    @Autowired
    FileService fileService;

    @Autowired
    ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public HttpHeaders create(@RequestBody FileInput fileInput) {
        log.debug("file input: {}", fileInput.toString());

        File input = modelMapper.map(fileInput, File.class);
        File entity = fileService.save(input);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(linkTo(FileRestController.class).slash(entity.getId()).toUri());
        return httpHeaders;
    }

}
