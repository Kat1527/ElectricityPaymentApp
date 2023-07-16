package com.example.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BillGenerationRequestDTO;
import com.example.dto.PreviousBillDTO;
import com.example.entity.Bill;
import com.example.service.BillPdfService;
import com.example.service.BillService;

@RestController
@RequestMapping("/billing")
public class BillController {
	
	@Autowired
  private final BillService billService;
	
	@Autowired
	private BillPdfService billPdfService;

  public BillController(BillService billService) {
    this.billService = billService;
  }
  
  // Bill Generation for a specific month

  @PostMapping("/generatebill")
  public ResponseEntity<Bill> generateMonthlyBill(@RequestBody BillGenerationRequestDTO requestDTO) {
    // Implement logic to generate monthly bills for consumers
	  Bill bill = billService.generateMonthlyBill(requestDTO);
    return new ResponseEntity<>(bill, HttpStatus.OK);
  }
  
  
  // Historical Bill generation for past months
  
  @PostMapping("/generate/previousbill")
  public ResponseEntity<List<Bill>> generateHistoricalBill(@RequestBody PreviousBillDTO previousBillDTO) {
    // Implement logic to generate monthly bills for consumers
    List<Bill> bills =  billService.generatePreviousBill(previousBillDTO);
    return new ResponseEntity<>(bills, HttpStatus.OK);
  }
  
//show due bills / unpaid bills
  
 @PostMapping("/generate/pendingbill/{propertyId}")
 public ResponseEntity<List<Bill>> showUnpaidBills(@PathVariable Long propertyId) {
   // Implement logic to generate monthly bills for consumers
   List<Bill> bills =  billService.generatePendingBill(propertyId);
   return new ResponseEntity<>(bills, HttpStatus.OK);
 }
 
 
 // Download PDF
 
 
 @GetMapping("/downloadpdf/{consumerId}/{propertyId}/{date}")
	public ResponseEntity<InputStreamResource> pdfGenerater(@PathVariable Long consumerId,@PathVariable Long propertyId,
			LocalDate date)	
	{
		
		//Link to Call->  http://localhost:8081/pdf/download
		
		ByteArrayInputStream pdf = billPdfService.createPdf(consumerId, propertyId, date);
		
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.add("Content Disposition", "inline; file= bill.pdf");
		
		return ResponseEntity.ok().headers(httpHeader).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
		
		
		
	}
  
  
}
