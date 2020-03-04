<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Credentials Entry Results</title>


</head>

<body>
    <main role="main" class="container-fluid">
	<h1> Credentials Entry </h1>
<?php
    $userID=$_POST["userID"];
    $websiteName=$_POST["websiteName"];
    $username=$_POST["username"];
    $password=$_POST["password"];
    $dateCreated=$_POST["dateCreated"];
    $currentLogin=$_POST["currentLogin"];
    $categoryID=$_POST["categoryID"];

    if (!$userID || !$websiteName || !$username || !$password || !$dateCreated || !$currentLogin || !$categoryID) {
        echo "You have not entered all required details try again.";
        exit;
    }

    //format input
    $userID = addslashes($userID);
    $websiteName = addslashes($websiteName);
    $username = addslashes($username);
    $password = addslashes($password);
    $dateCreated = addslashes($dateCreated);
    $currentLogin = addslashes($currentLogin);
    $categoryID = addslashes($categoryID);

    //connect to the database
    @$db = new mysqli('localhost', 'root', '', 'usernamesandpasswords');


    if ($db->connect_error) {
        die('Connect Error ' . $db->connect_errno . ': ' . $db->connect_error);
    }


    $query = "insert into credentials values (?, ?, ?, ?, ?, ?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param("dssssid", $userID, $websiteName, $username, $password, $dateCreated, $currentLogin, $categoryID);
    $stmt->execute();
    echo $stmt->affected_rows." Credentials inserted into database";

    $db->close();
?>
    <br/
    ><a href="Show_Credentials.php">Show Credentials</a>
</main>
</body>

</html>
