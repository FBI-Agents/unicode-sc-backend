package io.aspectleft.unicodesc.mapper;

import io.aspectleft.unicodesc.dto.MessageRequest;
import io.aspectleft.unicodesc.dto.MessageResponse;
import io.aspectleft.unicodesc.model.Message;
import io.aspectleft.unicodesc.model.User;
import io.aspectleft.unicodesc.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    @Autowired
    UserRepository userRepository;

    @Mapping(target = "receiver", expression = "java(userRepository.findByUsername(messageRequest.getReceiverName())" +
            ".orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException(messageRequest.getReceiverName())))")
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "messageId", ignore = true)
    public abstract Message map(MessageRequest messageRequest, User sender);

    @Mapping(target = "senderName", source = "sender.username")
    @Mapping(target = "receiverName", source = "receiver.username")
    public abstract MessageResponse mapToDto(Message message);
}
