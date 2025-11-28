package com.pharmacy_product.Response;

import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse  {

	private LocalDateTime timeStamp;
	private String message;
	private String status;
	
	public ApiResponse( String message, String status) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp=LocalDateTime.now();
	}
	
	
}
