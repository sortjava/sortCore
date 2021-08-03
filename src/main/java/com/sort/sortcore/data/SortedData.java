package com.sort.sortcore.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SortedData {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    String title;
    String album;
    String artists;
    String lyricists;
    String genres;
    String label;
    String userId;
    String deviceId;
    String itemId;
    String extYoutubeId;
    String extSpotifyId;
    String costTime;
    @CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated;
    @UpdateTimestamp
    Timestamp lastModified;
}