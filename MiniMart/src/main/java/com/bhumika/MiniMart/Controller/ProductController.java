package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Dto.ProductDto;
import com.bhumika.MiniMart.Entity.Product;
import com.bhumika.MiniMart.Service.ProductService;
import com.bhumika.MiniMart.Util.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Product Controller", description = "APIs for managing products in MiniMart")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @Operation(summary = "Create a new product", description = "Adds a new product to the MiniMart inventory")
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        Product product = DtoMapper.toProductEntity(dto);
        Product saved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoMapper.toProductDto(saved));
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Fetches the list of all available products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Fetches details of a product using its ID")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Updates the details of an existing product")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product from the inventory using its ID")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

