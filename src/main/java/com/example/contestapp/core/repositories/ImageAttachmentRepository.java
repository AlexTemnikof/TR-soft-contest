package com.example.contestapp.core.repositories;

import com.example.contestapp.core.entities.ImageAttachment;
import com.example.contestapp.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageAttachmentRepository extends JpaRepository<ImageAttachment, Integer> {
}
