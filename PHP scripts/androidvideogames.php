<?php
require_once('config.php');

$section=$_GET['section'];

$sql="SELECT * FROM question WHERE Section='$section' AND Approved=1";
$i=0;
//echo $sql;

if($result=mysqli_query($conn,$sql))

	{
	while($hey=mysqli_fetch_assoc($result))
	
		{
		$array[$i++]=array(
				"Topic"=>$hey['Topic'],
				"Closed"=>$hey['Closed']
		);
		}
	}


$array['Length']=$i;
$json=json_encode($array);
echo $json;
