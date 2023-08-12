package br.com.ricardovasc.hamlogbook.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class LogSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
	private UUID uuid;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@Column(nullable = false)
	private Integer frequency;
	
	@Column(length = 200)
	private String note;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "sheet_callsign", 
			joinColumns = @JoinColumn(name = "sheet_id"), 
			inverseJoinColumns = @JoinColumn(name = "callsign_id"))
	private Set<Callsign> callsigns;
	
	@PrePersist
	void onCreate() {
		this.setUuid(UUID.randomUUID());
	}
	
}
