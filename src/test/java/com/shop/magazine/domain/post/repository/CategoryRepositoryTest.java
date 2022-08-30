package com.shop.magazine.domain.post.repository;

import com.shop.magazine.domain.post.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    private Category getCategory() {
        return Category.builder()
                .name("JAVA")
                .build();
    }

    @Test
    @Rollback(value = false)
    void save() {
        // given
        Category category = getCategory();

        // when
        Category saveCategory = categoryRepository.save(category);

        // then
        Category findCategory = categoryRepository.findById(saveCategory.getId())
                .orElseThrow(() -> new NoSuchElementException("category not found"));

        assertThat(findCategory).isEqualTo(saveCategory);

    }

    @Test
    void find() {
        // given
        Category category = getCategory();
        Category save = categoryRepository.save(category);

        // when
        List<Category> list = categoryRepository.findAll();

        // then
        assertThat(list).contains(save);
    }
}
