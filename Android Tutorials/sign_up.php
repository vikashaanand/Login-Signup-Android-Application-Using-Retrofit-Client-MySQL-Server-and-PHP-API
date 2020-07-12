<?php

	include "db_config.php";

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$recRoll = $_POST["ROLL"];
	$recName = $_POST["NAME"];
	$recMobile = $_POST["MOBILE"];
	$recEMAIL = $_POST["EMAIL"];
	$recGender = $_POST["GENDER"];
	$recPassword = $_POST["PASSWORD"];

	$verifyRollNo = "SELECT * FROM `students` WHERE `roll` = '$recRoll'";

	$rollExists = mysqli_fetch_array(mysqli_query($con, $verifyRollNo));

	if(isset($rollExists)){

				$result["status"] = FALSE;
				$result["remarks"] = "Roll Number Already Present";

	}else{

			$insertQuery = "INSERT INTO `students`(`roll`, `name`, `mobile`, `email`, `gender`, `password`) 
					VALUES ('$recRoll', '$recName', '$recMobile', '$recEMAIL', '$recGender', '$recPassword')";

			if(mysqli_query($con, $insertQuery)){

				$result["status"] = TRUE;
				$result["remarks"] = "Successfullly Registered";

			}else{

				$result["status"] = FALSE;
				$result["remarks"] = "Registration Failed";
			}
	}

	mysqli_close($con);

	print(json_encode($result));



?>