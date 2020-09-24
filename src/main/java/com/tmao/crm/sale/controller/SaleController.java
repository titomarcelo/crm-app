package com.tmao.crm.sale.controller;

import static com.tmao.crm.commons.domain.Message.MSG;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmao.crm.commons.domain.Message;
import com.tmao.crm.commons.domain.MessageType;
import com.tmao.crm.commons.exception.NotFoundException;
import com.tmao.crm.customer.service.CustomerService;
import com.tmao.crm.product.service.ProductService;
import com.tmao.crm.sale.domain.Card;
import com.tmao.crm.sale.domain.Sale;
import com.tmao.crm.sale.service.SaleService;

@Controller
public class SaleController {

    private static final String REDIRECT_SALE_LIST = "redirect:/sales";

    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/sales")
    public String list(final Model model) {

        model.addAttribute("cardList", Arrays.asList(Card.values()));
        model.addAttribute("customerList", customerService.findAll());
        model.addAttribute("productList", productService.findAllActive());

        model.addAttribute("sales", saleService.findAll());
        return "sales";
    }

    // @GetMapping("/load/{id}")
    // public String load(@PathVariable final String id, final Model model, final
    // RedirectAttributes redirectAttributes) {
    // try {
    // model.addAttribute("sale", saleService.findById(id));
    //
    // } catch (DomainException domainException) {
    // redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER,
    // domainException.getMessage()));
    // }
    //
    // return "sale/productlist";
    // }

    @PostMapping("/sales/add")
    public String add(@ModelAttribute("sale") final SaleForm form, final RedirectAttributes redirectAttributes) {

        try {
            final Sale sale = new Sale();
            populateWithDataForm(sale, form);

            saleService.create(sale);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Sale added successfully"));

        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }

        return REDIRECT_SALE_LIST;
    }

    @PostMapping("/sales/delete")
    public String delete(@RequestParam(name = "beanId") final String id, final RedirectAttributes redirectAttributes) {

        try {
            saleService.delete(id);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Sale deleted successfully"));

        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }

        return REDIRECT_SALE_LIST;
    }

    @PostMapping("/sales/update")
    public String update(final @ModelAttribute("saleForm") SaleForm form, final RedirectAttributes redirectAttributes) {

        try {
            final Sale sale = saleService.findById(form.getId());
            populateWithDataForm(sale, form);

            saleService.update(sale);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Sale updated successfully"));

        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }

        return "/sales";
    }

    @ModelAttribute("saleForm")
    public SaleForm createFormBean() {
        return new SaleForm();
    }

    @InitBinder("saleForm")
    protected void initBinder(final WebDataBinder binder) {
        // init binder
    }

    private void populateWithDataForm(final Sale sale, final SaleForm form) throws NotFoundException {
        sale.setCard(form.getCard());
        sale.setDate(form.getDateAsLocalDate());
        sale.setAmount(createAmountFromString(form.getAmount()));
        sale.setCustomer(customerService.findById(form.getCustomerId()));

        for (final String productId : form.getProductIds()) {
            sale.addProduct(productService.findById(productId));
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
