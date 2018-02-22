<?php
require_once('config.php');

$sql="SELECT * FROM section WHERE Approved=1";


if ($result = mysqli_query($conn, $sql)) {
	$i=0;
    while ($row = mysqli_fetch_assoc($result)) {
       $i++;
	   $array[$i]=array(
	   			'Section'=>$row['Section']
	   );

    }

}
//echo $array['0'];
$i++;
$array['Length']=$i-1;
$json=json_encode($array);
echo $json;


?>