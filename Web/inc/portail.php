<?php
include 'dbConf/db_connect.php';
include 'business/BPentreprise.php';
$db = new DB_CONNECT();

	
switch ($_GET['page'])
{
    case "gestion_entreprises":
		include("inc/portail/gestion_entreprises.php");break;
    case "gestion_webservices":
		include("inc/portail/gestion_webservices.php");break;
	default:
		include("inc/portail/gestion_entreprises.php");
}
?>