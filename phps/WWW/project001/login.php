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
	$stmt->bind_param('ss',$suser, $spass);	
	$stmt->execute();             
	$stmt->store_result();
	$stmt->bind_result($user, $pass, $status);
	while ($stmt->fetch()){
		if(strcmp($pass, $spass) == 0)
		{
			$fileinfo['user'] = $user;
   	 		$fileinfo['pass'] = $pass;
			$p = 1;
			echo json_encode($fileinfo);
		}
	}

if($p == 0)
{
	echo 0;
}

$stmt->close();
$conn->close();
?>
