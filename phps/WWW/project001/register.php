<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "project001";
 
// ��������
$conn = new mysqli($servername, $username, $password, $dbname);
 
// �������
if ($conn->connect_error) {
    die("����ʧ��: " . $conn->connect_error);
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

// ��������
$conn = new mysqli($servername, $username, $password, $dbname);
 
// �������
if ($conn->connect_error) {
    die("����ʧ��: " . $conn->connect_error);
}
 
// Ԥ������
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
