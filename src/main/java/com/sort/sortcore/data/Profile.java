package com.sort.sortcore.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String fullName;
    @NotBlank
    @Size(max = 50)
    String email;
    String phone;
    @Size(max = 12)
    String gender;
    String dateOfBirth;
    Provider provider;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime dateCreated;
    @UpdateTimestamp
    LocalDateTime lastModified;
}