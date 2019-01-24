package com.SalesAPI.SalesAPI.Service;

import com.SalesAPI.SalesAPI.ItemData.ItemRepository;
import com.SalesAPI.SalesAPI.ItemData.SalesItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesItemService {
    private ItemRepository itemRepository;

    @Autowired
    public SalesItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Custom QUERIES
    public List<SalesItem> findByTitle(String title) {
        return itemRepository.findByTitle(title);
    }

    public List<SalesItem> findByPriceRange(Integer start, Integer end) {
        return itemRepository.findSalesItemsByPriceBetween(start, end);
    }

    public List<SalesItem>findByTitleOrderByTitleAsc(){
        return itemRepository.findAllByOrderByTitle();
    }

}
