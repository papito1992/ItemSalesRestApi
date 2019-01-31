package com.SalesAPI.SalesAPI.Service;

import com.SalesAPI.SalesAPI.ItemData.ItemRepository;
import com.SalesAPI.SalesAPI.ItemData.SalesItem;
import com.SalesAPI.SalesAPI.SalesItemResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesItemService extends ResourceSupport {
    private ItemRepository itemRepository;
    @Autowired
    private final SalesItemResourceAssembler assembler;

    @Autowired
    public SalesItemService(ItemRepository itemRepository, SalesItemResourceAssembler assembler) {
        this.itemRepository = itemRepository;
        this.assembler = assembler;
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

    public List<Resource<SalesItem>> getAllItems() {
        List<Resource<SalesItem>> allItems = itemRepository.findAll().stream()
                .map(assembler::toResource).collect(Collectors.toList());

        return  allItems;
    }

}
