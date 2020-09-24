<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<jsp:include page="header.jsp"></jsp:include>

<div class="table-wrapper" id="list-customers" >
    <div class="table-title">
        <div class="row">
            <div class="col-sm-2">
                <h2>Customers</h2>
            </div>
            <div class="col-sm-8">
                <input type="text" class="search form-control" placeholder="Filter by cpf or name . . .">
            </div>
            <div class="col-sm-2">
                <a href="#addModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add</span></a>
            </div>
        </div>
    </div>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
		        <th class='col-sm-2'>Cpf</th>
		        <th class='col-sm-3'>Name</th>
		        <th class='col-sm-2'>Email</th>
		        <th class='col-sm-2'>Phone</th>
		        <th class='col-sm-2'>Gender</th>
                <th class='col-sm-1'>Actions</th>
            </tr>
        </thead>
        <tbody class="list">
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td class="js-list-cpf cpf-mask"><c:out value="${customer.cpf}"/></td>
                <td class="js-list-name"><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td class='cell-phone-with-ddd-mask'><c:out value="${customer.phone}"/></td>
                <td><c:out value="${customer.gender.description}"/></td>
                <td>
                    <a href="#editModal" class="edit open-edit" data-toggle="modal" 
                        data-id="${customer.id}" 
                        data-cpf="${customer.cpf}" 
                        data-name="${customer.name}" 
                        data-email="${customer.email}" 
                        data-phone="${customer.phone}" 
                        data-gender-value="${customer.gender.value}">
                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        
                    <a href="#deleteModal" class="delete open-delete" data-toggle="modal" 
                        data-id="${customer.id}">
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
            <form action='/customers/add' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Add new customer</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Cpf</label>
                        <input type='text' name='cpf' class='form-control cpf-mask' required/>
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input type='text' name='name' class='form-control' maxlength='40' required/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type='email' name='email' class='form-control' required />
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type='text' name='phone' class='form-control cell-phone-with-ddd-mask' required/>
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender" class='form-control' required>
                          <option value="" label="" />
                          <c:forEach var="varGender" items="${genderList}">
                            <option value="${varGender.value}" label="${varGender.description}" />
                          </c:forEach>
                        </select>
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
            <form action='/customers/update' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Edit customer</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="customerId" id="customerId" value=""/>
                    
                    <div class="form-group">
                        <label>Cpf</label>
                        <input type='text' name='cpf' id='cpf' value="" class='form-control cpf-mask' readonly/>
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input type='text' name='name' id='name' value="" class='form-control' maxlength='40' required/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type='email' name='email' id='email' value="" class='form-control' required />
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type='text' name='phone' id='phone' value="" class='form-control cell-phone-with-ddd-mask' required/>
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender" id="gender" class='form-control' required >
                          <c:forEach var="varGender" items="${genderList}">
                            <option value="${varGender.value}" label="${varGender.description}" />
                          </c:forEach>
                        </select>
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
            <form action='/customers/delete' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Delete customer</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="beanId" id="beanId" value=""/>
                    <p>Are you sure you want to delete this customer?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
	                <input type="submit" class="btn btn-danger" value="Delete">
                </div>
            </form>
        </div>
    </div>
</div>


<script>
$(document).on("click", ".open-edit", function (e) {
    e.preventDefault();
    
    var customerId = $(this).data('id');
    $(".modal-body #customerId").val( customerId );
    
    var cpf = $(this).data('cpf');
    $(".modal-body #cpf").val( cpf );
    
    var name = $(this).data('name');
    $(".modal-body #name").val( name );
    
    var email = $(this).data('email');
    $(".modal-body #email").val( email );
    
    var phone = $(this).data('phone');
    $(".modal-body #phone").val( phone );
    
    var genderValue = $(this).data('gender-value');
    $(".modal-body #gender").val( genderValue );
});

var listCustomers = new List('list-customers', {
    valueNames: [ 'js-list-cpf', 'js-list-name' ]
});
</script>

</div>
<jsp:include page="footer.jsp"></jsp:include>