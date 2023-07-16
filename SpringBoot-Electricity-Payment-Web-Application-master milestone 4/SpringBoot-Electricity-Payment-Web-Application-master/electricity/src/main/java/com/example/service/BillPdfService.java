package com.example.service;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Bill;
import com.example.entity.Consumer;
import com.example.entity.Property;
import com.example.exception.NotFoundException;
import com.example.repository.BillRepository;
import com.example.repository.ConsumerRepository;
import com.example.repository.PropertyRepository;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class BillPdfService {
	
	 		@Autowired
	 		private ConsumerRepository consumerRepository;
	 		@Autowired
	 		private PropertyRepository propertyRepository;
	 		@Autowired
	 		private BillRepository billRepository;

	private Logger logger = LoggerFactory.getLogger(BillPdfService.class);
	
	public ByteArrayInputStream createPdf(Long consumerId, Long propertyId, LocalDate date)
	{
		Consumer consumer = consumerRepository.findById(consumerId)
			      .orElseThrow(() -> new NotFoundException("Consumer", "Consumer ID", consumerId));
		
		Property property = propertyRepository.findById(propertyId)
			      .orElseThrow(() -> new NotFoundException("Property","Property ID", propertyId));
		
		Bill pdfBill = findRequiredBill(propertyId, date);
		
		logger.info("Createing PDF...");
		 
			
		String title = "BSES Yamuna Power Limited";	
		String consumerDetails = "NAME: "+ consumer.getName()+"\n"
				+ "Email: " + consumer.getEmail() +"\n";
		
		String propertyDetails = "Address: " + property.getAddress()+ "\n"
								+"Meter Number: " + property.getMeterNumber() +"\n"
								+"Tariff Plan: "+ property.getTariffPlan() +"\n";
		
		
		String billDetails = "Number of Units Consumed: "+ pdfBill.getUnitsConsumed()+"\n"
				+ "Bill Generation Date: "+ pdfBill.getBillingDate()+ "\n"
				+ "Bill Due Date: "+ pdfBill.getDueDate()+ "\n"
				+ "TOTAL: " + pdfBill.getAmount() + "\n"
				+ "Payment Status: " + pdfBill.isPaid()	+"\n";
				
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		Document document = new Document();
		
		PdfWriter.getInstance(document, out);
		
		document.open();
		
		Font titleFont = new Font(FontFactory.HELVETICA, 25, Font.BOLD);
		Paragraph heading = new Paragraph(title);
	    document.add(heading);
		
		 
		document.add(new Paragraph(consumerDetails));
		document.add(new Paragraph(propertyDetails));
		document.add(new Paragraph(billDetails));
		 
		
		document.close();
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public Bill findRequiredBill(long propertyId, LocalDate date)
	{
		List<Bill> bills = billRepository.findBillsByPropertId(propertyId);
		
		for(Bill b: bills)
		{
			if(date.getMonth() == b.getBillingDate().getMonth())
				return b;
		}
		
		return null;
		
	}

}
