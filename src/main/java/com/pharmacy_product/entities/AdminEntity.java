package com.pharmacy_product.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Setter   
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "admin_id"))
@ToString(callSuper = true,  exclude = {"inventoryTransactions"})
public class AdminEntity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userDetails;

	@OneToMany(mappedBy = "adminDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InventoryEntity> inventoryTransactions;
}