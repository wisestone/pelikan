/*
 * (#) io.wisestone.file.service.impl.FileSystemStoragePolicyImpl.java
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

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import io.wisestone.file.service.FileMetadata;
import io.wisestone.file.service.FileSystemStoragePolicy;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author ms29.seo
 */
@Slf4j
@Component
public class SimpleFileSystemStoragePolicy implements FileSystemStoragePolicy {

    @Override
    public FileMetadata applyPolicy(String originalFilename) {
        LocalDate now = LocalDate.now();
        StringBuilder path = new StringBuilder();
        path.append(now.getYear());
        path.append("/").append(now.getMonthOfYear());
        path.append("/").append(now.getDayOfMonth());

        HashFunction hf  = Hashing.sha1();
        HashCode hc = hf.newHasher()
                .putLong(System.currentTimeMillis())
                .putString(path.toString(), Charsets.UTF_8)
                .putString(originalFilename, Charsets.UTF_8)
                .hash();
        String maskedFilename = BaseEncoding.base16().encode(hc.asBytes());

        return new FileMetadata(originalFilename, maskedFilename, path.toString());
    }

}
