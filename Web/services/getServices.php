<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();
$response = array();
	
$requete=	"Select Id_webService as ID , Url_webService as LINK ,DateAdd_webService as DATEADD from ann_webservice order by Id_webService";

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
        $product["LINK"] = $row["LINK"];
        $product["DATEADD"] = $row["DATEADD"];
        
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

?>
