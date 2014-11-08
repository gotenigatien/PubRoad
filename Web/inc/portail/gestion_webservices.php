<?php $response = array();
require_once 'dbConf/db_connect.php';
$db = new DB_CONNECT();


if($_POST['modif_webservice']=="ok"){
	
	for($j=1;$j<=2;$j++){
		mysql_query("UPDATE ann_webservice SET Url_webService = '".$_POST['webservice_'.$j]."' WHERE Id_webService = '".$j."'");
	}
}
?>

<div id="wrapper">
    <header id="header">
        <h1><a href="#">QQO / Administration</a></h1>
    </header>
    <?php include("inc/menu.php"); ?>
    <section id="gestion" class="webservices">
        <header class="clearfix">
            <h2>Gestion des web-services</h2>
        </header>
        <?php
		$result = mysql_query("SELECT * FROM ann_webservice") or die(mysql_error());
		$response = $result;
	if (mysql_num_rows($result) > 0)
	{
		echo '<form id="webservices" class="clearfix" action="/appa/index.php?page=gestion_webservices" method="post">';
		$i=1;
		while ($row = mysql_fetch_array($result))
		{
			echo '<input type="text" name="webservice_'.$i.'" class="text" value="'.$row['Url_webService'].'">
					<input type="hidden" name="'.$i.'" value="'.$row['Id_webService'].'">';
			$i++;
		}
		echo '
		<input type="hidden" name="modif_webservice" value="ok">
		<input type="submit" value="Enregistrer"></form>';
	}
		?>
    </section>
</div>