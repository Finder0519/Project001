<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "project001";

// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

$stmt = $conn->prepare("select jdname from jddata");

	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($jdname);
	$res = array();
	while ($stmt->fetch()){
		//$fileinfo['jdname'] = $jdname;
    		array_push($res, $jdname);
	}
echo json_encode($res);
$stmt->close();
$conn->close();
?>
