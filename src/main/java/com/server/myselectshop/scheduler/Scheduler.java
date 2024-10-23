package com.server.myselectshop.scheduler;

import com.server.myselectshop.entity.Product;
import com.server.myselectshop.naver.controller.NaverApiController;
import com.server.myselectshop.naver.dto.ItemDto;
import com.server.myselectshop.repository.ProductRepository;
import com.server.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final NaverApiController naverApiController;
    private final ProductService productService;
    private final ProductRepository productRepository;

    // 초, 분, 시, 일, 월, 주
//    @Scheduled(cron = "*/10 * * * * *") // 테스트용
    @Scheduled(cron = "0 0 1 * * *") // 매일 새벽 1시
    public void updatePrice() throws InterruptedException {
        log.info("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            // 1초에 한 상품씩 조회한다. (Naver 제한)
            TimeUnit.SECONDS.sleep(1);

            // i번째 관심 상품의 제목으로 검색한다.
            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiController.searchItem(title);
            if (itemDtoList.size() > 0) {
                ItemDto itemDto = itemDtoList.get(0);
                Long id = product.getId();
                try {
                    productService.updateBySearch(id, itemDto);
                } catch (Exception e) {
                    log.error(id + " : " + e.getMessage());
                }
            }
        }
    }

}
