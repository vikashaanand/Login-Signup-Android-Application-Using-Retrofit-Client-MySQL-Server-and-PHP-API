<?php

	include "db_config.php";

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$recRoll = $_POST["ROLL"];
	$recPassword = $_POST['PASSWORD'];

	$sqlQuery = "UPDATE `students` SET `password`= '$recPassword' WHERE `roll` = '$recRoll'";

	if(mysqli_query($con, $sqlQuery)){

		$result["status"] = TRUE;
		$result["remarks"] = "password updated";

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "Some Error Occured";
	}

	mysqli_close($con);

	print(json_encode($result));

?>
