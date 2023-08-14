package br.com.ricardovasc.hamlogbook.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class LogSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@Column(nullable = false)
	private Integer frequency;
	
	@Column(length = 200)
	private String note;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "log_sheet_callsign", 
			joinColumns = @JoinColumn(name = "log_sheet_id"), 
			inverseJoinColumns = @JoinColumn(name = "callsign_id"))
	private Set<Callsign> callsignList;
}
