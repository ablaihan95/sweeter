package com.ablaihan.springlearning.repos;

import com.ablaihan.springlearning.domain.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
