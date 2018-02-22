<?php
require_once('config.php');
$topic=$_GET['topic'];
$section=$_GET['section'];

$sql="SELECT * FROM question WHERE Topic='$topic' AND Section='$section' AND Approved=1";
$i=0;
if($result=mysqli_query($conn,$sql))
	{
	while($hey=mysqli_fetch_assoc($result))
		{	$submitter=$hey['Submitter'];
			$sql1="SELECT ImgLoc FROM demo WHERE Name='$submitter'";
			//echo $sql1;
			$result1=mysqli_query($conn,$sql1);
			$hey1=mysqli_fetch_assoc($result1);
		
			
		$array[$i]=array(
				"Submitter"=>$hey['Submitter'],
				"Time"=>$hey['Time'],
				"Image"=>$hey1['ImgLoc']
		);
		$i++;
		}
	
	}
	

$sql="SELECT * FROM reply WHERE Topic='$topic' AND Section='$section' AND Approved=1";

if($result=mysqli_query($conn,$sql))
	{
	while($hey=mysqli_fetch_assoc($result))
		{
		$array[$i++]=array(
					"Submitter"=>$hey['Submitter'],
					"Reply"=>$hey['Topic'],
					"Time"=>$hey['Time'],
					"Image"=>"Default Image"
					
		);
		}
	}	
$array[$i]=array("Length"=>$i);
$json=json_encode($array);
echo $json;

?>