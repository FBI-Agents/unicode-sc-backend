package io.aspectleft.unicodesc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TakeCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long takeCourseId;

    @NotBlank
    private String courseCode;

    @NotBlank
    private String courseName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

}
