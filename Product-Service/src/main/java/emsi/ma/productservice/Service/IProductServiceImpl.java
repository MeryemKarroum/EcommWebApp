package emsi.ma.productservice.Service;

import emsi.ma.productservice.Entity.Product;
import emsi.ma.productservice.Repository.ProductRepo;
import emsi.ma.productservice.clients.CategoryRestClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IProductServiceImpl implements IProductService {
    @Autowired
    public ProductRepo productRepo;
    @Autowired
    public CategoryRestClient categoryRestClient;
    @Override
    public void addProduct(Product p) {
        productRepo.save(p);
    }

    @Override
    public String deleteProduct(Integer id) {
        if(productRepo.findById(id).isPresent()){
            productRepo.deleteById(id);
            return String.format("le produit %d est supprimé",id);
        }
        return String.format("le produit %d n'existe pas ",id);
    }

    public String updateProduct(Integer id, Product p) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(p.getName());
            product.setCategoryId(p.getCategoryId());
            product.setDescription(p.getDescription());

            productRepo.save(product);
            return "Produit modifié avec succès";
        }
        return "Le produit n'existe pas";
    }


    @Override
    public Page<Product> findAllProducts(Pageable pageable) {

        Page<Product> productList= productRepo.findAll(pageable);
        productList.forEach(a->a.setCategory(categoryRestClient.getCategoryById(a.getCategoryId())));
        return productList;
    }

    @Override
    public Product findProductById(Integer id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new EntityNotFoundException("Product with ID " + id + " not found");
        }
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepo.findAllByName(name);
    }

    @Override
    public List<Product> findProductsByCategory(Integer category) {

        return productRepo.findAllByCategoryId(category);
    }

    @Override
    public List<Product> findProductsByPriceLessThan(Double price) {
        return productRepo.findAllByPriceLessThan(price);
    }
}
