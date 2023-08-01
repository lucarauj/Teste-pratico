package com.desafio.repository;

import com.desafio.model.HtmlTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HtmlTagRepository extends JpaRepository<HtmlTag, Long> {

    Optional<HtmlTag> findByUrlAndTag(String url, String tagName);
}
