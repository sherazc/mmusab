package com.sc.mmusab.controller;

import com.sc.mmusab.service.PaypalTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/paypal/import")
@RequiredArgsConstructor
public class PaypalTransactionController {

  private final PaypalTransactionService service;

  @PostMapping("/csv")
  public ResponseEntity<?> importFile(@RequestParam("file") MultipartFile file) {
    try {
      service.importCSV(file);
      return ResponseEntity.ok("File imported successfully.");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error importing file: " + e.getMessage());
    }
  }
}