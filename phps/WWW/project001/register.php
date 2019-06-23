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

$suser = $_REQUEST["user"];
$spass = $_REQUEST["pass"];
$p = 0;

$stmt = $conn->prepare("select * from userinfo where username = ? AND password = ?");             
	$stmt->bind_param('ss', $suser, $spass);	
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($suser, $spass, $status);
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
$stmt = $conn->prepare("INSERT INTO userinfo(username, password, status) VALUES (?, ?, 0)");
$stmt->bind_param("ss", $suser, $spass);
$suser = $_REQUEST["user"];
$spass = $_REQUEST["pass"];
$stmt->execute();
$stmt->close();
$conn->close();
if($p == 0)
	echo 2;
}
?>
