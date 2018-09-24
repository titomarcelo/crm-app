<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<div class="form-group">
    <c:forEach var="varProduct" items="${sale.products}">
      <p><c:out value="${varProduct.description}"/></p>
    </c:forEach>
</div>
</br>
<p class="text-warning"><small><c:out value="${sale.customer.name}"/> - CPF <span class='cpf_mask'><c:out value="${sale.customer.cpf}"/></span></small></p>
