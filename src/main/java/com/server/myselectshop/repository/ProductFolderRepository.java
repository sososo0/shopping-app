package com.server.myselectshop.repository;

import com.server.myselectshop.entity.Folder;
import com.server.myselectshop.entity.Product;
import com.server.myselectshop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
