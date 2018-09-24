<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<jsp:include page="../header.jsp"></jsp:include>

<c:if test="${not empty msg}">
  <div class="alert alert-${msg.style}" role="alert">
    <button type="button" class="close" data-dismiss="alert">x</button>
    <c:out value="${msg.text}"></c:out>
  </div>
</c:if>

<div class="table-wrapper" id="list-products" >
    <div class="table-title">
        <div class="row">
            <div class="col-sm-2">
                <h2>Produtos</h2>
            </div>
            <div class="col-sm-8">
                <input type="text" class="search form-control" placeholder="Filter products by description or category . . .">
            </div>
            <div class="col-sm-2">
                <a href="#addModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add</span></a>
            </div>
        </div>
    </div>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th class='col-sm-3'>Categoria</th>
                <th class='col-sm-6'>Descri&ccedil;&atilde;o</th>
                <th class='col-sm-2'>Situa&ccedil;&atilde;o</th>
                <th class='col-sm-1'>A&ccedil;&otilde;es</th>
            </tr>
        </thead>
        <tbody class="list">
        <c:forEach items="${products}" var="product">
            <tr>
                <td class="js-list-category"><c:out value="${product.category.description}"/></td>
                <td class="js-list-description"><c:out value="${product.description}"/></td>
                <td><c:out value="${product.activeToString}"/></td>
                <td>
                    <a href="#editModal" class="edit open-edit" data-toggle="modal" 
                        data-id="${product.id}" 
                        data-description="${product.description}" 
                        data-category-value="${product.category.value}" 
                        data-active="${product.active}">
                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        
                    <a href="#deleteModal" class="delete open-delete" data-toggle="modal" 
                        data-id="${product.id}">
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
            <form action='/product/add' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Add Produto</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">        
                    <div class="form-group">
                        <label>Category</label>
			            <select name="category" class='form-control' required>
			              <option value="" label="" />
			              <c:forEach var="varCategory" items="${categoryList}">
			                <option value="${varCategory.value}" label="${varCategory.description}" />
			              </c:forEach>
			            </select>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type='text' name='description' class='form-control' required/>
                    </div>
                    <div class="form-group">
                        <label>Active</label>
                        <input type="checkbox" id="active" name="active" checked="checked">
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
            <form action='/product/update' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Edit Produto</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="productId" id="productId" value=""/>
                    <div class="form-group">
                        <label>Category</label>
                        <select name="category" id="category" class='form-control' required >
                          <c:forEach var="varCategory" items="${categoryList}">
                            <option value="${varCategory.value}" label="${varCategory.description}" />
                          </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type='text' name='description' id='description' class='form-control' value="" required/>
                    </div>
                    <div class="form-group">
                        <label>Active</label>
                        <input type="checkbox" id="active" name="active" >
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
            <form action='/product/delete' method='post'>
                <div class="modal-header">                      
                    <h4 class="modal-title">Delete Produto</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="beanId" id="beanId" value=""/>
                    <p>Are you sure you want to delete this product?</p>
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
    
    var productId = $(this).data('id');
    $(".modal-body #productId").val( productId );
    
    var description = $(this).data('description');
    $(".modal-body #description").val( description );
    
    var categoryValue = $(this).data('category-value');
    $(".modal-body #category").val( categoryValue );
    
    var isActive = $(this).data('active');
    $(".modal-body #active").prop( "checked", isActive );
});

var listProducts = new List('list-products', {
    valueNames: [ 'js-list-category', 'js-list-description' ]
});
</script>

</div>
<jsp:include page="../footer.jsp"></jsp:include>