package guru.qaq.homework;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomeWorkParsingTests {
  
  private final ClassLoader cl = HomeWorkParsingTests.class.getClassLoader();
  
  @Test
  @DisplayName("Читаем CSV из архива")
  void readCsvFromZip() throws Exception {
    try (InputStream is = cl.getResourceAsStream("Archive.zip");
         ZipInputStream zis = new ZipInputStream(is)) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        System.out.println("Файл " + entry.getName());
        if (entry.getName().endsWith(".csv") && !entry.getName().startsWith("__MACOSX/")) {
          CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
          List<String[]> content = csvReader.readAll();
          assertThat(content.get(0)).isEqualTo(new String[]{"First", "Second"});
          System.out.println("");
        }
      }
    }
  }
  
  @Test
  @DisplayName("Читаем PDF из архива")
  void readPDFFromZip() throws Exception {
    try (InputStream is = cl.getResourceAsStream("Archive.zip");
         ZipInputStream zis = new ZipInputStream(is)) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().endsWith(".pdf") && !entry.getName().startsWith("__MACOSX/")) {
          PDF pdf = new PDF(zis);
          assertThat(pdf.title).isEqualTo("JUnit 5 User Guide");
        }
      }
    }
  }
  
  @Test
  @DisplayName("Читаем xlsx из архива")
  void readXLSFromZip() throws Exception {
    try (InputStream is = cl.getResourceAsStream("Archive.zip");
         ZipInputStream zis = new ZipInputStream(is)) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        System.out.println("Найден файл: " + entry.getName());
        if (entry.getName().endsWith(".xlsx") && !entry.getName().startsWith("__MACOSX/")) {
          XLS xls = new XLS(zis);
          String text = xls.excel.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
          assertThat(text).isEqualTo("Rafael");
        }
      }
    }
  }
}

