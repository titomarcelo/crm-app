package com.tmao.crm.customer.controller;

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
import com.tmao.crm.customer.domain.Customer;
import com.tmao.crm.customer.domain.Gender;
import com.tmao.crm.customer.service.CustomerService;

@Controller
public class CustomerController {

    private static final String REDIRECT_CUSTOMER_LIST = "redirect:/customers";

    @Autowired
    private CustomerService service;

    @GetMapping("/customers")
    public String list(final Model model) {
        model.addAttribute("genderList", Arrays.asList(Gender.values()));
        model.addAttribute("customers", service.findAll());
        return "customers";
    }

    @PostMapping("/customers/delete")
    public String delete(@RequestParam(name = "beanId") final String id, final RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Customer deleted successfully"));
        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }
        return REDIRECT_CUSTOMER_LIST;
    }

    @PostMapping("/customers/add")
    public String add(@ModelAttribute("customer") final Customer customer, final RedirectAttributes redirectAttributes) {
        try {
            service.create(customer);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Customer added successfully"));
        } catch (final DuplicateException duplicate) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, duplicate.getMessage()));
        }
        return REDIRECT_CUSTOMER_LIST;
    }

    @PostMapping("/customers/update")
    public String update(@RequestParam("cpf") final String cpf, @RequestParam("name") final String name, @RequestParam("email") final String email,
            @RequestParam("phone") final String phone, @RequestParam("gender") final String gender, final RedirectAttributes redirectAttributes) {

        try {
            final Customer customer = service.findByCpf(cpf);
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setGender(Gender.valueOf(gender));

            service.update(customer);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Customer edited successfully"));

        } catch (final NotFoundException notFound) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, notFound.getMessage()));
        }
        return REDIRECT_CUSTOMER_LIST;
    }

}
