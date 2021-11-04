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
@Table(name = "likeDislike")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    Long userId;
    @Size(max = 15)
    String itemType;
    @Size(max = 60)
    String itemId;
    @Size(max = 3)
    String likeFlag = "0";
    @Size(max = 3)
    String dislikeFlag = "0";
    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime dateCreated;
    @UpdateTimestamp
    LocalDateTime lastModified;
}