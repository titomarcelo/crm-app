package com.tmao.crm.product.controller;

import static com.tmao.crm.commons.domain.Message.MSG;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmao.crm.commons.domain.Message;
import com.tmao.crm.commons.domain.MessageType;
import com.tmao.crm.commons.exception.DuplicateException;
import com.tmao.crm.commons.exception.NotFoundException;
import com.tmao.crm.product.domain.Category;
import com.tmao.crm.product.domain.Product;
import com.tmao.crm.product.service.ProductService;

@Controller
public class ProductController {

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/products";

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public String list(final Model model) {

        model.addAttribute("categoryList", Arrays.asList(Category.values()));
        model.addAttribute("products", service.findAll());
        return "products";
    }

    @PostMapping("/products/delete")
    public String delete(@RequestParam(name = "beanId") final String id, final RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Product deleted successfully"));
        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }
        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/products/add")
    public String add(@ModelAttribute("product") final Product product, final RedirectAttributes redirectAttributes) {
        try {
            service.create(product);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Product added successfully"));
        } catch (final DuplicateException duplicate) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, duplicate.getMessage()));
        }
        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/products/update")
    public String update(@RequestParam("productId") final String id,
            @RequestParam("description") final String description, @RequestParam("category") final String category,
            @RequestParam(required = false, name = "active") final boolean active, final RedirectAttributes redirectAttributes) {

        try {
            final Product product = service.findById(id);
            product.setActive(active);
            product.setDescription(description);
            product.setCategory(Category.valueOf(category));

            service.update(product);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Product edited successfully"));

        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }
        return REDIRECT_PRODUCT_LIST;
    }

}
