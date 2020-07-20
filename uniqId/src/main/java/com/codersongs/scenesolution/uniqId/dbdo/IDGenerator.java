package com.codersongs.scenesolution.uniqId.dbdo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "id_generator")
public class IDGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "max_id")
    private Long maxId;
    private Long step;
    @Column(name = "biz_type")
    private Long bizType;
    //通过version实现乐观锁
    @Version
    private Long version;
}
