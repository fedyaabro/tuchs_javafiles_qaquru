package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {
  
  @Test
  void downloadFileTest() throws Exception {
    open("https://github.com/junit-team/junit5/blob/main/README.md");
    File downloaded = $("a[data-testid='raw-button']").download();
    
    try (InputStream is = new FileInputStream(downloaded)) {
      byte[] content = is.readAllBytes();
      String contentAsString = new String(content, StandardCharsets.UTF_8);
      Assertions.assertTrue(contentAsString.contains("Contributions to JUnit 5 are both welcomed and appreciated."));
    }
  }
  
  @Test
  void fileUploadTest(){
    open("https://fineuploader.com/demos.html");
    $("input[type='file']").uploadFromClasspath("file7.png");
    
  }
  
  @Test
  void pdfParsingTest(){
    open("https://junit.org/junit5/docs/current/user-guide/");
    File pdfFile = $("a['junit-user-guide-5.10.2.pdf']").download();
  
  }
  
  }

