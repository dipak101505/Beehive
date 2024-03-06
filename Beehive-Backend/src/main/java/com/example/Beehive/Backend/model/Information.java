package com.example.Beehive.Backend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "information")
public class Information implements Serializable {
	
	private static final long serialVersionUID = 3314503679473015415L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "code", length = 100, nullable = false)
	private String contact;

	@Column(length = 140)
	private String address;
	
	@Column(length = 140)
	private String content;
	
	@Column(length = 140)
	private String image_url;

}
