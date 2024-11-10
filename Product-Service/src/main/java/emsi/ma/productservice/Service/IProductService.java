package emsi.ma.productservice.Service;

import emsi.ma.productservice.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public void addProduct(Product p);
    public String deleteProduct(Integer id);
    public String updateProduct(Integer id,Product p);

    public List<Product> findAllProducts();
    public List<Product> findProductsByName(String name );
    public List<Product> findProductsByCategory(String category);
    public List<Product> findProductsByPriceLessThan(Double price);

}
