package com.bangalo.product.web;

import static com.bangalo.commons.web.message.Message.MSG;

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

import com.bangalo.commons.domain.DomainException;
import com.bangalo.commons.web.message.Message;
import com.bangalo.commons.web.message.MessageType;
import com.bangalo.product.domain.Category;
import com.bangalo.product.domain.Product;
import com.bangalo.product.domain.ProductService;

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
			redirectAttributes.addFlashAttribute(MSG,
					Message.get(MessageType.SUCCESS, "Produto excluído com sucesso."));

		} catch (DomainException domainException) {
			redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
		}

		return REDIRECT_PRODUCT_LIST;
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("product") final Product product, final RedirectAttributes redirectAttributes) {

		try {
			service.create(product);
			redirectAttributes.addFlashAttribute(MSG,
					Message.get(MessageType.SUCCESS, "Produto cadastrado com sucesso."));

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
			redirectAttributes.addFlashAttribute(MSG,
					Message.get(MessageType.SUCCESS, "Produto atualizado com sucesso."));

		} catch (DomainException domainException) {
			redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
		}

		return REDIRECT_PRODUCT_LIST;
	}

}
