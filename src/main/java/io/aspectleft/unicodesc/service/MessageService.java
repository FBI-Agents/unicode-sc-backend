package io.aspectleft.unicodesc.service;

import io.aspectleft.unicodesc.dto.MessageRequest;
import io.aspectleft.unicodesc.dto.MessageResponse;
import io.aspectleft.unicodesc.mapper.MessageMapper;
import io.aspectleft.unicodesc.model.User;
import io.aspectleft.unicodesc.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    private final AuthService authService;
    private final MessageMapper messageMapper;

    public void saveMessage(MessageRequest messageRequest) {
        User sender = authService.getCurrentUser();
        messageRepository.save(messageMapper.map(messageRequest, sender));
    }

    @Transactional(readOnly = true)
    public Page<MessageResponse> findByUser(Pageable pageable) {
        User user = authService.getCurrentUser();
        return messageRepository.findAllBySenderOrReceiver(user, user, pageable)
                .map(messageMapper::mapToDto);
    }
}
