package io.aspectleft.unicodesc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String content;
    private String senderName;
    private String receiverName;
    private Instant created;
}
