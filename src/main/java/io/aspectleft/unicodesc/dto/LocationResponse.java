package io.aspectleft.unicodesc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private Double latitude;
    private Double longitude;

    private String username;
}
