<?php
require_once('config.php');

$topic=$_GET['topic'];
$section=$_GET['section'];
$name=$_GET['name'];

$sql="SELECT Id from demo WHERE Name='$name'";
$result=mysqli_query($conn,$sql);

$hey=mysqli_fetch_assoc($result);

$id=$hey['Id'];
$time=date("Y-m-d h:i:sa");
$sql="INSERT INTO question(Id,Submitter,Topic,Section,Approved,Closed,Time) VALUES ($id,'$name','$topic','$section',0,0,'$time') ";

if(mysqli_query($conn,$sql)==TRUE)
	{	
	echo "Success";	
	}

?>