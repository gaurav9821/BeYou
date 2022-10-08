package com.beyou.admin.setting.country;

import java.util.List;

import com.beyou.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CountryRestController {
    
    @Autowired
    private CountryRepository repo;

    @GetMapping("/countries/list")
    public List<Country> listAll() {
        return repo.findAllByOrderByNameAsc();
    }
    
    @PostMapping("/countries/save")
    public String save(@RequestBody Country country){
        Country saveCountry = repo.save(country);
        return String.valueOf(saveCountry.getId());
    }

    @DeleteMapping("/countries/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        repo.deleteById(id);
    }
}
