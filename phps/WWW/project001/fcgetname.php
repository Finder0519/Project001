<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "project001";

// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

$stmt = $conn->prepare("select fcname from fcdata");

	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($fcname);
	$res = array();
	while ($stmt->fetch()){
		//$fileinfo['fcname'] = $fcname;
    		array_push($res, $fcname);
	}
echo json_encode($res);
$stmt->close();
$conn->close();
?>
