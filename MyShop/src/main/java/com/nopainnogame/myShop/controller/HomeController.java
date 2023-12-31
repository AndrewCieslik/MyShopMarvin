package com.nopainnogame.myShop.controller;

import com.nopainnogame.myShop.Cart;
import com.nopainnogame.myShop.model.Item;
import com.nopainnogame.myShop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public HomeController(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession httpSession) {
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
        }
        Optional<Item> oItem = itemRepository.findById(itemId);
        if(oItem.isPresent()){
            Item item = oItem.get();
            cart.add(item);
            session.setAttribute("cart", cart);
        }
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

}
