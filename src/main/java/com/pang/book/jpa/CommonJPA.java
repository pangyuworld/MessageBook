package com.pang.book.jpa;

import com.pang.book.domain.Common;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonJPA extends JpaRepository<Common,Long> {
    List<Common> findByMessage(long messageId, Sort sort);
}
