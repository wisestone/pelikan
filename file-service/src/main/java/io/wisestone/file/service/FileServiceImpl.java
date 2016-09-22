/*
 * (#) io.wisestone.file.service.FileServiceImpl.java
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
import io.wisestone.file.domain.FileJpaRepository;
import io.wisestone.file.service.FileKeyGenerator;
import io.wisestone.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class FileServiceImpl implements FileService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileJpaRepository fileJpaRepository;

    @Autowired
    private FileKeyGenerator fileKeyGenerator;

    @Override
    @Transactional
    public File save(File file) {
        if (file.getKey() == null) {
            file.setKey(fileKeyGenerator.generate(file));
        }
        return fileJpaRepository.save(file);
    }

}
