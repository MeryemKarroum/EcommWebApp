package emsi.ma.productservice.Service;

import emsi.ma.productservice.Entity.Product;
import emsi.ma.productservice.Repository.ProductRepo;
import emsi.ma.productservice.Service.IProductService;
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

    @Override
    public String updateProduct(Integer id, Product p) {
        Product product=productRepo.findById(id).get();
        if(product != null){
            product.setId(p.id);
            product.setPrice(p.price);
            product.setCategory(p.category);
            product.setName(p.name);
            product.setDescription(p.description);
            return "Produit est modifié";
        }
         return "Le produit n'existe pas ";
    }


    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return  productRepo.findAll(pageable);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepo.findAllByName(name);
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return productRepo.findAllByCategory(category);
    }

    @Override
    public List<Product> findProductsByPriceLessThan(Double price) {
        return productRepo.findAllByPriceLessThan(price);
    }
}
