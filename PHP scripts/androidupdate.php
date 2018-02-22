<?php
require_once('config.php');
$json = file_get_contents('php://input');
$obj = json_decode($json);
$name= $obj->{'name'};
$pass= $obj->{'password'};
$age=  $obj->{'age'};

$sql="UPDATE demo SET Age=$age WHERE Name='$name' AND Pass='$pass'";
if(mysqli_query($conn,$sql)==TRUE)
	{
	echo "Success";
	}

else
	{
	echo "Fail";
	}
	
?>