package com.koeyh.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.koeyh.backboard.common.NotFoundException;
import com.koeyh.backboard.entity.Category;
import com.koeyh.backboard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;    // bean으로 생성

    // 제목을 받아서 카테고리 생성하는 메서드
    public Category setCategory(String title) {
        Category cate = new Category();
        cate.setTitle(title);
        cate.setCreateDate(LocalDateTime.now());

        Category category = this.categoryRepository.save(cate);     // cate를 이용해서 저장한 정보를 category에 저장(반환할 변수)

        return category;
    }

    // 게시판 분류(free, qna)
    public Category getCategory(String title) {
        Optional<Category> cate = this.categoryRepository.findByTitle(title);

        if(cate.isEmpty()) {    // free나 qna 타이틀의 카테고리 데이터가 없으면
            cate = Optional.ofNullable(setCategory(title)); // 테이블에 해당 카테고리를 생성해주기(Insert 기능)
        }

        if(cate.isPresent()) {
            return cate.get();  // Category 반환
        } else {
            throw new NotFoundException("Category not Found!"); // 위에서 다 걸러서 발생할 일이 없을것.
        }
    }
}
