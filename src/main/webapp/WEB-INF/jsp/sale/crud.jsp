<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<jsp:include page="../header.jsp"></jsp:include>

<div class="table-wrapper" id="list-sales" >
    <div class="table-title">
        <div class="row">
            <div class="col-sm-2">
                <h2>Sales</h2>
            </div>
            <div class="col-sm-8">
                <input type="text" class="search form-control" placeholder="Filter by date, customer name or cpf . . .">
            </div>
            <div class="col-sm-2">
                <a href="#addModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add</span></a>
            </div>
        </div>
    </div>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th class="col-sm-2">Date</th>
                <th class="col-sm-3">Customer</th>
                <th class="col-sm-2">Cpf</th>
                <th class="col-sm-2">Card</th>
                <th class="col-sm-1">Amount (R$)</th>
                <th class="col-sm-1">Products</th>
                <th class="col-sm-1">Actions</th>
            </tr>
        </thead>
        <tbody class="list">
        <c:forEach items="${sales}" var="sale">
            <tr>
                <td class="js-list-date"><c:out value="${sale.date}"/></td>
                <td class="js-list-customer"><c:out value="${sale.customer.name}"/></td>
                <td class="js-list-cpf cpf_mask"><c:out value="${sale.customer.cpf}"/></td>
                <td><c:out value="${sale.card.description}"/></td>
                <td class='money3_mask'><c:out value="${sale.amount}"/></td>
                <td>
                    <a href="javascript:void(0);" data-href="/sale/load/${sale.id}" class="open-view">
                        <i class="material-icons" data-toggle="tooltip" title="Produtos da venda">list</i></a>
                </td>
                <td>
                    <a href="#editModal" class="edit open-edit" data-toggle="modal" 
                        data-id="${sale.id}" 
                        data-date="${sale.date}" 
                        data-customer-id="${sale.customer.id}" 
                        data-card="${sale.card.description}"
                        data-product-ids=""
                        data-amount="${sale.amount}">
                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        
                    <a href="#deleteModal" class="delete open-delete" data-toggle="modal" 
                        data-id="${sale.id}">
                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
    
    
<%-- Add Modal HTML --%>
<div id="addModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action='/sale/add' name="saleForm" method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Add Venda</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Date</label>
			           <div class="input-group date" data-provide="datepicker">
			              <input type="text" name="date" class="form-control">
			              <div class="input-group-addon">
			                  <span class="glyphicon glyphicon-th"></span>
			              </div>
			          </div>
                    </div>
                    <div class="form-group">
                        <label>Card</label>
			            <select name="card" class='form-control' required>
			              <option value="" label="" />
			              <c:forEach var="varCard" items="${cardList}">
			                <option value="${varCard.value}" label="${varCard.description}" />
			              </c:forEach>
			            </select>
                    </div>
                    <div class="form-group">
                        <label>Cliente</label>
                        <select name="customerId" class='form-control' required>
                          <option value="" label="" />
                          <c:forEach var="varCustomer" items="${customerList}">
                            <option value="${varCustomer.id}">
                              <c:out value="${varCustomer.name}"/> - CPF <div class='cpf_mask'><c:out value="${varCustomer.cpf}"/></div>
                            </option>
                          </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Produtos</label><br>
                        <c:forEach var="varProduct" items="${productList}">
                            <input type="checkbox" name="productIds" value="${varProduct.id}"> <c:out value="${varProduct.description}"/><br>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label>Valor (R$)</label>
                        <input type="text" name='amount' class='form-control money3_mask' required/>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>

        
<%-- Edit Modal HTML --%>
<div id="editModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action='/sale/update' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Edit Venda</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="saleId" id="saleId" value=""/>

                    <div class="form-group">
                        <label>Date</label>
                       <div class="input-group date" data-provide="datepicker">
                          <input type="text" name="date" id='date' class="form-control">
                          <div class="input-group-addon">
                              <span class="glyphicon glyphicon-th"></span>
                          </div>
                      </div>
                    </div>

                    <div class="form-group">
                        <label>Card</label>
                        <select name="card" id="card" class='form-control' required >
                          <c:forEach var="varCard" items="${cardList}">
                            <option value="${varCard.value}" label="${varCard.description}" />
                          </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Cliente</label>
                        <select name="customerId" id="customerId" class='form-control' required>
                          <c:forEach var="varCustomer" items="${customerList}">
                            <option value="${varCustomer.id}" label="${varCustomer.name} - CPF ${varCustomer.cpf}" />
                          </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Produtos</label><br>
                        <c:forEach var="varProduct" items="${productList}">
                          <input type="checkbox" name="productIds" id="productIds" value="${varProduct.id}"> <c:out value="${varProduct.description}"/><br>
                        </c:forEach>
                    </div>
                    
                    <div class="form-group">
                        <label>Valor (R$)</label>
                        <input type="text" name='amount' id='amount' value="" class='form-control money3_mask' required/>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Save">
                </div>
            </form>
        </div>
    </div>
</div>
    
    
<%-- Delete Modal HTML --%>
<div id="deleteModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action='/sale/delete' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Delete Venda</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="beanId" id="beanId" value=""/>
                    <p>Are you sure you want to delete this venda?</p>
                    <p class="text-warning"><small>Esta ação não poderá ser desfeita.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
	                <input type="submit" class="btn btn-danger" value="Delete">
                </div>
            </form>
        </div>
    </div>
</div>


<%-- View Modal HTML --%>
<div id="viewModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">                      
                    <h4 class="modal-title">Produtos da venda</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <%-- productlist.jsp --%>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">
                </div>
            </form>
        </div>
    </div>
</div>

<script>
$(document).ready(function(){
    $('.open-view').on('click',function(e){
    	    e.preventDefault();
    	    
        var dataURL = $(this).attr('data-href');
        $('.modal-body').load(dataURL,function(){
            $('#viewModal').modal('show');
        });
    }); 
});

$(document).on("click", ".open-edit", function (e) {
    e.preventDefault();
    
    var saleId = $(this).data('id');
    $(".modal-body #saleId").val( saleId );
    
    var cardValue = $(this).data('card-value');
    $(".modal-body #card").val( cardValue );
    
    var customerId = $(this).data('customer-id');
    $(".modal-body #customerId").val( customerId );


    
    
    var isActive = $(this).data('active');
    $(".modal-body #active").prop( "checked", isActive );
    
    
    
    
    
    
    var amount = $(this).data('amount');
    $(".modal-body #amount").val( amount );
});

var listSales = new List('list-sales', {
    valueNames: [ 'js-list-date', 'js-list-customer', 'js-list-cpf' ]
});
</script>

</div>
<jsp:include page="../footer.jsp"></jsp:include>