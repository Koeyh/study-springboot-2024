package com.koeyh.backboard.common;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    public String markdown(String content) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);  // 기존 마크다운으로 작성된 글 파싱
        HtmlRenderer renderer = HtmlRenderer.builder().build(); 
        
        return renderer.render(document);   // HTML로 랜더링한 텍스트 리턴
    }
}
