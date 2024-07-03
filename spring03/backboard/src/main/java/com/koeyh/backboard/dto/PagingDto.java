package com.koeyh.backboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingDto {
    private int pageSize;       // 한 페이지 당 몇 개의 게시물을 출력할 것인가
    private int totalPageNum;   // 총 페이지 수
    private long totalListSize; // 총 게시글 수
    
    private int page;           // 현재 페이지
    private int startPage;      // 시작 페이지 번호
    private int endPage;        // 마지막 페이지 번호

    private int startIndex;     // 시작 인덱스 번호
    
    private int block;          // 현재 블럭(구간)
    private int totalBlockNum;  // 총 블럭 수
    private int prevBlock;      // 이전 블럭
    private int nextBlock;      // 다음 블럭

    // 전체 리스트 크기, 현재 페이지, 페이지마다 나타낼 글 갯수, 블럭 수를 가지고
    // 필요 변수들의 값을 계산하는 생성자
    public PagingDto(Long totalListSize, Integer page, Integer pageSize, Integer blockNum) {
        this.pageSize = pageSize;
        this.page = page;
        this.totalListSize = totalListSize;

        //변수 값 계산
        // 전체 블럭 수 계산
        this.totalPageNum = (int) Math.ceil(this.totalPageNum * 1.0 / this.pageSize);
        
    }

}