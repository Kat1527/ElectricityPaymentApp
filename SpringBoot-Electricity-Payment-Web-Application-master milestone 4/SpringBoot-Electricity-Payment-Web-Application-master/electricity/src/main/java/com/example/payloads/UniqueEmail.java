package com.example.payloads;

import jakarta.validation.Payload;

public @interface UniqueEmail {
	
	String message() default "Email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
