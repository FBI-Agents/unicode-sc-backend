package io.aspectleft.unicodesc.repository;

import io.aspectleft.unicodesc.model.Message;
import io.aspectleft.unicodesc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    Page<Message> findAllBySenderOrReceiver(User sender, User receiver, Pageable pageable);
}
