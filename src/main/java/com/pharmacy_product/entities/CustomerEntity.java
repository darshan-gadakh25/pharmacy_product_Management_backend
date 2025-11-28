package com.pharmacy_product.entities;

import java.time.LocalDate;
import java.util.List;

import com.pharmacy_product.entities.enums.Gender;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;

@Entity
@Table(name = "customers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
@ToString(callSuper = true, exclude = {"userDetails", "orders"})
public class CustomerEntity extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Past
	private LocalDate dateOfBirth;

	private String altMobile;

	private Integer loyaltyPoints;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)   // FK in customers table
	private UserEntity userDetails;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderEntity> orders;
}
