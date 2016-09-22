# file-service

## References

### Spring Boot Externalized Configuration

  Application의 설정을 외부화 하는 방법을 설명합니다. `@Value`와 `@ConfigurationProperties`에 대해 설명하고 있습니다.

  * [Part IV. Spring Boot features - 24. Externalized Configuration](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)
  * [Spring에서 YAML 파일 데이터 객체에 매핑하여 로드하기](http://blog.saltfactory.net/java/load-yaml-file-in-spring.html)
  * [[Spring Boot] yaml 설정파일 불러오기(@ConfigurationProperties)](http://jekalmin.tistory.com/entry/Spring-Boot-yaml-설정파일-불러오기ConfigurationProperties)

### file upload

  * [Uploading Files](https://spring.io/guides/gs/uploading-files/)
    - [github source code](https://github.com/spring-guides/gs-uploading-files)
  * [Spring MVC File Upload Example Tutorial – Single and Multiple Files](http://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files)
  * [A REST web service, file uploads & Spring Boot](https://murygin.wordpress.com/2014/10/13/rest-web-service-file-uploads-spring-boot/)
    - [github source code](https://github.com/murygin/rest-document-archive)

### Java NIO

  * [How to convert String to InputStream in Java](https://www.mkyong.com/java/how-to-convert-string-to-inputstream-in-java/)
  * [How to check if a folder exists](http://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists)
  
    ```java
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    Path path = Paths.get(...);

    if (Files.exists(path)) {
        // do something
    }

    if (Files.notExists(path)) {
    ```
### Google Guava

#### Hashing

original filename을 masked filename으로 변경하기 위하여 다음과 같이 Guava의 [Hassing](https://github.com/google/guava/wiki/HashingExplained)을 사용하였다.

```java
// create path for under root location
LocalDate now = LocalDate.now();
StringBuilder path = new StringBuilder();
path.append(now.getYear());
path.append("/").append(now.getMonthOfYear());
path.append("/").append(now.getDayOfMonth());

// create masked filename by path + original filename + System.currentTimeMillis
HashFunction hf  = Hashing.sha1();
HashCode hc = hf.newHasher()
        .putLong(System.currentTimeMillis())
        .putString(path.toString(), Charsets.UTF_8)
        .putString(originalFilename, Charsets.UTF_8)
        .hash();
        
// encoding hex string
String maskedFilename = BaseEncoding.base16().encode(hc.asBytes());
```