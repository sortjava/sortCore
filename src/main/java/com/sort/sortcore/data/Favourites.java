package com.sort.sortcore.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "favourites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    Long userId;
    @Size(max = 60)
    String itemId;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime dateCreated;
    @UpdateTimestamp
    LocalDateTime lastModified;
}