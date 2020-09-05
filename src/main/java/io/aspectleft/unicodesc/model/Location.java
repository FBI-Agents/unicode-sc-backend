package io.aspectleft.unicodesc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long locationId;

    private Double latitude;

    private Double longitude;

    private Instant created;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
