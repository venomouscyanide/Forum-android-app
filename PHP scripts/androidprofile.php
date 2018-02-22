<?php

require_once('config.php');
$username= $_GET['username'];
$pass=$_GET['password'];

$sql="SELECT * FROM demo WHERE Name='$username' AND Pass='$pass'";

$result=mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0)
	{$hey=mysqli_fetch_assoc($result);
	 $data=array(
	 		array(
					"Email"=>$hey['Email'],
					"Address"=>$hey['Adress'],
					"Age"=>$hey['Age'],
					"Picture"=>$hey['ImgLoc']
	      		)
	            );
					 
	 echo json_encode($data);	 
	}
else
	echo "Fail";
?>