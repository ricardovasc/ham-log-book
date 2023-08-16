package br.com.ricardovasc.hamlogbook.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Callsign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false, updatable = false, length = 6)
	private String code;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "callsignList", fetch = FetchType.LAZY)
	private Set<LogSheet> logSheetList = new HashSet<>();
}
