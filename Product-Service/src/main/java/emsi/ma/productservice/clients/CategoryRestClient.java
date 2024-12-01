package emsi.ma.productservice.clients;

import emsi.ma.productservice.models.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="CATEGORY-SERVICE")
public interface CategoryRestClient {
    @GetMapping("/categories")
    List<Category> getAllCategories();
    @GetMapping("/categories/{id}")
    Category getCategoryById(@PathVariable Integer id);
}
