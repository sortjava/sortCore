package com.sort.sortcore.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MainBannerContent {
    String txn_id;
    String txn_title;
    String txn_type;
    String txn_banner_image;
    String txn_vertical_image;
    @JsonIgnore
    String year;
}