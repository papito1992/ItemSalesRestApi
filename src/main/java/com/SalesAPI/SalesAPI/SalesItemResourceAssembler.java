package com.SalesAPI.SalesAPI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.SalesAPI.SalesAPI.ItemData.SalesItem;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class SalesItemResourceAssembler implements ResourceAssembler<SalesItem, Resource<SalesItem>> {
    @Override
    public Resource<SalesItem> toResource(SalesItem salesItem) {

        return new Resource<>(salesItem,
                linkTo(methodOn(SalesItemController.class).getSalesItemById(salesItem.getId())).withSelfRel(),
                linkTo(methodOn(SalesItemController.class).allSaleItems()).withRel("items"));
    }

}
