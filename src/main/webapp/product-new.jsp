<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Valgames - New Product</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
        crossorigin="anonymous"/>
</head>
<body>
<div class="">
  <div class="container">
    <nav class="navbar navbar-expand">
      <div class="container">
        <a class="navbar-brand" href="index.jsp"><img src="${pageContext.request.contextPath}/assets/img/logo.png" style="height: 30px;"></a>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav container align-items-center">
            <li class="nav-item dropdown text-center">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Products</a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/product/new">New Product</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/product/edit">Edit Product</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/product/delete">Delete Product</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/product/list">List Products</a></li>
              </ul>
            </li>
            <li class="nav-item cart-class ms-auto">
              <a class="nav-link d-flex align-items-center bg-opacity-50 rounded-4 btn btn-outline-light" id="cartLink">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-cart m-1" viewBox="0 0 16 16">
                  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                </svg>
                <div class="p-2">ADMIN</div>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container mt-4" style="max-width: 600px;">
      <h4 class="mb-4">New Product</h4>

      <c:if test="${not empty error}">
        <div class="alert alert-danger"><c:out value="${error}"/></div>
      </c:if>

      <form action="${pageContext.request.contextPath}/product/new" method="post">
        <div class="mb-3">
          <label class="form-label">Category</label>
          <select name="categoryId" class="form-select" required>
            <c:forEach var="cat" items="${categories}">
              <option value="${cat.categoryId}">
                <c:out value="${cat.categoryName}"/>
              </option>
            </c:forEach>
          </select>
        </div>
        <div class="mb-3">
          <label class="form-label">Product Name</label>
          <input type="text" name="productName" class="form-control" value="<c:out value="${param.productName}"/>" required/>
        </div>
        <div class="mb-3">
          <label class="form-label">Description</label>
          <textarea name="productDescription" class="form-control" rows="3" required><c:out value="${param.productDescription}"/></textarea>
        </div>
        <div class="mb-3">
          <label class="form-label">Unit Price</label>
          <input type="number" step="0.01" min="0.01" name="unitPrice" class="form-control" value="<c:out value="${param.unitPrice}"/>" required/>
        </div>
        <div class="mb-3">
          <label class="form-label">SKU</label>
          <input type="number" name="sku" class="form-control" value="<c:out value="${param.sku}"/>" required/>
        </div>
        <button type="submit" class="btn btn-dark">Create Product</button>
        <a href="${pageContext.request.contextPath}/product/list" class="btn btn-secondary ms-2">Cancel</a>
      </form>
    </div>

  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
</body>
</html>
