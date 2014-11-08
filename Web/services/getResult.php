<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();
$response = array();
if (isset($_GET["Who"])&& isset($_GET["Where"]) && isset($_GET["Place"]) ) {
    $who = strtolower($_GET['Who']);
	$where=strtolower($_GET['Where']);
	$place=strtolower($_GET['Place']);
	
	$requete=	"SELECT id_pers as ID, CONCAT(nom_pers,' ',prenom_pers) as NomComplet,
				tel_pers as Tel, fax_pers as Fax, email_pers as Email , siteWeb as SiteWeb ,
				Description as Description , Photo as Photo, Libelle_Entreprise as Entreprise,
				Adresse as Adresse, Name as Name,Ville as Ville, CP as CodePostal FROM ann_personne ann 
				where (LOWER(nom_pers) like '%$who%' or LOWER(prenom_pers) like '%$who%' 
				or LOWER(tel_pers) like '%$who%'or LOWER(fax_pers) like '%$who%' 
				or LOWER(email_pers) like '%$who%' or LOWER(SiteWeb) like '%$who%' 
				or LOWER(Name)  like '%$who%') ";
	if (!empty($where)){
		$requete .="and (LOWER(Ville) like '%$where%' or LOWER(CP) like '%$where%')";
	}

// include db connect class
require_once   '../dbConf/db_connect.php';

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
        $product["Email"] = $row["Email"];
        $product["SiteWeb"] = $row["SiteWeb"];
        $product["Entreprise"] = $row["Entreprise"];
        $product["Name"] = $row["Name"];
        $product["Adresse"] = $row["Adresse"] .' ' .$row["Ville"] .' '.$row["CodePostal"];

		
		// distance
		$from = $product["Adresse"] .' '.$product["Ville"].' ' .$row["CodePostal"];
		$Distance = getDistance($from,$place);
		$product["Distance"]=$Distance;
		// nbr occurance
		$varWho=strtolower($product["NomComplet"].$product["Tel"].$product["Fax"].$product["Email"].$product["SiteWeb"].$product["Entreprise"]);
		$varWhere=strtolower($product["Adresse"].$product["Ville"].$product["CodePostal"]);
		$product["Occurance"]=getNbrOccurance($varWho,$who,$varWhere,$where);
		if (empty($product["Entreprise"])){
			$product["isParticulier"]=true;
		}else {
			$product["isParticulier"]=false;
		}
        // push single product into final response array
        array_push($response["Results"], $product);
		
    }
	$response["Results"]=trie_tableau($response["Results"],"Occurance");
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

function getNbrOccurance($Cwho,$Owho,$Cwhere,$Owhere){
	$result1=substr_count($Cwho, $Owho);
	if(!empty($Owhere)){
		$result2=substr_count($Cwhere, $Owhere);
	}
	return $result1+$result2;
}

function trie_tableau($array, $key)
        {
           for ($i = 0; $i < sizeof($array); $i++) {
                   $sort_values[$i] = $array[$i][$key];
           }
		   
           natsort($sort_values);

			$test=array_reverse($sort_values,true);
           while (list ($arr_key, $arr_val) = each ($test)) {
                         $sorted_arr[] = $array[$arr_key];
           }
           return $sorted_arr;
        }
?>
