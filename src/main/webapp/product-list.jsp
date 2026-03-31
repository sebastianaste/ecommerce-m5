<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Valgames</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous"
  />
</head>
<body>
<!-- body -->
<div class="">
  <div class="container">
    <!-- navbar -->
    <nav class="navbar navbar-expand">
      <div class="container">
        <a class="navbar-brand" href="index.jsp"><img src="${pageContext.request.contextPath}/assets/img/logo.png" style="height: 30px;"></a>
        <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNav"
                aria-controls="navbarNav"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav container align-items-center">

            <li class="nav-item dropdown text-center">
              <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      role="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
              >
                Products
              </a>
              <ul class="dropdown-menu ">
                <li>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/product/new"
                  >New Product</a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/product/edit"
                  >Edit Product</a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/product/delete"
                  >Delete Product</a
                  >
                </li>
                <li>
                  <a class="dropdown-item"  href="${pageContext.request.contextPath}/product/list">List Products</a
                  >
                </li>
              </ul>
            </li>

            <li class="nav-item cart-class ms-auto ">
              <div class="">
                <a
                        class="nav-link d-flex align-items-center bg-opacity-50 rounded-4 btn btn-outline-light"
                        id="cartLink"
                >
                  <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="32"
                          height="32"
                          fill="currentColor"
                          class="bi bi-cart m-1"
                          viewBox="0 0 16 16"
                  >
                    <path
                            d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"
                    />
                  </svg>
                  <div class="p-2">ADMIN</div>
                </a>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container mt-4">
      <form action="${pageContext.request.contextPath}/product/list" method="post" class="d-flex justify-content-end mb-4">
        <div class="input-group" style="max-width: 400px;">
          <input type="text" name="searchId" class="form-control" placeholder="Enter product name..." required>
          <button type="submit" class="btn btn-dark">Search</button>
          <a href="${pageContext.request.contextPath}/product/list" class="btn btn-secondary">Clear Search</a>
        </div>
      </form>

    </div>

    <div class="container-fluid me-3 content-row">
      <div id="all" class="row">
        <c:forEach var="p" items="${list}">
          <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 pt-3">
            <div class="card">
              <div class="img-card-product">
                <p class="bg-warning bg-opacity-10 rounded-2 position-absolute top-0 end-0 m-2" style="width: auto; padding: 8px 12px;">${p.unitPrice}</p>
                <img src="${pageContext.request.contextPath}/assets/img/${p.id}.png" class="card-img-top"/>
              </div>
              <div class="card-body">
                <h5 class="card-title">${p.productName}</h5>
                <p class="card-text description">${p.productDescription}</p>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>

  </div>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"
></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</body>
</html>