<?php

	include "db_config.php";

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$recRoll = $_POST["ROLL"];

	$sqlQuery = "SELECT * FROM `students` WHERE `roll` = '$recRoll'";

	$studentDetails = mysqli_fetch_assoc(mysqli_query($con, $sqlQuery));

	mysqli_close($con);

	print(json_encode($studentDetails));

?>
