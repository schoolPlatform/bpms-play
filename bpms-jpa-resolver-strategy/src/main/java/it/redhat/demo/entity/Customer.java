package it.redhat.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.drools.persistence.jpa.marshaller.VariableEntity;

/**
 * This class was automatically generated by the data modeler tool.
 */

@Entity
public class Customer extends VariableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8778840765450275959L;
	
	private String name;
	private Boolean premium;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "CUSTOMER_ID_GENERATOR")
	@SequenceGenerator(sequenceName = "CUSTOMER_ID_SEQ", name = "CUSTOMER_ID_GENERATOR")
	private Long id;

	public Customer() {
	}
	
	public Customer(Long id, String name, Boolean premium) {
		this.id = id;
		this.name = name;
		this.premium = premium;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPremium() {
		return this.premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", premium=" + premium + "]";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}