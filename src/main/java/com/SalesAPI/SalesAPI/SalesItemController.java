package com.SalesAPI.SalesAPI;

import com.SalesAPI.SalesAPI.ItemData.ItemRepository;
import com.SalesAPI.SalesAPI.ItemData.SalesItem;
import com.SalesAPI.SalesAPI.ItemData.SalesItemComment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SalesItemController extends ResourceSupport {
    private final ItemRepository repository;
    private final SalesItemResourceAssembler assembler;

    public SalesItemController(ItemRepository repository, SalesItemResourceAssembler assembler) {
        this.assembler = assembler;
        this.repository = repository;
    }

    @RequestMapping("/byTitle/{title}")
    Resources<Resource<SalesItem>> itemByTitle(@PathVariable(value = "title") String title) {
        List<Resource<SalesItem>> allItemsWithTitle = repository.findByTitle(title)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(allItemsWithTitle,
                linkTo(methodOn(SalesItemController.class).itemByTitle(title)).withSelfRel());

    }

    @RequestMapping("/priceRange/{start}/{end}")
    Resources<Resource<SalesItem>> itemByPriceRange(@PathVariable(value = "start") Integer start, @PathVariable(value = "end") Integer end) {
        List<Resource<SalesItem>> priceRangeItems = repository.
                findSalesItemsByPriceBetween(start, end)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(priceRangeItems,
                linkTo(methodOn(SalesItemController.class).itemByPriceRange(start, end)).withSelfRel());
    }

    @RequestMapping("/orderItems")
    Resources<Resource<SalesItem>> sortByTitle() {
        List<Resource<SalesItem>> priceRangeItems = repository.
                findAllByOrderByTitle()
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(priceRangeItems,
                linkTo(methodOn(SalesItemController.class).sortByTitle()).withSelfRel());
    }

    @GetMapping("/items/allItems")
    Resources<Resource<SalesItem>> allSaleItems() {
        List<Resource<SalesItem>> allItems = repository.findAll().stream()
                .map(assembler::toResource).collect(Collectors.toList());

        return new Resources<>(allItems,
                linkTo(methodOn(SalesItemController.class).allSaleItems()).withSelfRel());
    }

    @PostMapping("/items/addItem")
    ResponseEntity<?> newItem(@RequestBody SalesItem newSalesItem) throws URISyntaxException {
        Resource<SalesItem> resource = assembler.toResource(repository.save(newSalesItem));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping("/items/{id}/getItemById")
    Resource<SalesItem> getSalesItemById(@PathVariable Long id) {
        SalesItem salesItem = repository.findById(id).orElseThrow(() -> new SalesItemNotFoundException(id));
        return assembler.toResource(salesItem);
    }

    @PutMapping("/items/{id}/updateItem")
    ResponseEntity<?> updateItem(@RequestBody SalesItem newSalesItem, @PathVariable Long id) throws URISyntaxException {
        SalesItem updatedSalesItem = repository.findById(id)
                .map(item -> {
                    if (newSalesItem.getCountry() != null)
                        item.setCountry(newSalesItem.getCountry());
                    if (newSalesItem.getCity() != null)
                        item.setCity(newSalesItem.getCity());
                    if (newSalesItem.getStreet() != null)
                        item.setStreet(newSalesItem.getStreet());
                    if (newSalesItem.getGpsCoordinates() != null)
                        item.setGpsCoordinates(newSalesItem.getGpsCoordinates());
                    if (newSalesItem.getTitle() != null)
                        item.setTitle(newSalesItem.getTitle());
                    if (newSalesItem.getDescription() != null)
                        item.setDescription(newSalesItem.getDescription());
                    if (newSalesItem.getStock() == 0)
                        item.setStock(newSalesItem.getStock());
                    if (newSalesItem.getPrice() == 0)
                        item.setPrice(newSalesItem.getPrice());


                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newSalesItem.setId(id);
                    return repository.save(newSalesItem);
                });
        Resource<SalesItem> resource = assembler.toResource(updatedSalesItem);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/items/{id}/addComment/")
    ResponseEntity<?> addCommentToItem(@RequestBody SalesItemComment newSalesItem, @PathVariable Long id) throws URISyntaxException {
        SalesItem updatedSalesItem = repository.findById(id)
                .map(item -> {
                    item.getComments().add(newSalesItem);
                    return repository.save(item);
                })
                .orElseThrow(() -> new SalesItemNotFoundException(id));
        Resource<SalesItem> resource = assembler.toResource(updatedSalesItem);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    /**
     * Specify id and cid to delete comment from the items comment list by index.
     */
    @PutMapping("/items/{id}/delComment/{cid}")
    ResponseEntity<?> deleteCommentByIndex(@PathVariable Long id, @PathVariable Long cid) throws URISyntaxException {
        SalesItem salesItemComment = repository.findById(id).map(
                comment -> {
                    int commentIndex = Math.toIntExact(cid);
                    comment.getComments().remove(commentIndex);
                    return repository.save(comment);
                }).orElseThrow(() -> new SalesItemNotFoundException(id));
        Resource<SalesItem> resource = assembler.toResource(salesItemComment);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/items/{id}/delete")
    ResponseEntity<?> deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


}
