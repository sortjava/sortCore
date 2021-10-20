package com.sort.sortcore.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@SuppressWarnings("deprecation")
@Document(indexName = "txncontent")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class TxnContent {
    @Id
    String txnId;
    String txnSource;
    String txnType;
    String txnTitle;
    String txnUrl;
    String txnGenres;
    String txnBannerImage;
    String txnVerticalImage;
    String txnAndroidDlink;
    String txnIosDlink;
    String txnDirector;
    String txnAudio;
    String txnCast;
    String txnDuration;
    String txnYear;
    String txnMaturityRatings;
    String txnDescription;
    String txnReleaseDate;
    String txnSeasonNumber;
    String txnEpisodeNumber;
    String txnEpisodeTitle;
    String txnEpisodeDescription;
    String txnEventLocation;
    String txnEventPlatform;
    String txnEventDetails;
    String audPriority;
    String audActive;
    String audWishlistFlag;
    String audLikeFlag;
    String audUnlikeFlag;
    String audRecPriority;
    String audJsonGenres;
    String audCreatedBy;
    String audUpdatedBy;
    String audCreatedDate;
    String audUpdatedDate;
    String audImdbId;
    String audOpendbId;
    String srcId;
    String srcExtactDate;
}