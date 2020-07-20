package com.codersongs.scenesolution.uniqId.dbdo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sequence_id")
public class SequenceId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    public SequenceId() {
        this.value = "";
    }
}
