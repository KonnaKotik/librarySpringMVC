<!doctype html>
<html lang="en" class="full-height">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../static/css/edit.css">

    <title>Library</title>
</head>
<body style="background-color: #f2ddc6">
<div>
	<nav class="navbar navbar-expand-md navbar-light sticky-top">
        <div class="brand">
            <a class="navbar-brand" type = "brand" href="/"><strong>Your Library</strong></a>
        </div>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/books">Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/authors">Authors</a>
                </li>
            </ul>
        </div>
        <form class="form-inline my-2 my-lg-0">
            <div class="btn-group mr-2" role="group" aria-label="First group">
                <div class="nav-item btn-home">
                    <a href='/' class="btn btn-outline-light my-2 my-sm-0" type="back">Back</a>
                </div>
            </div>
        </form>
    </nav>
</div>

<div class="container">
        <br><br>
        <div class="row" style="margin-bottom: 50px ">
            <div class="col-4"></div>
            <div class="col-4"></div>
             <div class="col-4 text-right">
                 <form method="post">
                     <button href="http://localhost:8080/authors/edit/${author.id}/delete" class="btn btn-outline-danger" type="submit" data-toggle="modal" data-target="#deleteModal"  style="color: #000000; width: 150px; border-radius: 20px ">Delete</button>
                 </form>
             </div>
        </div>
     <form action="/authors/edit/${author.id}"   method="post">
        <div class="row" style="margin-bottom: 15px ">
        <div class="col-2 text-left" style="
  padding: 15px;">
                <h4>Author's name</h4>
            </div>
            <input type="text" class="col-10 themed-grid-col" name="name" id="name"  placeholder="${author.name}" >
        </div>
        <div class="row" style="margin-bottom: 30px ">
        <div class="col-2 text-left" style="
  padding: 15px;">
                <h4>Description</h4>
            </div>
            <input type="text" class="col-10 themed-grid-col" name="description" id="description"  placeholder="${author.description}" >
        </div>
        <div class="text-center">

            <button type="submit" class="btn btn-outline-success" style="color: #000000;width: 200px; border-radius: 20px ">Edit</button>

        </div>
     </form>
    </div>



</body>
</html>