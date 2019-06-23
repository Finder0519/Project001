<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "project001";
 
// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
}

$sfcname=$_REQUEST["fcname"];
$p=0;

$stmt = $conn->prepare("select fcname, fcdescp, fcprice from fcdata where fcname = ?");
            
	//$stmt->bind_param("i", $rid);
	
	//$rid = $_REQUEST["rid"];
	$stmt->bind_param('s',$sfcname);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($fcname, $fcdescp, $fcprice);
	$res = array();
	while ($stmt->fetch()){
		$fileinfo['fcname'] = $fcname;
		$fileinfo['fcdescp'] = $fcdescp;
		$fileinfo['fcprice'] = $fcprice;
		$p=1;
    	//array_push($res, $fileinfo);
		echo json_encode($fileinfo);
	}
if($p==0){
	echo 0;
}

$stmt->close();
$conn->close();
?>