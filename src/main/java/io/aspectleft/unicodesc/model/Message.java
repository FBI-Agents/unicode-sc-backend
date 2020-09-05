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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long messageId;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "senderId", referencedColumnName = "userId")
    private User sender;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "receiverId", referencedColumnName = "userId")
    private User receiver;

    private Instant created;
}
