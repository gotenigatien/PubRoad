<?php
$response = array();
require_once 'dbConf/db_connect.php';
$db = new DB_CONNECT();

//----------------------------------------------------
//       getEntreprise();
//----------------------------------------------------

function getEntreprise($id)
{
	$result = mysql_query("
	SELECT 
		ann.id_pers            AS ID,  
		ann.tel_pers           AS Tel, 
		ann.fax_pers           AS Fax, 
		ann.email_pers         AS Email, 
		ann.Name         AS Name, 
		ann.siteWeb            AS SiteWeb, 
		ann.Description        AS Description, 
		ann.Photo              AS Photo,
		ann.Libelle_Entreprise AS Entreprise,
		ann.Adresse            AS Adresse, 
		ann.Ville              AS Ville,
		ann.CP                 AS CodePostal,
		ann.PlageHoraire       AS Horaires

	FROM ann_personne ann
	
	WHERE ann.id_pers =$id") or die(mysql_error());
	
	if (mysql_num_rows($result) > 0)
	{
		$response = $result;
		return $response;
	}
	else { return NULL; }
}

//----------------------------------------------------
//       getListOfEntreprise();
//----------------------------------------------------

function getListOfEntreprise()
{
	if(isset($_GET['nb_page'])){
		$page = $_GET['nb_page'];
		$nbr_par_page = 10;
		$limit1 = $page * $nbr_par_page - $nbr_par_page; 
		$limit2 = $page * $nbr_par_page;
	}	
	else {$limit1=0; $limit2=10;}

	$result = mysql_query("
	SELECT 
		ann.id_pers            AS ID, 
		ann.tel_pers           AS Tel, 
		ann.fax_pers           AS Fax, 
		ann.email_pers         AS Email, 
		ann.Name         AS Name, 
		ann.siteWeb            AS SiteWeb, 
		ann.Description        AS Description, 
		ann.Photo              AS Photo, 
		ann.Libelle_Entreprise AS Entreprise,
		ann.Adresse            AS Adresse, 
		ann.Ville              AS Ville, 
		ann.CP                 AS CodePostal,
		ann.PlageHoraire       AS Horaires

	FROM ann_personne ann
	WHERE ann.nom_pers like '' AND ann.prenom_pers like ''
	ORDER BY ann.id_pers DESC
	LIMIT ".$limit1.", ".$limit2) or die(mysql_error());
	if (mysql_num_rows($result) > 0) 
	{
		$response = $result;
		return $response;
	}
	else { return NULL; }
}

//----------------------------------------------------
//       getListOfEntrepriseByParam($who String, $where String);
//----------------------------------------------------

function getListOfEntrepriseByParam($who, $where)
{
	$result = mysql_query("
	SELECT 
		ann.id_pers            AS ID, 
		ann.tel_pers           AS Tel, 
		ann.fax_pers           AS Fax, 
		ann.email_pers         AS Email, 
		ann.Name         AS Name, 
		ann.siteWeb            AS SiteWeb, 
		ann.Description        AS Description, 
		ann.Photo              AS Photo, 
		ann.Libelle_Entreprise AS Entreprise,
		ann.Adresse            AS Adresse, 
		ann.Ville              AS Ville, 
		ann.CP                 AS CodePostal,
		ann.PlageHoraire       AS Horaires

	FROM ann_personne ann
	
	WHERE( LOWER(ann.tel_pers)           like '%$who%'
		OR LOWER(ann.fax_pers)           like '%$who%' 
		OR LOWER(ann.email_pers)         like '%$who%' 
		OR LOWER(ann.SiteWeb)            like '%$who%'
		OR LOWER(ann.Name)            like '%$who%' 
		OR LOWER(ann.Description)            like '%$who%'
		OR LOWER(ann.Libelle_Entreprise) like '%$who%')
	AND ( LOWER(ann.Adresse)             like '%$where%'
		OR LOWER(ann.Ville)              like '%$where%' 
		OR LOWER(ann.CP)                 like '%$where%')
		AND ann.nom_pers like '' AND ann.prenom_pers like ''") or die(mysql_error());
 	if (mysql_num_rows($result) > 0) 
	{
		$response = $result;
		return $response;
	}
	else { return NULL; } 
}

//----------------------------------------------------
//       updateEntreprise($id, $nom, $prenom, $tel, $fax, $email, $siteWeb, $description, $photo, $libelleEntreprise, $adresse, $ville, $CP, $plageHoraire);
//----------------------------------------------------

function updateEntreprise($id, $tel, $fax, $email, $siteWeb, $description, $Name, $photo, $libelleEntreprise, $adresse, $ville, $CP, $plageHoraire)
{
	return mysql_query("
	UPDATE ann_personne 
		SET nom_pers ='',
			prenom_pers='',
			tel_pers           = '$tel',
			fax_pers           = '$fax',
			email_pers         = '$email',
			SiteWeb            = '$siteWeb',
			Name            = '$Name',
			Description        = '$description',
			Photo              = '$photo',
			Libelle_Entreprise = '$libelleEntreprise',
			Adresse            = '$adresse',
			Ville              = '$ville',
			CP                 = '$CP',
			PlageHoraire       = '$plageHoraire'
			
		WHERE ann_personne.id_pers ='$id'") or die(mysql_error());
}

//----------------------------------------------------
//       insertEntreprise($tel, $fax, $email, $siteWeb, $description, $photo, $libelleEntreprise, $adresse, $ville, $CP, $plageHoraire);
//----------------------------------------------------

function insertEntreprise($libelle_entreprise, $tel, $fax, $email, $Name, $siteWeb, $description, $photo, $adresse, $ville, $CP,$PlageHoraire)
{
	return mysql_query("
	INSERT INTO ann_personne (id_pers, nom_pers, prenom_pers, tel_pers, fax_pers, email_pers, Name, SiteWeb, Description, Photo, Libelle_Entreprise, Adresse, Ville, CP, PlageHoraire, date_enregistrement_pers)
	VALUES (NULL, '', '', '$tel', '$fax', '$email', '$Name', '$siteWeb', '$description', '$photo', '$libelle_entreprise', '$adresse', '$ville', '$CP', '$PlageHoraire', CURRENT_TIMESTAMP)") or die(mysql_error());
}

//----------------------------------------------------
//       deleteEntreprise($id);
//----------------------------------------------------

function deleteEntreprise($id)
{
	return mysql_query("DELETE FROM ann_personne WHERE ann_personne.id_pers=$id") or die(mysql_error());
}