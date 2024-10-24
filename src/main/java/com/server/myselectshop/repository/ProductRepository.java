package com.server.myselectshop.repository;

import com.server.myselectshop.entity.Product;
import com.server.myselectshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser(User user, Pageable pageable);

    Page<Product> findAllByUserAndProductFolderList_FolderId(User user, Long folderId, Pageable pageable);
    // folder_id를 가진 ProductFolder의 List가 필요 -> 이결로 Product를 뽑아낸다.
    /*
        select
            p.id,
            p.title,
            pf.product_id,
            pf.folder_id
        from
            product p left join product_folder pf
                on p.id = pf.product_id
        where p.user_id = 1;
            and
                pf.folder_id = 3
        order by p.id desc // desc를 빼면 asc
        limit 0, 10; // 앞 숫자는 시작 index, 뒷 숫자는 크기
    */
}
