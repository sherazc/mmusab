package com.sc.mmusab.controller;

import com.sc.mmusab.service.BoaCsvImportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/boa/import")
@AllArgsConstructor
public class BoaCsvUploadController {

  private final BoaCsvImportService csvImportService;

  @PostMapping("/csv")
  public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
    try {
      csvImportService.importCsv(file.getInputStream());
      return ResponseEntity.ok("CSV imported successfully.");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error importing CSV: " + e.getMessage());
    }
  }
}
