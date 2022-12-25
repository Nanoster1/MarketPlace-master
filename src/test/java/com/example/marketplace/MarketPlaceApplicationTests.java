package com.example.marketplace;

import com.example.marketplace.controlers.RESTApiController;
import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Comparator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class MarketPlaceApplicationTests {
    @Autowired
    private ItemService itemService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RESTApiController restController;

    @Test
    public void addItem() throws Exception{
        final var testName = "testName";
        var resp = new ItemResponse();
        resp.setName(testName);

        this.mockMvc.perform(post("/api/item")
                .content(objectMapper.writeValueAsString(resp))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(testName)));

        var lastItem = Lists.newArrayList(itemService.getAllItems())
                .stream()
                .max(Comparator.comparingInt(Item::getItemId));

        Assertions.assertThat(lastItem).isPresent();
        Assertions.assertThat(lastItem.get().getName()).isEqualTo(testName);

        itemService.remove(lastItem.get().getItemId());
    }

    @Test
    public void testGet() throws Exception {
        final var testName = "testName";
        var resp = new ItemResponse();
        resp.setName(testName);

        var added = itemService.add(resp);

        this.mockMvc.perform(get("/api/item/" + added.getItemId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(Matchers.containsString(testName)));

        itemService.remove(added.getItemId());
    }
}
