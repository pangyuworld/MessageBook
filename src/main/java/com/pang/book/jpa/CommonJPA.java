package com.pang.book.jpa;

import com.pang.book.domain.Common;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonJPA extends JpaRepository<Common,Long> {
}
