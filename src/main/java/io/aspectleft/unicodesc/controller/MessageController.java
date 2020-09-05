package io.aspectleft.unicodesc.controller;

import io.aspectleft.unicodesc.dto.MessageRequest;
import io.aspectleft.unicodesc.dto.MessageResponse;
import io.aspectleft.unicodesc.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getMessages(@PageableDefault(size = 100, sort = {"created"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findByUser(pageable));
    }
}
