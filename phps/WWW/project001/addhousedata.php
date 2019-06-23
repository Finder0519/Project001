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
$sprice = $_REQUEST["price"];
$p = 0;

$stmt = $conn->prepare("select fcname, fcdescp, fcprice from fcdata where fcname = ? AND fcdescp = ? AND fcprice = ?");             
	$stmt->bind_param('sss', $sname, $sdescp, $sprice);	
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($sname, $sdescp, $sprice);
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
$stmt = $conn->prepare("INSERT INTO fcdata(fcname, fcdescp, fcprice) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $sname, $sdescp, $sprice);
$sname = $_REQUEST["name"];
$sdescp = $_REQUEST["descp"];
$sprice = $_REQUEST["price"];
$stmt->execute();
$stmt->close();
$conn->close();
if($p == 0)
	echo 2;
}
?>