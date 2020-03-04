<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Category Entry Results</title>


</head>

<body>
    <main role="main" class="container-fluid">
	<h1> Category Entry </h1>
<?php
    $categoryID=$_POST["categoryID"];
    $categoryName=$_POST["categoryName"];

    if (!$categoryID || !$categoryName ) {
        echo "You have not entered all required details try again.";
        exit;
    }

    //format input
    $categoryID = addslashes($categoryID);
    $categoryName = addslashes($categoryName);

    //connect to the database
    @$db = new mysqli('localhost', 'root', '', 'usernamesandpasswords');


    if ($db->connect_error) {
        die('Connect Error ' . $db->connect_errno . ': ' . $db->connect_error);
    }


    $query = "insert into category values (?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param("ds", $categoryID, $categoryName);
    $stmt->execute();
    echo $stmt->affected_rows." Category inserted into database";

    $db->close();
?>
    <br/
    ><a href="Show_Categories.php">Show Categories</a>
</main>
</body>

</html>
