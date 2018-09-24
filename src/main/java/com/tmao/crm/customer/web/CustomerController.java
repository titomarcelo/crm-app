package com.tmao.crm.customer.web;

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
import com.tmao.crm.customer.domain.Customer;
import com.tmao.crm.customer.domain.CustomerService;
import com.tmao.crm.customer.domain.Gender;

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
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Customer deleted successfully"));

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
        }

        return REDIRECT_CUSTOMER_LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("customer") final Customer customer, final RedirectAttributes redirectAttributes) {

        try {
            service.create(customer);
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Customer added successfully"));

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
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
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.SUCCESS, "Customer edited successfully"));

        } catch (DomainException domainException) {
            redirectAttributes.addFlashAttribute(MSG, Message.get(MessageType.DANGER, domainException.getMessage()));
        }

        return REDIRECT_CUSTOMER_LIST;
    }

}
