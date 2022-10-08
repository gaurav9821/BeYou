package com.beyou.admin.category;

import java.util.List;
import java.util.Set;

import com.beyou.common.entity.Category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {
    
    @Autowired
    private CategoryRepository repo;

    @Test
    public void testCreateRootCategory(){
        Category category = new Category("Books");
        Category savedCategory = repo.save(category);
    }

    @Test
    public void testSubCategory(){
        Category parent = new Category(7);
        Category subCategory =new Category("iPhone",parent);
        Category savedCategory = repo.save(subCategory);

    }

    @Test
    public void testGetCategory(){
        Category category = repo.findById(2).get();
        System.out.println(category.getName());

        Set<Category> children = category.getChildren();

        for(Category subCategory : children){
            System.out.println(subCategory.getName());
        }
    }

    @Test
    public void testPrintHierarchicalCategories(){
        Iterable<Category> categories = repo.findAll();

        for(Category category : categories){
            if(category.getParent() == null){
                System.out.println(category.getName());

                Set<Category> children = category.getChildren();

                for(Category subCategory : children){
                    System.out.println("--" + subCategory.getName());
                    printChildren(subCategory, 1);
                }
            }
        }
    }
    //So we need to have a private method here for the recursive algorithm here.
    private void printChildren(Category parent, int subLevel){

        //Here we increase the sublevel by one new level.
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChildren();

        for(Category subCategory : children){
            for(int i=0; i<newSubLevel; i++){
                System.out.print("--");
            }
            System.out.println(subCategory.getName());

            printChildren(subCategory, newSubLevel);
        }
    }

    @Test
    public void testListRootCategories(){
        List<Category> rootCategories = repo.findRootCategories(Sort.by("name").ascending());
        rootCategories.forEach(cat -> System.out.println(cat.getName()));
    }

    @Test
    public void testFindByName(){
        String name = "Computers1234";
        Category category = repo.findByName(name);
        
    }

    @Test
    public void testFindByAlias(){
        String alias = "something";
        Category category = repo.findByAlias(alias);

        
    }
}
