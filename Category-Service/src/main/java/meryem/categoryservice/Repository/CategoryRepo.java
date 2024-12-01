package meryem.categoryservice.Repository;

import meryem.categoryservice.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
