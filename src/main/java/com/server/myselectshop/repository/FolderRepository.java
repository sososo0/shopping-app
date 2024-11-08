package com.server.myselectshop.repository;

import com.server.myselectshop.entity.Folder;
import com.server.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);

    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
    // select * from folder where user_id = ? and name in (?, ?, ?);
}
