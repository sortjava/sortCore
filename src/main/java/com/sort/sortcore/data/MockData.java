package com.sort.sortcore.data;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MockData {
    UUID id;
    Instant createTime;
}