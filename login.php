<?php

	include "db_config.php";

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$recRoll = $_POST["ROLL"];
	$recPassword = $_POST["PASSWORD"];

	$verifyStudent = "SELECT * FROM `students` WHERE `roll` = '$recRoll' AND `password` = '$recPassword'";

	$studentDetails = mysqli_fetch_assoc(mysqli_query($con, $verifyStudent));

	mysqli_close($con);

	print(json_encode($studentDetails));

?>