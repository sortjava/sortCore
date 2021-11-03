package com.sort.sortcore.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeRequest {
    @Size(max = 15)
    String itemType;
    @Size(max = 60)
    String itemId;
    @Size(max = 3)
    String likeFlag;
    @Size(max = 3)
    String dislikeFlag;
}