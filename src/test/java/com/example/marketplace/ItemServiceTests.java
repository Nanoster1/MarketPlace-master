package com.example.marketplace;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.repository.ItemRepository;
import com.example.marketplace.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ItemServiceTests {
    @Autowired
    private ItemService itemService;
    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void addItem() {
        final var testName = "testName";
        ItemResponse resp = new ItemResponse();
        resp.setName(testName);

        var added = itemService.add(resp);

        Assertions.assertThat(added).isNotNull();
        Assertions.assertThat(added.getName()).isEqualTo(testName);

        Mockito.verify(itemRepository, Mockito.times(1)).save(Mockito.any(Item.class));
    }

    @Test
    public void getItem() {
        final var testName = "testName";
        var item = new Item(testName);
        item.setItemId(1);
        Mockito.doReturn(Optional.of(item)).when(itemRepository).findById(1);

        var result = itemService.getById(1);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getName()).isEqualTo(testName);

        Mockito.verify(itemRepository, Mockito.times(0)).save(Mockito.any(Item.class));
    }
}
