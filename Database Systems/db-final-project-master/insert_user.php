<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Insert Users Results</title>


</head>

<body>
    <main role="main" class="container-fluid">
	<h1> Users Entry </h1>
<?php
    $userID=$_POST["userID"];
    $firstName=$_POST["firstName"];
    $lastName=$_POST["lastName"];
    $phoneNumber=$_POST["phoneNumber"];

    if (!$userID || !$firstName || !$lastName || !$phoneNumber) {
        echo "You have not entered all required details try again.";
        exit;
    }

    //format input
    $userID = addslashes($userID);
    $firstName = addslashes($firstName);
    $lastName = addslashes($lastName);
    $phoneNumber = doubleval($phoneNumber);

    //connect to the database
    @$db = new mysqli('localhost', 'root', '', 'usernamesandpasswords');


    if ($db->connect_error) {
        die('Connect Error ' . $db->connect_errno . ': ' . $db->connect_error);
    }


    $query = "insert into users values (?, ?, ?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param("dsss", $userID, $firstName, $lastName, $phoneNumber);
    $stmt->execute();
    echo $stmt->affected_rows." user inserted into database";

    $db->close();
?>
    <br/
    ><a href="Show_Users.php">Show Users</a>
</main>
</body>

</html>
