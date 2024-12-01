package emsi.ma.productservice.Controller;
import emsi.ma.productservice.Entity.Product;
import emsi.ma.productservice.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;
    @PostMapping("/add")
    public void add(@RequestBody Product p){
        productService.addProduct(p);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id,@RequestBody Product p){
        return productService.updateProduct(id, p);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.findProductById(id);
    }
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
      Pageable pageable= PageRequest.of(page, size);
     return productService.findAllProducts(pageable);
    }

    @GetMapping("/search/name/{productName}")
    public List<Product> searchProductByName(@PathVariable String productName){
        return productService.findProductsByName(productName);
    }
    @GetMapping("/search/category/{category}")
    public List<Product> searchProductByCategory(@PathVariable Integer category){
        return productService.findProductsByCategory(category);
    }

    @GetMapping("/search/{price}")
    public List<Product> searchProductByPrice(@PathVariable Double price){
        return productService.findProductsByPriceLessThan(price);
    }

}
