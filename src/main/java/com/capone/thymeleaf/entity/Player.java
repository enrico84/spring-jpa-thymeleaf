package com.capone.thymeleaf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "player")
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome è obbligatorio")
    private String name;
    
    @NotBlank(message = "Cognome è obbligatorio")
    private String lastName;
    
    @NotBlank(message = "Team è obbligatorio")
    private String team;
    
    @NotBlank(message = "Posizione è obbligatorio")
    @Size(max = 1, min = 1, message = "Massimo 1 Carattere")
    private String position;

}
