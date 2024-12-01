package meryem.orderdetailsservice.clients;

import meryem.orderdetailsservice.Models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Integer id);
    @GetMapping
    List<Product> getAllProducts();
}
