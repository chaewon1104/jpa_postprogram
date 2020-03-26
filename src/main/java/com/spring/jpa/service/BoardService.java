package com.spring.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.jpa.domain.post;
import com.spring.jpa.domain.BoardRepository;
import com.spring.jpa.domain.loginInfo;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	public Page<loginInfo> getBoardList(Pageable pageable) {

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);

        return boardRepository.findAll(pageable);
	}
	
	
}
