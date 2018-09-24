package com.bangalo.customer.web;

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
import com.bangalo.customer.domain.Customer;
import com.bangalo.customer.domain.CustomerService;
import com.bangalo.customer.domain.Gender;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final String REDIRECT_CUSTOMER_LIST = "redirect:/customer/list";

    @Autowired
    private CustomerService service;

    @GetMapping("/list")
    public String list(final Model model) {
        model.addAttribute("genderList", Arrays.asList(Gender.values()));
        model.addAttribute("customers", service.findAll());
        return "/customer/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "beanId") final String id, final RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("msg", "Customer delete success");

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute("msg", domainException.getMessage());
        }

        return REDIRECT_CUSTOMER_LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("customer") final Customer customer, final RedirectAttributes redirectAttributes) {

        try {
            service.create(customer);
            redirectAttributes.addFlashAttribute("msg", "Customer registration success");

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute("msg", domainException.getMessage());
        }

        return REDIRECT_CUSTOMER_LIST;
    }

    @PostMapping("/update")
    public String update(@RequestParam("cpf") final String cpf, @RequestParam("name") final String name, @RequestParam("email") final String email,
            @RequestParam("phone") final String phone, @RequestParam("gender") final String gender, final RedirectAttributes redirectAttributes) {

        try {
            Customer customer = service.findByCpf(cpf);

            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setGender(Gender.valueOf(gender));

            service.update(customer);

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute("msg", domainException.getMessage());
        }

        return REDIRECT_CUSTOMER_LIST;
    }

}
