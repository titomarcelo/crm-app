package com.bangalo.sale.web;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bangalo.commons.domain.DomainException;
import com.bangalo.customer.domain.CustomerService;
import com.bangalo.product.domain.ProductService;
import com.bangalo.sale.domain.Card;
import com.bangalo.sale.domain.Sale;
import com.bangalo.sale.domain.SaleService;

@Controller
@RequestMapping("/sale")
public class SaleController {

	private static final String REDIRECT_SALE_LIST = "redirect:/sale/crud";

	@Autowired
	private SaleService saleService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@GetMapping("/crud")
	public String list(final Model model) {

		model.addAttribute("cardList", Arrays.asList(Card.values()));
		model.addAttribute("customerList", customerService.findAll());
		model.addAttribute("productList", productService.findAllActive());

		model.addAttribute("sales", saleService.findAll());
		return "/sale/crud";
	}

	@GetMapping("/load/{id}")
	public String load(@PathVariable final String id, final Model model) {
		try {
			model.addAttribute("sale", saleService.findById(id));
		} catch (DomainException domainException) {
			model.addAttribute("msg", domainException.getMessage());
		}

		return "sale/productlist";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("sale") final SaleForm form, final RedirectAttributes redirectAttributes) {

		try {
			Sale sale = new Sale();
			populateWithDataForm(sale, form);

			saleService.create(sale);
			redirectAttributes.addFlashAttribute("msg", "Venda registrada com sucesso.");

		} catch (DomainException domainException) {
			redirectAttributes.addFlashAttribute("msg", domainException.getMessage());
		}

		return REDIRECT_SALE_LIST;
	}

	@PostMapping("/delete")
	public String delete(@RequestParam(name = "beanId") final String id, final RedirectAttributes redirectAttributes) {

		try {
			saleService.delete(id);
			redirectAttributes.addFlashAttribute("msg", "Venda excluída com sucesso.");

		} catch (DomainException domainException) {
			redirectAttributes.addFlashAttribute("msg", domainException.getMessage());
		}

		return REDIRECT_SALE_LIST;
	}

	@PostMapping("/update")
	public String update(final @ModelAttribute("saleForm") SaleForm form, final Model model) {

		try {
			Sale sale = saleService.findById(form.getId());
			populateWithDataForm(sale, form);
			saleService.update(sale);

		} catch (DomainException domainException) {
			model.addAttribute("msg", domainException.getMessage());
		}

		return "/sale/crud";
	}

	@ModelAttribute("saleForm")
	public SaleForm createFormBean() {
		return new SaleForm();
	}

	@InitBinder("saleForm")
	protected void initBinder(final WebDataBinder binder) {
		// init binder
	}

	private void populateWithDataForm(final Sale sale, final SaleForm form) throws DomainException {
		sale.setCard(form.getCard());
		sale.setDate(form.getDateAsLocalDate());
		sale.setAmount(createAmountFromString(form.getAmount()));
		sale.setCustomer(customerService.findById(form.getCustomerId()));

		for (String productId : form.getProductIds()) {
			sale.addProduct(productService.findById(productId));
		}
	}

	private BigDecimal createAmountFromString(final String amount) {
		return new BigDecimal(onlyNumbers(amount)).setScale(2);
	}

	private String onlyNumbers(final String string) {
		return Optional.ofNullable(string).map(str -> string.replaceAll("[^\\d]", EMPTY)).orElse(EMPTY);
	}

}