package com.example.springmedicine.dao.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 16)
    private UUID uuid;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column
    @LastModifiedDate
    private Instant updatedDate;

    @PrePersist
    public void generateUUID() {
        this.uuid = UUID.fromString(UUID.randomUUID().toString().substring(2, 36));
    }

}
