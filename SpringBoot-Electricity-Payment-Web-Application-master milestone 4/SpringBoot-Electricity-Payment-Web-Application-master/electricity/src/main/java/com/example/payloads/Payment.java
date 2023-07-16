package com.example.payloads;

import org.springframework.stereotype.Component;

@Component
public class Payment {
	
	// Method to calculate the total bill amount based on consumption
    public static double calculateBillAmount(double perUnitRate, double totalConsumption) {
        return perUnitRate * totalConsumption;
    }

    // Method to determine the per unit rate based on consumption tiers
    public static double determinePerUnitRate(double totalConsumption, String tariff) {
        // Define consumption tiers and their corresponding rates
        double[] consumptionTiers = {100, 200, 300};
        
        double[] ratesHousehold = {0.8, 1.0, 1.2, 1.5}; // Example rates per unit
        double[] ratesIndustrial = {1.2, 1.5, 1.8, 2.1}; // Example rates per unit
        double[] ratesPublic = {1.5, 1.8, 2.1, 2.4}; // Example rates per unit

        // Find the applicable rate based on the consumption tier
        int tier = 0;
        
        while (tier < consumptionTiers.length && totalConsumption > consumptionTiers[tier]) {
            tier++;
        }

        // Return the rate for the corresponding tier
        if(tariff.equals("HOUSEHOLD"))
        return ratesHousehold[tier];
        
        else if(tariff.equals("INDUSTRIAL"))
            return ratesIndustrial[tier];
        
        return ratesPublic[tier];      // PUBLIC
    }

}
