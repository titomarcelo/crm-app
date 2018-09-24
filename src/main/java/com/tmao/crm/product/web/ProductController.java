package com.tmao.crm.product.web;

import static com.tmao.crm.commons.web.message.Message.MSG;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmao.crm.commons.domain.DomainException;
import com.tmao.crm.commons.web.message.Message;
import com.tmao.crm.commons.web.message.MessageType;
import com.tmao.crm.product.domain.Category;
import com.tmao.crm.product.domain.Product;
import com.tmao.crm.product.domain.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    @Autowired
    private ProductService service;

    @GetMapping("/list")
    public String list(final Model model) {

        model.addAttribute("categoryList", Arrays.asList(Category.values()));
        model.addAttribute("products", service.findAll());
        return "/product/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "beanId") final String id, final RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Product deleted successfully"));

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
        }

        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("product") final Product product, final RedirectAttributes redirectAttributes) {

        try {
            service.create(product);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Product added successfully"));

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
        }

        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/update")
    public String update(@RequestParam("productId") final String id,
            @RequestParam("description") final String description, @RequestParam("category") final String category,
            @RequestParam(required = false, name = "active") final boolean active,
            final RedirectAttributes redirectAttributes) {

        try {
            Product product = service.findById(id);

            product.setActive(active);
            product.setDescription(description);
            product.setCategory(Category.valueOf(category));

            service.update(product);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Product edited successfully"));

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
        }

        return REDIRECT_PRODUCT_LIST;
    }

}
