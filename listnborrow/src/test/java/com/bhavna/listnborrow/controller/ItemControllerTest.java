package com.bhavna.listnborrow.controller;

import com.bhavna.listnborrow.dtos.ItemDto;
import com.bhavna.listnborrow.model.Item;
import com.bhavna.listnborrow.model.Owner;
import com.bhavna.listnborrow.repo.OwnerRepository;
import com.bhavna.listnborrow.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @MockBean
    private ItemService itemService;

    @MockBean
    private OwnerRepository ownerRepository;

    @InjectMocks
    private ItemController itemController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllItems()throws Exception {
        Item item1=new Item(1L, "Item1", "Description1", 10.0f, new Owner(1001), false, new Date());
        Item item2=new Item(2L, "Item2", "Description2", 20.0F, new Owner(1002), false, new Date());
        List<Item> items = Arrays.asList(item1, item2);

        when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemName").value("Item1"))
                .andExpect(jsonPath("$[1].itemName").value("Item2"));

        verify(itemService, times(1)).getAllItems();
    }

    @Test
    public void testGetItemById()throws Exception {
        Item item=new Item(1L, "Item1", "Description1", 10.0f, new Owner(1001), false, new Date());

        when(itemService.getItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Item1"));

        verify(itemService, times(1)).getItemById(1L);
    }

    @Test
    public void testCreateItemNoNameBadRequest()throws Exception {
        // DTO without id
        ItemDto item=new ItemDto(null, "Description1", 10.0f, 1004L, false);
        // The item to be returned by the mock service (with ID)
        Item savedItem=new Item(1L, "Item1", "Description1", 10.0f, new Owner(1004), false, new Date());
        // Mocking the service to return the item with an ID
        when(itemService.createItem(any(Item.class))).thenReturn(savedItem);
        // Perform the mock MVC request and verify the results
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isBadRequest());

        // Verify that the service was called once
        //verify(itemService, times(1)).createItem(any(Item.class));
    }

    @Test
    public void testCreateItem() throws Exception {
        // Mock owner object
        Owner mockOwner = new Owner(1004);
        // Mocking ownerRepository to return the mock owner
        when(ownerRepository.findById(1004L)).thenReturn(Optional.of(mockOwner));

        // DTO without id
        ItemDto item=new ItemDto("Item1", "Description1", 10.0f, 1004L, false);
        // The item to be returned by the mock service (with ID)
        Item savedItem=new Item(1L, "Item1", "Description1", 10.0f, new Owner(1004), false, new Date());
        // Mocking the service to return the item with an ID
        when(itemService.createItem(any(Item.class))).thenReturn(savedItem);
        // Perform the mock MVC request and verify the results
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemName").value("Item1"));
        // Verify that the service was called once
        verify(itemService, times(1)).createItem(any(Item.class));
    }

    @Test
    public void testUpdateItem()throws Exception {
        Item updatedItem=new Item(1L, "UpdatedItem", "UpdatedDescription", 15.0f, new Owner(1005), false, new Date());

        when(itemService.updateItem(eq(1L), any(Item.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("UpdatedItem"));

        verify(itemService, times(1)).updateItem(eq(1L), any(Item.class));
    }

    @Test
    public void testDeleteItem()throws Exception {
        when(itemService.deleteItem(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/items/1"))
                .andExpect(status().isOk());

        verify(itemService, times(1)).deleteItem(1L);
    }
}
