package io.wisestone.file.service;

import lombok.*;

/**
 * Created by ms29.seo on 2016-09-22.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FileMetadata {

    private String originalFilename;
    private String maskedFilename;
    private String relativePath;

}
