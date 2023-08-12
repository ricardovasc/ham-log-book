package br.com.ricardovasc.hamlogbook.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Callsign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
	private UUID uuid;
	
	@Column(unique = true, nullable = false, length = 6)
	private String code;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@ManyToMany(mappedBy = "callsigns", fetch = FetchType.EAGER)
	private Set<LogSheet> sheets;
	
	@PrePersist
	void onCreate() {
		this.setUuid(UUID.randomUUID());
	}

}