package com.liner.example.page.service;

import com.liner.example.exception.ErrorCode;
import com.liner.example.exception.NotFound;
import com.liner.example.page.domain.Page;
import com.liner.example.page.domain.repo.PageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page add(Long userId, String pageUrl) {
        Page page = Page.builder()
                .userId(userId)
                .pageUrl(pageUrl)
                .build();
        Page newPage = pageRepository.save(page);
        return newPage;
    }

    public Page getOneByPageUrl(String pageUrl) {
        Page page = pageRepository.findByPageUrl(pageUrl).orElseThrow(() -> new NotFound("Not Found Page", ErrorCode.NOT_FOUND_PAGE));
        return page;
    }

    public List<Page> getListByUserId(Long userId) {
        List<Page> pageList = pageRepository.selectJPQLByUserId(userId);
        return pageList;
    }


}
