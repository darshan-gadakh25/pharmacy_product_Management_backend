package com.pharmacy_product.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

import lombok.*;

@MappedSuperclass
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name ="created_on")
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "updated_on")
    private LocalDateTime updatedDate;
}

