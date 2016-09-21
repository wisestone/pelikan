/*
 * (#) io.wisestone.file.domain.File.java
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
package io.wisestone.file.domain;

import io.wisestone.common.AbstractPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "wise_files")
public class File extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -6774524169437233692L;

    @Column(name = "file_id", unique = true, nullable = false)
    private String key;

    @Column(name = "file_name1", nullable = false)
    private String name;

    @Column(name = "file_name2", nullable = false)
    private String maskedName;

    @Column(name = "file_path", nullable = false)
    private String path;

    @Column(name = "file_size", nullable = false)
    private long siez;

    @Column(name = "file_type")
    private String type;

    @Column(name = "file_hit_count")
    private int hitCount;

    @Column(name = "poster_id")
    private String createdBy;

    @Column(name = "post_time")
    private Date createdOn;

}
