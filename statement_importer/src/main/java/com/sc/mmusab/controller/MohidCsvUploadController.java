package com.sc.mmusab.controller;
import com.sc.mmusab.service.MohidCsvImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mohid/import")
@RequiredArgsConstructor
public class MohidCsvUploadController {

  private final MohidCsvImportService service;

  @PostMapping("/csv")
  public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
    try {
      service.importCsv(file.getInputStream());
      return ResponseEntity.ok("CSV imported successfully.");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Failed to import CSV: " + e.getMessage());
    }
  }
}