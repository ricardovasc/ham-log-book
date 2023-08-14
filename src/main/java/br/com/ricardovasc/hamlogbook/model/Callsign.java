package br.com.ricardovasc.hamlogbook.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Callsign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false, length = 6)
	private String code;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@ManyToMany(mappedBy = "callsignList", fetch = FetchType.EAGER)
	private Set<LogSheet> logSheetList;
}
