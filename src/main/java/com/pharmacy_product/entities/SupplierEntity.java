package com.pharmacy_product.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "suppliers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "supplier_id"))
@ToString(callSuper = true)
public class SupplierEntity extends BaseEntity{

	@Column(nullable = false, length = 100)
	private String companyName;

	@Column(length=500)
	private String companyAddress;

	@Column(unique = true)
	private String companyEmail;

	@Column(unique = true)
	private String companyPhone;

	private String supplyCategory;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userDetails;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private java.util.List<ProductEntity> products;
}
