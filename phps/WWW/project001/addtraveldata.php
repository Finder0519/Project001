<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "project001";
 
// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);
 
// 检测连接
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
}

$sname = $_REQUEST["name"];
$sdescp = $_REQUEST["descp"];
$sguide = $_REQUEST["guide"];
$sticket = $_REQUEST["ticket"];
$p = 0;

$stmt = $conn->prepare("select jdname, jddescp, jdguide, jdticket from jddata where jdname = ? AND jddescp = ? AND jdguide = ? AND jdticket = ?");             
	$stmt->bind_param('ssss', $sname, $sdescp, $sguide, $sticket);	
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($sname, $sdescp, $sguide, $sticket);
	while ($stmt->fetch()){
		$p = 1;
	}
if($p == 1){
	echo 0;
} else {
	
$stmt->close();
$conn->close();

// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);
 
// 检测连接
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
}
 
// 预处理及绑定
$stmt = $conn->prepare("INSERT INTO jddata(jdname, jddescp, jdguide, jdticket) VALUES (?, ?, ?, ?)");
$stmt->bind_param("ssss", $sname, $sdescp, $sguide, $sticket);
$sname = $_REQUEST["name"];
$sdescp = $_REQUEST["descp"];
$sguide = $_REQUEST["guide"];
$sticket = $_REQUEST["ticket"];
$stmt->execute();
$stmt->close();
$conn->close();
if($p == 0)
	echo 2;
}
?>