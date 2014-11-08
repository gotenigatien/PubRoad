<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();
if (isset($_GET["ID"]) && isset($_GET["Place"]) ) {
    $ID = strtolower($_GET['ID']);
	$place=strtolower($_GET['Place']);
$requete="SELECT id_pers as ID, CONCAT(nom_pers,' ',prenom_pers) as NomComplet, tel_pers as Tel, fax_pers as Fax, email_pers as Email , siteWeb as SiteWeb, Name as Name , Description as Description , Photo as Photo, Libelle_Entreprise as Entreprise,Adresse as Adresse,Ville as Ville, CP as CodePostal, PlageHoraire as Horaire FROM ann_personne ann where (id_pers=$ID)";

// include db connect class
require_once '../dbConf/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all products from products table
$result = mysql_query($requete) or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["Results"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["ID"] = $row["ID"];
        $product["NomComplet"] = $row["NomComplet"];
        $product["Tel"] = $row["Tel"];
        $product["Fax"] = $row["Fax"];
        $product["Email"] = $row["Email"];
        $product["Name"] = $row["Name"];
        $product["SiteWeb"] = $row["SiteWeb"];
		$product["Photo"] = $row["Photo"];
        $product["Description"] = $row["Description"];
        $product["Adresse"] = $row["Adresse"];
		$product["Adresse2"] =$row["Ville"].' '.$row["CodePostal"];
		
		// distance
		$from = $product["Adresse"] ;
		$Distance = getDistance($from,$place);
		$product["Distance"]=$Distance;
		$product["Horaire"] = $row["Horaire"];
		if (empty($row["Entreprise"])){
			$product["isParticulier"]=true;
		}else {
$product["isParticulier"]=false;
			$product["Entreprise"] = $row["Entreprise"];
		}
        // push single product into final response array
        array_push($response["Results"], $product);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "null";
    // echo no users JSON
    echo json_encode($response);
}
}
else {
$response["success"] = -1;
$response["message"] = "Error";

    // echo no users JSON
    echo json_encode($response);
}

function getDistance($From,$To){

$from = urlencode($From);
$to = urlencode($To);
$data = file_get_contents("http://maps.googleapis.com/maps/api/distancematrix/json?origins=$from&destinations=$to&language=fr-FR&units=metric&sensor=false");
$data = json_decode($data);

$time = 0;
$distance = 0;

foreach($data->rows[0]->elements as $road) {
    $time += $road->duration->value;
    $distance += $road->distance->value;
}
return $distance;
}

?>
