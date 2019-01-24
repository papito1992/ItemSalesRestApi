package com.SalesAPI.SalesAPI;

import com.SalesAPI.SalesAPI.ItemData.SalesItem;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class SalesItemResourceAssembler  implements ResourceAssembler<SalesItem, Resource<SalesItem>> {
    @Override
    public Resource<SalesItem> toResource(SalesItem salesItem) {

        try {
            return new Resource<>(salesItem,
                    linkTo(methodOn(SalesItemController.class).getSalesItemById(salesItem.getId())).withSelfRel(),
                    linkTo(methodOn(SalesItemController.class).allSaleItems()).withRel("items"),
                    linkTo(methodOn(SalesItemController.class).itemByTitle(salesItem.getTitle())).withRel("byTitle"),
                    linkTo(methodOn(SalesItemController.class).deleteItem(salesItem.getId())).withRel("items"),
                    linkTo(methodOn(SalesItemController.class).newItem(salesItem)).withRel("items"),
                    linkTo(methodOn(SalesItemController.class).updateItem(salesItem, salesItem.getId())).withRel("items")

                    );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    return  new Resource<>(salesItem,
                linkTo(methodOn(SalesItemController.class).getSalesItemById(salesItem.getId())).withSelfRel(),
                linkTo(methodOn(SalesItemController.class).allSaleItems()).withRel("items"),
                linkTo(methodOn(SalesItemController.class).itemByTitle(salesItem.getTitle())).withRel("byTitle"),
                linkTo(methodOn(SalesItemController.class).deleteItem(salesItem.getId())).withRel("items")
            );
    }

}
