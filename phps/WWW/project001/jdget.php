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

$sjdname=$_REQUEST["jdname"];
$p=0;

$stmt = $conn->prepare("select jdname, jddescp, jdguide, jdticket from jddata where jdname = ?");
            

	$stmt->bind_param('s',$sjdname);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($jdname, $jddescp, $jdguide, $jdticket);
	$res = array();
	while ($stmt->fetch()){
		$fileinfo['jdname'] = $jdname;
		$fileinfo['jddescp'] = $jddescp;
		$fileinfo['jdguide'] = $jdguide;
		$fileinfo['jdticket'] = $jdticket;
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