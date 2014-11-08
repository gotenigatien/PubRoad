<?php
require_once 'entreprise.php';

//// Test getEntreprise();
	$entreprise=getEntreprise(5);
	while ($row = mysql_fetch_array($entreprise)) {
		echo '<b>Test getEntreprise(1) : </b>'.$row["Entreprise"].'</br>';
	}
/// Test getListOfEntreprise()		
	$entreprise=getListOfEntreprise();
	echo '<b>Test getListOfEntreprise() :</b></br>';
	while ($row = mysql_fetch_array($entreprise)) {
		echo $row["NomComplet"].'</br>';
	}
/// Test getListOfEntrepriseByParam()
	$entreprises=getListOfEntrepriseByParam('ah','92620');
	echo '<b>Test getListOfEntrepriseByParam() :</b></br>';
		while ($row = mysql_fetch_array($entreprise)) {
		echo $row["NomComplet"].'</br>';
	}
// Test UpdateEntreprise
$id=1;
$libelle_entreprise='ah';
$tel='0646447088';
$fax='0646447088';
$email='m.ahsina@gmail.com';
$siteWeb='www.google.com';
$description='ahsina wassim sisim';
$photo='';
$adresse='23 avenue chevreul';
$ville='asnieres sur seine';
$CP='92620';
$PlageHoraire='test';

echo updateEntreprise($id, $libelle_entreprise, $tel, $fax, $email, $siteWeb, $description, $photo, $adresse, $ville, $CP,$PlageHoraire);

// Test insertEntreprise
$libelle_entreprise='ah2';
$tel='00000000';
$fax='00000000';
$email='marine@gmail.com';
$siteWeb='www.salutmarine.com';
$description='marine marine';
$photo='';
$adresse='40 avenue chevreul';
$ville='asnieres sur seine';
$CP='92630';
$PlageHoraire='zagini';
echo insertEntreprise($libelle_entreprise, $tel, $fax, $email, $siteWeb, $description, $photo, $adresse, $ville, $CP,$PlageHoraire);

// Test deleteEntreprise()

echo deleteEntreprise(6);
?>