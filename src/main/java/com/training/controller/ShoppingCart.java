package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Product;
import com.training.repository.ShoppingCartRepository;

@RestController
public class ShoppingCart {
	
	@Autowired
	private ShoppingCartRepository cartRepository;
	
	@RequestMapping("/findAll")
	public Iterable<Product> getAllProducts() {
		return cartRepository.findAll();	
	}
	
	@RequestMapping("/add")
	public String addNewProduct(@RequestBody Product prod) {
		cartRepository.save(prod);
		return "New product Saved";
	}

	@RequestMapping("/addQuantityPrice")
	public String addQuantityPrice(@RequestBody Product prod) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if(product.getID() == prod.getID())
			{
				product.setQuantity(prod.getQuantity());
				product.setPrice(prod.getPrice());
				cartRepository.save(product);
			}
		}
		return "Updated Quantity and Price";
	}
	
	@RequestMapping("/find")
	public Product findProduct(@RequestParam int productId) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if (product.getID() == productId)
				return product;
		}
		return null;
	}
	
	@RequestMapping("/findByQuantity")
	public Product findByQuantity(@RequestParam int q) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if (product.getQuantity() == q)
				return product;
		}
		return null;
	}
	
	@RequestMapping("/findByPrice")
	public Product findByPrice(@RequestParam int p) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if (product.getPrice() == p)
				return product;
		}
		return null;
	}
	@RequestMapping("/editQuantity")
	public String editQuantity (@RequestParam int id, @RequestParam int q) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if (product.getID() == id)
				product.setQuantity(q);
			cartRepository.save(product);
			
		}
		return "Quantity Updated";
	}
	
	@RequestMapping("/editPrice")
	public String editPrice (@RequestParam int id, @RequestParam int p) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if (product.getID() == id)
				product.setPrice(p);
			cartRepository.save(product);
			
		}
		return "Price Updated";
	}
	
	@RequestMapping("/buy")
	public String buy (@RequestParam int id, @RequestParam int q) {
		Iterable<Product> pr = cartRepository.findAll();
		for (Product product : pr) {
			if (product.getID() == id) {
				int quantity = product.getQuantity();
				quantity = product.getQuantity() - q;
				product.setQuantity(quantity);
			cartRepository.save(product);
			}
			
		}
		return "Purchased";
	}

}
