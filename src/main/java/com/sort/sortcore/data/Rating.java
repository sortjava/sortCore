package com.sort.sortcore.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Rating {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}