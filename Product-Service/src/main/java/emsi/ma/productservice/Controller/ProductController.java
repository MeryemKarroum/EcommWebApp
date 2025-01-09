package emsi.ma.productservice.Controller;
import emsi.ma.productservice.Entity.Product;
import emsi.ma.productservice.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void add(@RequestParam("name") String name,
                    @RequestParam("price") Double price,
                    @RequestParam("description") String description,
                    @RequestParam("categoryId") Integer categoryId,
                    @RequestParam("image") MultipartFile image) throws Exception {
        // Chemin où l'image sera enregistrée
        String folderPath = "C:\\Users\\Dounia\\Documents\\S9\\Ecom_Jee_Angular\\EcommWebApp\\Product-Service\\src\\main\\resources\\images";
        String imageName = image.getOriginalFilename();

        // Supprimer l'extension du nom de l'image (si présente)
        if (imageName != null && imageName.contains(".")) {
            imageName = imageName.substring(0, imageName.lastIndexOf('.'));
        }

        // Sauvegarder le fichier sur le disque
        Path path = Paths.get(folderPath + imageName + ".png"); // Ajouter l'extension désirée (par exemple, .png)
        Files.write(path, image.getBytes());

        // Créer le produit
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setImage(imageName); // Enregistrer uniquement le nom sans extension dans la base de données

        productService.addProduct(product);
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
    @GetMapping("/category/{category}")
    public List<Product> searchProductByCategory(@PathVariable Integer category){
        return productService.findProductsByCategory(category);
    }

    @GetMapping("/search/{price}")
    public List<Product> searchProductByPrice(@PathVariable Double price){
        return productService.findProductsByPriceLessThan(price);
    }

    @GetMapping(path= "/imageProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] image(@PathVariable(name="id")Integer id)throws Exception{
        Product p=productService.findProductById(id);
        String image=p.getImage();
        File file=new File("C:\\Users\\Dounia\\Documents\\S9\\Ecom_Jee_Angular\\EcommWebApp\\Product-Service\\src\\main\\resources\\images\\"+image+".png");
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

}
