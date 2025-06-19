package com.htetoakkar.socialmedia.user.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseUser {

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_locked", columnDefinition = "boolean default false")
    private boolean locked;

    @Version
    @Column(name = "version", columnDefinition = "bigint DEFAULT 0", nullable = false)
    private Long version;

    @Column(name = "deactivated", columnDefinition = "boolean default false")
    private boolean deactivated;

}
